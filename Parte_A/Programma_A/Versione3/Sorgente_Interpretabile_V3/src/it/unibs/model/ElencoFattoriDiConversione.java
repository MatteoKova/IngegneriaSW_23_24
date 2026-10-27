package it.unibs.model;

import java.util.ArrayList;

public class ElencoFattoriDiConversione {

	private static final double VALORE_MAX_FDC = 2.0;
	private static final double VALORE_MIN_FDC = 0.5;
	
	private static ArrayList<FattoreDiConversione> elencoFattoriDiConversione = new ArrayList<>();

	public static void aggiungiFDC(FattoreDiConversione fdC) {
		elencoFattoriDiConversione.add(fdC);
		elencoFattoriDiConversione.add(fdC.creaSimmetrico());
	}

	public static boolean verificaEsistenzaFDC(FattoreDiConversione fdC) {
		for(FattoreDiConversione f : elencoFattoriDiConversione) {
			if(fdC.verificaUguaglianzaFattoriDiConversione(f)) {
				return true;
			}
		}
		return false;
	}

	public static void creaFDC_Deducibili(FattoreDiConversione fdcNuovo) {
		FattoreDiConversione fdcDedotto;
		double valore;

		for(int i = 0; i < elencoFattoriDiConversione.size(); i++) {
			valore = fdcNuovo.getValore() * elencoFattoriDiConversione.get(i).getValore();
			valore = limitaValoreFDC(valore);

			fdcDedotto = new FattoreDiConversione(fdcNuovo.getC1(), elencoFattoriDiConversione.get(i).getC2(), valore);

			if(elencoFattoriDiConversione.get(i).getC1().verificaUguaglianzaFoglie(fdcNuovo.getC2()) &&
					!verificaEsistenzaFDC(fdcDedotto) && !fdcDedotto.fDCSullaStessaFoglia()) {
				aggiungiFDC(fdcDedotto);
			}
		}
	}

	private static double limitaValoreFDC(double valore) {
		if(valore < VALORE_MIN_FDC) {
			valore = VALORE_MIN_FDC;
		}
		if(valore > VALORE_MAX_FDC) {
			valore = VALORE_MAX_FDC;
		}
		return valore;
	}
	
	public static FattoreDiConversione trovaFDC(CategoriaFoglia c1, CategoriaFoglia c2) {
		for(FattoreDiConversione fdc : elencoFattoriDiConversione) {
			if(fdc.getC1().verificaUguaglianzaFoglie(c1) && fdc.getC2().verificaUguaglianzaFoglie(c2)) {
				return fdc;
			}
		}
		return null;
	}

	public static ArrayList<FattoreDiConversione> getElencoFattoriDiConversione() {
		return elencoFattoriDiConversione;
	}
}