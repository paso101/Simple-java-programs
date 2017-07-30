package it.univr.Orario;
import jbook.util.Input;


public class Main {

	/**
	 * @param no args
	 */
	public static void main(String[] args) {
		
		Orario o1 = new Orario(0,10,20);
		Orario o2 = new Orario(0,10,20);
		Orario o3 = new Orario(14,3,51);
		Orario o4 = new Orario(5,36,25);
				
		Orari orari = new Orari(o1, o2, o3, o4);
		orari.STAMPA();
				
		orari.aggiungiOrario(o1, o2, new Orario(17, 17, 17));
		orari.STAMPA();
		
		orari.rimuoviOrario(o1, o2, o3, o4);
		orari.STAMPA();
		System.out.println("\n" + "\n" + "\n");
		
		System.out.println("Perchè non inserisci tu un' orario???");
		int ora = Input.readInt("0 <= ORE <= 23\n");
		int minuti = Input.readInt("0 <= MINUTI <= 59\n");
		int secondi = Input.readInt("0 <= SECONDI <= 59\n");
		
		Orario o5 = new Orario(ora, minuti, secondi);
		
		orari.aggiungiOrario(o5, o1);
		orari.STAMPA();
		
		orari.rimuoviOrario(o5, o1,  new Orario(17, 17, 17));
		orari.STAMPA();
	}

}
