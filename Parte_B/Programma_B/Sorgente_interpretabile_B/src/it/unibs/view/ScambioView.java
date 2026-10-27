package it.unibs.view;

import it.unibs.model.Categoria;
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
		System.out.println("\nATTENZIONE\nHai selezionato la stessa prestazione per la richiesta e per l'offerta\n"
			+ "Seleziona un'altra prestazione da offrire");
	}
	
	public static void visualizzaScambio(Categoria fogliaRichiesta, int oreRichiesta,
		Categoria fogliaOfferta, int oreOfferta, String stato) {
			System.out.println("\nRichiesta: [" + fogliaRichiesta.getNome() + " (" + fogliaRichiesta.getRadice().getNome()
			+ "), " + oreRichiesta + " ore]\n" + "Offerta: [" + fogliaOfferta.getNome() + " ("
			+ fogliaOfferta.getRadice().getNome() + "), " + oreOfferta + " ore]\n" + "Stato: [" + stato + "]");
	}
	
	public static boolean confermaScambio() {
		return InputDati.yesOrNo("\nVuoi confermare questa proposta di scambio?\n");
	}
	
	public static void msgScambioScartato() {
		System.out.println("Questo scambio e' stato annullato");
	}
	
	public static void msgAnnullamentoScambio() {
		System.out.println("\nScambio annullato con successo");
	}
	
	public static void visualizzaScambiFruitore() {
		System.out.println("\nQuesti sono gli scambi che hai creato:");
	}
	
	public static void visualizzaScambiConfiguratore(Categoria categoria) {
		System.out.println("\nQuesti sono gli scambi che riguardano la foglia " + categoria.getNome()
			+ " (" + categoria.getRadice().getNome() + "):");
	}
	
	public static boolean propostaRitiroScambio() {
		return InputDati.yesOrNo("Vuoi ritirare questo scambio?\n");
	}
	
	public static void msgScambioAssente() {
		System.out.println("Non e' ancora stata creata alcuna proposta di Scambio");
	}
	
	public static void msgScambioRitirabileAssente() {
		System.out.println("Non e' stato trovato alcuno Scambio che tu possa ritirare");
	}
	
	public static void msgScambioNonTrovato() {
		System.out.println("\nNon e' stato trovato alcuno Scambio");
	}
	
	public static void visualizzaInsiemiChiusi() {
		System.out.println("\nQuesti sono gli Insiemi chiusi formati dalle proposte di Scambio dei Fruitori");
	}
	
	public static void msgInsiemeChiusoAssente() {
		System.out.println("Non e' ancora stato formato alcun insieme chiuso");
	}
	
	public static void delimitazioneInsiemiChiusi() {
		System.out.println("\nInsieme chiuso:");
	}
}