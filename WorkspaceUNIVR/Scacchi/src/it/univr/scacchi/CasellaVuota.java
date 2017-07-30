package it.univr.scacchi;


import javax.swing.ImageIcon;

public class CasellaVuota extends Casella {

	public CasellaVuota(Scacchiera scacchiera) {
		super(new ImageIcon("images/casella_vuota.gif"), scacchiera);
	}
}