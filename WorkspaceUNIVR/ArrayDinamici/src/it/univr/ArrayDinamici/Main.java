package it.univr.ArrayDinamici;
public class Main {
	public static void main(String[] args) {
		Insieme insieme1 = new Insieme();
		Insieme insieme2 = new Insieme();
		
		for (int i = 0; i <= 999; i++)
			insieme1.aggiungi(i);

		for (int i = -999; i <= 0; i++)
			insieme2.aggiungi(i);
		
		System.out.println(insieme1.toString());
		System.out.println(insieme2.toString());
		

		System.out.println("media: " + insieme1.media());
		System.out.println("max: " + insieme1.max());
		System.out.println("min: " + insieme1.min());
		
		System.out.println("media: " + insieme2.media());
		System.out.println("max: " + insieme2.max());
		System.out.println("min: " + insieme2.min());
		
		insieme1.unisci(insieme2);
		System.out.println(insieme1.toString());
		
		System.out.println("media: " + insieme1.media());
		System.out.println("max: " + insieme1.max());
		System.out.println("min: " + insieme1.min());
		
	}
}