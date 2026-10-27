package it.unibs.model;

import java.util.ArrayList;

public class ElencoScambi {

	private static ArrayList<Scambio> elencoScambi = new ArrayList<>();
	
	public static void aggiungiScambio(Scambio scambio) {
		elencoScambi.add(scambio);
	}

	public static ArrayList<Scambio> getElencoScambi() {
		return elencoScambi;
	}
}