package it.univr.beedeedee.gameOfLife;

import com.juliasoft.beedeedee.bdd.BDD;

/**
 * Beacon.java
 * 
 * @author Damiano Pasotto VR397209 mailto: damiano.pasotto@studenti.univr.it
 * @author Lara Scarpari VR401241 mailto: lara.scarpari@studenti.univr.it
 * @version 1.0
 * 
 * <h2>Beacon</h2>
 * <p>La classe <i>Beacon</i> estende la classe <i>GameOfLife</i>
 * e crea un BDD per il <i>pattern Beacon</i>.</p>
 */
public class Beacon extends GameOfLife {

	/**
	 * Invoca il costruttore della superclasse.
	 * 
	 * @param patternSelected Il nome del <i>pattern</i> scelto.
	 */
	public Beacon(String patternSelected) {
		super(patternSelected);
	}

	/**
	 * Costruisce un BDD per il <i>pattern Beacon</i>.
	 * 
	 * @return temp Il BDD generato.
	 */
	@Override
	public BDD getPattern() {
		BDD temp = factory.makeOne();
		
		for(int i = 0; i < this.DIM; i++) {
			for(int j = 0; j < this.DIM; j++) {
				if(initLiveCells(i, j))
					temp.andWith(world[i][j].copy());
				else
					temp.andWith(world[i][j].not());
			}
		}

		return temp;
	}
	
	/**
	 * Informa se la cella &egrave; viva o morta.
	 * 
	 * @param i Coordinata x della matrice di BDD.
	 * @param j Coordinata y della matrice di BDD.
	 * @return true se la cella &egrave; viva, false altrimenti.
	 */
	private boolean initLiveCells(int i, int j) {
		if(((j == 3 || j == 4) && (i == 3 || i == 4)) || ((j == 5 || j == 6) && (i == 5 || i == 6)))
			return true;
		return false;
	}

}
