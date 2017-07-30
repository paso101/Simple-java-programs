package it.univr.Tree;

public class Main {

	/**
	 * Costruzione albero
	 */
	public static void main(String[] args) {
		
		Tree alberoInteger = new Tree<Integer>(
				new Tree<Integer>(null, -2, null),
				5,
				new Tree<Integer>(new Tree<Integer>(null, 5, null), 11, null)
			);
		
		int alberoLen = alberoInteger.height();
		System.out.println("Altezza albero interi: " + alberoLen);
		System.out.println("Trasformazione labero in vettore: " + alberoInteger.toVector());
		
	
	}

}
