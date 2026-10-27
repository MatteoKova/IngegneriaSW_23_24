package it.unibs.model;

public class Scambio {
	
	private CategoriaFoglia fogliaRichiesta;
	private CategoriaFoglia fogliaOfferta;
	private int oreRichiesta;
	private int oreOfferta;
	private String stato;
	private Utente utente;
	
	public Scambio(CategoriaFoglia fogliaRichiesta, CategoriaFoglia fogliaOfferta,
			int oreRichiesta, int oreOfferta, String stato, Utente utente) {
		this.fogliaRichiesta = fogliaRichiesta;
		this.fogliaOfferta = fogliaOfferta;
		this.oreRichiesta = oreRichiesta;
		this.oreOfferta = oreOfferta;
		this.stato = stato;
		this.utente = utente;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public CategoriaFoglia getFogliaRichiesta() {
		return fogliaRichiesta;
	}

	public CategoriaFoglia getFogliaOfferta() {
		return fogliaOfferta;
	}

	public int getOreRichiesta() {
		return oreRichiesta;
	}

	public int getOreOfferta() {
		return oreOfferta;
	}

	public Utente getUtente() {
		return utente;
	}
}