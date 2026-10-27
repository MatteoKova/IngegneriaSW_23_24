package it.unibs.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Test;

import it.unibs.controller.LoginController;

import it.unibs.model.Utente;

class LoginConfiguratoreTest {
	
	LoginController loginController = new LoginController();
	
	@Test
	void loginConfiguratoreTest() {
		String simulatedInput = "utente\npassword\nnomeC\npasswordC\n"
				+ "nomeC\npasswordC\n";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
		System.setIn(inputStream);
		
		Utente nuovoConfiguratore = loginController.loginConfiguratore();
		
		assertEquals("nomeC", nuovoConfiguratore.getNome());
		assertEquals("passwordC", nuovoConfiguratore.getPassword());
		assertEquals("Configuratore", nuovoConfiguratore.getRuolo());

		
		Utente vecchioConfiguratore = loginController.loginConfiguratore();
		
		assertEquals("nomeC", vecchioConfiguratore.getNome());
		assertEquals("passwordC", vecchioConfiguratore.getPassword());
		assertEquals("Configuratore", vecchioConfiguratore.getRuolo());
		
		System.setIn(System.in);
	}
}