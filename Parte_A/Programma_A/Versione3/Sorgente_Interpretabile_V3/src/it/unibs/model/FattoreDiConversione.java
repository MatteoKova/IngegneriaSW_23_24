package it.unibs.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FattoreDiConversione {

	private CategoriaFoglia c1;
	private CategoriaFoglia c2;
	private double valore;

	public FattoreDiConversione(CategoriaFoglia c1, CategoriaFoglia c2, double valore) {
		this.c1 = c1;
		this.c2 = c2;
		this.valore = new BigDecimal(valore).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
	}

	public FattoreDiConversione creaSimmetrico() {
		return new FattoreDiConversione(getC2(), getC1(), 1/getValore());
	}

	public boolean verificaUguaglianzaFattoriDiConversione(FattoreDiConversione fdC) {
		CategoriaFoglia c12 = fdC.getC1();
		CategoriaFoglia c22 = fdC.getC2();

		if(!(c12.getRadice().getNome().equals(c1.getRadice().getNome())
				&& c22.getRadice().getNome().equals(c2.getRadice().getNome()))) {
			return false;
		}
		if(!(c12.getNome().equals(c1.getNome()) && c22.getNome().equals(c2.getNome()))) {
			return false;
		}
		return true;
	}

	public boolean fDCSullaStessaFoglia() {
		if(c1.verificaUguaglianzaFoglie(c2)){
			return true;
		}
		return false;
	}
	
	public CategoriaFoglia getC1() {
		return c1;
	}

	public CategoriaFoglia getC2() {
		return c2;
	}

	public double getValore() {
		return valore;
	}
}