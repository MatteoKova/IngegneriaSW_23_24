package it.unibs.model;

import java.util.ArrayList;

public class ElencoUtenti {

	private static final String UTENTE_PREDEFINITO = "utente";
	private static final String PASSWORD_PREDEFINITA = "password";
	private static final String FRUITORE = "Fruitore";
	
	private static ArrayList<Utente> elencoUtenti = new ArrayList<>();

	public static void aggiungiUtente(Utente newCredenziali) {
		elencoUtenti.add(newCredenziali);	
	}

	public static boolean isPrimoAccesso(String nome, String password) {
		if(nome.equals(UTENTE_PREDEFINITO) && password.equals(PASSWORD_PREDEFINITA)) {
			return true;
		}
		return false;
	}

	public static boolean isDuplicato(String nome) {
		for(Utente utente : elencoUtenti) {
			if(utente.getNome().equals(nome)) {
				return true;
			}
		}
		return false;
	}
	public static boolean verificaEsistenzaUtente(String nome, String ruolo) {
		for(Utente utente : ElencoUtenti.getElencoUtenti()) {
			if(utente.getNome().equals(nome) && utente.getRuolo().equals(ruolo)) {
				return true;	
			}
		}
		return false;
	}

	public static Utente trovaUtente(String nome, String password) {
		for(Utente utente : getElencoUtenti()) {
			if(utente.getNome().equals(nome) && utente.getPassword().equals(password)) {
				return utente;
			}
		}
		return null;
	}

	public static boolean erratoUsoCredenzialiBase(String nome) {
		if(nome.equals(UTENTE_PREDEFINITO)) {
			return true;
		}
		return false;
	}
	
	public static boolean esisteIndirizzo(String indirizzo) {
		for(Utente utente : getElencoUtenti()) {
			if(utente.getRuolo().equals(FRUITORE) && utente.getIndirizzo().equals(indirizzo)) {
				return true;
			}
		}
		return false;
	}
	
	public static ArrayList<Utente> getElencoUtenti() {
		return elencoUtenti;
	}
}