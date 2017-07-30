package it.univr.Vettori;
//import java.util.Vector;
//import jbook.util.Input;


public class Main {

	public static void main(String[] args) {

		Insieme I1 = new Insieme ();
		Insieme I2 = new Insieme ();
		
		
		
		
		System.out.println(I1.vuoto()); //stampa FALSE xk è vuoto
		System.out.println(I2.vuoto()); //stampa FALSE xk è vuoto
	
		for(double pos = 0; pos < 1000; pos++) 
			I1.aggiungi(pos);	
		
		for (double pos = -999; pos <= 0; pos++)
			I2.aggiungi(pos);
			
	
		System.out.println(I1.vuoto()); //stampa TRUE xk non è vuoto
		System.out.println(I2.vuoto()); //stampa TRUE xk non è vuoto
		
			
		System.out.println(I1.toString());
		System.out.println(I2.toString());
		
		System.out.println("MEDIA1   " + I1.media());
		System.out.println("MAX1   " + I1.max());
		System.out.println("MIN1   " + I1.min());
		
		System.out.println("MEDIA2  " + I2.media());
		System.out.println("MAX2   " + I2.max());
		System.out.println("MIN2   " + I2.min());
			
		
		System.out.println(I1.unisci(I2).toString());
		
	}
}