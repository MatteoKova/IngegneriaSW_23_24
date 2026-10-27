package it.unibs.model;

public enum TipoCategoria {
	FOGLIA("Foglia"),
	NON_FOGLIA("NonFoglia"),
	RADICE("Radice");
	
	private String descrizione;

	private TipoCategoria(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDescrizione() {
		return descrizione;
	}
}
