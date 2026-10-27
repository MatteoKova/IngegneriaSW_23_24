package it.unibs.model;

import java.util.ArrayList;

public class ValoreDominio {

	private String valore;
	private String descrizione;

	public ValoreDominio(String valore, String descrizione) {
		this.valore = valore;
		this.descrizione = descrizione;
	}

	public static ValoreDominio selezionaValore(String valoreScelto, ArrayList<ValoreDominio> arrayValori) {
		for(ValoreDominio valoreEsistente : arrayValori) {
			if(valoreScelto.equals(valoreEsistente.getValore())){
				return valoreEsistente;
			}
		}
		return null;
	}
	
	public boolean verificaUguaglianza(ValoreDominio valoreScelto) {
		if(valore.equals(valoreScelto.getValore()) && descrizione.equals(valoreScelto.getDescrizione())) {
			return true;
		}
		return false;
	}

	public String getValore() {
		return valore;
	}

	public String getDescrizione() {
		return descrizione;
	}
}