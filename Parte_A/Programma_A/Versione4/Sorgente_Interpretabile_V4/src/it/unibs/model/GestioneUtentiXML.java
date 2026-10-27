package it.unibs.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GestioneUtentiXML {
	
	private static final String ELENCO_UTENTI = "elencoUtenti";
	private static final String CONFIGURATORE = "Configuratore";
	private static final String UTENTE = "utente";
	private static final String NOME = "nomeU";
	private static final String PASSWORD = "password";
	private static final String RUOLO = "ruolo";
	private static final String COMPRENSORIO = "comprensorio";
	private static final String INDIRIZZO = "indirizzo";

	//PARTE SALVATAGGIO ======================================================================================================

	public static void salvaElencoUtentiSuXML(String filePath) {
		Document doc = GestioneGeneraleXML.creaFileXML();
		
		Element elementoElencoUtenti = doc.createElement(ELENCO_UTENTI);

		doc.appendChild(elementoElencoUtenti);

		for(Utente utente : ElencoUtenti.getElencoUtenti()) {
			Element elementoUtente = doc.createElement(UTENTE);
			
			if(utente.getRuolo().equals(CONFIGURATORE)) {
				salvaInfoBaseSuXML(elementoUtente, doc, utente, elementoElencoUtenti);
			}
			else {
				salvaInfoExtraSuXML(elementoUtente, doc, utente, elementoElencoUtenti);
			}
		}
		GestioneGeneraleXML.salvaFileXML(doc, filePath);
	}
	
	public static void salvaInfoBaseSuXML(Element elementoUtente, Document doc, Utente utente, Element elementoElencoUtenti) {
		elementoUtente.appendChild(GestioneGeneraleXML.creaElemento(doc, NOME, utente.getNome()));
		
		elementoUtente.appendChild(GestioneGeneraleXML.creaElemento(doc, PASSWORD, utente.getPassword()));
		
		elementoUtente.appendChild(GestioneGeneraleXML.creaElemento(doc, RUOLO, utente.getRuolo()));
		
		elementoElencoUtenti.appendChild(elementoUtente);
	}
	
	public static void salvaInfoExtraSuXML(Element elementoUtente, Document doc, Utente utente, Element elementoElencoUtenti) {	
		salvaInfoBaseSuXML(elementoUtente, doc, utente, elementoElencoUtenti);
		
		GestioneComprensoriXML.salvaComprensorioSuXML(doc, utente.getComprensorio(), elementoUtente);
		
		elementoUtente.appendChild(GestioneGeneraleXML.creaElemento(doc, INDIRIZZO, utente.getIndirizzo()));
		
		elementoElencoUtenti.appendChild(elementoUtente);
	}
	
	//PARTE CARICAMENTO ======================================================================================================

	public static void caricaElencoUtentiDaXML(String filePath) {
		Document doc = GestioneGeneraleXML.caricaFileXML(filePath);
		doc.getDocumentElement().normalize();

		NodeList listaUtenti = doc.getElementsByTagName(UTENTE);

		for(int i = 0; i < listaUtenti.getLength(); i++) {
			Node nodoUtente = listaUtenti.item(i);

			if(nodoUtente.getNodeType() == Node.ELEMENT_NODE) {
				Element elementoUtente = (Element) nodoUtente;
				
				Utente utente;
				
				utente = caricaInfoBaseUtenteSuXML(elementoUtente);
				
				if(elementoUtente.getElementsByTagName(COMPRENSORIO).item(0) != null) {
					utente = caricaInfoExtraUtenteSuXML(elementoUtente);
				}
				ElencoUtenti.getElencoUtenti().add(utente);
			}
		}
	}
	
	public static Utente caricaInfoBaseUtenteSuXML(Element elementoUtente) {
		String nome = elementoUtente.getElementsByTagName(NOME).item(0).getTextContent();
		
		String password = elementoUtente.getElementsByTagName(PASSWORD).item(0).getTextContent();
		
		String ruolo = elementoUtente.getElementsByTagName(RUOLO).item(0).getTextContent();
		
		return new Utente(nome, password, ruolo);
	}
	
	public static Utente caricaInfoExtraUtenteSuXML(Element elementoUtente) {
		Utente utente = caricaInfoBaseUtenteSuXML(elementoUtente);
		
		Comprensorio comprensorio = GestioneComprensoriXML.caricaComprensorioSuXML(elementoUtente);
		
		String indirizzo = elementoUtente.getElementsByTagName(INDIRIZZO).item(0).getTextContent();
		
		return new Utente(utente.getNome(), utente.getPassword(), utente.getRuolo(), comprensorio, indirizzo);
	}
}