package it.unibs.controller;

import it.unibs.model.CategoriaFoglia;
import it.unibs.model.ElencoFattoriDiConversione;
import it.unibs.model.ElencoScambi;
import it.unibs.model.FattoreDiConversione;
import it.unibs.model.Scambio;
import it.unibs.model.Utente;
import it.unibs.view.ScambioView;

public class ScambioController {

	private static final String APERTO = "Aperto";

	public static void creaScambio(Utente utente) {
		GerarchiaController gerarchiaController = new GerarchiaController();
		boolean proposta;
		
		do {
			ScambioView.scegliFogliaRichiesta();
			CategoriaFoglia fogliaRichiesta = (CategoriaFoglia)gerarchiaController.navigaGerarchiaFinoAFoglia();
			
			int oreRichiesta = ScambioView.inserisciOreRichiesta();
			
			ScambioView.scegliFogliaOfferta();
			CategoriaFoglia fogliaOfferta = (CategoriaFoglia)gerarchiaController.navigaGerarchiaFinoAFoglia();
			while(fogliaOfferta.verificaUguaglianzaFoglie(fogliaRichiesta)) {
				ScambioView.msgStessaFoglia();
				fogliaOfferta = (CategoriaFoglia)gerarchiaController.navigaGerarchiaFinoAFoglia();
			}
			
			FattoreDiConversione fdc = ElencoFattoriDiConversione.trovaFDC(fogliaRichiesta, fogliaOfferta);
			int oreOfferta = (int)(Math.round(oreRichiesta * fdc.getValore()));
			
			ScambioView.visualizzaScambio(fogliaRichiesta.getNome(), fogliaRichiesta.getRadice().getNome(), oreRichiesta,
					fogliaOfferta.getNome(), fogliaOfferta.getRadice().getNome(), oreOfferta);
			if(ScambioView.confermaScambio()) {
				Scambio scambio = new Scambio(fogliaRichiesta, fogliaOfferta, oreRichiesta, oreOfferta, utente);
				scambio.setStato(APERTO);
				ElencoScambi.aggiungiScambio(scambio);
				break;
			} else {
				proposta = ScambioView.propostaScambio();
			}
		} while(proposta);
	}
}
