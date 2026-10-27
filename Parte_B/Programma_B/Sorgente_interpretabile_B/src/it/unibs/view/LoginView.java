package it.unibs.view;

import it.unibs.mylib.InputDati;

public class LoginView {
	
	public static void visualizzazioneInizioLogin() {
		System.out.println("--------------------------------");
		System.out.println("FASE DI LOGIN");
		System.out.println("--------------------------------");
	}
	
	public static int sceltaRuolo() {
		return InputDati.leggiInteroRange("Inserisci 1 per Configuratore e 2 per Fruitore\n", 1, 2);
	}

	public static String inserisciNome() {
		return InputDati.leggiStringaNonVuota("\nInserisci un Nome\n");
	}

	public static String inserisciPassword() {
		return InputDati.leggiStringaNonVuota("Inserisci una Password\n");
	}

	public static void msgConfiguratoreInesistente() {
		System.out.println("Le credenziali inserite non corrispondono a nessun Configuratore esistente\n");
	}
	
	public static void msgFruitoreInesistente() {
		System.out.println("Le credenziali inserite non corrispondono a nessun Fruitore esistente\n");
	}

	public static void msgConfiguratoreGiaEsistente() {
		System.out.println("Esiste gia' un Configuratore con questo nome");
	}
	
	public static void msgFruitoreGiaEsistente() {
		System.out.println("Esiste gia' un Fruitore con questo nome");
	}

	public static boolean confermaScelta() {
		return InputDati.yesOrNo("Sei sicuro della tua scelta?\n");
	}

	public static void msgCambiamentoCredenziali() {
		System.out.println("\nCreazione di credenziali personali:");	
	}
	
	public static String inserisciIndirizzo() {
		return InputDati.leggiStringaNonVuota("Inserisci il tuo Indirizzo di posta elettronica\n");
	}
	
	public static void msgIndirizzoErrato() {
		System.out.println("Questo indirizzo di posta elettronica e' gia' in uso");
	}
}