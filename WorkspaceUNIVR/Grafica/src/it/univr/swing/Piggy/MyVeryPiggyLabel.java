package it.univr.swing.Piggy;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyVeryPiggyLabel extends JLabel {

	/**
	 * Meglio che l'immagine sia statica, tanto è uguale per tutte le label
	 * di questa classe. Così risparmiamo memoria.
	 */

	private final static ImageIcon piggy = new ImageIcon("images/piggy.jpg");

	public MyVeryPiggyLabel() {
		super(piggy);

		setToolTipText("oing oing");
	}
}
