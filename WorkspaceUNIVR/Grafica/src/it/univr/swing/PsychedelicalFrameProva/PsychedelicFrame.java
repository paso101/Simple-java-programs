package it.univr.swing.PsychedelicalFrameProva;

import java.awt.Color;
import javax.swing.JFrame;

/**
 * Un semplice esempio di estensione di un frame Swing.
 * 
 * @author <A HREF="mailto:fausto.spoto@univr.it">Fausto Spoto</A>
 */

public class PsychedelicFrame extends JFrame {

	public static void main(String[] args) {
		new PsychedelicFrame("Here I am!");
	}

	public PsychedelicFrame(String title) {
		super(title);

		// indichiamo le nostre dimensioni
		setBounds(100, 200, 300, 400);

		// indichiamo cosa deve accadere quando si clicca sul pulsante di chiusura:
		// il programma deve essere terminato
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// don't be shy!
		setVisible(true);

		// adesso giochiamo: modifichiamo il nostro colore ciclicamente
		int red = 0, green = 96, blue = 192;
		while (true) {
			// chiedo di cambiare colore di background al content pane
			// che contiene il corpo della finestra
			getContentPane().setBackground(new Color(red++ % 256, green++ % 256, blue++ % 256));

			sleepFor(10);
		}
	}

	/**
	 * Aspetta per il numero di millisecondi indicato.
	 * 
	 * @param milliseconds
	 */

	private void sleepFor(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
		}
	}
}