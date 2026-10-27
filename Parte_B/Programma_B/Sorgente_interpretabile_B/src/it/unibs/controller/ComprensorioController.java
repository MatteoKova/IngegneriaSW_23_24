package it.unibs.controller;

import java.util.ArrayList;

import it.unibs.model.Comprensorio;
import it.unibs.model.ElencoComprensori;
import it.unibs.view.ComprensorioView;

public class ComprensorioController {

	public void creaComprensorio() {
		Comprensorio comprensorio = new Comprensorio(creaNomeComprensorio(),
				creaComuniComprensorio());
		ElencoComprensori.aggiungiComprensorio(comprensorio);
	}

	public String creaNomeComprensorio() {
		String nomeComprensorio = ComprensorioView.inserisciComprensorio();

		while(ElencoComprensori.trovaComprensorio(nomeComprensorio) != null) {
			ComprensorioView.msgComprensorioGiaPresente();
			nomeComprensorio = ComprensorioView.inserisciComprensorio();
		}

		return nomeComprensorio;
	}

	public ArrayList<String> creaComuniComprensorio() {
		ArrayList<String> elencoComuni = new ArrayList<>();

		do {
			String nuovoComune = ComprensorioView.inserisciComune();

			if(elencoComuni.contains(nuovoComune)) {
				ComprensorioView.msgComuneGiaPresente();
			}
			else elencoComuni.add(nuovoComune);
		} while(ComprensorioView.inserisciAltroComune());

		return elencoComuni;
	}

	public static Comprensorio scegliComprensorio() {
		Comprensorio comprensorio;

		GestioneViewComprensorio.visualizzaComprensori();
		String nomeComprensorio = ComprensorioView.inserisciComprensorio();
		comprensorio = ElencoComprensori.trovaComprensorio(nomeComprensorio);

		while(comprensorio == null) {
			ComprensorioView.msgComprensorioNonEsistente();
			GestioneViewComprensorio.visualizzaComprensori();
			nomeComprensorio = ComprensorioView.inserisciComprensorio();
			comprensorio = ElencoComprensori.trovaComprensorio(nomeComprensorio);
		}
		return comprensorio;
	}
}