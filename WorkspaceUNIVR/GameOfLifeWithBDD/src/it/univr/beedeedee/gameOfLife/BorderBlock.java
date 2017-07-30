package it.univr.beedeedee.gameOfLife;

import com.juliasoft.beedeedee.bdd.BDD;

/**
 * BorderBlock.java
 * 
 * @author Damiano Pasotto VR397209 mailto: damiano.pasotto@studenti.univr.it
 * @author Lara Scarpari VR401241 mailto: lara.scarpari@studenti.univr.it
 * @version 1.0
 * 
 * <h2>BorderBlock</h2>
 * <p>La classe <i>BorderBlock</i> estende la classe <i>GameOfLife</i>
 * e crea un BDD per il <i>pattern BorderBlock</i>,
 * il quale consiste in un blocco posizionato nel <i>world</i> in basso a destra.</p>
 */
public class BorderBlock extends GameOfLife {
	
	/**
	 * Invoca il costruttore della superclasse.
	 * 
	 * @param patternSelected Il nome del <i>pattern</i> scelto.
	 */
	public BorderBlock(String patternSelected) {
		super(patternSelected);
	}

	/**
	 * Costruisce un BDD per il <i>pattern BorderBlock</i>.
	 * 
	 * @return temp Il BDD generato.
	 */
	@Override
	public BDD getPattern() {
		BDD temp = factory.makeOne();
		
		for(int i = 0; i < this.DIM; i++) {
			for(int j = 0; j < this.DIM; j++) {
				if(initLifeCells(i, j))
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
	private boolean initLifeCells(int i, int j) {
		if((j == 9 && i == 9) || (j == 9 && i == 8) || (j == 8 && i == 9) || (j == 9 && i == 9))
			return true;
		return false;
	}

}
