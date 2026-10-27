package it.unibs.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import it.unibs.controller.ScambioController;
import it.unibs.model.CategoriaFoglia;
import it.unibs.model.ElencoFattoriDiConversione;
import it.unibs.model.ElencoGerarchie;
import it.unibs.model.ElencoScambi;
import it.unibs.model.FattoreDiConversione;
import it.unibs.model.Gerarchia;
import it.unibs.model.Scambio;
import it.unibs.model.Utente;
import it.unibs.model.ValoreDominio;

class CreaScambioTest {

	@Test
	void creaScambioTest() {
		String simulatedInput = "nomeRadice1\nvalDomFoglia1\n10\nnomeRadice2\nvalDomFoglia2\ns\n";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
		System.setIn(inputStream);
		
		ValoreDominio valDomFoglia1 = new ValoreDominio("valDomFoglia1", "descrizione1");
		
		ArrayList<ValoreDominio> dominioRadice1 = new ArrayList<>();
		dominioRadice1.add(valDomFoglia1);
		Gerarchia gerarchia1 = new Gerarchia("nomeRadice1", "campo1", dominioRadice1);
		ElencoGerarchie.getElencoGerarchie().add(gerarchia1);
		
		CategoriaFoglia foglia1 = new CategoriaFoglia("nomeFoglia1", valDomFoglia1, gerarchia1.getRadice());
		
		gerarchia1.getRadice().aggiungiFiglio(foglia1);
		
		
		ValoreDominio valDomFoglia2 = new ValoreDominio("valDomFoglia2", "descrizioneFoglia2");
		
		ArrayList<ValoreDominio> dominioRadice2 = new ArrayList<>();
		dominioRadice2.add(valDomFoglia2);
		Gerarchia gerarchia2 = new Gerarchia("nomeRadice2", "campo2", dominioRadice2);
		ElencoGerarchie.getElencoGerarchie().add(gerarchia2);
		
		CategoriaFoglia foglia2 = new CategoriaFoglia("nomeFoglia2", valDomFoglia2, gerarchia2.getRadice());
		
		gerarchia2.getRadice().aggiungiFiglio(foglia2);
		
		FattoreDiConversione fdc = new FattoreDiConversione(foglia1, foglia2, 1);
		ElencoFattoriDiConversione.getElencoFattoriDiConversione().add(fdc);
		
		Utente conf = new Utente("nomeConf", "passConf", "Configuratore");
		
		ScambioController.creaScambio(conf);
		Scambio scambio = ElencoScambi.getElencoScambi().get(0);
		
		assertEquals("nomeFoglia1", scambio.getFogliaRichiesta().getNome());
		assertEquals("nomeRadice1", scambio.getFogliaRichiesta().getRadice().getNome());
		assertEquals("nomeFoglia2", scambio.getFogliaOfferta().getNome());
		assertEquals("nomeRadice2", scambio.getFogliaOfferta().getRadice().getNome());
		assertEquals(10, scambio.getOreRichiesta());
		assertEquals("nomeConf", scambio.getUtente().getNome());
		assertEquals("passConf", scambio.getUtente().getPassword());
		assertEquals("Configuratore", scambio.getUtente().getRuolo());
		
		System.setIn(System.in);
	}
}