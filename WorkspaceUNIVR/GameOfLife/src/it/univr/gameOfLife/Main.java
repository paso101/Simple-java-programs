/*
 * Game of Life, version 1.0, March 26, 2013
 */

package it.univr.gameOfLife;

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

public class Main {
	
	public static void main(String[] args) {
		GameOfLife game = new GameOfLife(4);
		while(true)
			game.startGame();
	}

}
