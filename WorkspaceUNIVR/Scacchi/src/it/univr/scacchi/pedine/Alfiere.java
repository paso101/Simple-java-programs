package it.univr.scacchi.pedine;

import it.univr.scacchi.Casella;
import it.univr.scacchi.CasellaVuota;
import it.univr.scacchi.Pedina;
import it.univr.scacchi.Scacchiera;

import javax.swing.ImageIcon;

public class Alfiere extends Pedina {

	public Alfiere(boolean colore, Scacchiera scacchiera) {
		super(new ImageIcon(colore == BIANCO ? "images/alfiere_bianco.gif" : "images/alfiere_nero.gif"), colore, scacchiera);
	}

	@Override
	protected boolean puoSpostarsiSu(Casella destinazione) {
		int dx = destinazione.getColonna() - getColonna();
		int dy = destinazione.getRiga() - getRiga();

		if (destinazione instanceof CasellaVuota || ((Pedina) destinazione).getColore() != getColore())
			return Math.abs(dx) == Math.abs(dy) && nienteInMezzo(dx, dy);
		else
			return false;
	}
}