package it.unibs.model;

import java.util.ArrayList;

public class ElencoInsiemiChiusi {

	private static ArrayList<ArrayList<Scambio>> elencoInsiemiChiusi = new ArrayList<>();
	
	public static void aggiungiScambio(ArrayList<Scambio> anelloScambi) {
		elencoInsiemiChiusi.add(anelloScambi);
	}

	public static ArrayList<ArrayList<Scambio>> getElencoInsiemiChiusi() {
		return elencoInsiemiChiusi;
	}
}