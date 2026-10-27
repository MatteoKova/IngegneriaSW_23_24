package it.unibs.controller;

import it.unibs.model.Categoria;
import it.unibs.model.CategoriaFoglia;
import it.unibs.model.ElencoFattoriDiConversione;
import it.unibs.model.ElencoGerarchie;
import it.unibs.model.FattoreDiConversione;
import it.unibs.model.Gerarchia;
import it.unibs.model.ValoreDominio;
import it.unibs.view.FDCView;
import it.unibs.view.GerarchiaView;

public class GestioneViewGerarchia {

	public static void visualizzaRadici() {
		GerarchiaView.introduzioneElencoGerarchie();
		for(Gerarchia gerarchia : ElencoGerarchie.getElencoGerarchie()) {
			GerarchiaView.visualizzaNomeRadiceGerarchia(gerarchia.getRadice().getNome());
		}
	}

	public static void visualizzaValoriCampo(Categoria categoria) {
		GerarchiaView.presentazioneValoriDiCampo(categoria.getCampo());

		for(ValoreDominio valore : categoria.getDominio()) {
			Categoria categoriaFiglio = categoria.selezionaFiglioDalValore(valore);
			GerarchiaView.visualizzaNomeValore(valore.getValore(), categoriaFiglio);
		}
	}

	public static void visualizzaGerarchie() {
		for(Gerarchia gerarchia : ElencoGerarchie.getElencoGerarchie()) {
			GerarchiaView.visualizzaNomeRadiceGerarchia(gerarchia.getRadice().getNome());
			visualizzaFigliCategoria(gerarchia.getRadice());
		}
	}

	public static void visualizzaFigliCategoria(Categoria categoriaPadre) {
		for(Categoria categoria : categoriaPadre.getFigli()) {
			GerarchiaView.visualizzaNomeFiglioCategoria(categoriaPadre.getNome(), categoria);
			visualizzaFigliCategoria(categoria);
		}
	}

	public static void visualizzaFattoriDiConversione(CategoriaFoglia foglia) {
		if(foglia == null) {
			GerarchiaView.msgFogliaNonTrovata();
			return;
		}
		for(FattoreDiConversione fattore : ElencoFattoriDiConversione.getElencoFattoriDiConversione()) {
			if(fattore.getC1().verificaUguaglianzaFoglie(foglia)) {
				FDCView.visualizzaFattoreDiConversione(fattore);
			}
		}
	}
}