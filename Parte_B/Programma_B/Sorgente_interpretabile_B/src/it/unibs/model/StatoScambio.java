package it.unibs.model;

public enum StatoScambio {
	APERTO("Aperto"),
	CHIUSO("Chiuso"),
	RITIRATO("Ritirato");
	
	private String descrizione;

	private StatoScambio(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDescrizione() {
		return descrizione;
	}
}
