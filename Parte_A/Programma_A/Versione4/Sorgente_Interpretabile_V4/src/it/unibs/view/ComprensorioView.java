package it.unibs.view;

import it.unibs.mylib.InputDati;

public class ComprensorioView {

	//PARTE COMPRENSORIO

	public static String inserisciComprensorio() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome del Comprensorio che vuoi creare\n");
	}

	public static void msgComprensorioGiaPresente() {
		System.out.println("Esiste gia' un Comprensorio con questo nome");
	}
	
	public static String selezionaComprensorio() {
		return InputDati.leggiStringaNonVuota("\nInserisci il nome del Comprensorio che vuoi selezionare\n");
	}

	public static void visualizzaNomeComprensorio(String nome) {
		System.out.println("\nNome Comprensorio: " + nome);
	}
	
	public static void msgComprensorioNonEsistente() {
		System.out.println("Il Comprensorio inserito non compare nell'elenco");
	}
	
	public static void msgComprensoriAssenti() {
		System.out.println("Non e' stato creato alcun Comprensorio dai Configuratori, quindi non e' possibile continuare come Fruitore\n");
	}

	//PARTE COMUNE DEL COMPRENSORIO

	public static String inserisciComune() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome del Comune\n");
	}

	public static boolean inserisciAltroComune() {
		return InputDati.yesOrNo("Vuoi inserire un altro Comune?\n");
	}

	public static void msgComuneGiaPresente() {
		System.out.println("Il Comune inserito e' gia' presente in questo Comprensorio");
	}

	public static void visualizzaNomeComune(String nome) {
		System.out.println("Nome Comune: " + nome);
	}
}