package it.univr.Cupido;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import jbook.util.Input;

import jbook.util.Input;

public class Cupido {

	private HashMap<String, String> database;
	
	public Cupido() {
		
		
		database = new HashMap<String, String>();
			
	}
	
	public void fidanza(String X, String Y) {
		database.remove(database.get(X)); //rompo il fidanzamento con quello che sta con X
		database.remove(database.get(Y)); //rompo il fidanzamento con quello che sta con Y
		database.put(X, Y);
		database.put(Y, X);		
	}
	
	public void separa(String X, String Y) {
		database.remove(X);
		database.remove(Y);
	}
	
	public String cosaSaiDi(String Z) {
		if(database.get(Z) == null)
			return "non so nulla di " + Z;
		
		return Z + " è fidanzato con " + database.get(Z); 
			
	}
	
	public String stampa() {
		StringBuilder s = new StringBuilder();
		
			// creo stringa definitiva
			for (String nome : database.keySet()) {//per ogni nome dell'Insieme 
												   //delle chiavi database.keySet() 
				s.append(nome);
				s.append(" è fidanzato/a con ");
				s.append(database.get(nome));
				s.append("\n");
				
			}
			
			String newStr = s.toString();
			return newStr;

	}
}
