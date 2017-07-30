package it.univr.Felino;


public class Main {

	public static void main(String[] args) {
	
		Leopardo leo = new Leopardo("Leopardo");
		System.out.println(leo.toString());

		Siamese siam = new Siamese("Siamese");
		System.out.println(siam.toString());

		Scozzese scoz = new Scozzese("Scozzese");
		System.out.println(scoz.toString());
	}

}
