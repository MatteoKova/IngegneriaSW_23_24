package it.unibs.model;

public class ValoreDominio {

	private String valore;
	private String descrizione;

	public ValoreDominio(String valore, String descrizione) {
		super();
		this.valore = valore;
		this.descrizione = descrizione;
	}

	public String getValore() {
		return valore;
	}

	public String getDescrizione() {
		return descrizione;
	}
}