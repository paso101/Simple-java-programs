package it.univr.scacchi.pedine;

import it.univr.scacchi.Casella;
import it.univr.scacchi.CasellaVuota;
import it.univr.scacchi.Pedina;
import it.univr.scacchi.Scacchiera;

import javax.swing.ImageIcon;

public class Regina extends Pedina {

	public Regina(boolean colore, Scacchiera scacchiera) {
		super(new ImageIcon(colore == BIANCO ? "images/regina_bianca.gif" : "images/regina_nera.gif"), colore, scacchiera);
	}

	@Override
	protected boolean puoSpostarsiSu(Casella destinazione) {
		int dx = destinazione.getColonna() - getColonna();
		int dy = destinazione.getRiga() - getRiga();

		if (destinazione instanceof CasellaVuota || ((Pedina) destinazione).getColore() != getColore())
			return (dx == 0 || dy == 0 || Math.abs(dx) == Math.abs(dy)) && nienteInMezzo(dx, dy);
		else
			return false;
	}
}