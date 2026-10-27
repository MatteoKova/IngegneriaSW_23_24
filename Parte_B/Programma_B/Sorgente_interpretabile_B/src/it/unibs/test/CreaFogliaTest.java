package it.unibs.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import it.unibs.controller.GerarchiaController;
import it.unibs.model.CategoriaFoglia;
import it.unibs.model.CategoriaRadice;
import it.unibs.model.ElencoFattoriDiConversione;
import it.unibs.model.ElencoGerarchie;
import it.unibs.model.FattoreDiConversione;
import it.unibs.model.Gerarchia;
import it.unibs.model.ValoreDominio;

class CreaFogliaTest {
	
	GerarchiaController gerarchiaController = new GerarchiaController();

	@Test
	void creaFogliaTest() {
		String simulatedInput = "nomeFogliaNuova\nnomeFogliaNuova\nnomeRadice\nnomeFogliaVecchia\nnomeRadice\n1\n";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
		System.setIn(inputStream);
		
		ArrayList<ValoreDominio> dominio = new ArrayList<>();
		Gerarchia gerarchia = new Gerarchia("nomeRadice", "campo", dominio);
		CategoriaRadice radice = gerarchia.getRadice();
		
		ValoreDominio valoreDominioFogliaVecchia = new ValoreDominio("nomeValoreFogliaVecchia", "Assente");
		dominio.add(valoreDominioFogliaVecchia);
		CategoriaFoglia fogliaVecchia = new CategoriaFoglia("nomeFogliaVecchia", valoreDominioFogliaVecchia, radice);
		
		radice.getFigli().add(fogliaVecchia);
		ElencoGerarchie.aggiungiGerarchia(gerarchia);
		
		
		ValoreDominio valoreDominioFogliaNuova = new ValoreDominio("nomeValoreFogliaNuova", "Assente");
		dominio.add(valoreDominioFogliaNuova);
		CategoriaFoglia fogliaNuova = gerarchiaController.creaFoglia(radice, valoreDominioFogliaNuova);
		
		FattoreDiConversione fdc = ElencoFattoriDiConversione.trovaFDC(fogliaNuova, fogliaVecchia);
		
		assertEquals("nomeFogliaNuova", fogliaNuova.getNome());
		assertEquals("nomeRadice", fogliaNuova.getRadice().getNome());
		assertTrue(fogliaNuova.getValoreDominio().verificaUguaglianza(valoreDominioFogliaNuova));
		assertTrue(fogliaNuova.verificaUguaglianzaFoglie(fdc.getC1()));
		
		System.setIn(System.in);
	}
}