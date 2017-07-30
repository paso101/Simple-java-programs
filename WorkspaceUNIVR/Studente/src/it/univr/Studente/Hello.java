package it.univr.Studente;


public class Hello {
	
	private String nome;
	private String cognome;
	private int eta;
	private char letteraPreferita;
	
	public Hello() {
		
		nome = "Lara";
		cognome = "Scarpari";
		eta = 20;
		letteraPreferita = 'L';
		
	}
	
	public Hello(String n, String c, int e, char l) {
		
		this.nome = n;
		this.cognome = c;
		this.eta = e;
		this.letteraPreferita = l;
		
	}
	
	public void printPersona() {
		
		System.out.println(this.nome);
		System.out.println(this.cognome);
		System.out.println(this.eta);
		System.out.println(this.letteraPreferita);
		
	}
	
	public void trasforma(Hello altro) {
		
		this.nome = altro.nome;
		this.cognome = altro.cognome;
		this.eta = altro.eta;
		this.letteraPreferita = altro.letteraPreferita;

	}

	public Hello ritrasforma() {
		this.nome = "Adele";
		this.cognome = "Kleim";
		this.eta = 62;
		this.letteraPreferita = 'X';
		
		return  this;
	}
	
}