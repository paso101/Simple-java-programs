package it.univr.swing.PallinaRimbalzante;

import java.awt.Color;

import javax.swing.JFrame;

/**
 * Un divertentissimo frame con una pallina rimbalzante.
 * Attenzione: può provocare dipendenza.
 * 
 * @author <A HREF="mailto:fausto.spoto@univr.it">Fausto Spoto</A>
 */

public class BouncingBall extends JFrame {

	// le dimensioni iniziali della finestra
	private final static int X_SIZE = 400;
	private final static int Y_SIZE = 350;

	// la pallina che rimbalza
	private final Ball ball = new Ball();

	public static void main(String[] args) {
		new BouncingBall();
	}

	public BouncingBall() {
		super("This is great fun!");

		initFrame();
		moveBallAround();
	}

	private void initFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// non vogliamo nessun layout manager: gestiamo la grafica direttamente
		getContentPane().setLayout(null);
		getContentPane().add(ball);
		getContentPane().setBackground(Color.WHITE);

		// dobbiamo esplicitamente indicare la dimensione della finestra, poiché
		// non abbiamo a disposizione il layout manager, che farebbe tutto da solo per noi
		setBounds(100, 30, X_SIZE, Y_SIZE);

		// rendiamoci visibili
		setVisible(true);
	}

	private void moveBallAround() {
		while (true) {
			shiftBall(ball);
			sleepFor(7);
		}
	}

	private void shiftBall(Ball ball) {
		if (ball.isGoingLeft() && ball.getX() < 0)
			ball.changeXDirection();
		else if (ball.isGoingRight() && ball.getX() > getWidth() - ball.getWidth())
			ball.changeXDirection();

		if (ball.isGoingUp() && ball.getY() < 0)
			ball.changeYDirection();
		else if (ball.isGoingDown() && ball.getY() > getHeight() - ball.getHeight() - 27) // teniamo conto dei bordi...
			ball.changeYDirection();

		ball.shift();
	}

	/**
	 * Dorme per il numero di millisecondi indicato.
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