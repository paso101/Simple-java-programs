package it.univr.swing.PallinaRimbalzante;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Ball extends JLabel {

	/**
	 * Le dimensioni della pallina.
	 */

	private final static int BALL_SIZE = 50;

	/**
	 * Lo spostamento orizzontale della pallina.
	 */

	private int dx = 1;

	/**
	 * Lo spostamento verticale della pallina.
	 */

	private int dy = 1;

	/**
	 * Meglio che l'immagine sia statica, tanto è uguale per tutte le label
	 * di questa classe. Così risparmiamo memoria.
	 */

	private final static ImageIcon image = new ImageIcon("images/ball.jpg");

	public Ball() {
		super(image);

		setBounds(0, 0, BALL_SIZE, BALL_SIZE);
	}

	public void shift() {
		setBounds(getX() + dx, getY() + dy, getWidth(), getHeight());
	}

	public void changeXDirection() {
		dx = -dx;
	}

	public void changeYDirection() {
		dy = -dy;
	}

	public boolean isGoingUp() {
		return dy < 0;
	}

	public boolean isGoingDown() {
		return dy > 0;
	}

	public boolean isGoingLeft() {
		return dx < 0;
	}

	public boolean isGoingRight() {
		return dx > 0;
	}
}
