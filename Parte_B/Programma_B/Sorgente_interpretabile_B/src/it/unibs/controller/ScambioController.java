package it.unibs.controller;

import java.util.ArrayList;

import it.unibs.model.CategoriaFoglia;
import it.unibs.model.ElencoFattoriDiConversione;
import it.unibs.model.ElencoInsiemiChiusi;
import it.unibs.model.ElencoScambi;
import it.unibs.model.FattoreDiConversione;
import it.unibs.model.Scambio;
import it.unibs.model.StatoScambio;
import it.unibs.model.Utente;
import it.unibs.view.ScambioView;

public class ScambioController {

	public static void creaScambio(Utente utente) {
		GerarchiaController gerarchiaController = new GerarchiaController();
		
		ScambioView.scegliFogliaRichiesta();
		CategoriaFoglia fogliaRichiesta = gerarchiaController.navigaGerarchiaFinoAFoglia();

		int oreRichiesta = ScambioView.inserisciOreRichiesta();

		ScambioView.scegliFogliaOfferta();
		CategoriaFoglia fogliaOfferta = gerarchiaController.navigaGerarchiaFinoAFoglia();
		while(fogliaOfferta.verificaUguaglianzaFoglie(fogliaRichiesta)) {
			ScambioView.msgStessaFoglia();
			fogliaOfferta = gerarchiaController.navigaGerarchiaFinoAFoglia();
		}

		FattoreDiConversione fdc = ElencoFattoriDiConversione.trovaFDC(fogliaRichiesta, fogliaOfferta);
		int oreOfferta = (int)(Math.round(oreRichiesta * fdc.getValore()));

		ScambioView.visualizzaScambio(fogliaRichiesta, oreRichiesta, fogliaOfferta, oreOfferta,
				StatoScambio.APERTO.getDescrizione());
		if(ScambioView.confermaScambio()) {
			Scambio nuovoScambio = new Scambio(fogliaRichiesta, fogliaOfferta, oreRichiesta, oreOfferta,
					StatoScambio.APERTO.getDescrizione(), utente);

			ElencoInsiemiChiusi.controllaInsiemeChiuso(nuovoScambio);

			ElencoScambi.aggiungiScambio(nuovoScambio);
		}
		else {
			ScambioView.msgAnnullamentoScambio();
		}
	}
	
	public static void cambiaStatoScambio(Utente utente) {
		ArrayList<Scambio> scambiRitirabili = new ArrayList<>();
		
		if(ElencoScambi.getElencoScambi().size() == 0) {
			ScambioView.msgScambioAssente();
		}
		else {
			ArrayList<Scambio> listaScambi = ElencoScambi.trovaScambioConFruitore(utente);
			for(Scambio s : listaScambi) {
				if(s.getStato().equals(StatoScambio.APERTO.getDescrizione())) {
					scambiRitirabili.add(s);
				}
			}
			if(scambiRitirabili.size() == 0) {
				ScambioView.msgScambioRitirabileAssente();
			}
			else {
				ScambioView.visualizzaScambiFruitore();

				for(Scambio scambio : scambiRitirabili) {
					ScambioView.visualizzaScambio(scambio.getFogliaRichiesta(), scambio.getOreRichiesta(),
							scambio.getFogliaOfferta(), scambio.getOreOfferta(), scambio.getStato());

					if(ScambioView.propostaRitiroScambio()) {
						scambio.setStato(StatoScambio.RITIRATO.getDescrizione());
					}
				}
			}
		}
	}
}