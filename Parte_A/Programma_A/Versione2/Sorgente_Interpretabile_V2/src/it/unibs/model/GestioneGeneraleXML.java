package it.unibs.model;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import it.unibs.view.XMLView;

import java.io.File;

public class GestioneGeneraleXML {
	
	private static final String RESOURCES_FILE_UTENTI_XML = "resources/FileUtenti.xml";
	private static final String RESOURCES_FILE_COMPRENSORI_XML = "resources/FileComprensori.xml";
	private static final String RESOURCES_FILE_GERARCHIE_XML = "resources/FileGerarchie.xml";
	private static final String RESOURCES_FILE_FATTORI_DI_CONVERSIONE_XML = "resources/FileFattoriDiConversione.xml";
	
	private static final String ELENCO_UTENTI = "elencoUtenti";
	private static final String ELENCO_COMPRENSORI = "elencoComprensori";
	private static final String ELENCO_GERARCHIE = "elencoGerarchie";
	private static final String ELENCO_FATTORI_DI_CONVERSIONE = "elencoFattoriDiConversione";
	
	//PARTE FORMATTAZIONE ====================================================================================================

	public static void formattazioneCompleta() {
		formattaFileXML(RESOURCES_FILE_UTENTI_XML, ELENCO_UTENTI);
		formattaFileXML(RESOURCES_FILE_COMPRENSORI_XML, ELENCO_COMPRENSORI);
		formattaFileXML(RESOURCES_FILE_GERARCHIE_XML, ELENCO_GERARCHIE);
		formattaFileXML(RESOURCES_FILE_FATTORI_DI_CONVERSIONE_XML, ELENCO_FATTORI_DI_CONVERSIONE);
	}

	public static void formattaFileXML(String filePath, String elenco) {
		Document doc = creaFileXML();

		Element elementoElencoUtenti = doc.createElement(elenco);
		doc.appendChild(elementoElencoUtenti);

		salvaFileXML(doc, filePath);
	}

	//PARTE SALVATAGGIO ======================================================================================================

	public static void salvataggioCompleto() {
		GestioneUtentiXML.salvaElencoUtentiSuXML(RESOURCES_FILE_UTENTI_XML);
		GestioneComprensoriXML.salvaElencoComprensoriSuXML(RESOURCES_FILE_COMPRENSORI_XML);
		GestioneGerarchieXML.salvaElencoGerarchieSuXML(RESOURCES_FILE_GERARCHIE_XML);
		GestioneFattoriDiConversioneXML.salvaElencoFDCSuXML(RESOURCES_FILE_FATTORI_DI_CONVERSIONE_XML);
	}

	public static Element creaElemento(Document doc, String tagElemento, String datoElemento) {
		Element elemento = doc.createElement(tagElemento);
		elemento.appendChild(doc.createTextNode(datoElemento));
		return elemento;
	}

	public static Document creaFileXML() {
		DocumentBuilderFactory docFactory;
		DocumentBuilder docBuilder;
		Document doc = null;

		try {
			docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.newDocument();

		} catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}
		return doc;
	}

	public static void salvaFileXML(Document doc, String filePath) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filePath));
			transformer.transform(source, result);

			XMLView.msgSalvataggioFileXML(filePath.substring(10));

		} catch(TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	//PARTE CARICAMENTO ======================================================================================================

	public static void caricamentoCompleto() {
		GestioneUtentiXML.caricaElencoUtentiDaXML(RESOURCES_FILE_UTENTI_XML);
		GestioneComprensoriXML.caricaElencoComprensoriDaXML(RESOURCES_FILE_COMPRENSORI_XML);
		GestioneGerarchieXML.caricaElencoGerarchieDaXML(RESOURCES_FILE_GERARCHIE_XML);
		GestioneFattoriDiConversioneXML.caricaElencoFDCDaXML(RESOURCES_FILE_FATTORI_DI_CONVERSIONE_XML);
	}

	public static Document caricaFileXML(String filePath) {
		File inputFile;
		DocumentBuilderFactory dbFactory;
		DocumentBuilder dBuilder;
		Document doc = null;

		try {
			inputFile = new File(filePath);
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(inputFile);

		} catch(Exception e) {
			e.printStackTrace();
		}
		return doc;
	}
}