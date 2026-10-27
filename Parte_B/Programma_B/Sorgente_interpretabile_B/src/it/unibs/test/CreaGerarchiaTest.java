package it.unibs.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Test;

import it.unibs.controller.GerarchiaController;
import it.unibs.model.ElencoGerarchie;
import it.unibs.model.Gerarchia;

class CreaGerarchiaTest {

	GerarchiaController gerarchiaController = new GerarchiaController();

	@Test
	void creaGerarchiaTest() {
		String simulatedInput = "nomeRadice\ncampo\nvalDom\nn\nn\ns\nnomeFoglia\n";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
		System.setIn(inputStream);
		
		gerarchiaController.creaGerarchia();
		Gerarchia gerarchia = ElencoGerarchie.getElencoGerarchie().get(0);
		
		assertEquals("nomeRadice", gerarchia.getRadice().getNome());
		assertEquals("campo", gerarchia.getRadice().getCampo());
		assertEquals("valDom", gerarchia.getRadice().getDominio().get(0).getValore());
		assertEquals("Assente", gerarchia.getRadice().getDominio().get(0).getDescrizione());
		assertEquals("nomeFoglia", gerarchia.getRadice().getFigli().get(0).getNome());
		
		System.setIn(System.in);
	}
}