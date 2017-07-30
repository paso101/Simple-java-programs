package it.univr.beedeedee.gameOfLife;

/**
 * Main.java
 * 
 * @author Damiano Pasotto VR397209 mailto: damiano.pasotto@studenti.univr.it
 * @author Lara Scarpari VR401241 mailto: lara.scarpari@studenti.univr.it
 * @version 1.0
 * 
 * <h2>Main</h2>
 * <p>La classe <i>Main</i> contiene il metodo <i>main()</i>,
 * il quale invoca un altro metodo per la scelta del pattern
 * da inserire nella griglia.</p>
 */

public class Main {

	/**
	 * Chiama il metodo <i>selectPattern()</i>
	 * della classe <i>MenuSelect</i> per acquisire
	 * una stringa da tastiera relativa ai <i>pattern</i>
	 * inseribili nella griglia.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new MenuSelect().selectPattern();
	}

}