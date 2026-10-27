package it.unibs.model;

import java.util.ArrayList;

public class ElencoUtenti {

	private static final String UTENTE_PREDEFINITO = "utente";
	private static final String PASSWORD_PREDEFINITA = "password";
	
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
			if(utente.getNome().equals(nome) || UTENTE_PREDEFINITO.equals(nome)) {
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

	public static ArrayList<Utente> getElencoUtenti() {
		return elencoUtenti;
	}
}