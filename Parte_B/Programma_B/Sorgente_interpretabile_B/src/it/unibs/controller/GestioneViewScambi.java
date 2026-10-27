package it.unibs.controller;

import java.util.ArrayList;

import it.unibs.model.CategoriaFoglia;
import it.unibs.model.ElencoInsiemiChiusi;
import it.unibs.model.ElencoScambi;
import it.unibs.model.Scambio;
import it.unibs.model.Utente;
import it.unibs.view.ScambioView;

public class GestioneViewScambi {

	public static void visualizzaScambiConfiguratore(GerarchiaController gerarchia) {
		if(ElencoScambi.getElencoScambi().size() == 0) {
			ScambioView.msgScambioAssente();
		}
		else {
			ScambioView.scegliFogliaRichiesta();
			CategoriaFoglia fogliaRichiesta = gerarchia.navigaGerarchiaFinoAFoglia();		
			
			ArrayList<Scambio> listaScambi = ElencoScambi.trovaScambioConFoglia(fogliaRichiesta);
			if(listaScambi.size() == 0) {
				ScambioView.msgScambioNonTrovato();
			}
			else {
				ScambioView.visualizzaScambiConfiguratore(fogliaRichiesta);
				
				for(Scambio scambio : listaScambi) {
					if(scambio.getFogliaRichiesta().verificaUguaglianzaFoglie(fogliaRichiesta) ||
							scambio.getFogliaOfferta().verificaUguaglianzaFoglie(fogliaRichiesta)) {
						ScambioView.visualizzaScambio(scambio.getFogliaRichiesta(), scambio.getOreRichiesta(),
								scambio.getFogliaOfferta(), scambio.getOreOfferta(), scambio.getStato());
					}
				}
			}
		}
	}
	
	public static void visualizzaScambiFruitore(Utente utente) {
		if(ElencoScambi.getElencoScambi().size() == 0) {
			ScambioView.msgScambioAssente();
		}
		else {
			ArrayList<Scambio> listaScambi = ElencoScambi.trovaScambioConFruitore(utente);
			if(listaScambi.size() == 0) {
				ScambioView.msgScambioNonTrovato();
			}
			else {
				ScambioView.visualizzaScambiFruitore();
				
				for(Scambio scambio : listaScambi) {
					ScambioView.visualizzaScambio(scambio.getFogliaRichiesta(), scambio.getOreRichiesta(),
							scambio.getFogliaOfferta(), scambio.getOreOfferta(), scambio.getStato());
				}
			}
		}
	}
	
	public static void visualizzaInsiemiChiusi() {
		if(ElencoInsiemiChiusi.getElencoInsiemiChiusi().size() == 0) {
			ScambioView.msgInsiemeChiusoAssente();
		}
		else {
			ScambioView.visualizzaInsiemiChiusi();
			
			for(ArrayList<Scambio> anelloDiScambi : ElencoInsiemiChiusi.getElencoInsiemiChiusi()) {
				ScambioView.delimitazioneInsiemiChiusi();
				for(Scambio scambio : anelloDiScambi) {
					ScambioView.visualizzaScambio(scambio.getFogliaRichiesta(), scambio.getOreRichiesta(),
							scambio.getFogliaOfferta(), scambio.getOreOfferta(), scambio.getStato());
				}
			}
		}
	}
}