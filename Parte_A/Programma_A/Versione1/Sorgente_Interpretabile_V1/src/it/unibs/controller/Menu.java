package it.unibs.controller;

import it.unibs.mylib.MyMenu;
import it.unibs.model.GestioneGeneraleXML;

public class Menu {

	private static final String CREA_COMPRENSORIO = "Crea un nuovo Comprensorio geografico";
	private static final String CREA_GERARCHIA = "Crea una nuova Gerarchia di Categorie";
	private static final String VISUALIZZA_COMPRENSORI = "Visualizza tutti i Comprensori";
	private static final String VISUALIZZA_GERARCHIE = "Visualizza tutte le Gerarchie";
	private static final String VISUALIZZA_FDC = "Visualizza tutti i Fattori di Conversione di una certa Foglia";
	private static final String SALVA_SU_XML = "Salva tutti i dati sui file xml";
	private static final String FORMATTA_XML = "Formatta tutti i file xml";
	private static final String SCELTA = "MENU' CONFIGURATORE";

	public static void menu(ComprensorioController comprensorio, GerarchiaController gerarchia) {
		String[] voci = {CREA_COMPRENSORIO, CREA_GERARCHIA, VISUALIZZA_COMPRENSORI, 
				VISUALIZZA_GERARCHIE, VISUALIZZA_FDC, SALVA_SU_XML, FORMATTA_XML};
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
				ComprensorioController.visualizzaComprensori();
				break;
			case 4:
				GerarchiaController.visualizzaGerarchie();
				break;
			case 5:
				GerarchiaController.visualizzaGerarchie();
				GerarchiaController.visualizzaFattoriDiConversione(
						gerarchia.selezionaCategoriaFogliaPerFDC());
				break;
			case 6:
				GestioneGeneraleXML.salvataggioCompleto();
				break;
			case 7:
				GestioneGeneraleXML.formattazioneCompleta();
				//Dopo averla fatta, avviare un'altra sessione
				break;
			default:
				break;
			}
		} while(scelta != 0);
	}
}