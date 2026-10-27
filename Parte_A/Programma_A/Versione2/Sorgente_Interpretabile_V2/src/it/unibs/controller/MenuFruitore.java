package it.unibs.controller;

import it.unibs.mylib.MyMenu;
import it.unibs.model.GestioneGeneraleXML;

public class MenuFruitore {
	
	private static final String NAVIGA_GERARCHIA = "Scegli una Gerarchia ed esplorala";
	private static final String SALVA_SU_XML = "Salva tutti i dati sui file xml";
	private static final String SCELTA = "MENU' FRUITORE";
	
	public static void menuFruitore(GerarchiaController gerarchia) {
		String[] voci = {NAVIGA_GERARCHIA, SALVA_SU_XML};
		int scelta = 0;
		MyMenu menu = new MyMenu(SCELTA, voci);

		do {
			scelta = menu.scegli();
			switch(scelta) {
			case 1:
				gerarchia.navigaGerarchiaFinoAFoglia();
				break;
			case 2:
				GestioneGeneraleXML.salvataggioCompleto();
				break;
			default:
				break;
			}
		} while(scelta != 0);
	}
}