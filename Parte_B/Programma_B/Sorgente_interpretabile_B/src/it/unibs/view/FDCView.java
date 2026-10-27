package it.unibs.view;

import it.unibs.model.FattoreDiConversione;
import it.unibs.mylib.InputDati;

public class FDCView {

	public static double inserisciValoreFDC() {
		return InputDati.leggiDoubleRange("\nInserisci un valore tra 0.5 e 2.0 per il Fattore di Conversione\n", 0.5, 2.0);
	}

	public static void visualizzaFattoreDiConversione(FattoreDiConversione fdc) {
		System.out.println("Il Fattore di Conversione tra " + fdc.getC1().getNome() + " (" + fdc.getC1().getRadice().getNome() + ") e "
			+ fdc.getC2().getNome() + " (" + fdc.getC2().getRadice().getNome() + ") vale: " + fdc.getValore());
	}

	public static String inserisciFogliaPerFDC() {
		return InputDati.leggiStringaNonVuota("\nInserisci il nome della Categoria Foglia di cui vuoi conoscere tutti i Fattori di Conversione\n");
	}

	public static String inserisciRadicePerFDC() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della Categoria Radice della Gerarchia a cui appartiene la Foglia\n");
	}

	public static void msgFogliaDiGerarchiaSbagliata(String nomeRadice) {
		System.out.println("ATTENZIONE\nDevi inserire il nome di una Categoria Foglia che abbia come Radice " + nomeRadice);
	}

	public static void inserimentoPrimaFogliaPerFDC() {
		System.out.print("\nINSERIMENTO PRIMA FOGLIA");
	}

	public static void inserimentoSecondaFogliaPerFDC() {
		System.out.print("\nINSERIMENTO SECONDA FOGLIA");
	}
}