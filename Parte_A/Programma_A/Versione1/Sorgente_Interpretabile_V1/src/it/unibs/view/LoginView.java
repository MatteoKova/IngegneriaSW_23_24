package it.unibs.view;

import it.unibs.mylib.*;

public class LoginView {
	
	public static void visualizzazioneInizioLogin() {
		System.out.println("--------------------------------");
		System.out.println("FASE DI LOGIN");
		System.out.println("--------------------------------");
	}

	public static String inserisciNome() {
		return InputDati.leggiStringaNonVuota("Inserisci un Nome\n");
	}

	public static String inserisciPassword() {
		return InputDati.leggiStringaNonVuota("Inserisci una Password\n");
	}

	public static void msgConfiguratoreInesistente() {
		System.out.println("Le credenziali inserite non corrispondono a nessun Configuratore esistente\n");
	}

	public static void msgConfiguratoreGiaEsistente() {
		System.out.println("Il nome inserito corrisponde ad un configuratore gia' esistente\n");
	}

	public static boolean confermaScelta() {
		return InputDati.yesOrNo("Sei sicuro della tua scelta?\n");
	}

	public static void faseCambiamentoCredenziali() {
		System.out.println("\nCreazione di credenziali personali:");	
	}
}