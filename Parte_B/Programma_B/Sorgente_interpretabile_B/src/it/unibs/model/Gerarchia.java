package it.unibs.model;

import java.util.ArrayList;

public class Gerarchia {
	
	private CategoriaRadice radice;

	public Gerarchia(String nome, String campo, ArrayList<ValoreDominio> dominio) {
		this.radice = new CategoriaRadice(nome, campo, dominio);
	}

	public Gerarchia(String nome, String campo, ArrayList<ValoreDominio> dominio, ArrayList<Categoria> figli) {
		this.radice = new CategoriaRadice(nome, campo, dominio, figli);
	}

	public static CategoriaFoglia trovaFoglia(Categoria categoriaPadre, String nomeFoglia) {
		CategoriaFoglia foglia;
		
		for(Categoria categoria : categoriaPadre.getFigli()) {
			if(categoria.getTipo().equals(TipoCategoria.FOGLIA.getDescrizione()) && categoria.getNome().equals(nomeFoglia)) {
				return (CategoriaFoglia)categoria;
			}
			foglia = trovaFoglia(categoria, nomeFoglia);
			
			if(foglia != null) {
				return foglia;
			}
		}
		return null;
	}

	public CategoriaRadice getRadice() {
		return radice;
	}
}