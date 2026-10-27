package it.unibs.model;

public enum RuoloUtente {
	CONFIGURATORE("Configuratore"),
	FRUITORE("Fruitore");
	
	private String descrizione;

	private RuoloUtente(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDescrizione() {
		return descrizione;
	}
}