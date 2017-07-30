package it.univr.Person;
import java.util.Iterator;

public class TestMainInsiemePersone {
	
	public static void main(String[] args) {
		Persone riunione = new Persone();
		
		Iterator <Persona> iterator = riunione.iterator();
		
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		
		}
	}
}
