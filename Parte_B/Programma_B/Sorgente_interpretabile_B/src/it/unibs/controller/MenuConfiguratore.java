package it.unibs.controller;

import it.unibs.view.LoginView;
import it.unibs.view.MenuView;
import it.unibs.view.XMLView;
import it.unibs.xml.FacciataXML;
import it.unibs.mylib.MyMenu;

public class MenuConfiguratore {

	private static final String CREA_COMPRENSORIO = "Crea un nuovo Comprensorio Geografico";
	private static final String CREA_GERARCHIA = "Crea una nuova Gerarchia di Categorie";
	private static final String VISUALIZZA_COMPRENSORI = "Visualizza tutti i Comprensori";
	private static final String VISUALIZZA_GERARCHIE = "Visualizza tutte le Gerarchie";
	private static final String VISUALIZZA_FDC = "Visualizza tutti i Fattori di Conversione di una certa Foglia";
	private static final String VISUALIZZA_SCAMBI = "Visualizza tutti gli Scambi che riguardano una certa Foglia";
	private static final String VISUALIZZA_INSIEMI_CHIUSI = "Visualizza tutti gli Insiemi chiusi";
	private static final String SALVA_SU_XML = "Salva tutti i dati sui file xml";
	private static final String FORMATTA_XML = "Formatta tutti i file xml";
	private static final String SCELTA = "MENU' CONFIGURATORE";
	
	public static void menuConfiguratore(ComprensorioController comprensorio, GerarchiaController gerarchia) {
		String[] voci = {CREA_COMPRENSORIO, CREA_GERARCHIA, VISUALIZZA_COMPRENSORI, VISUALIZZA_GERARCHIE, VISUALIZZA_FDC,
				VISUALIZZA_SCAMBI, VISUALIZZA_INSIEMI_CHIUSI, SALVA_SU_XML, FORMATTA_XML};
		int scelta = 0;
		MyMenu menu = new MyMenu(SCELTA, voci);

		do {
			scelta = menu.scegli();
			switch(scelta) {
			case 1:
				comprensorio.creaComprensorio();
				break;
			case 2:
				gerarchia.creaGerarchia();
				break;
			case 3:
				GestioneViewComprensorio.visualizzaComprensori();
				break;
			case 4:
				GestioneViewGerarchia.visualizzaGerarchie();
				break;
			case 5:
				GestioneViewGerarchia.visualizzaGerarchie();
				GestioneViewGerarchia.visualizzaFattoriDiConversione(
						gerarchia.selezionaCategoriaFogliaPerFDC());
				break;
			case 6:
				GestioneViewScambi.visualizzaScambiConfiguratore(gerarchia);
				break;
			case 7:
				GestioneViewScambi.visualizzaInsiemiChiusi();
				break;
			case 8:
				FacciataXML.salvataggioCompleto();
				break;
			case 9:
				if(LoginView.confermaScelta()) {
					FacciataXML.formattazioneCompleta();
					//Dopo averla fatta, avviare un'altra sessione
				}
				else{
					XMLView.msgFormattazioneNonAvvenuta();
				}
				break;
			default:
				break;
			}
		} while(scelta != 0);

		MenuView.uscitaApplicazione();
	}
}