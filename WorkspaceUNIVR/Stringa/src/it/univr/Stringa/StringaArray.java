package it.univr.Stringa;
import java.util.Iterator;

import jbook.util.Input;

public class StringaArray implements Iterable <Stringa> {
	
	private Stringa[] array;
	
	public StringaArray() {
		array = new Stringa[5];
		
		initArray();
	}
	
	private void initArray() { // inizializza l'array inserendo 5 elementi di tipo Stringa
		for(int pos = 0; pos < array.length; pos++)
			array[pos] = new Stringa(Input.readString("> "));
	}
	
	protected Stringa[] getArray() { // ritorna l'array
		return array;
	}
	
	protected int getArrayLength() { // ritorna la lunghezza dell'array
		return array.length;
	}

	public Iterator<Stringa> iterator() {
		return new Iterator <Stringa> () {
			
			private int pos = 0; // posizione iniziale

			@Override
			public boolean hasNext() { // scorre gli elementi fino alla fine dell'array
				// cioè fino a quando pos risulta minore della dimensione dell'array
				return pos < array.length;
			}

			@Override
			public Stringa next() { // ritorna l'elemento in posizione pos-esima
				// e incrementa pos
				return array[pos++];
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
			
		};
	}

}
