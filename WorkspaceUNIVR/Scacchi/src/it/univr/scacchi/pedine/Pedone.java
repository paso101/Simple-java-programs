package it.univr.scacchi.pedine;

import it.univr.scacchi.Casella;
import it.univr.scacchi.CasellaVuota;
import it.univr.scacchi.Pedina;
import it.univr.scacchi.Scacchiera;

import javax.swing.ImageIcon;

public class Pedone extends Pedina {

	public Pedone(boolean colore, Scacchiera scacchiera) {
		super(new ImageIcon(colore == BIANCO ? "images/pedone_bianco.gif" : "images/pedone_nero.gif"), colore, scacchiera);
	}

	@Override
	protected boolean puoSpostarsiSu(Casella destinazione) {
		int dx = destinazione.getColonna() - getColonna();
		int dy = destinazione.getRiga() - getRiga();

		if (destinazione instanceof CasellaVuota)
			return dx == 0 &&
				((getColore() == BIANCO && dy == -1) ||
					(getColore() == NERO && dy == 1));
		else if (((Pedina) destinazione).getColore() != getColore())
			return (dx == 1 || dx == -1) &&
				((getColore() == BIANCO && dy == -1) ||
					(getColore() == NERO && dy == 1));
		else
			return false;
	}
}