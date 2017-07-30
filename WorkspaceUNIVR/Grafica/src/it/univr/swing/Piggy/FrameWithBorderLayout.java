package it.univr.swing.Piggy;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Un frame con layout di tipo BorderLayout.
 * 
 * @author <A HREF="mailto:fausto.spoto@univr.it">Fausto Spoto</A>
 */

public class FrameWithBorderLayout extends JFrame {

	public static void main(String[] args) {
		new FrameWithBorderLayout("Here I am!");
	}

	public FrameWithBorderLayout(String title) {
		super(title);

		// indichiamo la dimensione della finestra
		setBounds(100, 200, 400, 400);

		// indichiamo cosa deve accadere quando si clicca sul pulsante di chiusura:
		// il programma deve essere terminato
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		contentPane.add(new JLabel("ciao amico"), BorderLayout.PAGE_START);
		contentPane.add(new JLabel("questo va a destra"), BorderLayout.LINE_END);
		contentPane.add(new JLabel("questo va a sinistra"), BorderLayout.LINE_START);
		contentPane.add(new MyLabelWithToolTip("che bella giornata!", "uhm..."), BorderLayout.PAGE_END);
		contentPane.add(new MyVeryPiggyLabel(), BorderLayout.CENTER);

		// rendiamoci visibili
		pack();
		setVisible(true);
	}
}