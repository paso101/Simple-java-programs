package it.univr.Television;

public class Television {
	
	private final static int WIDTH = 30;
	private int channel;
	
	public Television() {
		this.channel = 1;
	}
	
	/**
	 * restituisce il numero del canale su cui il televisore `
	e sintonizzato
	 */
	
	public int getChannel() {
		return this.channel;
	}
		
	/**
	 * riceve come argomento il numero intero di un canale e sintonizza il televisore su tale
canale;
	 */
	
	public void setChannel(int numberChannel) {
		this.channel = numberChannel;
		
	}
	
	/**
	 * restituisce una stringa che descrive il televisore
	 */
	
	@Override
	public String toString() {
		String name = getNameChannel();
		String result = "";

		for (int counter = 0; counter < WIDTH; counter++)
			result += "*";
		result += "\n";

		result += "* ";
		int spazi = (WIDTH - name.length() - 4) / 2;
		for (int counter = 0; counter < spazi; counter++)
			result += " ";
		result += name;
		for (int counter = 0; counter < spazi; counter++)
			result += " ";
		result += " *\n";

		for (int counter = 0; counter < WIDTH; counter++)
			result += "*";
		result += "\n";

				
		return result;
	}

	private String getNameChannel() {
		
		switch (channel) {
		case 1: return "rave uno";
		case 2: return "rave due";
		case 3: return "rave tre";
		case 4: return "canale squatto";
		case 5: return "canale cinto";
		default: return "canale sconosciuto";
		}
	}

}
