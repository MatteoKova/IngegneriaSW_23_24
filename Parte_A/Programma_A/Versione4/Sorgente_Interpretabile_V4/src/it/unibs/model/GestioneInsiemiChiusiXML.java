package it.unibs.model;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GestioneInsiemiChiusiXML {

	private static final String ELENCO_INSIEMI_CHIUSI = "elencoInsiemiChiusi";
	private static final String ANELLO_DI_SCAMBI = "anelloDiScambi";
	private static final String SCAMBIO = "scambio";

	//PARTE SALVATAGGIO ======================================================================================================

	public static void salvaElencoInsiemiChiusiSuXML(String filePath) {
		Document doc = GestioneGeneraleXML.creaFileXML();

		Element elementoInsiemiChiusi = doc.createElement(ELENCO_INSIEMI_CHIUSI);
		doc.appendChild(elementoInsiemiChiusi);

		for(ArrayList<Scambio> anelliDiScambi : ElencoInsiemiChiusi.getElencoInsiemiChiusi()) {
			salvaAnelloDiScambiSuXML(doc, anelliDiScambi, elementoInsiemiChiusi);
		}
		GestioneGeneraleXML.salvaFileXML(doc, filePath);
	}
	
	public static void salvaAnelloDiScambiSuXML(Document doc, ArrayList<Scambio> anelloDiScambi, Element elementoPadre) {
		Element elementoAnelloDiScambi = doc.createElement(ANELLO_DI_SCAMBI);
		
		for(Scambio scambio : anelloDiScambi) {
			GestioneScambiXML.salvaScambioSuXML(doc, scambio, elementoAnelloDiScambi);
		}
		elementoPadre.appendChild(elementoAnelloDiScambi);
	}
	
	//PARTE CARICAMENTO ======================================================================================================

	public static void caricaElencoInsiemiChiusiDaXML(String filePath) {
		Document doc = GestioneGeneraleXML.caricaFileXML(filePath);
		doc.getDocumentElement().normalize();

		NodeList listaAnelli = doc.getElementsByTagName(ANELLO_DI_SCAMBI);

		for(int i = 0; i < listaAnelli.getLength(); i++) {
			Node nodoAnello = listaAnelli.item(i);

			if(nodoAnello.getNodeType() == Node.ELEMENT_NODE) {
				ArrayList<Scambio> anelloDiScambi = caricaAnelloDiScambiSuXML(nodoAnello);
				
				ElencoInsiemiChiusi.getElencoInsiemiChiusi().add(anelloDiScambi);
			}
		}
	}

	public static ArrayList<Scambio> caricaAnelloDiScambiSuXML(Node nodoAnelloDiScambi) {
		Element elementoAnelloDiScambi = (Element)nodoAnelloDiScambi;
		
		ArrayList<Scambio> anelloDiScambi = new ArrayList<>();
		
		NodeList listaScambi = elementoAnelloDiScambi.getElementsByTagName(SCAMBIO);
		
		for(int i = 0; i < listaScambi.getLength(); i++) {
			Node nodoScambio = listaScambi.item(i);
			
			Scambio scambio = GestioneScambiXML.caricaScambioSuXML(nodoScambio);
			
			anelloDiScambi.add(scambio);
		}
		
		return anelloDiScambi;
	}
}