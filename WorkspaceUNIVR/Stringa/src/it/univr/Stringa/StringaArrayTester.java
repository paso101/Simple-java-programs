package it.univr.Stringa;
import java.util.Iterator;

public class StringaArrayTester {

	public static void main(String[] args) {
		
		// ***************************************************************** \\
		// N.B.: stringhe non è un array di elementi di tipo Stringa         \\
		// ma è un oggetto (o istanza) della classe StringaArray,            \\
		// la quale ha come unico campo un array di elementi di tipo Stringa \\
		// infatti sull'array della classe StringaArray si possono applicare \\
		// i metodi della classe Array,                                      \\
		// invece sulla variabile stringhe si possono applicare i metodi     \\
		// della classe StringaArray e non quelli degli array                \\
		// ***************************************************************** \\
		
		StringaArray stringhe = new StringaArray();
		
		// utilizzo dell'iteratore
		Iterator<Stringa> iterator = stringhe.iterator();
		while(iterator.hasNext())
			System.out.println(iterator.next());

	}

}
