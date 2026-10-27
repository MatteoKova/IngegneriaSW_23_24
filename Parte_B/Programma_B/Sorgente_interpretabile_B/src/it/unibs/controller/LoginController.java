package it.unibs.controller;

import it.unibs.model.*;
import it.unibs.view.*;

public class LoginController {
	
	public Utente loginGenerale() {
		Utente utente;
		
		do {
			LoginView.visualizzazioneInizioLogin();
			String ruolo = scegliRuolo();
				if(ruolo.equals(RuoloUtente.CONFIGURATORE.getDescrizione())) {
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
		
		String nome = inserisciNomeConfiguratore();
		
		String password = LoginView.inserisciPassword();

		boolean isPrimoAccesso = ElencoUtenti.isPrimoAccesso(nome, password);
		if(isPrimoAccesso) {
			utente = new Utente(nome, password, RuoloUtente.CONFIGURATORE.getDescrizione());
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

		String nome = inserisciNomeFruitore();
		if(ElencoUtenti.verificaEsistenzaUtente(nome, RuoloUtente.FRUITORE.getDescrizione())) {
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

		utente = new Utente(nome, password, RuoloUtente.FRUITORE.getDescrizione(), comprensorio, indirizzo);
		ElencoUtenti.aggiungiUtente(utente);
		return utente;
	}
	
	public String inserisciNomeConfiguratore() {
		String nome = LoginView.inserisciNome();	
		while(ElencoUtenti.verificaEsistenzaUtente(nome, RuoloUtente.FRUITORE.getDescrizione())) {
			LoginView.msgFruitoreGiaEsistente();
			nome = LoginView.inserisciNome();
		}
		return nome;
	}
	
	public String inserisciNomeFruitore() {
		String nome = LoginView.inserisciNome();
		while(ElencoUtenti.erratoUsoCredenzialiBase(nome) ||
				ElencoUtenti.verificaEsistenzaUtente(nome, RuoloUtente.CONFIGURATORE.getDescrizione())) {
			LoginView.msgConfiguratoreGiaEsistente();
			nome = LoginView.inserisciNome();
		}
		return nome;
	}
	
	public String inserisciIndirizzo() {
		String indirizzo = LoginView.inserisciIndirizzo();
		while(ElencoUtenti.esisteIndirizzo(indirizzo)) {
			LoginView.msgIndirizzoErrato();
			indirizzo = LoginView.inserisciIndirizzo();
		}
		return indirizzo;
	}
	
	public Comprensorio scegliComprensorio() {
		Comprensorio comprensorio;
		
		if(ElencoComprensori.getElencoComprensori().size() == 0) {
			ComprensorioView.msgComprensoriAssenti();
			return null;
		}
		
		GestioneViewComprensorio.visualizzaComprensori();
		String nomeComprensorio = ComprensorioView.selezionaComprensorio();
		comprensorio = ElencoComprensori.trovaComprensorio(nomeComprensorio);
		
		while(comprensorio == null) {
			ComprensorioView.msgComprensorioNonEsistente();
			GestioneViewComprensorio.visualizzaComprensori();
			nomeComprensorio = ComprensorioView.inserisciComprensorio();
			comprensorio = ElencoComprensori.trovaComprensorio(nomeComprensorio);
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

	public static String scegliRuolo() {
		int ruolo;
		boolean risposta;

		do {
			ruolo = LoginView.sceltaRuolo();
			risposta = LoginView.confermaScelta();
		} while(!risposta);

		if(ruolo == 1) {
			return RuoloUtente.CONFIGURATORE.getDescrizione();
		}
		return RuoloUtente.FRUITORE.getDescrizione(); 
	}
}