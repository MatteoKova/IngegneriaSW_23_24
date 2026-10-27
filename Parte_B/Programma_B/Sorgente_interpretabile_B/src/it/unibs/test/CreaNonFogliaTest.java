package it.unibs.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import it.unibs.controller.GerarchiaController;
import it.unibs.model.CategoriaFoglia;
import it.unibs.model.CategoriaNonFoglia;
import it.unibs.model.CategoriaRadice;
import it.unibs.model.ValoreDominio;

class CreaNonFogliaTest {
	
	GerarchiaController gerarchiaController = new GerarchiaController();

	@Test
	void creaNonFogliaTest() {
		String simulatedInput = "nomeNonFoglia\ncampo\nnomeValoreFoglia\nn\nn\ns\nnomeFoglia\n";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
		System.setIn(inputStream);
		
		ArrayList<ValoreDominio> dominio = new ArrayList<>();
		CategoriaRadice radice = new CategoriaRadice("nomeRadice", "campo", dominio);
		ValoreDominio valoreDominioNonFoglia = new ValoreDominio("nomeValoreNonFoglia", "descrizione");
		CategoriaNonFoglia nonFoglia = gerarchiaController.creaNonFoglia(radice, valoreDominioNonFoglia);
		
		ValoreDominio valDomFoglia = new ValoreDominio("nomeValoreFoglia", "descrizioneFoglia");
		CategoriaFoglia foglia = new CategoriaFoglia("nomeFoglia", valDomFoglia, radice);
		nonFoglia.getFigli().add(foglia);
		
		assertEquals("nomeNonFoglia", nonFoglia.getNome());
		assertTrue(nonFoglia.getValoreDominio().verificaUguaglianza(valoreDominioNonFoglia));
		assertEquals("campo", nonFoglia.getCampo());
		assertEquals("nomeValoreFoglia", nonFoglia.getDominio().get(0).getValore());
		assertEquals("Assente", nonFoglia.getDominio().get(0).getDescrizione());
		assertEquals("nomeFoglia", nonFoglia.getFigli().get(0).getNome());
		
		System.setIn(System.in);
	}
}