package it.unibs.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import it.unibs.controller.GerarchiaController;
import it.unibs.model.CategoriaFoglia;
import it.unibs.model.ElencoGerarchie;
import it.unibs.model.FattoreDiConversione;
import it.unibs.model.Gerarchia;
import it.unibs.model.ValoreDominio;

class CreaFattoreDiConversioneTest {
	
	GerarchiaController gerarchiaController = new GerarchiaController();
	
	@Test
	void creaFattoreDiConversioneTest() {
		String simulatedInput = "nomeFoglia1\nnomeRadice1\nnomeFoglia2\nnomeRadice2\n1\n";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
		System.setIn(inputStream);
		
		ArrayList<ValoreDominio> dominioRadice1 = new ArrayList<>();
		Gerarchia gerarchia1 = new Gerarchia("nomeRadice1", "campo1", dominioRadice1);
		ElencoGerarchie.getElencoGerarchie().add(gerarchia1);
		
		ValoreDominio valDomFoglia1 = new ValoreDominio("valDomFoglia1", "descrizione1");
		CategoriaFoglia foglia1 = new CategoriaFoglia("nomeFoglia1", valDomFoglia1, gerarchia1.getRadice());
		
		gerarchia1.getRadice().aggiungiFiglio(foglia1);
		
		
		ArrayList<ValoreDominio> dominioRadice2 = new ArrayList<>();
		Gerarchia gerarchia2 = new Gerarchia("nomeRadice2", "campo2", dominioRadice2);
		ElencoGerarchie.getElencoGerarchie().add(gerarchia2);
		
		ValoreDominio valDomFoglia2 = new ValoreDominio("valDomFoglia2", "descrizione2");
		CategoriaFoglia foglia2 = new CategoriaFoglia("nomeFoglia2", valDomFoglia2, gerarchia2.getRadice());
		
		gerarchia2.getRadice().aggiungiFiglio(foglia2);
		
		FattoreDiConversione fdc = gerarchiaController.creaFattoreDiConversione(foglia1);
		
		assertEquals("nomeFoglia1", fdc.getC1().getNome());
		assertEquals("nomeRadice1", fdc.getC1().getRadice().getNome());
		assertEquals("nomeFoglia2", fdc.getC2().getNome());
		assertEquals("nomeRadice2", fdc.getC2().getRadice().getNome());
		assertEquals(1, fdc.getValore());
		
		System.setIn(System.in);
	}
}