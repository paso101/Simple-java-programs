package it.univr.Orario;
import java.util.Iterator;
import java.util.Vector;

public class Orari implements Iterable <Orario>{
	
	private Vector <Orario> orari;
	private boolean elemento = false;
	
	public Orari(Orario... orari) {
		
		
		this.orari = new Vector <Orario>();
		this.orari.add(orari[0]);
	
		for(int i = 0; i != orari.length; i++) {
			for(int j = 0; j != this.orari.size(); j++) {
				if(this.orari.elementAt(j).equals(orari[i])) {
					elemento = true;
					break;
				 }
				else
					elemento = false;
				}
		
		if(elemento == false)
			this.orari.add(orari[i]);
		}

		
	}
	
	protected void aggiungiOrario(Orario... orari) {
		
		for(int i = 0; i != orari.length; i++) {
			for(int j = 0; j != this.orari.size(); j++) {
				if(this.orari.elementAt(j).equals(orari[i])) {
					elemento = true;
					break;
				 }
				else
					elemento = false;
				}
		
		if(elemento == false)
			this.orari.add(orari[i]);
		}
		
	}
	
	protected void rimuoviOrario(Orario... orari) {
	
		for(int pos = 0; pos != orari.length; pos++)
			this.orari.remove(orari[pos]);
	}
	
	public void STAMPA() {
		
		System.out.println(this.orari);
	}

	@Override
	public Iterator<Orario> iterator() {
		
		return this.orari.iterator();
	}
		
}

