package it.unibs.controller;

import java.util.ArrayList;

import it.unibs.model.CategoriaFoglia;
import it.unibs.model.ElencoFattoriDiConversione;
import it.unibs.model.ElencoInsiemiChiusi;
import it.unibs.model.ElencoScambi;
import it.unibs.model.FattoreDiConversione;
import it.unibs.model.Scambio;
import it.unibs.model.Utente;
import it.unibs.view.ScambioView;

public class ScambioController {

	private static final int MAX_LUNGHEZZA_ANELLO_SCAMBI = 3;	//Serve a non rendere la ricerca infinita o comunque troppo lunga
	private static final String APERTO = "Aperto";
	private static final String CHIUSO = "Chiuso";
	private static final String RITIRATO = "Ritirato";

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
					fogliaOfferta.getNome(), fogliaOfferta.getRadice().getNome(), oreOfferta, APERTO);
			if(ScambioView.confermaScambio()) {
				Scambio nuovoScambio = new Scambio(fogliaRichiesta, fogliaOfferta, oreRichiesta, oreOfferta, APERTO, utente);
				
				nuovoScambio.setStato(APERTO);
				
				int lunghezzaMax = MAX_LUNGHEZZA_ANELLO_SCAMBI;
				ArrayList<Scambio> anelloDiScambi = ElencoScambi.trovaAnelloDiScambi(nuovoScambio, lunghezzaMax);
				if(anelloDiScambi != null) {
					for(Scambio scambio : anelloDiScambi) {
						scambio.setStato(CHIUSO);
					}
					ElencoInsiemiChiusi.aggiungiScambio(anelloDiScambi);
				}
				
				ElencoScambi.aggiungiScambio(nuovoScambio);
				break;
			}
			else {
				proposta = ScambioView.propostaNuovoScambio();
			}
		} while(proposta);
	}
	
	public static void visualizzaScambiConfiguratore(GerarchiaController gerarchia) {
		if(ElencoScambi.getElencoScambi().size() == 0) {
			ScambioView.msgScambioAssente();
		}
		else {
			ScambioView.scegliFogliaRichiesta();
			CategoriaFoglia fogliaRichiesta = (CategoriaFoglia)gerarchia.navigaGerarchiaFinoAFoglia();		
			
			ArrayList<Scambio> listaScambi = trovaScambioConFoglia(fogliaRichiesta);
			if(listaScambi.size() == 0) {
				ScambioView.msgScambioNonTrovato();
			}
			else {
				ScambioView.visualizzaScambiConfiguratore(fogliaRichiesta.getNome(), fogliaRichiesta.getRadice().getNome());
				
				for(Scambio scambio : listaScambi) {
					if(scambio.getFogliaRichiesta().verificaUguaglianzaFoglie(fogliaRichiesta) ||
							scambio.getFogliaOfferta().verificaUguaglianzaFoglie(fogliaRichiesta)) {
						ScambioView.visualizzaScambio(scambio.getFogliaRichiesta().getNome(),
								scambio.getFogliaRichiesta().getRadice().getNome(), scambio.getOreRichiesta(),
								scambio.getFogliaOfferta().getNome(), scambio.getFogliaOfferta().getRadice().getNome(),
								scambio.getOreOfferta(), scambio.getStato());
					}
				}
			}
		}
	}
	
	public static ArrayList<Scambio> trovaScambioConFoglia(CategoriaFoglia foglia) {
		ArrayList<Scambio> listaScambi = new ArrayList<>();
		
		for(Scambio scambio : ElencoScambi.getElencoScambi()) {
			if(scambio.getFogliaRichiesta().verificaUguaglianzaFoglie(foglia) ||
					scambio.getFogliaOfferta().verificaUguaglianzaFoglie(foglia)) {
				listaScambi.add(scambio);
			}
		}
		
		return listaScambi;
	}
	
	public static void visualizzaScambiFruitore(Utente utente) {
		if(ElencoScambi.getElencoScambi().size() == 0) {
			ScambioView.msgScambioAssente();
		}
		else {
			ArrayList<Scambio> listaScambi = trovaScambioConFruitore(utente);
			if(listaScambi.size() == 0) {
				ScambioView.msgScambioNonTrovato();
			}
			else {
				ScambioView.visualizzaScambiFruitore();
				
				for(Scambio scambio : listaScambi) {
					if(scambio.getUtente().getNome().equals(utente.getNome())) {
						ScambioView.visualizzaScambio(scambio.getFogliaRichiesta().getNome(),
								scambio.getFogliaRichiesta().getRadice().getNome(), scambio.getOreRichiesta(),
								scambio.getFogliaOfferta().getNome(), scambio.getFogliaOfferta().getRadice().getNome(),
								scambio.getOreOfferta(), scambio.getStato());
					}
				}
			}
		}
	}
	
	public static ArrayList<Scambio> trovaScambioConFruitore(Utente utente) {
		ArrayList<Scambio> listaScambi = new ArrayList<>();
		
		for(Scambio scambio : ElencoScambi.getElencoScambi()) {
			if(scambio.getUtente().getNome().equals(utente.getNome())) {
				listaScambi.add(scambio);
			}
		}
		
		return listaScambi;
	}
	
	public static void cambiaStatoScambio(Utente utente) {
		if(ElencoScambi.getElencoScambi().size() == 0) {
			ScambioView.msgScambioAssente();
		}
		else {
			ArrayList<Scambio> scambiRitirabili = ElencoScambi.trovaScambiRitirabili(utente);
			if(scambiRitirabili.size() == 0) {
				ScambioView.msgScambioRitirabileAssente();
			}
			else {
				ScambioView.visualizzaScambiFruitore();

				for(Scambio scambio : scambiRitirabili) {
					if(scambio.getUtente().getNome().equals(utente.getNome()) && scambio.getStato().equals(APERTO)) {
						ScambioView.visualizzaScambio(scambio.getFogliaRichiesta().getNome(),
								scambio.getFogliaRichiesta().getRadice().getNome(), scambio.getOreRichiesta(),
								scambio.getFogliaOfferta().getNome(), scambio.getFogliaOfferta().getRadice().getNome(),
								scambio.getOreOfferta(), scambio.getStato());

						if(ScambioView.propostaRitiroScambio()) {
							scambio.setStato(RITIRATO);
						}
					}
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
					ScambioView.visualizzaScambio(scambio.getFogliaRichiesta().getNome(),
							scambio.getFogliaRichiesta().getRadice().getNome(), scambio.getOreRichiesta(),
							scambio.getFogliaOfferta().getNome(), scambio.getFogliaOfferta().getRadice().getNome(),
							scambio.getOreOfferta(), scambio.getStato());
				}
			}
		}
	}
}