package it.univr.TryCatchFinally;

import jbook.util.Input;

public class TryCatchFinally {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int dividendo,divisore,risultato;	
		dividendo = Input.readInt("Inserisci dividendo intero > 0:    ");
		divisore = Input.readInt("Inserisci divisore intero > 0:    ");
		
		try{
			
			
			if(dividendo <= 0 && divisore <=0)
				throw new DIVIDENDOeDIVISOREArithmeticException("DIVIDENDO e DIVISORE <= 0 !");
			
			else if(dividendo <= 0)
				 	throw new DIVIDENDOArithmeticException("DIVISORE <= 0 !");
			
			else if(divisore <= 0)
			 	throw new DIVISOREArithmeticException("DIVISORE <= 0 !");

			System.out.println(dividendo + " / " + "divisore" + " = " + dividendo/divisore);
			
		}
		
		catch(DIVIDENDOArithmeticException e) {
			System.out.println(e);
		}
		
		catch(DIVISOREArithmeticException e) {
			System.out.println(e);
		}
		
		catch(DIVIDENDOeDIVISOREArithmeticException e) {
			System.out.println(e);
		}
		
		
		finally {	
			System.out.println("QUESTO MESSAGGIO VIENE STAMPATO SEMPRE!!!");
		}
		
		
	}

}
