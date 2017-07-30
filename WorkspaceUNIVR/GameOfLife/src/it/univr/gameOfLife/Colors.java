/*
 * Game of Life, version 1.0, March 26, 2013
 */

package it.univr.gameOfLife;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

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

public class Colors extends JFrame {
	
	/**
	 * I nomi dei colori che le cellule vive potranno assumere.
	 */
	
	private String[] colorNames = {"Gray", "Black", "Dark Magenta", "Dark Blue", "Light Blue", "Dark Green", "Light Green", "Yellow", "Orange", "Strawberry", "Salmon", "Brown"};
	
	/**
	 * Il colore corrente assunto dalle cellule vive.
	 */
	
	protected Color currentColor = new Color(0, 0, 0);
	
	/**
	 * L'ascoltatore dei bottoni dei colori.
	 */
	
	private AscColors ascColors = new AscColors();
	
	/**
	 * Il campo che informa se il colore &egrave; stato appena modificato.
	 */
	
	protected boolean colorChanged = false;
	
	/**
	 * Il campo che informa se la finestra dei colori sia visibile o non visibile.
	 */
	
	protected boolean colorMenuON = true;
	
	/**
	 * Costruisce una finestra di dimensioni 42 * 560 pixel
	 * in posizione (560, 50)
	 * avente sfondo bianco
	 * e che si adatta nella sua dimensione al contenuto.
	 */
	
	public Colors() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(560, 50);
		setSize(42, 560);
		setResizable(false);
		
		setButtons();
		
		Container container = getContentPane();
		container.setLayout(new GridLayout(12, 1));
		container.setBackground(Color.WHITE);
		
		pack();
		setVisible(true);
	}
	
	/**
	 * Configura i bottoni dei colori.<br>
	 * Ogni bottone ha sfondo bianco e ha associata una scritta a comparsa.
	 */
	
	private void setButtons() {
		for(int pos = 0; pos < colorNames.length; pos++) {
			ImageIcon colorImage = new ImageIcon("colors/" + colorNames[pos] + ".jpg");
			JButton colorButton = new JButton(colorImage);
			colorButton.setSize(42, 42);
			colorButton.setBackground(Color.WHITE);
			colorButton.setToolTipText(colorNames[pos]);
			colorButton.addActionListener(ascColors);
			getContentPane().add(colorButton);
		}
	}
	
	/**
	 * Permette di ottenere il colore corrente delle cellule vive.
	 * 
	 * @return il colore corrente delle cellule vive
	 */
	
	protected Color getCurrentColor() {
		return currentColor;
	}
	
	/**
	 * L'ascoltatore dei bottoni dei colori.
	 */
	
	public class AscColors implements ActionListener {
		
		/**
		 * Quando viene premuto un qualsiasi bottone dei colori
		 * viene eseguito il seguente metodo.<br>
		 * Imposta nelle cellule vive il colore relativo al bottone premuto
		 * e avvisa che il colore &egrave; stato appena modificato.
		 */

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton buttonSelected = (JButton) e.getSource();
			String buttonName = buttonSelected.getToolTipText();
			
			if(buttonName.equals(colorNames[0]))
				currentColor = new Color(102, 102, 102);
			else if(buttonName.equals(colorNames[1]))
				currentColor = new Color(0, 0, 0);
			else if(buttonName.equals(colorNames[2]))
				currentColor = new Color(102, 0, 102);
			else if(buttonName.equals(colorNames[3]))
				currentColor = new Color(0, 0, 128);
			else if(buttonName.equals(colorNames[4]))
				currentColor = new Color(131, 202, 255);
			else if(buttonName.equals(colorNames[5]))
				currentColor = new Color(53, 94, 0);
			else if(buttonName.equals(colorNames[6]))
				currentColor = new Color(87, 157, 28);
			else if(buttonName.equals(colorNames[7]))
				currentColor = new Color(255, 181, 21);
			else if(buttonName.equals(colorNames[8]))
				currentColor = new Color(255, 99, 9);
			else if(buttonName.equals(colorNames[9]))
				currentColor = new Color(255, 51, 102);
			else if(buttonName.equals(colorNames[10]))
				currentColor = new Color(255, 128, 128);
			else if(buttonName.equals(colorNames[11]))
				currentColor = new Color(102, 51, 0);
			
			colorChanged = true;
		}
		
	}
	
	/**
	 * Informa se il colore delle cellule vive sia stato appena modificato.
	 * 
	 * @return true se il colore &egrave; stato appena modificato
	 */
	
	protected boolean colorIsChanged() {
		return colorChanged;
	}
	
	/**
	 * Rende visibile la finestra dei colori.
	 */
	
	protected void appeare() {
		setVisible(true);
	}
	
	/**
	 * Rende invisibile la finestra dei colori.
	 */
	
	protected void disappeare() {
		setVisible(false);
	}
	
	/**
	 * Ritorna la stringa "ON" oppure la stringa "OFF" a seconda che la finestra dei colori sia invisibile o visibile.
	 * 
	 * @return "OFF" se la finestra dei colori &egrave; visibile, "ON" altrimenti
	 */
	
	protected String getONOFF() {
		return colorMenuON == true? "OFF" : "ON";
	}

}
