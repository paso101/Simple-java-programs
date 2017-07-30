package it.univr.scacchi;

import it.univr.scacchi.pedine.Alfiere;
import it.univr.scacchi.pedine.Cavallo;
import it.univr.scacchi.pedine.Pedone;
import it.univr.scacchi.pedine.Re;
import it.univr.scacchi.pedine.Regina;
import it.univr.scacchi.pedine.Torre;

import java.awt.Color;
import javax.swing.JFrame;

public class Scacchiera extends JFrame {

	/**
	 * Il numero di scacchi per ogni lato.
	 */

	public final static int NUMERO_CASELLE = 8;

	/**
	 * L'ultima pedina cliccata dall'utente.
	 */

	private Pedina ultimaPedinaCliccata;

	/**
	 * Una tabella che ricorda cosa c'e' in ogni casella.
	 */

	private final Casella[][] caselle = {
		{ new Torre(Pedina.NERO, this), new Cavallo(Pedina.NERO, this), new Alfiere(Pedina.NERO, this), new Regina(Pedina.NERO, this), new Re(Pedina.NERO, this), new Alfiere(Pedina.NERO, this), new Cavallo(Pedina.NERO, this), new Torre(Pedina.NERO, this) },
		{ new Pedone(Pedina.NERO, this), new Pedone(Pedina.NERO, this), new Pedone(Pedina.NERO, this), new Pedone(Pedina.NERO, this), new Pedone(Pedina.NERO, this), new Pedone(Pedina.NERO, this), new Pedone(Pedina.NERO, this), new Pedone(Pedina.NERO, this) },
		{ new CasellaVuota(this), new CasellaVuota(this), new CasellaVuota(this), new CasellaVuota(this), new CasellaVuota(this), new CasellaVuota(this), new CasellaVuota(this), new CasellaVuota(this) },
		{ new CasellaVuota(this), new CasellaVuota(this), new CasellaVuota(this), new CasellaVuota(this), new CasellaVuota(this), new CasellaVuota(this), new CasellaVuota(this), new CasellaVuota(this) },
		{ new CasellaVuota(this), new CasellaVuota(this), new CasellaVuota(this), new CasellaVuota(this), new CasellaVuota(this), new CasellaVuota(this), new CasellaVuota(this), new CasellaVuota(this) },
		{ new CasellaVuota(this), new CasellaVuota(this), new CasellaVuota(this), new CasellaVuota(this), new CasellaVuota(this), new CasellaVuota(this), new CasellaVuota(this), new CasellaVuota(this) },
		{ new Pedone(Pedina.BIANCO, this), new Pedone(Pedina.BIANCO, this), new Pedone(Pedina.BIANCO, this), new Pedone(Pedina.BIANCO, this), new Pedone(Pedina.BIANCO, this), new Pedone(Pedina.BIANCO, this), new Pedone(Pedina.BIANCO, this), new Pedone(Pedina.BIANCO, this) },
		{ new Torre(Pedina.BIANCO, this), new Cavallo(Pedina.BIANCO, this), new Alfiere(Pedina.BIANCO, this), new Regina(Pedina.BIANCO, this), new Re(Pedina.BIANCO, this), new Alfiere(Pedina.BIANCO, this), new Cavallo(Pedina.BIANCO, this), new Torre(Pedina.BIANCO, this) }
	};

	/**
	 * Crea e disegna la scacchiera.
	 */

	public Scacchiera() {
		super("scacchi");

		esciSeChiusa();
		nonUsareLayoutManager();
		fissaLeDimensioni();
		disegnaTutteLeCaselle();
	}

	private void esciSeChiusa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void nonUsareLayoutManager() {
		getContentPane().setLayout(null);
	}

	private void fissaLeDimensioni() {
		getContentPane().setBounds(40, 40,
			Casella.DIMENSIONE * NUMERO_CASELLE,
			Casella.DIMENSIONE * NUMERO_CASELLE);
		setSize(Casella.DIMENSIONE * NUMERO_CASELLE + 1000,
				Casella.DIMENSIONE * NUMERO_CASELLE + 1000);
		setResizable(false);
	}

	private void disegnaTutteLeCaselle() {
		int riga = 0;
		for (Casella[] rigaDiPedine: caselle) {
			int colonna = 0;
			for (Casella casella: rigaDiPedine)
				posiziona(casella, riga, colonna++);
			riga++;
		}
	}

	/**
	 * Posiziona una casella alla riga e colonna indicate di questa scacchiera.
	 *
	 * @param casella la casella
	 * @param riga la riga
	 * @param colonna la colonna
	 */

	public void posiziona(Casella casella, int riga, int colonna) {
		getContentPane().remove(caselle[riga][colonna]);
		getContentPane().add(casella);
		caselle[riga][colonna] = casella;
		casella.spostaIn(riga, colonna);
	}

	/**
	 * Un semplice main di prova.
	 *
	 * @param args argomenti da linea di comando (non usati)
	 */

	public static void main(String[] args) {
		new Scacchiera().setVisible(true);
	}

	/**
	 * Prende nota che &egrave; stato cliccata la casella indicata.
	 *
	 * @param casella la casella che &egrave; stata cliccata
	 */

	protected void hannoCliccato(Casella casella) {
		if (ultimaPedinaCliccata != null && ultimaPedinaCliccata.puoSpostarsiSu(casella)) {
			posiziona(new CasellaVuota(this),
				ultimaPedinaCliccata.getRiga(), ultimaPedinaCliccata.getColonna());
			posiziona(ultimaPedinaCliccata, casella.getRiga(), casella.getColonna());
			ultimaPedinaCliccata = null;

			// dobbiamo ridisegnare la finestra
			repaint();
		}
		else if (casella instanceof Pedina)
			ultimaPedinaCliccata = (Pedina) casella;
		else
			ultimaPedinaCliccata = null;
	}

	/**
	 * Restituisce la casella alle seguenti coordinate.
	 *
	 * @param riga la riga
	 * @param colonna la colonna
	 * @return la casella a tali coordinate
	 */

	protected Casella getCasella(int riga, int colonna) {
		return caselle[riga][colonna];
	}
}