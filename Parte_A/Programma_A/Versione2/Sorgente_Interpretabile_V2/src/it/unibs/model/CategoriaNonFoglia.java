package it.unibs.model;

import java.util.ArrayList;

public class CategoriaNonFoglia implements Categoria {

	private static final String NON_FOGLIA = "NonFoglia";
	
	private String nome;
	private ValoreDominio valoreDominio;
	private CategoriaRadice radice;
	private final String tipo = NON_FOGLIA;
	private String campo;
	private ArrayList<ValoreDominio> dominio = new ArrayList<>();
	private ArrayList<Categoria> figli = new ArrayList<>();	

	public CategoriaNonFoglia(String nome, ValoreDominio valore, CategoriaRadice radice,
			String campo, ArrayList<ValoreDominio> dominio) {
		this.nome = nome;
		this.valoreDominio = valore;
		this.radice = radice;
		this.campo = campo;
		this.dominio = dominio;
	}

	public CategoriaNonFoglia(String nome, ValoreDominio valore, CategoriaRadice radice,
			String campo, ArrayList<ValoreDominio> dominio, ArrayList<Categoria> figli) {
		this.nome = nome;
		this.valoreDominio = valore;
		this.radice = radice;
		this.campo = campo;
		this.dominio = dominio;
		this.figli = figli;
	}

	public String getNome() {
		return nome;
	}

	public String getTipo() {
		return tipo;
	}

	public String getCampo() {
		return campo;
	}

	public ArrayList<ValoreDominio> getDominio() {
		return dominio;
	}

	public ValoreDominio getValoreDominio() {
		return valoreDominio;
	}

	public ArrayList<Categoria> getFigli() {
		return figli;
	}

	public CategoriaRadice getRadice() {
		return radice;
	}
}