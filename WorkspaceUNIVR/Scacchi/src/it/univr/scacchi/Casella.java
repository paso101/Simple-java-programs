package it.univr.scacchi;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;

public abstract class Casella extends JButton {

	/**
	 * La scacchiera in cui si trova questa casella.
	 */

	private final Scacchiera scacchiera;

	/**
	 * La riga in cui si trova questa casella.
	 */

	private int riga;

	/**
	 * La colonna in cui si trova questa casella.
	 */

	private int colonna;

	/**
	 * La dimensione di una casella, in pixel.
	 */

	public final static int DIMENSIONE = 35;

	/**
	 * Crea una casella con l'immagine indicata.
	 *
	 * @param immagine l'immagine
	 * @param scacchiera la scacchiera a cui appartiene questa casella
	 */

	protected Casella(Icon immagine, final Scacchiera scacchiera) {
		super(immagine);

		this.scacchiera = scacchiera;

		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				scacchiera.hannoCliccato(Casella.this);
			}			
		});
	}

	/**
	 * Sposta questa casella alle coordinate indicate.
	 *
	 * @param riga la riga
	 * @param colonna la colonna
	 */

	public void spostaIn(int riga, int colonna) {
		this.riga = riga;
		this.colonna = colonna;

		setBounds(colonna * Casella.DIMENSIONE, riga * Casella.DIMENSIONE,
				Casella.DIMENSIONE, Casella.DIMENSIONE);

		if ((riga + colonna) % 2 == 0)
			setBackground(Color.WHITE);
		else
			setBackground(Color.GRAY);
	}

	public Scacchiera getScacchiera() {
		return scacchiera;
	}

	public int getRiga() {
		return riga;
	}

	public int getColonna() {
		return colonna;
	}
}