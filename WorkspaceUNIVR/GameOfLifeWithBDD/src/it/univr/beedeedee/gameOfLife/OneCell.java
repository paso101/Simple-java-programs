package it.univr.beedeedee.gameOfLife;

import com.juliasoft.beedeedee.bdd.BDD;

/**
 * OneCell.java
 * 
 * @author Damiano Pasotto VR397209 mailto: damiano.pasotto@studenti.univr.it
 * @author Lara Scarpari VR401241 mailto: lara.scarpari@studenti.univr.it
 * @version 1.0
 * 
 * <h2>OneCell</h2>
 * <p>La classe <i>OneCell</i> estende la classe <i>GameOfLife</i>
 * e crea un BDD per il <i>pattern OneCell</i>,
 * il quale consiste in una sola cella viva all'interno del <i>world</i>.</p>
 */
public class OneCell extends GameOfLife {
	
	/**
	 * Invoca il costruttore della superclasse.
	 * 
	 * @param patternSelected Il nome del <i>pattern</i> scelto.
	 */
	public OneCell(String patternSelected) {
		super(patternSelected);
	}
	
	/**
	 * Costruisce un BDD per il <i>pattern OneCell</i>.
	 * 
	 * @return temp Il BDD generato.
	 */
	@Override
	public BDD getPattern() {
		BDD temp = factory.makeOne();
		
		for(int i = 0; i < this.DIM; i++) {
			for(int j = 0; j < this.DIM; j++) {
				if(i == 5 && j == 5)
					temp.andWith(world[i][j].copy()); 	
				else
					temp.andWith(world[i][j].not()); 
			}
		}
						
		return temp;	
	}

}
