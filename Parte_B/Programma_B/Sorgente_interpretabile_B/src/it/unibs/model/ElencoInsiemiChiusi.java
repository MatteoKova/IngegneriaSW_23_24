package it.unibs.model;

import java.util.ArrayList;

public class ElencoInsiemiChiusi {
	
	private static final int MAX_LUNGHEZZA_ANELLO_SCAMBI = 3;	//Serve a non rendere la ricerca infinita o comunque troppo lunga

	private static ArrayList<ArrayList<Scambio>> elencoInsiemiChiusi = new ArrayList<>();
	
	public static void aggiungiInsiemeChiuso(ArrayList<Scambio> anelloScambi) {
		elencoInsiemiChiusi.add(anelloScambi);
	}
	
	public static void controllaInsiemeChiuso(Scambio nuovoScambio) {
		ArrayList<Scambio> anelloDiScambi = ElencoScambi.trovaAnelloDiScambi(nuovoScambio, MAX_LUNGHEZZA_ANELLO_SCAMBI);
		if(anelloDiScambi != null) {
			for(Scambio scambio : anelloDiScambi) {
				scambio.setStato(StatoScambio.CHIUSO.getDescrizione());
			}
			aggiungiInsiemeChiuso(anelloDiScambi);
		}
	}

	public static ArrayList<ArrayList<Scambio>> getElencoInsiemiChiusi() {
		return elencoInsiemiChiusi;
	}
}