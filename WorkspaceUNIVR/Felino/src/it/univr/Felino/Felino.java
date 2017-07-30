package it.univr.Felino;


public abstract class Felino {

	private final String nome;

	public Felino(String nome) {//costruttore a cui passo il nome dal main
		this.nome = nome;
	}

	public String getName() {
		return this.nome;
	}

	public abstract int getCost();

	public abstract boolean isDomestic();

	public String toString() {
		return "sono " + getName()
				+ " costo " + getCost() + " euro e "
				+ (isDomestic() ? "" : "non ")
				+ "sono domestico";
	}
}
