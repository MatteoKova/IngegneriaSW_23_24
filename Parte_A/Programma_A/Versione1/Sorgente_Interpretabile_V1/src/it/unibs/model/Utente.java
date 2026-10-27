package it.unibs.model;

public class Utente {

	private String nome;
	private String password;
	private String ruolo;

	public Utente(String nome, String password, String ruolo) {
		this.nome = nome;
		this.password = password;
		this.ruolo = ruolo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
}