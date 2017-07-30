package it.univr.Felino;


public class Siamese extends Gatto {

	public Siamese(String nome) {
		super(nome);
	}
	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public int getCost() {
		return 1000;
	}
	
	@Override
	public final String toString() {//metodo final perché è uguale sempre 
		return super.toString();
	}
}