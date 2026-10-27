package it.unibs.model;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GestioneComprensoriXML {

	private static final String ELENCO_COMPRENSORI = "elencoComprensori";
	private static final String COMPRENSORIO = "comprensorio";
	private static final String NOME = "nomeC";
	private static final String COMUNE = "comune";
	
	//PARTE SALVATAGGIO ======================================================================================================

	public static void salvaElencoComprensoriSuXML(String filePath) {
		Document doc = GestioneGeneraleXML.creaFileXML();

		Element elementoElencoComprensori = doc.createElement(ELENCO_COMPRENSORI);
		doc.appendChild(elementoElencoComprensori);

		for(Comprensorio comprensorio : ElencoComprensori.getElencoComprensori()) {
			salvaComprensorioSuXML(doc, comprensorio, elementoElencoComprensori);
		}
		GestioneGeneraleXML.salvaFileXML(doc, filePath);
	}

	public static void salvaComprensorioSuXML(Document doc, Comprensorio comprensorio, Element elementoPadre) {
		Element elementoComprensorio = doc.createElement(COMPRENSORIO);
		
		elementoComprensorio.appendChild(GestioneGeneraleXML.creaElemento(doc, NOME, comprensorio.getNome()));
		
		for(String comune : comprensorio.getComuniComprensorio()) {
			elementoComprensorio.appendChild(GestioneGeneraleXML.creaElemento(doc, COMUNE, comune));
		}
		
		elementoPadre.appendChild(elementoComprensorio);
	}

	//PARTE CARICAMENTO ======================================================================================================

	public static void caricaElencoComprensoriDaXML(String filePath) {
		Document doc = GestioneGeneraleXML.caricaFileXML(filePath);
		doc.getDocumentElement().normalize();

		NodeList listaComprensori = doc.getElementsByTagName(COMPRENSORIO);

		for(int i = 0; i < listaComprensori.getLength(); i++) {
			Node nodoComprensorio = listaComprensori.item(i);

			if(nodoComprensorio.getNodeType() == Node.ELEMENT_NODE) {
				Element elementoComprensorio = (Element)nodoComprensorio;
				
				Comprensorio comprensorio = caricaComprensorioSuXML(elementoComprensorio);

				ElencoComprensori.getElencoComprensori().add(comprensorio);
			}
		}
	}

	public static Comprensorio caricaComprensorioSuXML(Element elementoComprensorio) {
		String nome = elementoComprensorio.getElementsByTagName(NOME).item(0).getTextContent();

		NodeList listaComuni = elementoComprensorio.getElementsByTagName(COMUNE);
		ArrayList<String> comuniComprensorio = new ArrayList<>();

		for(int j = 0; j < listaComuni.getLength(); j++) {
			Node nodoComune = listaComuni.item(j);
			if(nodoComune.getNodeType() == Node.ELEMENT_NODE) {
				comuniComprensorio.add(nodoComune.getTextContent());
			}
		}
		return new Comprensorio(nome, comuniComprensorio);
	}
}