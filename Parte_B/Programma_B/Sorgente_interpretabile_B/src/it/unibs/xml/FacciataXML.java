package it.unibs.xml;

public class FacciataXML {
	
	private static final String RESOURCES_FILE_UTENTI_XML = "resources/FileUtenti.xml";
	private static final String RESOURCES_FILE_COMPRENSORI_XML = "resources/FileComprensori.xml";
	private static final String RESOURCES_FILE_GERARCHIE_XML = "resources/FileGerarchie.xml";
	private static final String RESOURCES_FILE_FATTORI_DI_CONVERSIONE_XML = "resources/FileFattoriDiConversione.xml";
	private static final String RESOURCES_FILE_SCAMBI_XML = "resources/FileScambi.xml";
	private static final String RESOURCES_FILE_INSIEMI_CHIUSI_XML = "resources/FileInsiemiChiusi.xml";
	
	private static final String ELENCO_UTENTI = "elencoUtenti";
	private static final String ELENCO_COMPRENSORI = "elencoComprensori";
	private static final String ELENCO_GERARCHIE = "elencoGerarchie";
	private static final String ELENCO_FATTORI_DI_CONVERSIONE = "elencoFattoriDiConversione";
	private static final String ELENCO_SCAMBI = "elencoScambi";
	private static final String ELENCO_INSIEMI_CHIUSI = "elencoInsiemiChiusi";

	public static void formattazioneCompleta() {
		GestioneGeneraleXML.formattaFileXML(RESOURCES_FILE_UTENTI_XML, ELENCO_UTENTI);
		GestioneGeneraleXML.formattaFileXML(RESOURCES_FILE_COMPRENSORI_XML, ELENCO_COMPRENSORI);
		GestioneGeneraleXML.formattaFileXML(RESOURCES_FILE_GERARCHIE_XML, ELENCO_GERARCHIE);
		GestioneGeneraleXML.formattaFileXML(RESOURCES_FILE_FATTORI_DI_CONVERSIONE_XML, ELENCO_FATTORI_DI_CONVERSIONE);
		GestioneGeneraleXML.formattaFileXML(RESOURCES_FILE_SCAMBI_XML, ELENCO_SCAMBI);
		GestioneGeneraleXML.formattaFileXML(RESOURCES_FILE_INSIEMI_CHIUSI_XML, ELENCO_INSIEMI_CHIUSI);
	}
	
	public static void salvataggioCompleto() {
		GestioneUtentiXML.salvaElencoUtentiSuXML(RESOURCES_FILE_UTENTI_XML);
		GestioneComprensoriXML.salvaElencoComprensoriSuXML(RESOURCES_FILE_COMPRENSORI_XML);
		GestioneGerarchieXML.salvaElencoGerarchieSuXML(RESOURCES_FILE_GERARCHIE_XML);
		GestioneFattoriDiConversioneXML.salvaElencoFDCSuXML(RESOURCES_FILE_FATTORI_DI_CONVERSIONE_XML);
		GestioneScambiXML.salvaElencoScambiSuXML(RESOURCES_FILE_SCAMBI_XML);
		GestioneInsiemiChiusiXML.salvaElencoInsiemiChiusiSuXML(RESOURCES_FILE_INSIEMI_CHIUSI_XML);
	}
	
	public static void caricamentoCompleto() {
		GestioneUtentiXML.caricaElencoUtentiDaXML(RESOURCES_FILE_UTENTI_XML);
		GestioneComprensoriXML.caricaElencoComprensoriDaXML(RESOURCES_FILE_COMPRENSORI_XML);
		GestioneGerarchieXML.caricaElencoGerarchieDaXML(RESOURCES_FILE_GERARCHIE_XML);
		GestioneFattoriDiConversioneXML.caricaElencoFDCDaXML(RESOURCES_FILE_FATTORI_DI_CONVERSIONE_XML);
		GestioneScambiXML.caricaElencoScambiDaXML(RESOURCES_FILE_SCAMBI_XML);
		GestioneInsiemiChiusiXML.caricaElencoInsiemiChiusiDaXML(RESOURCES_FILE_INSIEMI_CHIUSI_XML);
	}
}
