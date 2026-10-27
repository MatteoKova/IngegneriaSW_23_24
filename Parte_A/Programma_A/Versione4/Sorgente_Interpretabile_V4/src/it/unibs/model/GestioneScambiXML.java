package it.unibs.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GestioneScambiXML {
	
	private static final String ELENCO_SCAMBI = "elencoScambi";
	private static final String SCAMBIO = "scambio";
	private static final String FOGLIA_RICHIESTA = "fogliaRichiesta";
	private static final String FOGLIA_OFFERTA = "fogliaOfferta";
	private static final String RADICE = "radice";
	private static final String ORE_RICHIESTA = "oreRichiesta";
	private static final String ORE_OFFERTA = "oreOfferta";
	private static final String STATO = "stato";
	private static final String FRUITORE = "fruitore";
	
	//PARTE SALVATAGGIO ======================================================================================================

	public static void salvaElencoScambiSuXML(String filePath) {
		Document doc = GestioneGeneraleXML.creaFileXML();

		Element elementoElencoScambi = doc.createElement(ELENCO_SCAMBI);
		doc.appendChild(elementoElencoScambi);

		for(Scambio scambio : ElencoScambi.getElencoScambi()) {
			salvaScambioSuXML(doc, scambio, elementoElencoScambi);
		}
		GestioneGeneraleXML.salvaFileXML(doc, filePath);
	}
	
	public static void salvaScambioSuXML(Document doc, Scambio scambio, Element elementoPadre) {
		Element elementoScambio = doc.createElement(SCAMBIO);
		
		Element elementoFogliaRichiesta = doc.createElement(FOGLIA_RICHIESTA);
		GestioneGerarchieXML.salvaCategoriaFoglia(scambio.getFogliaRichiesta(), doc, elementoFogliaRichiesta);
		elementoScambio.appendChild(elementoFogliaRichiesta);
		
		elementoScambio.appendChild(GestioneGeneraleXML.creaElemento(doc, ORE_RICHIESTA, String.valueOf(scambio.getOreRichiesta())));
		
		Element elementoFogliaOfferta = doc.createElement(FOGLIA_OFFERTA);
		GestioneGerarchieXML.salvaCategoriaFoglia(scambio.getFogliaOfferta(), doc, elementoFogliaOfferta);
		elementoScambio.appendChild(elementoFogliaOfferta);
		
		elementoScambio.appendChild(GestioneGeneraleXML.creaElemento(doc, ORE_OFFERTA, String.valueOf(scambio.getOreOfferta())));
		
		elementoScambio.appendChild(GestioneGeneraleXML.creaElemento(doc, STATO, String.valueOf(scambio.getStato())));
		
		Element elementoUtente = doc.createElement(FRUITORE);
		GestioneUtentiXML.salvaInfoExtraSuXML(elementoUtente, doc, scambio.getUtente(), elementoScambio);
		
		elementoPadre.appendChild(elementoScambio);
	}
	
	//PARTE CARICAMENTO ======================================================================================================

	public static void caricaElencoScambiDaXML(String filePath) {
		Document doc = GestioneGeneraleXML.caricaFileXML(filePath);
		doc.getDocumentElement().normalize();

		NodeList listaScambi = doc.getElementsByTagName(SCAMBIO);

		for(int i = 0; i < listaScambi.getLength(); i++) {
			Node nodoScambio = listaScambi.item(i);

			if(nodoScambio.getNodeType() == Node.ELEMENT_NODE) {
				Scambio scambio = caricaScambioSuXML(nodoScambio);
				
				ElencoScambi.getElencoScambi().add(scambio);
			}
		}
	}

	public static Scambio caricaScambioSuXML(Node nodoScambio) {
		Element elementoScambio = (Element)nodoScambio;

		Integer oreRichiesta = Integer.parseInt(elementoScambio.getElementsByTagName(ORE_RICHIESTA).item(0).getTextContent());
		
		Integer oreOfferta = Integer.parseInt(elementoScambio.getElementsByTagName(ORE_OFFERTA).item(0).getTextContent());
		
		CategoriaFoglia fogliaRichiesta = null;
		CategoriaFoglia fogliaOfferta = null;
		
		Node nodoFogliaRichiesta = elementoScambio.getElementsByTagName(FOGLIA_RICHIESTA).item(0);
		if(nodoFogliaRichiesta.getNodeType() == Node.ELEMENT_NODE) {
			Element elementoFogliaRichiesta = (Element) nodoFogliaRichiesta;
			
			String nomeRadiceFogliaRichiesta = elementoFogliaRichiesta.getElementsByTagName(RADICE).item(0).getTextContent();

			CategoriaRadice radiceFogliaRichiesta = ElencoGerarchie.trovaRadice(nomeRadiceFogliaRichiesta);

			fogliaRichiesta = GestioneGerarchieXML.caricaCategoriaFoglia(elementoFogliaRichiesta, radiceFogliaRichiesta);
		}

		Node nodoFogliaOfferta = elementoScambio.getElementsByTagName(FOGLIA_OFFERTA).item(0);
		if(nodoFogliaOfferta.getNodeType() == Node.ELEMENT_NODE) {
			Element elementoFogliaOfferta = (Element) nodoFogliaOfferta;
			
			String nomeRadiceFogliaOfferta = elementoFogliaOfferta.getElementsByTagName(RADICE).item(0).getTextContent();

			CategoriaRadice radiceFogliaOfferta = ElencoGerarchie.trovaRadice(nomeRadiceFogliaOfferta);

			fogliaOfferta = GestioneGerarchieXML.caricaCategoriaFoglia(elementoFogliaOfferta, radiceFogliaOfferta);
		}
		
		String stato = elementoScambio.getElementsByTagName(STATO).item(0).getTextContent();
		
		Utente utente = GestioneUtentiXML.caricaInfoExtraUtenteSuXML(elementoScambio);
		
		return new Scambio(fogliaRichiesta, fogliaOfferta, oreRichiesta, oreOfferta, stato, utente);
	}
}