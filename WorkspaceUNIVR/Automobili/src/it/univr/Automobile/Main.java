package it.univr.Automobile;
import jbook.util.Input;


public class Main {

	/**
	 * @param no args
	 * @return 
	 */
	public static void main(String[] args) {
		
		String comando;
		boolean menu = true;
		
		Ford f0a = new Ford("Mondeo",26500,70,18,"Nero");
		Ford f0b = new Ford("Mondeo ST",40000,70,15,"Bianca");
		Ford f0c = new Ford("Fiesta ST",22000,60,16,"Rosso");
		Ford f0d = new Ford("Mustang",50000,75,11,"Nero");
		Ford f1 = new Ford("GT 40", 100000, 80, 5,"Rosso");
		Ford f2 = new Ford("Focus RS", 3000, 70, 6,"Verde");
		
		Concessionaria concessionariaFord = new Concessionaria(f0b,f0c,f0d,f1,f2);
		
		System.out.println("°°° BENVENUTO NEL MONDO FORD °°°\n\n");
		
		do {
			System.out.println("Cosa desideri scegliere?\n");
			System.out.println(">ESCI\n>COMPRA AUTO\n>VENDI AUTO\n>DENARO A DISPOSIZIONE\n>AUTO IN CONCESSIONARIA\n>VALORE AUTOMOBILI\n");

			comando = Input.readString();
			comando = comando.toUpperCase();
			
			int tot;
			
			switch(comando) {
			
			case "ESCI":
				menu = false;
				break;
			
			case "COMPRA AUTO":
				tot = Input.readInt("Quante auto vuoi comperare? ");
				for(int pos = 0; pos < tot; pos++) {
					concessionariaFord.compraAuto(new Ford(Input.readString("NOME: "), Input.readInt("PREZZO: "), 
					Input.readInt("CARBURANTE: "), Input.readInt("CONSUMO(KM/L): "),
					Input.readString("COLORE: ")));
					System.out.println();
				}
				break;
			
			case "VENDI AUTO": 
				tot = Input.readInt("Quante auto vuoi vendere? ");
				for(int pos = 0; pos < tot; pos++) {
				concessionariaFord.vendiAuto(new Ford(Input.readString("NOME: "), Input.readInt("PREZZO: "), 
											Input.readInt("CARBURANTE: "), Input.readInt("CONSUMO(KM/L): "),
											Input.readString("COLORE: ")));
				System.out.println();}
				break;
				
			case"DENARO A DISPOSIZIONE":
				System.out.println("TOTALE SOLDI LIQUIDI DELLA INCONCESSIONARIA: " +
				concessionariaFord.getDenaro());
				break;
				
			case "AUTO IN CONCESSIONARIA":
				System.out.println("TOTALI AUTO PRESENTI  IN CONCESSIONARIA: " +
				concessionariaFord.getNumeroAutomobili() + "\n");
				concessionariaFord.getListaAuto();
			
			case "VALORE AUTOMOBILI":
				System.out.println("VALORE AUTO PRESENTI  IN CONCESSIONARIA: " +
				concessionariaFord.getValoreInDenaro() +"\n");
				
				break;
							
			default:
				try{
					throw new nullComandoException("Comando non valido!");
				}
				catch(nullComandoException e) {
					System.out.println(e);
					break;
				}
			
				
			}
			
		}while(menu);
		
		System.out.println("\n\n\nTORNA A TROVARCI...FORD, GO FURTHER!");

	}

}
