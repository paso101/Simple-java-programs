package it.univr.Automobile;
import java.util.Vector;


public class Concessionaria {
	
	private int valoreInDenaroDelleAutoInConcessionaria;
	private int denaroConcessionaria = 90000;
	private Vector <Ford> concessionaria;
	
	public Concessionaria(Ford... ford) {
		
		
		//inizializzo l'insieme "concessionaria2
		concessionaria = new Vector <Ford> ();
		
		//ogni oggetto Ford passato dal main viene memorizzato in concessionaria
		for(Ford f : ford) {
			concessionaria.add(f);
			valoreInDenaroDelleAutoInConcessionaria += f.getPrezzo();
		}
	}

	public void compraAuto(Ford ford) {
		
		int prezzo = ford.getPrezzo();
		denaroConcessionaria -= prezzo;
		valoreInDenaroDelleAutoInConcessionaria += prezzo;
		concessionaria.add(ford);
		
	}
	
	public void vendiAuto(Ford ford) {
		
		int prezzo = ford.getPrezzo();
		denaroConcessionaria += prezzo; 
		valoreInDenaroDelleAutoInConcessionaria -= prezzo;
		concessionaria.remove(ford);
		
	}

	public int getNumeroAutomobili() {
		
		return concessionaria.size();
	}
	
	public int getValoreInDenaro() {
		
		return valoreInDenaroDelleAutoInConcessionaria;
	}
	
	public int getDenaro() {
		
		return this.denaroConcessionaria;
	}
	
	public void getListaAuto() {
		for(Ford f : concessionaria)
			System.out.println(f.toString());
	}
}
