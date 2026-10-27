package it.unibs.controller;

import it.unibs.model.*;
import it.unibs.view.*;

public class LoginController {

	private static final String FRUITORE = "Fruitore";
	private static final String CONFIGURATORE = "Configuratore";
	
	public Utente loginGenerale() {
		Utente utente;
		
		do {
			LoginView.visualizzazioneInizioLogin();
			String ruolo = inserisciRuolo();
				if(ruolo.equals(CONFIGURATORE)) {
					utente = loginConfiguratore();
				}
				else {
					utente = loginFruitore();
				}
			} while(utente == null);
		return utente;
	}
	
	public Utente loginConfiguratore() {
		Utente utente;
		
		String nome = LoginView.inserisciNome();	
		while(ElencoUtenti.verificaEsistenzaUtente(nome, FRUITORE)) {
			LoginView.msgFruitoreGiaEsistente();
			nome = LoginView.inserisciNome();
		}
		
		String password = LoginView.inserisciPassword();

		boolean isPrimoAccesso = ElencoUtenti.isPrimoAccesso(nome, password);
		if(isPrimoAccesso) {
			utente = new Utente(nome, password, CONFIGURATORE);
			cambiaCredenziali(utente);
			ElencoUtenti.aggiungiUtente(utente);
			return utente;
		}
		utente = ElencoUtenti.trovaUtente(nome, password);

		if(utente != null) {
			return utente;
		}
		else {
			LoginView.msgConfiguratoreInesistente();
			return null;
		}
	}
	
	public Utente loginFruitore() {
		Utente utente;
		
		String password;

		String nome = LoginView.inserisciNome();
		while(ElencoUtenti.erratoUsoCredenzialiBase(nome) || ElencoUtenti.verificaEsistenzaUtente(nome, CONFIGURATORE)) {
			LoginView.msgConfiguratoreGiaEsistente();
			nome = LoginView.inserisciNome();
		}
		if(ElencoUtenti.verificaEsistenzaUtente(nome, FRUITORE)) {
			password = LoginView.inserisciPassword();
			
			utente = ElencoUtenti.trovaUtente(nome, password);
			if(utente == null) {
				LoginView.msgFruitoreInesistente();
			}
			return utente;
		}
		password = LoginView.inserisciPassword();

		Comprensorio comprensorio = scegliComprensorio();
		if(comprensorio == null) {
			return null;
		}

		String indirizzo = inserisciIndirizzo();
		while(ElencoUtenti.esisteIndirizzo(indirizzo)) {
			LoginView.msgIndirizzoErrato();
			indirizzo = inserisciIndirizzo();
		}

		utente = new Utente(nome, password, FRUITORE, comprensorio, indirizzo);
		ElencoUtenti.aggiungiUtente(utente);
		return utente;
	}
	
	public String inserisciIndirizzo() {
		return LoginView.inserisciIndirizzo();
	}
	
	public Comprensorio scegliComprensorio() {
		Comprensorio comprensorio;
		
		if(ElencoComprensori.getElencoComprensori().size() == 0) {
			ComprensorioView.msgComprensoriAssenti();
			return null;
		}
		
		ComprensorioController.visualizzaComprensori();
		String nomeComprensorio = ComprensorioView.selezionaComprensorio();
		comprensorio = ComprensorioController.trovaComprensorio(nomeComprensorio);
		
		while(comprensorio == null) {
			ComprensorioView.msgComprensorioNonEsistente();
			ComprensorioController.visualizzaComprensori();
			nomeComprensorio = ComprensorioView.inserisciComprensorio();
			comprensorio = ComprensorioController.trovaComprensorio(nomeComprensorio);
		}
		return comprensorio;
	}

	
	public void cambiaCredenziali(Utente utente) {
		String nome;
		String password;
		
		LoginView.msgCambiamentoCredenziali();

		nome = LoginView.inserisciNome();

		while(ElencoUtenti.isDuplicato(nome)) {
			LoginView.msgConfiguratoreGiaEsistente();
			nome = LoginView.inserisciNome();
		}
		utente.setNome(nome);
		password = LoginView.inserisciPassword();
		utente.setPassword(password);
	}

	public static String inserisciRuolo() {
		int ruolo;
		boolean risposta;

		do {
			ruolo = LoginView.sceltaRuolo();
			risposta = LoginView.confermaScelta();
		} while(!risposta);

		if(ruolo == 1) {
			return CONFIGURATORE;
		}
		return FRUITORE; 
	}
}