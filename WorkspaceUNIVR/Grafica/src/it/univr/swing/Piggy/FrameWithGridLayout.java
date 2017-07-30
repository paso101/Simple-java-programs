package it.univr.swing.Piggy;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Un frame con layout di tipo GridLayout.
 * 
 * @author <A HREF="mailto:fausto.spoto@univr.it">Fausto Spoto</A>
 */

public class FrameWithGridLayout extends JFrame {

	public static void main(String[] args) {
		new FrameWithGridLayout("Here I am!");
	}

	public FrameWithGridLayout(String title) {
		super(title);

		// indichiamo la dimensione della finestra
		setBounds(100, 200, 400, 400);

		// indichiamo cosa deve accadere quando si clicca sul pulsante di chiusura:
		// il programma deve essere terminato
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = getContentPane();
		// 2 righe e 3 colonne, con 100 pixel di distanza tra
		// un componente e l'altro
		contentPane.setLayout(new GridLayout(2, 3, 100, 100));

		contentPane.add(new JLabel("ciao amico"));
		contentPane.add(new JLabel("numero due"));
		contentPane.add(new JLabel("numero tre"));

		JLabel quattro = new MyLabelWithToolTip("che bella giornata!", "Questa label è proprio bella!");
		contentPane.add(quattro);

		JLabel cinque = new MyVeryPiggyLabel();
		contentPane.add(cinque);

		// rendiamo visibile la finestra
		pack();
		setVisible(true);
	}
}