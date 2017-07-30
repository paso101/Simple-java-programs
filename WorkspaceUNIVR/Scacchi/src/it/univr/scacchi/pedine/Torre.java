package it.univr.scacchi.pedine;

import it.univr.scacchi.Casella;
import it.univr.scacchi.CasellaVuota;
import it.univr.scacchi.Pedina;
import it.univr.scacchi.Scacchiera;

import javax.swing.ImageIcon;

public class Torre extends Pedina {

	public Torre(boolean colore, Scacchiera scacchiera) {
		super(new ImageIcon(colore == BIANCO ? "images/torre_bianca.gif" : "images/torre_nera.gif"), colore, scacchiera);
	}

	@Override
	protected boolean puoSpostarsiSu(Casella destinazione) {
		int dx = destinazione.getColonna() - getColonna();
		int dy = destinazione.getRiga() - getRiga();

		if (destinazione instanceof CasellaVuota || ((Pedina) destinazione).getColore() != getColore())
			return (dx == 0 || dy == 0) && nienteInMezzo(dx, dy);
		else
			return false;
	}
}