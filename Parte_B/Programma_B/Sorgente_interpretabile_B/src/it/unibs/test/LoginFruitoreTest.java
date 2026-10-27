package it.unibs.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import it.unibs.controller.LoginController;
import it.unibs.model.Comprensorio;
import it.unibs.model.ElencoComprensori;
import it.unibs.model.Utente;

class LoginFruitoreTest {

	LoginController loginController = new LoginController();
	
	@Test
	void loginFruitoreTest() {
		String simulatedInput = "nomeF\npasswordF\nnomeC\nindirizzo\n"
				+ "nomeF\npasswordF";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
		System.setIn(inputStream);
		
		ArrayList<String> comuni = new ArrayList<String>();
		Comprensorio comprensorio = new Comprensorio("nomeC", comuni);
		ElencoComprensori.aggiungiComprensorio(comprensorio);
		Utente nuovoFruitore = loginController.loginFruitore();
		
		assertEquals("nomeF", nuovoFruitore.getNome());
		assertEquals("passwordF", nuovoFruitore.getPassword());
		assertEquals("Fruitore", nuovoFruitore.getRuolo());
		assertEquals("nomeC", nuovoFruitore.getComprensorio().getNome());
		assertEquals("indirizzo", nuovoFruitore.getIndirizzo());
		
		
		Utente vecchioFruitore = loginController.loginFruitore();
		
		assertEquals("nomeF", vecchioFruitore.getNome());
		assertEquals("passwordF", vecchioFruitore.getPassword());
		assertEquals("Fruitore", vecchioFruitore.getRuolo());
		assertEquals("nomeC", vecchioFruitore.getComprensorio().getNome());
		assertEquals("indirizzo", vecchioFruitore.getIndirizzo());
		
		System.setIn(System.in);
	}

}