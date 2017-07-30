/**
 * CNF-SAT, version 1.0, October 18 , 2014
 */

package CNF_SAT;

/**
 * 
 * @author Damiano Pasotto mailto: damiano.pasotto@univr.it VR363785
 * @version 1.0
 * 
 * NOTE:<br>
 * <ul>
 * <li> Dopo l'apparizione della finestra principale <i>CNF-SAT</i>,
 * per far eseguire l'algoritmo &egrave necessario inserire nel textfield l'espression cnf<br>
 * e premere invio. Poi, premere il pulsante di verifica per il calcolo.
 * </ul>
 *
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Application extends JFrame {
	
	/**
	 * Etichetta "Inserisci espressione CNF da valutare:".
	 */
	private JLabel label;
	
	/**
	 * Etichetta "Esempio: ( A | b ) & ( B | A ) & ( a | c ) & ( C | a )".
	 */
	private JLabel label2;
	
	/**
	 */
	private JLabel labelSoddisfacibile;
	
	/**
	 */
	private JLabel labelNonSoddisfacibile;
	
	/**
	 * Il textfield per inserire l'esressione da valutare.
	 */
	private TextField textField;
	
	
	/**
	 * Il checkUpButton per eseguire l'algoritmo.
	 */
	private JButton checkUpButton;
	
	
	/**
	 * Stringa contenente l'espression cnf da valutare.
	 */
	private String cnf;
	
	
	/**
	 * ArrayList contenente i simboli dell'alfabeto.
	 */	private ArrayList<Character> symbols;

	private BufferedWriter bW;
	 
	 /**
		 * <p> Costruisce una finestra di dimensioni fissa 750 * 600 pixel
		 * di colore grigio chiaro 
		 * in posizione 500 * 100 pixel  contenente un textfield, un pulsante e
		 * due etichette.</p>
		 */

	public Application() {
			
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setTitle("CNF-SAT");
	setLocation(500, 100);
	setSize(750, 600);
	setResizable(false);
	setLabels();
	setTextField();
	setButton();
			
	Container container = getContentPane();
	container.setLayout(null);
	container.setBackground(Color.lightGray);
	container.add(label);
	container.add(label2);
	container.add(textField);
	container.add(checkUpButton);
	setVisible(true);

	}
	
	/**
	 * <p> Configura tutte le etichetta.<\p>
	 */
		private void setLabels() {
		label = new JLabel();
		label.setText("Inserisci espressione CNF da valutare:");
		label.setBounds(10,10,500,100);
		
		
		label2 = new JLabel();
		label2.setText("Esempio: ( A | b ) & ( B | A ) & ( a | c ) & ( C | a )");
		label2.setBounds(10,55,500,100);
		
		
		labelSoddisfacibile = new JLabel();
		labelSoddisfacibile.setText("SODDISFACIBILE");
		labelSoddisfacibile.setBounds(750/2 - 75, 500,500,100);
		
		labelNonSoddisfacibile = new JLabel();
		labelNonSoddisfacibile.setText("NON SODDISFACIBILE");
		labelNonSoddisfacibile.setBounds(750/2 - 75, 500,500,100);
		
	}
		
	/**
	 * <p> Configura seconda etichetta.<\p>
	 */
	private void setTextField() {
		
		textField = new TextField();
		textField.setBackground(Color.white);
		textField.setBounds(25, 160, 700, 50);
		AscTextField ascTextField = new AscTextField();
		textField.addActionListener(ascTextField);
	}
	
	/**
	 * <p> Configura bottone.<\p>
	 */
	private void setButton() {
		
		checkUpButton = new JButton("CHECK UP");
		checkUpButton.setBounds(25 + 200, 260, 300, 200);
		checkUpButton.setBackground(Color.GREEN);
		checkUpButton.setToolTipText("START");
		AscCheckUpButton ascCheckUpButton = new AscCheckUpButton();
	
		checkUpButton.addActionListener(ascCheckUpButton);
	}
	
	/**
	 * Classe interna che realizza l'ascoltatore del TextField.
	 */	
	public class AscTextField implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			cnf = textField.getText();	
			if(!cnf.isEmpty()) 				
				System.out.println("CNF inserita : " + cnf);	
			}
		
		
	}
	
	/**
	 * Classe interna che realizza l'ascoltatore del CheckUpButton.
	 */	
	public class AscCheckUpButton implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {

			remove(labelSoddisfacibile);
			remove(labelNonSoddisfacibile);

			try {
				
				/**
				 * <p> Metodo che chiamer&agrave l'esecuzione dell'algoritmo. </p> 
				 */
				starting();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			repaint();


			
		}
	}

		private void starting() throws IOException {
			
			/** <p> Passaggio dell'espression CNF letta tutta in minuscolo in quanto mi serve solo sapere il numero di letterali</p>*/		
			Alphabet alphabet = new Alphabet(cnf.toLowerCase());
			
			/** <p> Ottengo dell'espression CNF come array di caratteri</p> */
			this.symbols = alphabet.getAlphabet();
			System.out.println("Alfabeto = " + alphabet.toString());
			
			/** <p> Ottengo esèressione cnf come ArrayList di stringhe, ognula rappresentante una clausola. </p> */
			Clauses clauses = new Clauses(cnf);
			ArrayList <String> allClauses = clauses.getClauses();
			System.out.println("C {C1, C2, ..., Cn} = " + clauses.toString());
			System.out.println();
			
			/** <p> Istanziazione dell'oggetto Algorithm. </p>*/
			Algorithm algorithm = new Algorithm();
			
			/** <p> Costrzione dell'albero. </p>*/
			Node node = algorithm.makeTree(this.symbols, allClauses, 0);
			
			/** <p> Trasformo l'abero in un arrayList.<br>
			 * Se l'array contiene una stringa "TRUE" stampo "Soddisfacibile.<br>
			 * Altrimenti stampo la "NON Soddisfacibile". </p>*/
			if(node.toArrayList().contains("TRUE")) {
				System.out.println("\n\nSoddisfacibile");
				add(labelSoddisfacibile);
			}
			else {
				System.out.println("\n\nNON Soddisfacibile");
				add(labelNonSoddisfacibile);
			}
			
			/**<p> Assegno una key progressiva alle label di ogni nodo.</p>*/
			algorithm.prefixVisit(node);
			System.out.println();
			
			/**<p> Creo file con "Tree.gv"</p>*/
		    FileWriter fileWriter = new FileWriter("Tree.gv");
		    bW = new BufferedWriter (fileWriter);
	
		    /**<p> Scriv ul fle creato precedentemente il pseudocodice per rappresentare l'albero con grphivz.</p>*/
		    bW.write(algorithm.drawTree(node));
		    
		    bW.flush();

			
		}

		
}
