package it.univr.Automobile;

public class Ford implements Automobile {

	private String nome;
	private int prezzo;
	private int carburante;
	private int consumo;
	private String colore;
	
	public Ford(String nome, int prezzo, int carburante, int consumo, String colore) {

		this.nome = nome;
		this.prezzo = prezzo;
		this.carburante = carburante;
		this.consumo = consumo;
		this.colore = colore;
	}
	@Override
	public int getPrezzo() {
		
		return this.prezzo;
	}

	@Override
	public int getCarburante() {
		
		return this.carburante;
	}

	@Override
	public int getConsumo() {
		
		return this.consumo;
	}

	@Override
	public String getColore() {
		
		return this.colore;
	}
	
	@Override
	public String toString() {
	
		return "NOME: " + this.nome + "\nCOSTO: " + this.prezzo + " euro\n" +
				"CARBURANTE: " + this.carburante + " litri\nCONSUMO: " + 
				this.consumo + " KM/L \n" + "COLORE: " + this.colore + "\n"; 
		
	}

	@Override
	public boolean equals(Object other) {

		if(!(other instanceof Ford))
			return false;
		
		Ford otherFord = (Ford) other;
		
		if(this.carburante == otherFord.carburante && this.prezzo == otherFord.prezzo
				&& this.consumo == otherFord.consumo && this.colore.equals(otherFord.colore)
				&& this.nome.equals(otherFord.nome))
			return true;
		
		
		return true;
		
	}

	@Override
	public int hashCode() {
		
		return this.nome.hashCode() ^ this.colore.hashCode() ^ this.prezzo ^ this.consumo ^ this.carburante;
	}

}
