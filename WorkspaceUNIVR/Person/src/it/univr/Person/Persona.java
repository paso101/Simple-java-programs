package it.univr.Person;

public class Persona {
	private String nome;
	private int eta;
	
	public Persona(String n, int e) {
		this.nome = n;
		this.eta = e;
	}

	protected String getNome() {
		return this.nome;
	}
	
	protected int getEta() {
		return this.eta;
	}
	
	@Override
	public String toString() {
		String s = this.nome + " " + this.eta;
		return s;
	}
}
