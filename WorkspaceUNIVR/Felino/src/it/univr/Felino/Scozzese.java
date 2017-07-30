package it.univr.Felino;


public class Scozzese extends Gatto {

	public Scozzese(String nome) {
		super(nome);
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public int getCost() {
		return 1;
	}
	
	@Override
	public final String toString() {//metodo final perché è uguale sempre 
		return super.toString();
	}
}
