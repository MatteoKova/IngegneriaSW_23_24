package it.unibs.controller;

import it.unibs.model.Comprensorio;
import it.unibs.model.ElencoComprensori;
import it.unibs.view.ComprensorioView;

public class GestioneViewComprensorio {

	public static void visualizzaComprensori() {
		for(Comprensorio comprensorio : ElencoComprensori.getElencoComprensori()) {
			ComprensorioView.visualizzaNomeComprensorio(comprensorio.getNome());
			for(String comune : comprensorio.getComuniComprensorio()) {
				ComprensorioView.visualizzaNomeComune(comune);
			}
		}
	}
}