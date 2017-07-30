package it.univr.Felino;


public class Leopardo extends Felino {

	public Leopardo(String nome) {
		super(nome);
	}
	/**getName() è facoltativa la sua implementazione perchè è uguale in tutte le classi 
	 * e sottoclassi
	 */
	@Override
	public String getName() {
		return super.getName();
	}

	@Override						/** @Override mi permette di ridefinire una funzione
								  * usata dalla classe Leopardo**/
	public int getCost() {
		return 1000000;
	}

	@Override
	public final boolean isDomestic() {
		return false;
	}
	
	@Override
	public final String toString() {//metodo final perché è uguale sempre 
		return super.toString();
	}
}
