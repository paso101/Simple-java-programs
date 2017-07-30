package it.univr.Orario;

public class Orario implements Comparable <Orario>{
	private int ora;
	private int minuti;
	private int secondi;
	
	public Orario (int h, int min, int sec) {
		
		if(h > 23 || h < 0)
			throw new OraMinutiSecondiIllegalException("Ora > 23 o < 0"); 
				
		if(min > 59 || min < 0)
			throw new OraMinutiSecondiIllegalException("Minuti > 59 o < 0");

		if(sec > 59 || sec < 0)
			throw new OraMinutiSecondiIllegalException("Secondi > 59 o < 0");
		
		this.ora = h;
		this.minuti = min;
		this.secondi = sec;
	}
		
	public int compareTo(Orario other) {
	
		if(this.ora != other.ora)
			return this.ora - other.ora;
		else
			if(this.minuti != other.minuti)
				return this.minuti - other.minuti;
			else
				if(this.secondi != other.secondi)
					return this.secondi - other.secondi;
				else
					return 0;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof Orario) {
			Orario otherOrario = (Orario) other;
			return this.ora == otherOrario.ora && this.minuti == otherOrario.minuti && this.secondi == otherOrario.secondi;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.ora ^ this.minuti ^ this.secondi;
	}
	
	@Override
	public String toString() {
		
		return this.ora + ":" + this.minuti + ":" + this.secondi;
		
	}
}
