package it.univr.Stringa;
public class Stringa {
	
	private String stringa;
	
	public Stringa(String stringa) { // memorizza una stringa data in input
		this.stringa = stringa;
	}
	
	protected String getStringa() { // ritorna la stringa
		return this.stringa;
	}
	
	protected void setStringa(String stringa) { // modifica la stringa
		this.stringa = stringa;
	}
	
	protected int getStringLength() { // ritorna la lunghezza della stringa
		return stringa.length();
	}
	
	// è necessario ridefinire il metodo toString()
	// perché altrimenti non è possibile stampare l'oggetto
	// in questo caso l'oggetto un'istanza della classe Stringa
	@Override
	public String toString() {
		return stringa;
	}

}
