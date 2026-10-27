package it.unibs.model;

import java.util.ArrayList;

public class ElencoScambi {

	private static final String APERTO = "Aperto";
	
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
			if(!percorsoCorrente.contains(prossimo) && prossimo.getStato().equals(APERTO) &&
				!(prossimo.getUtente().getNome().equals(corrente.getUtente().getNome())) &&
				prossimo.getUtente().getComprensorio().getNome().equals(corrente.getUtente().getComprensorio().getNome()) &&
				prossimo.getFogliaRichiesta().verificaUguaglianzaFoglie(corrente.getFogliaOfferta()) &&
				prossimo.getOreRichiesta() == corrente.getOreOfferta()) {
				percorsoCorrente.add(prossimo);

				if(percorsoCorrente.size() > 1 && prossimo.getStato().equals(APERTO) &&
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
	
	public static ArrayList<Scambio> trovaScambiRitirabili(Utente utente) {
		ArrayList<Scambio> scambiRitirabili = new ArrayList<>();
	
		for(Scambio scambio : ElencoScambi.getElencoScambi()) {
			if(scambio.getUtente().getNome().equals(utente.getNome()) && scambio.getStato().equals(APERTO)) {
				scambiRitirabili.add(scambio);
			}
		}
		return scambiRitirabili;
	}

	public static ArrayList<Scambio> getElencoScambi() {
		return elencoScambi;
	}
}