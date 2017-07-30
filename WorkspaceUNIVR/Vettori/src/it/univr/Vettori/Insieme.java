package it.univr.Vettori;
import java.util.Vector;
//import jbook.util.Input;

public class Insieme {

	public Vector <Double> vettore;
	
	public Insieme () {
		vettore = new Vector <Double > ();
			
	}
	
	
	public void aggiungi(double position) {
		this.vettore.add(position);
	
			
		
	}
	
	public boolean vuoto() {
		if(vettore.size() == 0)
			return true;
		
		return false;
	}
	
	public double media() {
		double somma = 0.0;
		for (double d : this.vettore) // d elemento del vettore 
			somma+=d; 
		
		return somma/vettore.size();   //ritorna la media
	}
	
	public double max() {
		
		double max = 0.0;
		for (double d : this.vettore) // d elemento del vettore 
			if(d > max)
				max = d;
		return max;					//ritorna elemento MAX
	}
	
	public double min() {
		
		double min = 0.0;
		for (double d : this.vettore) // d elemento del vettore 
			if(d < min)
				min = d;
		return min;					//ritorna lemento min
	}
	

	public String toString() {
	
		StringBuilder s = new StringBuilder();
		s.append("[");
		
		for(double d :this.vettore){ //per ogni elemento d( d non è l'indice) appartenente a this.vettore
			s.append(d);
			if (d != (this.vettore.size()-1) && (d - (this.vettore.size()-1) != -999.0)) 
				s.append(", ");
		}
				
		s.append("]");
		String newS = s.toString();
		
		return newS;
		
	}
	
	 public Vector <Double> unisci(Insieme altro) {
		 for (double element : altro.vettore)
			 this.vettore.add(element);
			 
		  return this.vettore;
		 
	 }
	
	
		
		
			
			
	}

