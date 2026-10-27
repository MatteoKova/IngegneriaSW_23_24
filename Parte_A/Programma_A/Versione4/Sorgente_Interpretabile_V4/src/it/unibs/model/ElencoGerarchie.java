package it.unibs.model;

import java.util.ArrayList;

public class ElencoGerarchie {

	private static ArrayList<Gerarchia> elencoGerarchie = new ArrayList<>();

	public static Gerarchia aggiungiGerarchia(String nome, String campo, ArrayList<ValoreDominio> dominio) {
		Gerarchia nuovaGerarchia = new Gerarchia(nome, campo, dominio);
		elencoGerarchie.add(nuovaGerarchia);
		return nuovaGerarchia;
	}

	public static boolean verificaEsistenzaRadice(String nomeRadice) {
		for(Gerarchia g : elencoGerarchie) {
			if(g.getRadice().getNome().equals(nomeRadice)) {
				return true;
			}
		}
		return false;
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
			count += contaFoglie(g.getRadice().getFigli());
		}

		if(count > 1) {
			return true;
		}
		return false;
	}

	public static int contaFoglie(ArrayList<Categoria> listaCategorie) {
		int count = 0;

		for(Categoria c : listaCategorie) {
			if(c instanceof CategoriaFoglia) {
				count++;
			}
			count += contaFoglie(c.getFigli());
		}
		return count;
	}
	
	public static ArrayList<Gerarchia> getElencoGerarchie() {
		return elencoGerarchie;
	}
}