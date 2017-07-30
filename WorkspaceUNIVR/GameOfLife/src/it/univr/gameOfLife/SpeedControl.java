/*
 * Game of Life, version 1.0, March 26, 2013
 */

package it.univr.gameOfLife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JSlider;

/**
 * 
 * @author Damiano Pasotto VR363785 mailto: damiano.pasotto@univr.it
 * @author Lara Scarpari VR361106 mailto: lara.scarpari@univr.it
 * @version 1.0<br><br>
 * 
 * NOTE:<br>
 * <ul>
 * <li> Dopo l'apparizione della finestra principale <i>Game of Life</i>,
 * per far comparire la griglia occorre spostare il cursore nella parte superiore della finestra,
 * causando cos&igrave; la comparsa del menù e dei bottoni.
 * A questo punto &egrave; necessario premere il bottone <i>RESET</i>.</li>
 * <li> L'eventuale spostamento non voluto delle finestre nello schermo
 * &egrave; dovuto alle diverse tipologie di schermo stesso
 * e al fatto che non abbiamo fatto dipendere dalle misure dello schermo
 * le dimensioni di finestre e oggetti.</li>
 * <li> Le immagini contenute nella directory <i>images</i>
 * provengono da http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life.</li>
 * </ul>
 *
 */

public class SpeedControl extends JFrame {

	/**
	 * Il regolatore di velocit&agrave;. 
	 */
	
	private JSlider slider;
	
	/**
	 * Il campo che informa se la finestra dello slider sia visibile o non visibile.
	 */
	
	protected boolean sliderMenuON = true;
	
	/**
	 * Costruisce una finestra di dimensioni 500 * 100 pixel in posizione (50, 750) 
	 * contenente uno slider.
	 */
	
	public SpeedControl() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Speed Control");
		setLocation(50, 740);
		setSize(500, 100);
		setResizable(false);
		setVisible(true);
		
		setSlider();
			
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		container.setBackground(Color.WHITE);
		container.add(slider, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	/**
	 * Imposta le caratteristiche dello slider.<br>
	 * Questo ha valore minimo 0 e valore massimo 1800.
	 * Alla sua comparsa assume valore intermedio 900.
	 */
	
	private final void setSlider() {
		slider = new JSlider(JSlider.HORIZONTAL, 0, 1800, 900);
		slider.setBorder(BorderFactory.createTitledBorder("Speed Control"));
		slider.setMajorTickSpacing(500);
		slider.setMinorTickSpacing(100);
		slider.setPaintTicks(true);
		slider.setPaintLabels(false);
	}
	
	/**
	 * Consente di conoscere il valore attuale dello slider.
	 * 
	 * @return il valore attuale dello slider.
	 */
	
	protected int getValue() {
		return slider.getValue();
	}
	
	/**
	 * Rende visibile la finestra dello slider.
	 */
	
	protected void appeare() {
		setVisible(true);
	}
	
	/**
	 * Rende invisibile la finestra dello slider.
	 */
	
	protected void disappeare() {
		setVisible(false);
	}
	
	/**
	 * Ritorna la stringa "ON" oppure la stringa "OFF" a seconda che la finestra dello slider sia invisibile o visibile.
	 * 
	 * @return "OFF" se la finestra dello slider &egrave; visibile, "ON" altrimenti
	 */
	
	protected String getONOFF() {
		return sliderMenuON == true? "OFF" : "ON";
	}
	
}