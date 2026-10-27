package it.unibs.view;

import it.unibs.mylib.InputDati;

public class ScambioView {

	public static void scegliFogliaRichiesta() {
		System.out.println("Scegli la prestazione (Categoria Foglia) a cui sei interessato");
	}
	
	public static int inserisciOreRichiesta() {
		return InputDati.leggiIntero("\nInserisci il numero di ore della prestazione richiesta\n");
	}
	
	public static void scegliFogliaOfferta() {
		System.out.println("Scegli la prestazione (Categoria Foglia) che sei disposto/a ad offrire");
	}
	
	public static void msgStessaFoglia() {
		System.out.println("\nATTENZIONE\nHai selezionato la stessa prestazione per la richiesta e per l'offerta\nSeleziona un'altra prestazione da offrire");
	}
	
	public static void visualizzaScambio(String fogliaRichiesta, String radiceRichiesta, int oreRichiesta,
			String fogliaOfferta, String radiceOfferta, int oreOfferta) {
		System.out.println("\nRichiesta: [" + fogliaRichiesta + " (" + radiceRichiesta + "), " + oreRichiesta + " ore]\n"
				+ "Offerta: [" + fogliaOfferta + " (" + radiceOfferta + "), " + oreOfferta + " ore]");
	}
	
	public static boolean confermaScambio() {
		return InputDati.yesOrNo("\nVuoi confermare questa proposta di scambio?\n");
	}
	
	public static void msgScambioScartato() {
		System.out.println("Questo scambio e' stato annullato");
	}
	
	public static boolean propostaScambio() {
		return InputDati.yesOrNo("Vuoi creare un'altra proposta di scambio?\n");
	}
}