package it.univr.Elezioni;

public class Partito {
	
	String nomePartito;
	
	public Partito(String nome) {
		this.nomePartito = nome;
	}
	
	public String getNome() { //restituisce il nome del partito
	
		return this.nomePartito;
	}
	
	@Override
	public boolean equals(Object other) {
		
		if(other instanceof Partito) {
			Partito otherPartito = (Partito) other;
			return this.nomePartito.equals(otherPartito.nomePartito);
		}
		
		else 
			return false;
	}
	
	@Override
	public int hashCode() {
		return nomePartito.hashCode();
	}

}
