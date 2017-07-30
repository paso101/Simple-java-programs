package it.univr.Studente;
	
		
public class Main {

	public static void main(String[] args) {
		Hello femmina = new Hello(); // crea Lara
		Hello femmina2 = new Hello(); // crea Lara2
		Hello maschio = new Hello("Damiano", "Pasotto", 21, 'D'); // crea Damiano
		
		femmina.printPersona(); // stampa Lara
		System.out.println();
		maschio.printPersona(); // stampa Damiano
		
		femmina.trasforma(maschio); // Lara diventa Damiano
		System.out.println(); 		
		femmina.printPersona();	// stampa Lara trasformata in Damiano (quindi stampa Damiano)
		
		System.out.println(); 
		femmina.ritrasforma().printPersona(); //Lara non è + Damiano: ora è Adele
		
		System.out.println(); 
		femmina2.ritrasforma(); //femmina2 è vuota, e diventa Adele
		femmina2.printPersona();//stampa femmina2 che ha acquisito i dati di Adele
		
		
		
		
	}

}