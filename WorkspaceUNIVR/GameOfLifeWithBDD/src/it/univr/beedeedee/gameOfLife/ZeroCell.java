package it.univr.beedeedee.gameOfLife;

import com.juliasoft.beedeedee.bdd.BDD;

/**
 * ZeroCell.java
 * 
 * @author Damiano Pasotto VR397209 mailto: damiano.pasotto@studenti.univr.it
 * @author Lara Scarpari VR401241 mailto: lara.scarpari@studenti.univr.it
 * @version 1.0
 * 
 * <h2>ZeroCell</h2>
 * <p>La classe <i>ZeroCell</i> estende la classe <i>GameOfLife</i>
 * e crea un BDD per il <i>pattern ZeroCell</i>,
 * il quale rappresenta il <i>world</i> privo di celle vive.</p>
 */
public class ZeroCell extends GameOfLife {
	
	/**
	 * Invoca il costruttore della superclasse.
	 * 
	 * @param patternSelected Il nome del <i>pattern</i> scelto.
	 */
	public ZeroCell(String patternSelected) {
		super(patternSelected);
	}

	/**
	 * Costruisce un BDD per il <i>pattern ZeroCell</i>.
	 * 
	 * @return temp Il BDD generato.
	 */
	@Override
	public BDD getPattern() {
		BDD temp = factory.makeOne();
		
		for(int i = 0; i < this.DIM; i++)
			for(int j = 0; j < this.DIM; j++)
				temp.andWith(world[i][j].not());

		return temp;
	}

}
