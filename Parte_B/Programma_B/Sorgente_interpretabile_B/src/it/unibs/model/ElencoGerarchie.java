package it.unibs.model;

import java.util.ArrayList;

public class ElencoGerarchie {
	
	private static ArrayList<Gerarchia> elencoGerarchie = new ArrayList<>();
	
	public static void aggiungiGerarchia(Gerarchia gerarchia) {
		elencoGerarchie.add(gerarchia);
	}

	public static CategoriaRadice trovaRadice(String nomeRadice) {
		for(Gerarchia g : elencoGerarchie) {
			if(g.getRadice().getNome().equals(nomeRadice)) {
				return g.getRadice();
			}
		}
		return null;
	}

	public static CategoriaFoglia selezionaFoglia(String nomeFoglia, String nomeRadice) {
		for(Gerarchia g : elencoGerarchie) {
			if(g.getRadice().getNome().equals(nomeRadice)) {
				return Gerarchia.trovaFoglia(g.getRadice(), nomeFoglia);
			}
		}
		return null;	 
	}

	public static boolean dueOpiuFoglie() {
		int count = 0;

		for(Gerarchia g : elencoGerarchie) {
			count += g.getRadice().contaFoglieCategoria();
		}

		if(count > 1) {
			return true;
		}
		return false;
	}
	
	public static ArrayList<Gerarchia> getElencoGerarchie() {
		return elencoGerarchie;
	}
}