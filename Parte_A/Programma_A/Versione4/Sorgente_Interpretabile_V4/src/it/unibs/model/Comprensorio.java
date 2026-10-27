package it.unibs.model;

import java.util.ArrayList;

public class Comprensorio {

	private String nome;
	private ArrayList<String> comuniComprensorio = new ArrayList<>();

	public Comprensorio(String name, ArrayList<String> comuniComprensorio) {
		this.nome = name;
		this.comuniComprensorio = comuniComprensorio;
	}

	public String getNome() {
		return nome;
	}

	public ArrayList<String> getComuniComprensorio() {
		return comuniComprensorio;
	}
}