package it.univr.SfideDiProgrammazione.Tron.Bots;

import it.univr.SfideDiProgrammazione.Tron.Giocatore;

public class Giocatore_Nico_v2 extends Giocatore	{
	
	public Giocatore_Nico_v2(String nome, int i, int j) {
		super(nome, i, j);
	}

	/**
	 * Funzione per implementare una strategia. Si ha a disposizio: - char[][]
	 * scacchiera --> la scacchiera - iAvversario, jAvversario --> coordinate
	 * dell'avversaio - this.posX, this.posY --> le proprie coordinate -
	 * storicoMosseAvversario --> arrayList delle mosse dell'avversario;
	 */
	int posY_inizio = this.posY;
	char[][] scacchiera_temp;
	
	@Override
	// riempie verticalmente la scacchiera ignorando l'avversario.
	// se parte a sx costeggia il bordo sx e si sposta lentamente a dx
	// viceversa se parte a dx
	protected char calcolaMossa(int iAvversario, int jAvversario, char[][] scacchiera) {

		//System.out.println("Nico_fill_V: Sono partito in Y = " + posY_inizio);
		//System.out.println("Nico_fill_V: Sono in (" + posX + ", " + posY + "). ");

		if (posY_inizio == 0) {
			if (mossaOK('U', scacchiera))
				return 'U';
			else if (mossaOK('L', scacchiera))
				return 'L';

			else if (mossaOK('D', scacchiera))
				return 'D';
			else
				return 'R';
		} else {
			if (mossaOK('U', scacchiera))
				return 'U';
			else if (mossaOK('R', scacchiera))
				return 'R';

			else if (mossaOK('D', scacchiera))
				return 'D';
			else
				return 'L';

		}

	}

	// verifica se la mossa è lecita = dentro la scacchiera e verso una casella
	// libera
	private boolean mossaOK(char a, char[][] scacchiera) {
		// controlla se resto dentro alla scacchiera
		int nuovaX = posX, nuovaY = posY;
		switch (a) {
		case 'U':
			nuovaX = posX - 1;
			break;
		case 'D':
			nuovaX = posX + 1;
			break;
		case 'L':
			nuovaY = posY - 1;
			break;
		case 'R':
			nuovaY = posY + 1;
			break;
		}
		// controlla se è dentro la scacchiera
		if ((nuovaX >= 0 && nuovaX < this.DIM) && (nuovaY >= 0 && nuovaY < this.DIM)) {
			// controlla se la la nuova casella è libera
			if (scacchiera[nuovaX][nuovaY] == '0')
				return true;
		}

		// se esco dalla scacchiera o se la casella è già visitata, ritorno
		// false
		return false;
	}
}