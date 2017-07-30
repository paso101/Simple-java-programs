package it.univr.scacchi.pedine;

import it.univr.scacchi.Casella;
import it.univr.scacchi.CasellaVuota;
import it.univr.scacchi.Pedina;
import it.univr.scacchi.Scacchiera;

import javax.swing.ImageIcon;

public class Re extends Pedina {

	public Re(boolean colore, Scacchiera scacchiera) {
		super(new ImageIcon(colore == BIANCO ? "images/re_bianco.gif" : "images/re_nero.gif"), colore, scacchiera);
	}

	@Override
	protected boolean puoSpostarsiSu(Casella destinazione) {
		int dx = getColonna() - destinazione.getColonna();
		int dy = getRiga() - destinazione.getRiga();

		if (destinazione instanceof CasellaVuota || ((Pedina) destinazione).getColore() != getColore())
			return dx <= 1 && dx >= -1 && dy <= 1 && dy >= -1;
		else
			return false;
	}
}