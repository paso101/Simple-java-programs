package it.univr.Cupido;
import jbook.util.Input;
import java.util.Collection;

public class Main {
	/**
	 * Un programma che gestisce delle coppie di fidanzati.
	 * 
	 * 
	 */

	public static void main(String[] args) {
		Cupido c = new Cupido();
				
		boolean key = true; 
		while(key == true) {
			String comando = Input.readString("Comando: ");
			String [] arrayComando = new String[4];
			arrayComando = comando.split(" ");	
			
			if(arrayComando[0].equals("fidanza") && arrayComando[2].equals("con")) 
				c.fidanza(arrayComando[1], arrayComando[3]);
						
			else
				if (arrayComando[0].equals("cosa") && arrayComando[1].equals("sai") && arrayComando[2].equals("di"))
					System.out.println(c.cosaSaiDi(arrayComando[3]));
				
			else
				if(arrayComando[0].equals("separa") && arrayComando[2].equals("da"))
					c.separa(arrayComando[1], arrayComando[3]);
				
			else
				if(arrayComando[0].equals("fine"))
					key = false;
			
			else
				if(arrayComando[0].equals("stampa"))
					System.out.print(c.stampa());//chiamo la funz stampa che mi ritorna una stringa 
												 //non + modificabile
			
			else
				System.out.println("comando incomprensibile");
				
			
		}
	}
}