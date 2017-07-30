/*
 * Game of Life, version 1.0, March 26, 2013
 */

package it.univr.gameOfLife;

import java.awt.Color;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 * @author Damiano Pasotto VR363785 mailto: damiano.pasotto@univr.it
 * @author Lara Scarpari VR361106 mailto: lara.scarpari@univr.it
 * @version 1.0<br><br>
 * 
 * NOTE:<br>
 * <ul>
 * <li> Dopo l'apparizione della finestra principale <i>Game of Life</i>,
 * per far comparire la griglia occorre spostare il cursore nella parte superiore della finestra,
 * causando cos&igrave; la comparsa del menù e dei bottoni.
 * A questo punto &egrave; necessario premere il bottone <i>RESET</i>.</li>
 * <li> L'eventuale spostamento non voluto delle finestre nello schermo
 * &egrave; dovuto alle diverse tipologie di schermo stesso
 * e al fatto che non abbiamo fatto dipendere dalle misure dello schermo
 * le dimensioni di finestre e oggetti.</li>
 * <li> Le immagini contenute nella directory <i>images</i>
 * provengono da http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life.</li>
 * </ul>
 *
 */

public class Pattern extends JFrame {
	
	/**
	 * L'etichetta realizzata con l'immagine di una configurazione di cellule inseribile nella griglia.
	 */

	JLabel pattern;
	
	/**
	 * Costruisce una finestra di dimensioni 250 * 250 pixel in posizione (650, 50),
	 * contenente l'immagine di una configurazione di cellule inseribile nella griglia,
	 * centrata nella finestra.
	 * 
	 * @param patternName il nome della configurazione di cellule inseribile nella griglia
	 */
	
	public Pattern(String patternName) {
		ImageIcon image = new ImageIcon("images/" + patternName + ".gif");
		
		setTitle(patternName);
		setLocation(650, 50);
		setSize(250, 250);
		setResizable(false);
		
		Container container = getContentPane();
		container.setBackground(Color.WHITE);
		
		pattern = new JLabel(image);
		pattern.setHorizontalAlignment(JLabel.CENTER);
		pattern.setVerticalAlignment(JLabel.CENTER);
		pattern.setToolTipText(patternName);
	}
	
	/**
	 * Aggiunge l'etichetta alla finestra e rende visibile la finestra stessa.
	 */
	
	protected void addPattern() {
		getContentPane().add(pattern);
		setVisible(true);
	}
	
	/**
	 * Rimuove l'etichetta dalla finestra e rende invisibile la finestra stessa.
	 */
	
	protected void removePattern() {
		getContentPane().remove(pattern);
		setVisible(false);
	}

}
