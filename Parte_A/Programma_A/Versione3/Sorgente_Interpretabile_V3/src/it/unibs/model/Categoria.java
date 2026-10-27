package it.unibs.model;

import java.util.ArrayList;

public interface Categoria {
	
	default public ArrayList<String> getNomiGerarchia() {
		ArrayList<String> nomiGerarchia = new ArrayList<>();
		for(Categoria c : this.getFigli()) {
			nomiGerarchia.add(c.getNome());
			c.getNomiGerarchia();
		}
		return nomiGerarchia;
	}
	
	default public Categoria selezionaFiglioDalValore(ValoreDominio valoreScelto) {
		for(Categoria categoriaFiglio : getFigli()) {
			if(categoriaFiglio.getValoreDominio().verificaUguaglianza(valoreScelto)) {
				return categoriaFiglio;
			}
		}
		return null;
	}

	public String getTipo();

	public ValoreDominio getValoreDominio();

	public ArrayList<ValoreDominio> getDominio();

	public ArrayList<Categoria> getFigli();

	public String getNome();
	
	public CategoriaRadice getRadice();
	
	public String getCampo();
}