package it.univr.Felino;


public abstract class Gatto extends Felino {

	public Gatto(String nome) {
		super(nome);
	}
	
	
		public final boolean isDomestic() {
		return true;
	}
}
