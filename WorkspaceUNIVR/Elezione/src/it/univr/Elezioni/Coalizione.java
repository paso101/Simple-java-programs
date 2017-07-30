package it.univr.Elezioni;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class Coalizione implements Iterable<Partito>{

	private String nomeCoalizione;

	// i partiti che fanno farte di questa coalizione
	private final Set<Partito> partiti = new HashSet<Partito>();
	
	public Coalizione(String nome, Partito... partiti) {
		
		if(partiti.length == 0)
			throw new IllegalArgumentException("Una coalizione non può essere vuota");
		
		else {	
			for(Partito partito : partiti)
				this.partiti.add(partito);
		}
		
		this.nomeCoalizione = nome;
	}
	
	@Override
	public Iterator<Partito> iterator() {
		// deleghiamo all'insieme, che sa come iterare su se stesso
		return partiti.iterator();
	}
	
	
	public String getNome() { // che restituisce il nome della coalizione
			
		return this.nomeCoalizione;	
	}
	
	//elimina il partito indicato dalla coalizione, se c'era, altrimenti
	//non fa nulla. Restituisce true se e solo se la coalizione è diventata vuota.
	public boolean rimuovi(Partito partito) {
	
		this.partiti.remove(partito);
		
		return this.partiti.isEmpty();
	}
	
	@Override
	public boolean equals(Object other) {

		if(other instanceof Coalizione) {
			Coalizione otherCoalizione = (Coalizione) other;
			return this.nomeCoalizione.equals(otherCoalizione.nomeCoalizione);
		}
		else
			return false;
	}
	
	
	@Override
	public int hashCode() {
		return nomeCoalizione.hashCode();
	}

}


