package it.unibs.controller;

import java.util.ArrayList;

import it.unibs.model.Categoria;
import it.unibs.model.CategoriaFoglia;
import it.unibs.model.CategoriaNonFoglia;
import it.unibs.model.CategoriaRadice;
import it.unibs.model.ElencoFattoriDiConversione;
import it.unibs.model.ElencoGerarchie;
import it.unibs.model.FattoreDiConversione;
import it.unibs.model.Gerarchia;
import it.unibs.model.ValoreDominio;
import it.unibs.view.FDCView;
import it.unibs.view.GerarchiaView;

public class GerarchiaController {

	public void creaGerarchia() {
		String nomeRadice = GerarchiaView.inserisciNomeRadiceGerarchia();

		while(ElencoGerarchie.verificaEsistenzaRadice(nomeRadice)) {
			GerarchiaView.msgRadiceGiaPresente();
			nomeRadice = GerarchiaView.inserisciNomeRadiceGerarchia();
		}

		String campo = GerarchiaView.inserisciCampo();
		ArrayList<ValoreDominio> dominio = creaDominio();
		Gerarchia nuovaGerarchia = ElencoGerarchie.aggiungiGerarchia(nomeRadice, campo, dominio);

		creaFigliCategoria(nuovaGerarchia.getRadice());
	}

	private void creaFigliCategoria(Categoria categoriaPadre) {
		for(ValoreDominio valore : categoriaPadre.getDominio()) {
			if(GerarchiaView.richiestaAggiuntaCategoriaFoglia(valore.getValore())) {
				creaFoglia(categoriaPadre, valore);			
			}
			else {
				CategoriaNonFoglia nonFoglia = creaNonFoglia(categoriaPadre, valore);

				creaFigliCategoria(nonFoglia);
			}
		}		
	}

	public CategoriaFoglia creaFoglia(Categoria padre, ValoreDominio valore) {
		ArrayList<String> elencoNomiGerarchia = padre.getRadice().getNomiGerarchia();

		String nomeFoglia = GerarchiaView.inserisciNomeFogliaGerarchia();
		
		while(elencoNomiGerarchia.contains(nomeFoglia)) {
			GerarchiaView.msgCategoriaGiaEsistente();
			nomeFoglia = GerarchiaView.inserisciNomeFogliaGerarchia();
		}

		CategoriaFoglia foglia = new CategoriaFoglia(nomeFoglia, valore, padre.getRadice());
		padre.getFigli().add(foglia);

		if(ElencoGerarchie.dueOpiuFoglie()) {
			FattoreDiConversione fdcNuovo = creaFattoreDiConversione(foglia);
			ElencoFattoriDiConversione.creaFDC_Deducibili(fdcNuovo);
			ElencoFattoriDiConversione.creaFDC_Deducibili(fdcNuovo.creaSimmetrico());
		}
		return foglia;
	}
	
	public FattoreDiConversione creaFattoreDiConversione(CategoriaFoglia fogliaNuova) {		
		FattoreDiConversione fdcNuovo;
		CategoriaFoglia f1, f2;
		double valore;

		do {
			GerarchiaController.visualizzaGerarchie();
			
			do {
				FDCView.inserimentoPrimaFogliaPerFDC();
				f1 = selezionaCategoriaFogliaConRadiceFissata(fogliaNuova.getRadice());
			} while(f1 == null);

			do {
				FDCView.inserimentoSecondaFogliaPerFDC();
				f2 = selezionaCategoriaFoglia();
			} while(f2 == null);

			valore = FDCView.inserisciValoreFDC();
			fdcNuovo = new FattoreDiConversione(f1, f2, valore);
		} while(ElencoFattoriDiConversione.verificaEsistenzaFDC(fdcNuovo) || fdcNuovo.fDCSullaStessaFoglia());

		ElencoFattoriDiConversione.aggiungiFDC(fdcNuovo);
		return fdcNuovo;
	}

	public CategoriaNonFoglia creaNonFoglia(Categoria padre, ValoreDominio valore) {
		CategoriaNonFoglia nonFoglia;
		ArrayList<String> elencoNomiGerarchia = padre.getRadice().getNomiGerarchia();

		String nomeNonFoglia = GerarchiaView.inserisciNomeNonFogliaGerarchia();
		
		while(elencoNomiGerarchia.contains(nomeNonFoglia)) {
			GerarchiaView.msgCategoriaGiaEsistente();
			nomeNonFoglia = GerarchiaView.inserisciNomeNonFogliaGerarchia();
		}

		String campo = GerarchiaView.inserisciCampo();
		ArrayList<ValoreDominio> dominio = creaDominio();

		nonFoglia = new CategoriaNonFoglia(nomeNonFoglia, valore, padre.getRadice(), campo, dominio);
		padre.getFigli().add(nonFoglia);
		return nonFoglia;
	}

	public ArrayList<ValoreDominio> creaDominio() {
		ArrayList<ValoreDominio> dominio = new ArrayList<>();
		String nomeValore;
		String descrizione;
		ArrayList<String> listaNomiValori = new ArrayList<>();

		do {
			descrizione = "Assente";
			nomeValore = GerarchiaView.inserisciNomeValoreDominio();

			while(listaNomiValori.contains(nomeValore)) {
				GerarchiaView.msgEsisteGiaNomeValoreDominio();
				nomeValore = GerarchiaView.inserisciNomeValoreDominio();
			}

			listaNomiValori.add(nomeValore);

			if(GerarchiaView.richiestaDescrizioneValoreDominio()) {
				descrizione = GerarchiaView.inserisciDescrizioneValoreDominio();	
			}
			dominio.add(new ValoreDominio(nomeValore, descrizione));
		} while(GerarchiaView.richiestaNomeValoreDominio());

		return dominio;
	}

