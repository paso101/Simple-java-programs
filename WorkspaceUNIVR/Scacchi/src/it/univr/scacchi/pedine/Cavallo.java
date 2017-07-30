package it.univr.scacchi.pedine;

import it.univr.scacchi.Casella;
import it.univr.scacchi.CasellaVuota;
import it.univr.scacchi.Pedina;
import it.univr.scacchi.Scacchiera;

import javax.swing.ImageIcon;

public class Cavallo extends Pedina {

	public Cavallo(boolean colore, Scacchiera scacchiera) {
		super(new ImageIcon(colore == BIANCO ? "images/cavallo_bianco.gif" : "images/cavallo_nero.gif"), colore, scacchiera);
	}

	@Override
	protected boolean puoSpostarsiSu(Casella destinazione) {
		int dx = destinazione.getColonna() - getColonna();
		int dy = destinazione.getRiga() - getRiga();

		if (destinazione instanceof CasellaVuota || ((Pedina) destinazione).getColore() != getColore())
			return dx != 0 && dy != 0 && Math.abs(dx) + Math.abs(dy) == 3;
		else
			return false;
	}
}