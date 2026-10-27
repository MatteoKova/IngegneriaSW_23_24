package it.unibs.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GestioneUtentiXML {

	private static final String ELENCO_UTENTI = "elencoUtenti";
	private static final String UTENTE = "utente";
	private static final String NOME = "nomeU";
	private static final String PASSWORD = "password";
	private static final String RUOLO = "ruolo";
	
	//PARTE SALVATAGGIO ======================================================================================================

	public static void salvaElencoUtentiSuXML(String filePath) {
		Document doc = GestioneGeneraleXML.creaFileXML();
		Element elementoElencoUtenti = doc.createElement(ELENCO_UTENTI);

		doc.appendChild(elementoElencoUtenti);

		for(Utente utente : ElencoUtenti.getElencoUtenti()) {
			Element elementoUtente = doc.createElement(UTENTE);

			elementoUtente.appendChild(GestioneGeneraleXML.creaElemento(doc, NOME, utente.getNome()));
			elementoUtente.appendChild(GestioneGeneraleXML.creaElemento(doc, PASSWORD, utente.getPassword()));			
			elementoUtente.appendChild(GestioneGeneraleXML.creaElemento(doc, RUOLO, utente.getRuolo()));			
			elementoElencoUtenti.appendChild(elementoUtente);
		}
		GestioneGeneraleXML.salvaFileXML(doc, filePath);
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

				String nome = elementoUtente.getElementsByTagName(NOME).item(0).getTextContent();		
				String password = elementoUtente.getElementsByTagName(PASSWORD).item(0).getTextContent();				
				String ruolo = elementoUtente.getElementsByTagName(RUOLO).item(0).getTextContent();				
				Utente utente = new Utente(nome, password, ruolo);
				ElencoUtenti.getElencoUtenti().add(utente);
			}
		}
	}
}