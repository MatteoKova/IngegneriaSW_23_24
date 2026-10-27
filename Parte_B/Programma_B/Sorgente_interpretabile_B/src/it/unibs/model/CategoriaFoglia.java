package it.unibs.model;

import java.util.ArrayList;

public class CategoriaFoglia implements Categoria {
	
	private String nome;
	private ValoreDominio valoreDominio;
	private CategoriaRadice radice;
	private final String tipo = TipoCategoria.FOGLIA.getDescrizione();
	private final String campo = null;
	private final ArrayList<ValoreDominio> dominio = new ArrayList<>();
	private final ArrayList<Categoria> figli = new ArrayList<>();

	public CategoriaFoglia(String nome, ValoreDominio valore, CategoriaRadice radice) {
		this.nome = nome;
		this.valoreDominio = valore;
		this.radice = radice;
	}

	public boolean verificaUguaglianzaFoglie(CategoriaFoglia foglia) {
		if(this.getNome().equals(foglia.getNome()) && this.getRadice().getNome().equals(foglia.getRadice().getNome())) {
			return true;
		}
		return false;
	}
	
	public boolean haStessaRadice(CategoriaFoglia fogliaNuova) {
		if(this.getRadice().getNome().equals(fogliaNuova.getRadice().getNome())) {
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<String> getNomiGerarchia() {
		ArrayList<String> nomiGerarchia = new ArrayList<>();
		nomiGerarchia.add(nome);
		return nomiGerarchia;
	}

	@Override
	public Categoria selezionaFiglioDalValore(ValoreDominio valoreScelto) {
		return null;
	}

	@Override
	public int contaFoglieCategoria() {
		return 1;
	}

	public String getNome() {
		return nome;
	}

	public ValoreDominio getValoreDominio() {
		return valoreDominio;
	}

	public CategoriaRadice getRadice() {
		return radice;
	}
	
	public String getTipo() {
		return tipo;
	}

	public ArrayList<ValoreDominio> getDominio() {
		return dominio;
	}

	public ArrayList<Categoria> getFigli() {
		return figli;
	}

	public String getCampo() {
		return campo;
	}
}