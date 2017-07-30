package it.univr.Data;


public class Main {
	/** 
    Classi e oggetti
    Definizione di classi: campi, costruttori e metodi
    Campi d'istanza e campi statici
    Unità di compilazione e package
    Un primo esempio di classe: i semafori in modalità italiana o inglese: Semaforo.java e Test.java
    Si scriva una classe it.univr.data.Data che rappresenta una data del calendario. Tale classe deve avere i costruttori (non ci si preoccupi di date inesistenti):
        Data(int g, int m, int a) che costruisce una data che rappresenta il giorno g del mese m (tra 1 e 12) dell'anno a
        Data(int g, int m) che costruisce una data che rappresenta il giorno g del mese m (tra 1 e 12) dell'anno 2008
        Data(int g) che costruisce una data che rappresenta il giorno g del mese di gennaio dell'anno 2008
        Data() che costruisce una data che rappresenta il 13 gennaio 1973 
    Inoltre deve avere dei metodi
        String toString() che restituisce una stringa del tipo 12 marzo 2007
        boolean precede(Data altra) che restituisce true se e solo se la data viene strettamente prima dell'altra
        int passatiDallInizio() che restituisce il numero di giorni passati dall'inizio dell'anno per la data (incluso il giorno rappresentato dalla data). Si tenga conto degli anni bisestili 
    Quindi si scriva una classe it.univr.data.Main con un metodo main che crea l'11 agosto 2008 e il 13 gennaio 1973, li stampa a video, stampa il risultato di precede dell'uno rispetto all'altro e quindi stampa quanti giorni sono passati dall'inizio dell'anno per l'uno e per l'altro. Il risultato dovrebbe essere:

    11 agosto 2008
    13 gennaio 1973
    false
    true
    224
    13**/
	public static void main(String[] args) {
	
			
		Data d1 = new Data(11, 8, 2008); /**genero il nuovo oggetto Data */
		System.out.println(d1.toString());
		
		Data d2 = new Data(13, 1, 1973);/**genero un nuovo oggetto Data */
		System.out.println(d2.toString());
		
		System.out.println(d1.precede(d2)); //passo giorno2 e confronto con giorno1
		System.out.println(d2.precede(d1)); //passo giorno1 e confronto con giorno2
		
		System.out.println(d1.passatiDallInizio());
    	System.out.println(d2.passatiDallInizio());
		

		
		
		


	}

}
