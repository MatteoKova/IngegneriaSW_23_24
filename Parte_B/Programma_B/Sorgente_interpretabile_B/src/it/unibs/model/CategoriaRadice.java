package it.unibs.model;

import java.util.ArrayList;

public class CategoriaRadice implements Categoria {

	private static final String VALORE_DELL_ELEMENTO_RADICE = "Valore dell'elemento radice";
	
	private String nome;
	private final ValoreDominio valore = new ValoreDominio(TipoCategoria.RADICE.getDescrizione(),
			VALORE_DELL_ELEMENTO_RADICE);
	private final String tipo = TipoCategoria.RADICE.getDescrizione();
	private String campo;
	private ArrayList<ValoreDominio> dominio = new ArrayList<>();
	private ArrayList<Categoria> figli = new ArrayList<>();

	public CategoriaRadice(String nome, String campo, ArrayList<ValoreDominio> dominio) {
		this.nome = nome;
		this.campo = campo;
		this.dominio = dominio;
	}

	public CategoriaRadice(String nome, String campo, ArrayList<ValoreDominio> dominio, ArrayList<Categoria> figli) {
		this.nome = nome;
		this.campo = campo;
		this.dominio = dominio;
		this.figli = figli;
	}

	public void aggiungiFiglio(Categoria nuovoFiglio) {
		figli.add(nuovoFiglio);
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

	public ValoreDominio getValoreDominio() {
		return valore;
	}

	public ArrayList<ValoreDominio> getDominio() {
		return dominio;
	}

	public ArrayList<Categoria> getFigli() {
		return figli;
	}

	public CategoriaRadice getRadice() {
		return this;
	}
}