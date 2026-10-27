package it.unibs.model;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GestioneGerarchieXML {

	private static final String ELENCO_GERARCHIE = "elencoGerarchie";
	private static final String FOGLIA = "Foglia";
	private static final String GERARCHIA = "gerarchia";
	private static final String RADICE = "radice";
	private static final String CATEGORIA = "categoria";
	private static final String NOME = "nome";
	private static final String CAMPO = "campo";
	private static final String DOMINIO = "dominio";
	private static final String VALORE = "valore";
	private static final String DESCRIZIONE = "descrizione";
	private static final String VALORE_DOMINIO = "valoreDominio";
	
	//PARTE SALVATAGGIO ======================================================================================================

	public static void salvaElencoGerarchieSuXML(String filePath) {
		Document doc = GestioneGeneraleXML.creaFileXML();

		Element elementoElencoGerarchie = doc.createElement(ELENCO_GERARCHIE);
		doc.appendChild(elementoElencoGerarchie);

		for(Gerarchia gerarchia : ElencoGerarchie.getElencoGerarchie()) {

			Element elementoGerarchia = doc.createElement(GERARCHIA);
			
			Element elementoRadice = salvaCategoriaRadice(gerarchia.getRadice(), doc);
			salvaFigliCategoria(gerarchia.getRadice(), doc, elementoRadice);

			elementoGerarchia.appendChild(elementoRadice);
			elementoElencoGerarchie.appendChild(elementoGerarchia);
		}

		GestioneGeneraleXML.salvaFileXML(doc, filePath);
	}

	public static void salvaFigliCategoria(Categoria categoria, Document doc, Element elementoGerarchia) {
		for(Categoria cat : categoria.getFigli()) {

			Element nuovaCategoria = doc.createElement(CATEGORIA);
			
			if(cat.getTipo().equals(FOGLIA)) {

				salvaCategoriaFoglia(cat, doc, nuovaCategoria);
			}
			else {
				salvaCategoriaNonFoglia(cat, doc, nuovaCategoria);
			}
			elementoGerarchia.appendChild(nuovaCategoria);

			salvaFigliCategoria(cat, doc, nuovaCategoria);
		}
	}

	public static Element salvaCategoriaRadice(Categoria categoria, Document doc) {
		Element elementoRadice = doc.createElement(RADICE);
		
		elementoRadice.appendChild(GestioneGeneraleXML.creaElemento(doc, NOME, categoria.getNome()));
		
		elementoRadice.appendChild(GestioneGeneraleXML.creaElemento(doc, CAMPO, categoria.getCampo()));
		
		Element elementoDominio = doc.createElement(DOMINIO);
		
		for(ValoreDominio valore : categoria.getDominio()) {
			salvaValoreDominio(valore, doc, elementoDominio);
		}

		elementoRadice.appendChild(elementoDominio);

		return elementoRadice;
	}

	public static void salvaCategoriaFoglia(Categoria categoria, Document doc, Element elementoGerarchia) {
		elementoGerarchia.appendChild(GestioneGeneraleXML.creaElemento(doc, NOME, categoria.getNome()));
		
		elementoGerarchia.appendChild(GestioneGeneraleXML.creaElemento(doc, RADICE, categoria.getRadice().getNome()));
		
		salvaValoreDominio(categoria.getValoreDominio(), doc, elementoGerarchia);
	}

	public static void salvaCategoriaNonFoglia(Categoria categoria, Document doc, Element elementoGerarchia) {
		elementoGerarchia.appendChild(GestioneGeneraleXML.creaElemento(doc, NOME, categoria.getNome()));
		
		elementoGerarchia.appendChild(GestioneGeneraleXML.creaElemento(doc, CAMPO, categoria.getCampo()));
		
		Element elementoDominio = doc.createElement(DOMINIO);
		
		salvaValoreDominio(categoria.getValoreDominio(), doc, elementoGerarchia);
		
		for(ValoreDominio valore : categoria.getDominio()) {
			salvaValoreDominio(valore, doc, elementoDominio);
		}

		elementoGerarchia.appendChild(elementoDominio);
		
		elementoGerarchia.appendChild(GestioneGeneraleXML.creaElemento(doc, RADICE, categoria.getRadice().getNome()));
		
	}

	public static void salvaValoreDominio(ValoreDominio valoreDominio, Document doc, Element elementoGerarchia) {
		Element elementoValoreDominio = doc.createElement(VALORE_DOMINIO);
		
		elementoValoreDominio.appendChild(GestioneGeneraleXML.creaElemento(doc, VALORE, valoreDominio.getValore()));
		
		elementoValoreDominio.appendChild(GestioneGeneraleXML.creaElemento(doc, DESCRIZIONE, valoreDominio.getDescrizione()));

		elementoGerarchia.appendChild(elementoValoreDominio);
	}

	//PARTE CARICAMENTO ======================================================================================================

	public static void caricaElencoGerarchieDaXML(String filePath) {
		Document doc = GestioneGeneraleXML.caricaFileXML(filePath);
		doc.getDocumentElement().normalize();

		NodeList listaGerarchie = doc.getElementsByTagName(GERARCHIA);

		for(int i = 0; i < listaGerarchie.getLength(); i++) {
			Node nodoGerarchia = listaGerarchie.item(i);
			if(nodoGerarchia.getNodeType() == Node.ELEMENT_NODE) {
				Element elementoGerarchia = (Element) nodoGerarchia;

				Node nodoRadice = elementoGerarchia.getElementsByTagName(RADICE).item(0);

				if(nodoRadice.getNodeType() == Node.ELEMENT_NODE) {
					Element elementoRadice = (Element)nodoRadice;

					String nomeRadice = elementoRadice.getElementsByTagName(NOME).item(0).getTextContent();

					String campoRadice = elementoRadice.getElementsByTagName(CAMPO).item(0).getTextContent();

					ArrayList<ValoreDominio> dominioRadice = caricaValoriDominio(elementoRadice.getElementsByTagName(DOMINIO).item(0));

					CategoriaRadice radice = new CategoriaRadice(nomeRadice, campoRadice, dominioRadice);
					ArrayList<Categoria> figliRadice = new ArrayList<>();

					caricaFigliCategoria(elementoRadice, radice, figliRadice);

					Gerarchia gerarchia = new Gerarchia(nomeRadice, campoRadice, dominioRadice, figliRadice);
					ElencoGerarchie.getElencoGerarchie().add(gerarchia);
				}
			}
		}
	}

	public static void caricaFigliCategoria(Element elementoPadre, CategoriaRadice radice, ArrayList<Categoria> figli) {
		NodeList listaFigliPadre = elementoPadre.getChildNodes();

		for(int j = 0; j < listaFigliPadre.getLength(); j++) {
			Node nodoFiglio = listaFigliPadre.item(j);
			if(nodoFiglio.getNodeType() == Node.ELEMENT_NODE) {
				Element elementoFiglio = (Element)nodoFiglio;

				
				if(elementoFiglio.getNodeName().equals(CATEGORIA)) {
				
				boolean isFoglia = true;
				NodeList listaFigliCategoria = elementoFiglio.getChildNodes();
				
					for(int k = 0; k < listaFigliCategoria.getLength(); k++) {
						if(listaFigliCategoria.item(k).getNodeName().equals(CAMPO)) {
							isFoglia = false;
						}
					}

					if(isFoglia) {
						figli.add(caricaCategoriaFoglia(elementoFiglio, radice));
					}
					else {
						ArrayList<Categoria> figliNonFoglia = new ArrayList<Categoria>();
						caricaFigliCategoria(elementoFiglio, radice, figliNonFoglia);
						figli.add(caricaCategoriaNonFoglia(elementoFiglio, radice, figliNonFoglia));
					}
				}
			}
		}
	}

	public static CategoriaFoglia caricaCategoriaFoglia(Element elemento, CategoriaRadice radice) {
		String nome = elemento.getElementsByTagName(NOME).item(0).getTextContent();

		ValoreDominio valoreDominio = caricaValoreDominio(elemento.getElementsByTagName(VALORE_DOMINIO).item(0));
		
		return new CategoriaFoglia(nome, valoreDominio, radice);
	}

	public static CategoriaNonFoglia caricaCategoriaNonFoglia(Element elemento, CategoriaRadice radice, ArrayList<Categoria> figli) {
		String nome = elemento.getElementsByTagName(NOME).item(0).getTextContent();
		
		String campo = elemento.getElementsByTagName(CAMPO).item(0).getTextContent();
		
		ValoreDominio valoreDominio = caricaValoreDominio(elemento.getElementsByTagName(VALORE_DOMINIO).item(0));
		
		ArrayList<ValoreDominio> dominio = caricaValoriDominio(elemento.getElementsByTagName(DOMINIO).item(0));
		
		return new CategoriaNonFoglia(nome, valoreDominio, radice, campo, dominio, figli);
	}

	public static ValoreDominio caricaValoreDominio(Node node) {
		if(node.getNodeType() == Node.ELEMENT_NODE) {
			Element elementoValoreDominio = (Element)node;
			
			String valore = elementoValoreDominio.getElementsByTagName(VALORE).item(0).getTextContent();
			
			String descrizione = elementoValoreDominio.getElementsByTagName(DESCRIZIONE).item(0).getTextContent();
			
			return new ValoreDominio(valore, descrizione);
		}
		return null;
		
	}

	public static ArrayList<ValoreDominio> caricaValoriDominio(Node node) {
		NodeList listaValori = node.getChildNodes();
		ArrayList<ValoreDominio> dominioRadice = new ArrayList<>();

		for(int i = 0; i < listaValori.getLength(); i++) {
			if(listaValori.item(i).getNodeName().equals(VALORE_DOMINIO)) {
				dominioRadice.add(caricaValoreDominio(listaValori.item(i)));
			}
		}
		return dominioRadice;
	}
}