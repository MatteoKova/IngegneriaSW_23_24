package it.unibs.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import it.unibs.controller.ComprensorioController;
import it.unibs.model.Comprensorio;
import it.unibs.model.ElencoComprensori;

class CreaComprensorioTest {
	
	ComprensorioController comprensorioController = new ComprensorioController();

	@Test
	void creaComprensorioTest() {
		String simulatedInput = "comprensorio\ncomune1\ns\ncomune2\nn\n" + // (1), (4)
				"comprensorio\ncomprensorio2\ncomune1\ns\ncomune1\ns\ncomune3\nn\n" + // (2), (5)
				"\ncomprensorio3\n\ncomune1\nn\n"; // (3), (6)
		ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
		System.setIn(inputStream);

		comprensorioController.creaComprensorio();
		comprensorioController.creaComprensorio();
		comprensorioController.creaComprensorio();

		ArrayList<Comprensorio> elencoComprensori = ElencoComprensori.getElencoComprensori();

		ArrayList<String> comuni = new ArrayList<>();
		comuni.add("comune1");
		comuni.add("comune2");

		ArrayList<String> comuni2 = new ArrayList<>();
		comuni2.add("comune1");
		comuni2.add("comune3");

		ArrayList<String> comuni3 = new ArrayList<>();
		comuni3.add("comune1");

		assertEquals(elencoComprensori.get(0).getNome(), "comprensorio");// (1)
		assertEquals(elencoComprensori.get(0).getComuniComprensorio(), comuni);// (4)

		assertEquals(elencoComprensori.get(1).getNome(), "comprensorio2");// (2)
		assertEquals(elencoComprensori.get(1).getComuniComprensorio(), comuni2);// (5)

		assertEquals(elencoComprensori.get(2).getNome(), "comprensorio3");// (3)
		assertEquals(elencoComprensori.get(2).getComuniComprensorio(), comuni3);// (6)

		System.setIn(System.in);
	}
}