	public Categoria navigaGerarchiaFinoAFoglia() {
		if(ElencoGerarchie.getElencoGerarchie().size() == 0) {
			GerarchiaView.msgGerarchieAssenti();
			return null;
		}
		CategoriaRadice radice = selezionaRadice();
		return navigaStrutturaGerarchia(radice);
	}

	public Categoria navigaStrutturaGerarchia(Categoria padre) {		
		visualizzaValoriCampo(padre);
		String nomeValoreScelto = GerarchiaView.inserisciValoreScelto();
		ValoreDominio valoreScelto = ValoreDominio.selezionaValore(nomeValoreScelto, padre.getDominio());

		while(valoreScelto == null) {
			GerarchiaView.msgValoreNonTrovato();
			nomeValoreScelto = GerarchiaView.inserisciValoreScelto();
			valoreScelto = ValoreDominio.selezionaValore(nomeValoreScelto, padre.getDominio());
		}

		Categoria categoriaScelta = padre.selezionaFiglioDalValore(valoreScelto);

		if(categoriaScelta instanceof CategoriaFoglia) {
			GerarchiaView.visualizzaCategoria(categoriaScelta.getNome(), categoriaScelta.getTipo());
			return categoriaScelta;
		}
		else {
			GerarchiaView.visualizzaCategoria(categoriaScelta.getNome(), categoriaScelta.getTipo());
			return navigaStrutturaGerarchia(categoriaScelta);
		}
	}

	public CategoriaFoglia selezionaCategoriaFogliaConRadiceFissata(CategoriaRadice radice) {
		String nomeFoglia = GerarchiaView.inserisciNomeFogliaRicerca();
		String nomeRadice = GerarchiaView.inserisciNomeRadiceRicerca();

		CategoriaFoglia foglia = ElencoGerarchie.selezionaFoglia(nomeFoglia, nomeRadice);
		if(foglia == null){
			GerarchiaView.msgFogliaNonTrovata();
			return null;
		}

		if(!radice.getNome().equals(nomeRadice)) {
			FDCView.msgFogliaDiGerarchiaSbagliata(radice.getNome());
			return null;
		}
		else return foglia;
	}

	public CategoriaFoglia selezionaCategoriaFogliaPerFDC() {
		String nomeFoglia = FDCView.inserisciFogliaPerFDC();
		String nomeRadice = FDCView.inserisciRadicePerFDC();
		return ElencoGerarchie.selezionaFoglia(nomeFoglia, nomeRadice);
	}

	public CategoriaFoglia selezionaCategoriaFoglia() {
		String nomeFoglia = GerarchiaView.inserisciNomeFogliaRicerca();
		String nomeRadice = GerarchiaView.inserisciNomeRadiceRicerca();
		CategoriaFoglia foglia = ElencoGerarchie.selezionaFoglia(nomeFoglia, nomeRadice);

		if(foglia == null) {
			GerarchiaView.msgFogliaNonTrovata();
			return null;
		}
		else return foglia;
	}

	public CategoriaRadice selezionaRadice() {
		visualizzaRadici();
		String nomeRadice = GerarchiaView.inserisciNomeRadiceRicerca();

		while(!ElencoGerarchie.verificaEsistenzaRadice(nomeRadice)) {
			GerarchiaView.msgRadiceNonEsiste();
			nomeRadice = GerarchiaView.inserisciNomeRadiceRicerca();
		}
		return ElencoGerarchie.trovaRadice(nomeRadice);
	}

	public void visualizzaRadici() {
		GerarchiaView.introduzioneElencoGerarchie();
		for(Gerarchia gerarchia : ElencoGerarchie.getElencoGerarchie()) {
			GerarchiaView.visualizzaNomeRadiceGerarchia(gerarchia.getRadice().getNome());
		}
	}

	public static void visualizzaValoriCampo(Categoria categoria) {
		GerarchiaView.presentazioneValoriDiCampo(categoria.getCampo());

		for(ValoreDominio valore : categoria.getDominio()) {
			Categoria categoriaFiglio = categoria.selezionaFiglioDalValore(valore);
			GerarchiaView.visualizzaNomeValore(valore.getValore(), categoriaFiglio.getNome(), categoriaFiglio.getTipo());
		}
	}

	public static void visualizzaGerarchie() {
		for(Gerarchia gerarchia : ElencoGerarchie.getElencoGerarchie()) {
			GerarchiaView.visualizzaNomeRadiceGerarchia(gerarchia.getRadice().getNome());
			visualizzaFigliCategoria(gerarchia.getRadice());
		}
	}

	public static void visualizzaFigliCategoria(Categoria categoria) {
		for(Categoria c : categoria.getFigli()) {
			GerarchiaView.visualizzaNomeFiglioCategoria(categoria.getNome(), c.getNome(), c.getTipo());
			visualizzaFigliCategoria(c);
		}
	}

	public static void visualizzaFattoriDiConversione(CategoriaFoglia foglia) {
		if(foglia == null) {
			GerarchiaView.msgFogliaNonTrovata();
			return;
		}
		for(FattoreDiConversione fattore : ElencoFattoriDiConversione.getElencoFattoriDiConversione()) {
			if(fattore.getC1().verificaUguaglianzaFoglie(foglia)) {
				FDCView.visualizzaFattoreDiConversione(fattore.getC1().getNome(), fattore.getC1().getRadice().getNome(),
						fattore.getC2().getNome(), fattore.getC2().getRadice().getNome(), fattore.getValore());
			}
		}
	}
}