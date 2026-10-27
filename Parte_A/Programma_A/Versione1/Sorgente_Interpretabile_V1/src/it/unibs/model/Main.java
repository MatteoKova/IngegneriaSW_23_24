package it.unibs.model;

import it.unibs.controller.ComprensorioController;
import it.unibs.controller.GerarchiaController;
import it.unibs.controller.LoginController;
import it.unibs.controller.Menu;

public class Main {
	
	public static void main(String[] args) {
		GestioneGeneraleXML.caricamentoCompleto();
		
		LoginController loginController = new LoginController();
		Utente utente = loginController.loginGenerale();

		ComprensorioController comprensorio = new ComprensorioController();
		GerarchiaController gerarchia = new GerarchiaController();

		if(utente != null) {
			Menu.menu(comprensorio, gerarchia);
		}
	}
}