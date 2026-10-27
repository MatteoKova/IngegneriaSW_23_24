package it.unibs.model;

import java.util.ArrayList;

public class ElencoScambi {
	
	private static ArrayList<Scambio> elencoScambi = new ArrayList<>();
	
	public static void aggiungiScambio(Scambio scambio) {
		elencoScambi.add(scambio);
	}
	
	public static ArrayList<Scambio> trovaAnelloDiScambi(Scambio nuovoScambio, int lunghezzaMax) {
		ArrayList<Scambio> percorsoCorrente = new ArrayList<>();
		percorsoCorrente.add(nuovoScambio);
		return cercaAnello(nuovoScambio, percorsoCorrente, lunghezzaMax);
	}

	private static ArrayList<Scambio> cercaAnello(Scambio corrente, ArrayList<Scambio> percorsoCorrente, int lunghezzaMax) {
		if(percorsoCorrente.size() > lunghezzaMax) {
			return null;
		}

		for(Scambio prossimo : elencoScambi) {
			if(!percorsoCorrente.contains(prossimo) && prossimo.getStato().equals(StatoScambio.APERTO.getDescrizione()) &&
				!(prossimo.getUtente().getNome().equals(corrente.getUtente().getNome())) &&
				prossimo.getUtente().getComprensorio().getNome().equals(corrente.getUtente().getComprensorio().getNome()) &&
				prossimo.getFogliaRichiesta().verificaUguaglianzaFoglie(corrente.getFogliaOfferta()) &&
				prossimo.getOreRichiesta() == corrente.getOreOfferta()) {
				percorsoCorrente.add(prossimo);

				if(percorsoCorrente.size() > 1 && prossimo.getStato().equals(StatoScambio.APERTO.getDescrizione()) &&
					!(prossimo.getUtente().getNome().equals(percorsoCorrente.get(0).getUtente().getNome())) &&
					prossimo.getUtente().getComprensorio().getNome().equals(percorsoCorrente.get(0).getUtente().getComprensorio().getNome()) &&
					prossimo.getFogliaOfferta().verificaUguaglianzaFoglie(percorsoCorrente.get(0).getFogliaRichiesta()) &&
					prossimo.getOreOfferta() == percorsoCorrente.get(0).getOreRichiesta()) {
					return percorsoCorrente;
				}
				else {
					ArrayList<Scambio> risultato = cercaAnello(prossimo, percorsoCorrente, lunghezzaMax);
					if(risultato != null) {
						return risultato;
					}
				}
				percorsoCorrente.remove(percorsoCorrente.size() - 1);
			}
		}
		return null;
	}
	
	public static ArrayList<Scambio> trovaScambioConFoglia(CategoriaFoglia foglia) {
		ArrayList<Scambio> listaScambi = new ArrayList<>();
		
		for(Scambio scambio : elencoScambi) {
			if(scambio.getFogliaRichiesta().verificaUguaglianzaFoglie(foglia) ||
					scambio.getFogliaOfferta().verificaUguaglianzaFoglie(foglia)) {
				listaScambi.add(scambio);
			}
		}
		
		return listaScambi;
	}
	
	public static ArrayList<Scambio> trovaScambioConFruitore(Utente utente) {
		ArrayList<Scambio> listaScambi = new ArrayList<>();
		
		for(Scambio scambio : elencoScambi) {
			if(scambio.getUtente().getNome().equals(utente.getNome())) {
				listaScambi.add(scambio);
			}
		}
		
		return listaScambi;
	}

	public static ArrayList<Scambio> getElencoScambi() {
		return elencoScambi;
	}
}