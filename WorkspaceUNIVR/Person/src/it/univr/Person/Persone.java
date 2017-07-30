package it.univr.Person;
import java.util.Iterator;
import java.util.Vector;

import jbook.util.Input;
public class Persone implements Iterable <Persona> {
	
	private Vector<Persona> insiemePersone;
	
	public Persone() {
		insiemePersone = new Vector<Persona>();
		for(int pos = 0; pos < 3; pos++) {
			insiemePersone.add(new Persona(Input.readString("Insert person's name: "),
										   Input.readInt("Insert a number: ")
							               )
			                   );
			System.out.println();
			}
		
		
	}
		
	public Iterator <Persona> iterator() {
		return new Iterator<Persona>() {
		private int index = 0;
		
		 	@Override
		 	public boolean hasNext() {
			 	return index < insiemePersone.size();
		 	}
		 	@Override
		 	public Persona next() {
			 	return insiemePersone.elementAt(index++);
		 	}
		 	@Override
		 	public void remove() {
		 	}
		};
	}
	
	// Siccome i vettori sono già iterabili, si può semplificare la scritture in questo modo:
	/**
	
	@Override
	public Iterator<Persona> iterator() {
		
		return this.insiemePersone.iterator();
	}
	*/	
}

