package it.unibs.model;

import java.util.ArrayList;

public class ElencoComprensori {

	private static ArrayList<Comprensorio> elencoComprensori = new ArrayList<>();

	public static void aggiungiComprensorio(Comprensorio comprensorio) {
		elencoComprensori.add(comprensorio);
	}

	public static boolean verificaEsistenzaComprensorio(String nomeComprensorio) {
		for(Comprensorio comprensorio : elencoComprensori) {
			if(comprensorio.getNome().equals(nomeComprensorio)) {
				return true;
			}
		}
		return false;
	}

	public static ArrayList<Comprensorio> getElencoComprensori() {
		return elencoComprensori;
	}
}