package it.univr.swing.DuePallineSpeed;

import it.univr.swing.Piggy.MyVeryPiggyLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Un divertentissimo frame con DUE palline rimbalzanti.
 * Le cose si fanno complesse...
 * 
 * @author <A HREF="mailto:fausto.spoto@univr.it">Fausto Spoto</A>
 */

public class BouncingBallsWithSpeedControl extends JFrame {

	// le dimensioni iniziali della finestra
	private final static int X_SIZE = 400;
	private final static int Y_SIZE = 350;

	// le palline che rimbalzano
	private final Ball ball1 = new Ball();
	private final Ball ball2 = new Ball();

	// la finestra usata per controllare la velocità
	private final SliderFrame control = new SliderFrame(1, 8, 1);

	public static void main(String[] args) {
		new BouncingBallsWithSpeedControl();
	}

	public BouncingBallsWithSpeedControl() {
		super("This is great fun!");

		initFrame();
		moveBallsAround();
	}

	private void initFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// non vogliamo nessun layout manager: gestiamo la grafica direttamente
		getContentPane().setLayout(null);			
		getContentPane().add(ball1);
		getContentPane().add(ball2);
	

		// dobbiamo esplicitamente indicare la dimensione della finestra, poiché
		// non abbiamo a disposizione il layout manager, che farebbe tutto da solo per noi
		setBounds(100, 30, X_SIZE, Y_SIZE);

		// all'inizio posizioniamo la seconda pallina lontano dalla prima
		ball2.setBounds(X_SIZE - ball2.getWidth() - 28, Y_SIZE / 2, ball2.getWidth(), ball2.getHeight());

		// rendiamoci visibili
		setVisible(true);
	}

	private void moveBallsAround() {
		while (true) {
			ball1.setSpeed(control.getValue());
			ball2.setSpeed(control.getValue());

			checkIfTheyHit(ball1, ball2);
			shiftBall(ball1);
			shiftBall(ball2);
			sleepFor(7);
		}
	}

	private void checkIfTheyHit(Ball ball1, Ball ball2) {
		int diffX = ball1.getX() - ball2.getX();
		int radius1 = ball1.getWidth() / 2;
		int diffY = ball1.getY() - ball2.getY();
		int radius2 = ball2.getWidth() / 2;

		if ((diffX * diffX) + (diffY * diffY) < (radius1 + radius2) * (radius1 + radius2)) {
			ball1.changeXDirection();
			ball1.changeYDirection();
			ball2.changeXDirection();
			ball2.changeYDirection();
			ball1.shift();
			ball2.shift();
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