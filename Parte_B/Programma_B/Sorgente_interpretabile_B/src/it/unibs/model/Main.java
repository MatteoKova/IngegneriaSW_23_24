package it.unibs.model;

import it.unibs.controller.ComprensorioController;
import it.unibs.controller.GerarchiaController;
import it.unibs.controller.LoginController;
import it.unibs.controller.MenuConfiguratore;
import it.unibs.controller.MenuFruitore;
import it.unibs.xml.FacciataXML;

public class Main {

	public static void main(String[] args) {
		FacciataXML.caricamentoCompleto();

		LoginController loginController = new LoginController();
		Utente utente = loginController.loginGenerale();

		ComprensorioController comprensorio = new ComprensorioController();
		GerarchiaController gerarchia = new GerarchiaController();

		if(utente != null) {
			if(utente.getRuolo().equals(RuoloUtente.CONFIGURATORE.getDescrizione())) {
				MenuConfiguratore.menuConfiguratore(comprensorio, gerarchia);
			}
			else {
				MenuFruitore.menuFruitore(gerarchia, utente);
			}
		}
	}
}