package it.unibs.model;

import java.util.ArrayList;

public class CategoriaNonFoglia implements Categoria {
	
	private String nome;
	private ValoreDominio valoreDominio;
	private CategoriaRadice radice;
	private final String tipo = TipoCategoria.NON_FOGLIA.getDescrizione();
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

	@Override
	public ArrayList<String> getNomiGerarchia() {
		ArrayList<String> nomiGerarchia = new ArrayList<>();
		nomiGerarchia.add(nome);
		for(Categoria c : this.getFigli()) {
			nomiGerarchia.addAll(c.getNomiGerarchia());
		}
		return nomiGerarchia;
	}

	@Override
	public Categoria selezionaFiglioDalValore(ValoreDominio valoreScelto) {
		for(Categoria categoriaFiglio : getFigli()) {
			if(categoriaFiglio.getValoreDominio().verificaUguaglianza(valoreScelto)) {
				return categoriaFiglio;
			}
		}
		return null;
	}
	
	@Override
	public int contaFoglieCategoria() {
		int count = 0;
		for(Categoria c : this.getFigli()) {
			count += c.contaFoglieCategoria();
		}
		return count;
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