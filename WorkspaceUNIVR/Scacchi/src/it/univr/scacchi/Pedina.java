package it.univr.scacchi;

import javax.swing.Icon;

public abstract class Pedina extends Casella {

	/**
	 * Il colore di questa pedina.
	 */
	
	private final boolean colore;

	/**
	 * Il colore bianco.
	 */

	public final static boolean BIANCO = true;

	/**
	 * Il colore nero.
	 */

	public final static boolean NERO = false;

	/**
	 * Crea una pedina con l'immagine indicata.
	 *
	 * @param immagine l'immagine
	 * @param colore il colore della pedina
	 * @param scacchiera la scacchiera a cui appartiene la pedina
	 */

	protected Pedina(Icon immagine, boolean colore, Scacchiera scacchiera) {
		super(immagine, scacchiera);

		this.colore = colore;
	}

	public boolean getColore() {
		return colore;
	}

	/**
	 * Determina se questa pedina pu&ograve; spostarsi sulla casella indicata.
	 *
	 * @param destinazione la casella destinazione
	 * @return true sse lo spostamento &egrave; possibile
	 */

	protected abstract boolean puoSpostarsiSu(Casella destinazione);

	/**
	 * Determina se non c'&egrave; alcun pezzo degli scacchi tra questo
	 * e quello all'offset indicato.
	 *
	 * @param dx l'offset sulle ascisse
	 * @param dy l'offset sulle ordinate
	 * @return true sse tale condizione vale
	 */

	protected final boolean nienteInMezzo(int dx, int dy) {
		int numeroPassi = Math.max(Math.abs(dx), Math.abs(dy));
		int colonna = getColonna(), riga = getRiga();
		int passi = 0;

		while (passi < numeroPassi - 1) {
			if (dx > 0)
				colonna++;
			else if (dx < 0)
				colonna--;

			if (dy > 0)
				riga++;
			else if (dy < 0)
				riga--;

			passi++;

			if (!(getScacchiera().getCasella(riga, colonna) instanceof CasellaVuota))
				return false;
		}

		return true;
	}
}