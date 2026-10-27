package it.unibs.controller;

import java.util.ArrayList;

import it.unibs.model.Comprensorio;
import it.unibs.model.ElencoComprensori;
import it.unibs.view.ComprensorioView;

public class ComprensorioController {
	
	public String creaNomeComprensorio() {
		String nomeComprensorio = ComprensorioView.inserisciComprensorio();

		while(ElencoComprensori.verificaEsistenzaComprensorio(nomeComprensorio)) {
			ComprensorioView.comuneGiaPresente();
			nomeComprensorio = ComprensorioView.inserisciComprensorio();
		}

		return nomeComprensorio;
	}

	public ArrayList<String> creaComuniComprensorio() {
		ArrayList<String> elencoComuni = new ArrayList<>();

		do {
			String nuovoComune = ComprensorioView.inserisciComune();

			if(elencoComuni.contains(nuovoComune)) {
				ComprensorioView.comuneGiaPresente();
			}
			else elencoComuni.add(nuovoComune);
		} while(ComprensorioView.inserisciAltroComune());

		return elencoComuni;
	}

	public void creaComprensorio() {
		Comprensorio comprensorio = new Comprensorio(creaNomeComprensorio(), creaComuniComprensorio());
		ElencoComprensori.aggiungiComprensorio(comprensorio);
	}

	public static void visualizzaComprensori() {
		for(Comprensorio comprensorio : ElencoComprensori.getElencoComprensori()) {
			ComprensorioView.visualizzaNomeComprensorio(comprensorio.getNome());
			for(String comune : comprensorio.getComuniComprensorio()) {
				ComprensorioView.visualizzaNomeComune(comune);
			}
		}
	}
}
