/*
 * Game of Life, version 1.0, March 26, 2013
 */

package it.univr.gameOfLife;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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

public class GameOfLife extends JFrame {
	
	// GRIGLIA
	
	/**
	 * La matrice che rappresenta il mondo di cellule, utilizzata per disegnare la griglia.	
	 */
	
	private int[][] world = new int[50][50];
		
	/**
	 * La dimensione della cellula espressa in pixel.
	 */
		
	private final static int CELL_SIZE = 10;
	
	// MENÙ

	/**
	 * I nomi delle opzioni del menù <i>File</i>.
	 */
	
	private String[] fileNames = {"Colors   OFF", "Slider    OFF", "Exit"};
	
	/**
	 * I nomi delle figure fisse da inserire nella griglia.
	 */
	
	private String[] stillLifesType = {"Block", "Beehive", "Loaf", "Boat"};
	
	/**
	 * I nomi degli oscillatori da inserire nella griglia.
	 */
	
	private String[] oscillatorsType = {"Blinker", "Toad", "Beacon", "Pulsar"};
	
	/**
	 * I nomi delle navicelle da inserire nella griglia.
	 */
	
	private String[] spaceShipsType = {"Glider", "LWSS"};
	
	/**
	 * La barra dei menù.
	 */
	
	private JMenuBar menuBar = new JMenuBar();
	
	/**
	 * Il menù <i>File</i>.
	 */
	
	private JMenu fileMenu;
	
	/**
	 * Il menù delle figure fisse.
	 */
	
	private JMenu stillLifesMenu;
	
	/**
	 * Il menù degli oscillatori.
	 */
	
	private JMenu oscillatorsMenu;
	
	/**
	 * Il menù delle navicelle.
	 */
		
	private JMenu spaceShipsMenu;
	
	/**
	 * Il menù dei cannoni.
	 */
	
	private JMenu gunsMenu;
	
	// PULSANTI
	
	/**
	 * Il pulsante <i>START</i>.
	 */
	
	private JButton startButton;
	
	/**
	 * Il pulsante <i>STOP</i>.
	 */
	
	private JButton stopButton;
	
	/**
	 * Il pulsante <i>RESET</i>.
	 */
	
	private JButton resetButton;
	
	/**
	 * Il pulsante <i>RANDOM</i>.
	 */
	
	private JButton randomButton;
		
	// GENERAZIONI
	
	/**
	 * L'etichetta che visualizza il numero progressivo di generazione.
	 */
		
	private JLabel generations;
	
	/**
	 * L'intero che rappresenta la generazione corrente.
	 */
	
	private int numberOfGenerations = 0;
	
	// ASCOLTATORI
	
	/**
	 * L'ascoltatore del pulsante <i>START</i>.
	 */
	
	private AscStart ascStart = new AscStart();
	
	/**
	 * L'ascoltatore del pulsante <i>STOP</i>.
	 */
	
	private AscStop ascStop = new AscStop();
	
	/**
	 * L'ascoltatore del pulsante <i>RESET</i>.
	 */
	
	private AscReset ascReset = new AscReset();
	
	/**
	 * L'ascoltatore del pulsante <i>RANDOM</i>.
	 */
	
	private AscRandom ascRandom = new AscRandom();
	
	/**
	 * L'ascoltatore del click del mouse.
	 */
	
	private AscMouse ascMouse = new AscMouse();
	
	// THREAD
	
	/**
	 * La matrice ausiliaria usata per calcolare il nuovo stato di ogni cellula.
	 */
	
	private int [][] temp = new int[50][50];
	
	/**
	 * Il numero di thread usate dall'applicazione.
	 */
	
	private int numberOfThreads;
	
	/**
	 * La riga della matrice da cui le thread partono per riempire la matrice stessa.
	 */
	
	private int X = 0;
	
	/**
	 * L'array di thread.
	 */
	
	private Slave[] slaves;
	
	// SLIDER
	
	/**
	 * Il regolatore di velocit&agrave; di passaggio da una generaziona alla successiva.
	 */
	
	private final SpeedControl speedControl = new SpeedControl();
	
	// COLORS
	
	/**
	 * La finestra contenente i colori selezionabili per mutare il colore delle cellule vive.
	 */
	
	private final Colors colors = new Colors();
	
	/**
	 * La finestra in cui compaiono le immagini delle figure per mostrarne l'evoluzione.
	 */
	
	private Pattern pattern;
	
	// ALTRI CAMPI
	
	/**
	 * La variabile utilizzata per iniziare e fermare il gioco.
	 */
	
	private boolean nextGeneration = false;
	
	/**
	 * La variabile che permette la comparsa iniziale di una scritta contenente
	 * un'indicazione per far apparire la griglia.
	 */
	
	private boolean beginning = true;
	
	/**
	 * La variabile utilizzata per indicare se il menù sia attivo o meno,
	 * ovvero se sia stata cliccata una voce del menù per inserire una figura.
	 */
	
	private boolean menuON = false;
	
	/**
	 * La voce del menù selezionata.
	 */
	
	private String patternSelectedMenu = null;
	
	/**
	 * <p> Costruisce una finestra di dimensioni 500 * 650 pixel
	 * in posizione 50 * 50 pixel
	 * contenente una barra dei menu,
	 * quattro pulsanti per regolare il gioco,
	 * un'etichetta per tenere il conto del passaggio delle generazioni
	 * e una griglia di gioco in cui si accendo (nascono) e spengono (muoiono) delle cellule.</p>
	 * 
	 * <p> Il numero di thread usate dall'applicazione &egrave; passato come parametro al costruttore.</p>
	 * 
	 * @param numberOfThreads il numero di thread usate dal programma
	 * @throws IllegalArgumentException il numero di thread deve essere maggiore o uguale a 1
	 */
	
	public GameOfLife(int numberOfThreads) {
		
		if(numberOfThreads < 1)
			throw new IllegalArgumentException("Occorre almeno una thread");
		this.numberOfThreads = numberOfThreads;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Game of Life");
		setLocation(50, 50);
		setSize(500, 650);
		setResizable(false);
		
		setMenu();
		setButtons();
		setLabel();
		
		Container container = getContentPane();
		container.setLayout(null);
		container.setBackground(Color.WHITE);
		
		container.add(startButton);
		container.add(stopButton);
		container.add(resetButton);
		container.add(randomButton);
		container.add(generations);

		addMouseListener(ascMouse);
		
		setVisible(true);
	}
	
	// IMPOSTAZIONI INIZIALI
	
	/**
	 * Configura la barra dei menù.<br><br>
	 * 
	 * <p>"File" contiene le seguenti voci:
	 * <ul>
	 * <li>Colors</li>
	 * <li>Slider</li>
	 * <li>Exit</li>
	 * </ul>
	 * </p>
	 * 
	 * <p>"Still Lifes" contiene le seguenti voci:
	 * <ul>
	 * <li>Block</li>
	 * <li>Beehive</li>
	 * <li>Loaf</li>
	 * <li>Boat</li>
	 * </ul>
	 * </p>
	 * 
	 * <p>"Oscillators" contiene le seguenti voci:
	 * <ul>
	 * <li>Blinker</li>
	 * <li>Toad</li>
	 * <li>Beacon</li>
	 * <li>Pulsar</li>
	 * </ul>
	 * </p>
	 * 
	 * <p>"Space Ships" contiene le seguenti voci:
	 * <ul>
	 * <li>Glider</li>
	 * <li>LWSS</li>
	 * </ul>
	 * </p>
	 * 
	 * <p>"Guns" contiene le seguenti voci:
	 * <ul>
	 * <li>Gosper's Glider Gunr</li>
	 * </ul>
	 * </p>
	 */
	
	private void setMenu() {
		AscPatterns ascPatterns = new AscPatterns();
		AscFileMenu ascFileMenu = new AscFileMenu();
		
		fileMenu = new JMenu("File");
		fileMenu.setToolTipText("File");
		for(int pos = 0; pos < fileNames.length; pos++) {
			JMenuItem item = new JMenuItem(fileNames[pos]);
			item.setBackground(new Color(200, 0, 100));
			item.addActionListener(ascFileMenu);
			fileMenu.add(item);
			if(pos != fileNames.length - 1)
				fileMenu.addSeparator();
		}
		
		menuBar.add(fileMenu);
		
		stillLifesMenu = new JMenu("Still Lifes");
		stillLifesMenu.setToolTipText("Patterns which stay still");
		for(int pos = 0; pos < stillLifesType.length; pos++) {
			JMenuItem item = new JMenuItem(stillLifesType[pos]);
			item.setBackground(new Color(200, 0, 100));
			item.addActionListener(ascPatterns);
			stillLifesMenu.add(item);
			if(pos != stillLifesType.length - 1)
				stillLifesMenu.addSeparator();
		}
		menuBar.add(stillLifesMenu);
		
		oscillatorsMenu = new JMenu("Oscillators");
		oscillatorsMenu.setToolTipText("Patterns which oscillate");
		for(int pos = 0; pos < oscillatorsType.length; pos++) {
			JMenuItem item = new JMenuItem(oscillatorsType[pos]);
			item.setBackground(new Color(200, 0, 100));
			item.addActionListener(ascPatterns);
			oscillatorsMenu.add(item);
			if(pos != oscillatorsType.length - 1)
				oscillatorsMenu.addSeparator();
		}
		menuBar.add(oscillatorsMenu);
		
		spaceShipsMenu = new JMenu("Space Ships");
		spaceShipsMenu.setToolTipText("Patterns which move around on the gameboard");
		for(int pos = 0; pos < spaceShipsType.length; pos++) {
			JMenuItem item = new JMenuItem(spaceShipsType[pos]);
			item.setBackground(new Color(200, 0, 100));
			item.addActionListener(ascPatterns);
			spaceShipsMenu.add(item);
			if(pos != spaceShipsType.length - 1)
				spaceShipsMenu.addSeparator();
		}
		menuBar.add(spaceShipsMenu);
		
		gunsMenu = new JMenu("Guns");
		gunsMenu.setToolTipText("Patterns which shoot out moving objects");
		JMenuItem item = new JMenuItem("Gosper's Glider Gun");
		item.setBackground(new Color(200, 0, 100));
		item.addActionListener(ascPatterns);
		gunsMenu.add(item);
		menuBar.add(gunsMenu);
		
		menuBar.setBackground(Color.WHITE);
		
		setJMenuBar(menuBar);		
	}
	
	/**
	 * Configura i bottoni <i>START</i>, <i>STOP</i>, <i>RESET</i> e <i>RANDOM</i>.
	 */
	
	private void setButtons() {		
		startButton = new JButton("START");
		startButton.setBounds(0, 0, 100, 30);
		startButton.setBackground(Color.GREEN);
		startButton.setToolTipText("START");
		startButton.addActionListener(ascStart);
		
		stopButton = new JButton("STOP");
		stopButton.setBounds(101, 0, 100, 30);
		stopButton.setBackground(Color.RED);
		stopButton.setToolTipText("STOP");
		stopButton.addActionListener(ascStop);

		resetButton = new JButton("RESET");
		resetButton.setBounds(202, 0, 100, 30);
		resetButton.setBackground(Color.ORANGE);
		resetButton.setToolTipText("RESET");
		resetButton.addActionListener(ascReset);
		
		randomButton = new JButton("RANDOM");
		randomButton.setBounds(303, 0, 100, 30);
		randomButton.setBackground(new Color(0, 200, 200));
		randomButton.setToolTipText("RANDOM");
		randomButton.addActionListener(ascRandom);
	}
	
	/**
	 * Crea un'etichetta che rappresenta il numero dell'attuale generazione.
	 */
	
	private void setLabel() {
		generations = new JLabel();
		generations.setBounds(0, 40, 160, 20);
	}
	
	// DISEGNO DEL MONDO
	
	/**
	 * Sulla base della matrice <i>world</i>,
	 * disegna una griglia formata da varie cellule.
	 * Queste possono essere vive (1), morte (0) o morte per sempre (2).
	 * A seconda del valore di ogni cella della matrice,
	 * questo metodo ne chiama altri per disegnare la cellula corretta.
	 * Inoltre chiama il metodo <i>setNumberOfGeneration</i> per incrementare il numero di generazione.
	 * 
	 * @param g lo stato grafico, cio&egrave; il pittore che consente di disegnare
	 */
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		if(beginning == true) {
			g2.drawString("Press (( Reset )) to make grid appeare", 0, 100);
			beginning = false;
		}
		
		setNumberOfGeneration();
		
		int row = 0;
		int column = 0;
		
		for(int[] r: world) {
			for(int c: r) {
				if(world[row][column] == 0) {
					drawCellOFF(g2, row, column);
				}
				else if(world[row][column] == 1){
					drawCellON(g2, row, column);
				}
				else if(world[row][column] == 2){
					drawDeadCell(g2, row, column);
				}
				column++;
			}
			row++;
			column = 0;
		}
		
	}
	
	/**
	 * Aggiorna l'etichetta <i>Generation</i> con il nuovo valore di <i>numberOfGenerations</i>.
	 */
	
	private void setNumberOfGeneration() {
		generations.setText("Generation: " + numberOfGenerations);
		getContentPane().add(generations);
	}
	
	/**
	 * Disegna un quadratino bianco
	 * di dimensioni 10 * 10 pixel
	 * in posizione (row * CELL_SIZE, column * CELL_SIZE + 150).
	 * 
	 * @param g2 lo stato grafico, cio&egrave; il pittore che consente di disegnare
	 * @param row la riga della matrice che rappresenta il mondo
	 * @param column la colonna della matrice che rappresenta il mondo
	 */
	
	private void drawCellOFF(Graphics2D g2, int row, int column) {
		g2.setColor(Color.WHITE);
		g2.fillRect(row * CELL_SIZE, column * CELL_SIZE + 150, 10, 10);
		g2.setColor(Color.BLACK);
		g2.drawRect(row * CELL_SIZE, column * CELL_SIZE + 150, 10, 10);
	}
	
	/**
	 * Disegna un quadratino di colore <i>colors.getCurrentColor()</i>,
	 * corrispondente al colore selezionato nella finestra dei colori,
	 * di dimensioni 10 * 10 pixel
	 * in posizione (row * CELL_SIZE, column * CELL_SIZE + 150).
	 * 
	 * @param g2 lo stato grafico, cio&egrave; il pittore che consente di disegnare
	 * @param row la riga della matrice che rappresenta il mondo
	 * @param column la colonna della matrice che rappresenta il mondo
	 */
	
	private void drawCellON(Graphics2D g2, int row, int column) {
		g2.setColor(colors.getCurrentColor());
		g2.fillRect(row * CELL_SIZE, column * CELL_SIZE + 150, 10, 10);
		g2.setColor(Color.BLACK);
		g2.drawRect(row * CELL_SIZE, column * CELL_SIZE + 150, 10, 10);
	}
	
	/**
	 * Disegna un quadratino rosso
	 * di dimensioni 10 * 10 pixel
	 * in posizione (row * CELL_SIZE, column * CELL_SIZE + 150).
	 * 
	 * @param g2 lo stato grafico, cio&egrave; il pittore che consente di disegnare
	 * @param row la riga della matrice che rappresenta il mondo
	 * @param column la colonna della matrice che rappresenta il mondo
	 */
	
	private void drawDeadCell(Graphics2D g2, int row, int column) {
		g2.setColor(Color.RED);
		g2.fillRect(row * CELL_SIZE, column * CELL_SIZE + 150, 10, 10);
		g2.setColor(Color.BLACK);
		g2.drawRect(row * CELL_SIZE, column * CELL_SIZE + 150, 10, 10);
	}
	
	// INIZIO DEL GIOCO
	
	/**
	 * Realizza un ciclo in cui le cellule nascono e muoiono generazione dopo generazione.<br>
	 * Se il colore delle cellule &egrave; stato appena modificato dalla pressione di un bottone di colore,
	 * fa riapparire le cellule sulla griglia con il colore corretto.
	 */
	
	protected void startGame() {
		while(nextGeneration) {
			evolve();
			numberOfGenerations++;
			repaint();
			sleepFor(2000 - speedControl.getValue());
		}
		if(colors.colorIsChanged() == true) {
			repaint();
			colors.colorChanged = false;
		}
	}
	
	/**
	 * Blocca il thread corrente per un determinato numero di millisecondi.
	 * 
	 * @param milliseconds i millisecondi da aspettare
	 */
	
	private void sleepFor(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
		}
	}
	
	/**
	 * <p> Usa una matrice ausiliaria <i>temp</i> per memorizzare il vecchio stato del mondo,
	 * in modo tale da poter calcolarne il nuovo stato
	 * e stabilire quali cellule saranno vive e quali morte nella generazione successiva.</p>
	 * 
	 * <p> Fa partire le varie thread che coopereranno per calcolare il nuovo stato del mondo.</p>
	 * 
	 * <p> Aggiorna la matrice <i>world</i>, ottenuta grazie alla matrice <i>temp</i>.
	 */
	
	private void evolve() {
		temp = copyTemp();
		
		startSlaves();
		waitForSlavesToFinish();
		X = 0;
		
		copyWorld();
	}
	
	/**
	 * Copia la matrice <i>world</i> nella matrice <i>temp</i> per salvare il vecchio stato del mondo.
	 * 
	 * @return la matrice ausiliaria
	 */
	
	private int[][] copyTemp() {
		int[][] temp = new int[50][50];
		int row = 0;
		int column = 0;
		
		for(int[] r: world) {
			for(int c: r) {
				temp[row][column] = world[row][column];
				column++;
			}
			row++;
			column = 0;
		}
		
		return temp;
	}
	
	/**
	 * Crea un array di thread lungo quanto il numero di thread specificato
	 * e fa partire ogni thread.
	 */
	
	private void startSlaves() {
		slaves = new Slave[numberOfThreads];
		for(int pos = 0; pos < numberOfThreads; pos++)
			(slaves[pos] = new Slave()).start();
	}
	
	/**
	 * Attende che tutte le thread abbiano terminato il loro compito.
	 */
	
	private void waitForSlavesToFinish() {
		for(Slave slave: slaves) {
			try {
				slave.join();
			}
			catch(InterruptedException e) {
				
			}
		}
	}
	
	/**
	 * Classe interna contenente il metodo <i>run()</i> che ogni thread dovr&agrave; eseguire.
	 */
	
	private class Slave extends Thread {
		
		@Override
		public void run() {
			int row,column;
			int oldState;
			
			do {
				
				synchronized(GameOfLife.this) {
					row = GameOfLife.this.X++;
				}
				
				if(row < world.length) {

					for(column = 0; column < 50; column++) {
						if(world[row][column] != 2) {
							oldState = world[row][column];
							temp[row][column] = evolveCell(row, column, oldState);
						}
						else
							temp[row][column] = 2;
					}
				
				}
				
			}
			while(row < world.length);
			
		}
		
	}
	
	/**
	 * Copia la matrice <i>temp</i> nella matrice <i>world</i> per salvare lo stato attuale del mondo.
	 */
	
	private void copyWorld() {
		int row = 0;
		int column = 0;
		
		for(int[] r: world) {
			for(int c: r) {
				world[row][column] = temp[row][column];
				column++;
			}
			row++;
			column = 0;
		}
	}
	
	/**
	 * <p> Considerando il vecchio stato della cellula individuata da riga e colonna,
	 * ovvero se sia viva (1), morta (0) o morta per sempre (2),
	 * calcola il nuovo stato della cellula,
	 * osservando le otto cellule circostanti.</p>
	 * 
	 * <p> Per ogni cellula viva circondata da due o tre cellule vive,
	 * il nuovo stato sar&agrave; 1 (viva).<br>
	 * Per ogni cellula morta circondata da due cellule vive,
	 * il nuovo stato sar&agrave; 1 (viva).<br>
	 * Per le cellule morte per sempre, il nuovo stato sar&agrave; 2 (morta per sempre), uguale al precedente stato.<br>
	 * Per tutte le altre cellule il nuovo stato sar&agrave; 0 (morta).</p>
	 * 
	 * @param row la riga della matrice
	 * @param column la colonna della matrice
	 * @param oldState il vecchio stato della cellula
	 * @return il nuovo stato della cellula
	 */
	
	private int evolveCell(int row, int column, int oldState) {
		int numberOfAliveCellsAround = 0;
		int newState = 0;
		
		if(row == 0 && column == 0)
			numberOfAliveCellsAround = cellZeroZero();
		
		else if(row == 0 && column == 49)
			numberOfAliveCellsAround = cellZeroFortynine();
		
		else if(row == 49 && column == 0)
			numberOfAliveCellsAround = cellFortynineZero();
		
		else if(row == 49 && column == 49)
			numberOfAliveCellsAround = cellFortynineFortynine();
		
		else if(row == 0 && (column > 0 && column < 49))
			numberOfAliveCellsAround = cellRowZero(column);
		
		else if(row == 49 && (column > 0 && column < 49))
			numberOfAliveCellsAround = cellRowFortynine(column);
		
		else if(column == 0 && (row > 0 && row < 49))
			numberOfAliveCellsAround = cellColumnZero(row);
		
		else if(column == 49 && (row > 0 && row < 49))
			numberOfAliveCellsAround = cellColumnFortynine(row);
		
		else
			numberOfAliveCellsAround = otherCells(row, column);

		if(oldState == 0)
			if(numberOfAliveCellsAround == 3)
				newState = 1;

		if(oldState == 1)
			if(numberOfAliveCellsAround == 2 || numberOfAliveCellsAround == 3)
				newState = 1;
		
		return newState;
	}
	
	/**
	 * Calcola il numero di cellule vive attorno alla cellula di posizione (0, 0),
	 * considerando solo le posizioni ammissibili per questo caso particolare.
	 * 
	 * @return il numero di cellule vive attorno alla cellula considerata
	 */
	
	private int cellZeroZero() {
		int numberOfAliveCellsAround = 0;
		
		if(world[0][1] == 1)
			numberOfAliveCellsAround++;
		if(world[1][1] == 1)
			numberOfAliveCellsAround++;
		if(world[1][0] == 1)
			numberOfAliveCellsAround++;
		
		return numberOfAliveCellsAround;
	}
	
	/**
	 * Calcola il numero di cellule vive attorno alla cellula di posizione (0, 49),
	 * considerando solo le posizioni ammissibili per questo caso particolare.
	 * 
	 * @return il numero di cellule vive attorno alla cellula considerata
	 */
	
	private int cellZeroFortynine() {
		int numberOfAliveCellsAround = 0;
		
		if(world[0][48] == 1)
			numberOfAliveCellsAround++;
		if(world[1][48] == 1)
			numberOfAliveCellsAround++;
		if(world[1][49] == 1)
			numberOfAliveCellsAround++;
		
		return numberOfAliveCellsAround;
	}
	
	/**
	 * Calcola il numero di cellule vive attorno alla cellula di posizione (49, 0),
	 * considerando solo le posizioni ammissibili per questo caso particolare.
	 * 
	 * @return il numero di cellule vive attorno alla cellula considerata
	 */
	
	private int cellFortynineZero() {
		int numberOfAliveCellsAround = 0;
		
		if(world[48][0] == 1)
			numberOfAliveCellsAround++;
		if(world[48][1] == 1)
			numberOfAliveCellsAround++;
		if(world[49][1] == 1)
			numberOfAliveCellsAround++;
		
		return numberOfAliveCellsAround;
	}
	
	/**
	 * Calcola il numero di cellule vive attorno alla cellula di posizione (49, 49),
	 * considerando solo le posizioni ammissibili per questo caso particolare.
	 * 
	 * @return il numero di cellule vive attorno alla cellula considerata
	 */
	
	private int cellFortynineFortynine() {
		int numberOfAliveCellsAround = 0;
		
		if(world[49][48] == 1)
			numberOfAliveCellsAround++;
		if(world[48][48] == 1)
			numberOfAliveCellsAround++;
		if(world[48][49] == 1)
			numberOfAliveCellsAround++;
		
		return numberOfAliveCellsAround;
	}
	
	/**
	 * Calcola il numero di cellule vive attorno alle cellule di riga 0,
	 * considerando solo le posizioni ammissibili per questo caso particolare.
	 * 
	 * @return il numero di cellule vive attorno alla cellula considerata
	 */
	
	private int cellRowZero(int column) {
		int numberOfAliveCellsAround = 0;
		
		if(world[0][column - 1] == 1)
			numberOfAliveCellsAround++;
		
		if(world[0][column + 1] == 1)
			numberOfAliveCellsAround++;
		
		if(world[1][column - 1] == 1)
			numberOfAliveCellsAround++;
		
		if(world[1][column] == 1)
			numberOfAliveCellsAround++;
		
		if(world[1][column + 1] == 1)
			numberOfAliveCellsAround++;
		
		return numberOfAliveCellsAround;
	}
	
	/**
	 * Calcola il numero di cellule vive attorno alle cellule di riga 49,
	 * considerando solo le posizioni ammissibili per questo caso particolare.
	 * 
	 * @return il numero di cellule vive attorno alla cellula considerata
	 */
	
	private int cellRowFortynine(int column) {
		int numberOfAliveCellsAround = 0;
		
		if(world[49][column - 1] == 1)
			numberOfAliveCellsAround++;
		
		if(world[49][column + 1] == 1)
			numberOfAliveCellsAround++;
		
		if(world[48][column - 1] == 1)
			numberOfAliveCellsAround++;
		
		if(world[48][column] == 1)
			numberOfAliveCellsAround++;
		
		if(world[48][column + 1] == 1)
			numberOfAliveCellsAround++;
		
		return numberOfAliveCellsAround;
	}
	
	/**
	 * Calcola il numero di cellule vive attorno alle cellule di colonna 0,
	 * considerando solo le posizioni ammissibili per questo caso particolare.
	 * 
	 * @return il numero di cellule vive attorno alla cellula considerata
	 */
	
	private int cellColumnZero(int row) {
		int numberOfAliveCellsAround = 0;
		
		if(world[row - 1][0] == 1)
			numberOfAliveCellsAround++;
		
		if(world[row + 1][0] == 1)
			numberOfAliveCellsAround++;
		
		if(world[row - 1][1] == 1)
			numberOfAliveCellsAround++;
		
		if(world[row][1] == 1)
			numberOfAliveCellsAround++;
		
		if(world[row + 1][1] == 1)
			numberOfAliveCellsAround++;
		
		return numberOfAliveCellsAround;
	}
	
	/**
	 * Calcola il numero di cellule vive attorno alle cellule di colonna 49,
	 * considerando solo le posizioni ammissibili per questo caso particolare.
	 * 
	 * @return il numero di cellule vive attorno alla cellula considerata
	 */
	
	private int cellColumnFortynine(int row) {
		int numberOfAliveCellsAround = 0;
		
		if(world[row - 1][49] == 1)
			numberOfAliveCellsAround++;
		
		if(world[row + 1][49] == 1)
			numberOfAliveCellsAround++;
		
		if(world[row - 1][48] == 1)
			numberOfAliveCellsAround++;
		
		if(world[row][48] == 1)
			numberOfAliveCellsAround++;
		
		if(world[row + 1][48] == 1)
			numberOfAliveCellsAround++;
		
		return numberOfAliveCellsAround;
	}
	
	/**
	 * Calcola il numero di cellule vive attorno ad una cellula di posizione diversa dai casi precedenti
	 * e che quindi non si trova presso i bordi della griglia.
	 * 
	 * @return il numero di cellule vive attorno alla cellula considerata
	 */
	
	private int otherCells(int row, int column) {
		int numberOfAliveCellsAround = 0;
		
		if(world[row][column - 1] == 1)
			numberOfAliveCellsAround++;
		
		if(world[row][column + 1] == 1)
			numberOfAliveCellsAround++;
		
		if(world[row - 1][column] == 1)
			numberOfAliveCellsAround++;
		
		if(world[row + 1][column] == 1)
			numberOfAliveCellsAround++;
		
		if(world[row - 1][column - 1] == 1)
			numberOfAliveCellsAround++;
		
		if(world[row - 1][column + 1] == 1)
			numberOfAliveCellsAround++;
		
		if(world[row + 1][column - 1] == 1)
			numberOfAliveCellsAround++;
		
		if(world[row + 1][column + 1] == 1)
			numberOfAliveCellsAround++;
		
		return numberOfAliveCellsAround;
	}
	
	// ASCOLTATORI (BOTTONI)
	
	/**
	 * Classe interna che realizza l'ascoltatore del bottone <i>START</i>.
	 */
	
	public class AscStart implements ActionListener {
		
		/**
		 * Quando viene premuto il bottone <i>START</i>
		 * viene eseguito il seguente metodo.
		 */

		@Override
		public void actionPerformed(ActionEvent e) {
			startAction();
			
		}
		
	}
	
	/**
	 * Ponendo la variabile <i>nextGeneration</i> pari a true,
	 * consente di avviare il <i>ciclo while</i> del metodo <i>startGame()</i>
	 * e il succedersi delle generazioni di cellule.
	 */
	
	private void startAction() {
		nextGeneration = true;
	}
	
	/**
	 * Classe interna che realizza l'ascoltatore del bottone STOP.
	 */
	
	public class AscStop implements ActionListener {
		
		/**
		 * Quando viene premuto il bottone <i>STOP</i>
		 * viene eseguito il seguente metodo.
		 */

		@Override
		public void actionPerformed(ActionEvent e) {
			
			stopAction();
			
		}
		
	}
	
	/**
	 * Ponendo la variabile <i>nextGeneration</i> pari a false,
	 * consente di interrompere il <i>ciclo while</i> del metodo <i>startGame()</i>
	 * e di bloccare il succedersi delle generazioni di cellule.
	 */
	
	private void stopAction() {
		nextGeneration = false;
	}
	
	/**
	 * Classe interna che realizza l'ascoltatore del bottone <i>RESET</i>.
	 */
	
	public class AscReset implements ActionListener {
		
		/**
		 * Quando viene premuto il bottone <i>RESET</i>
		 * viene eseguito il seguente metodo.
		 */

		@Override
		public void actionPerformed(ActionEvent e) {
			
			resetAction();
			
		}
		
	}
	
	/**
	 * Ponendo la variabile <i>nextGeneration</i> pari a false,
	 * consente di interrompere il <i>ciclo while</i> del metodo <i>startGame()</i>
	 * e di bloccare il succedersi delle generazioni di cellule.<br>
	 * Fa tornare il mondo ad uno stato iniziale in cui le generazioni sono azzerate e il mondo &egrave; vuoto.
	 */
	
	private void resetAction() {
		nextGeneration = false;
		numberOfGenerations = 0;
		resetWorld();
		repaint();
	}
	
	/**
	 * Azzera il mondo, ponendo a zero ogni cellula individuata dalla matrice.
	 */
	
	private void resetWorld() {
		int row = 0;
		int column = 0;
		
		for(int[] r: world) {
			for(int c: r) {
				world[row][column] = 0;
				column++;
			}
			row++;
			column = 0;
		}
	}
	
	/**
	 * Classe interna che realizza l'ascoltatore del bottone <i>RANDOM</i>.
	 */
	
	public class AscRandom implements ActionListener {
		
		/**
		 * Quando viene premuto il bottone <i>RANDOM</i>
		 * viene eseguito il seguente metodo.
		 */

		@Override
		public void actionPerformed(ActionEvent e) {
			
			randomAction();

		}
		
	}
	
	/**
	 * Crea casualmente una disposizione di cellule vive e cellule morte.
	 */
	
	private void randomAction() {
		randomWorld();
		repaint();
	}
	
	/**
	 * Per ogni cellula del mondo stabilisce casualmente se essa debba essere viva oppure morta.
	 */
	
	private void randomWorld() {
		int row = 0;
		int column = 0;
		
		for(int[] r: world) {
			for(int c: r) {
				world[row][column] = new Random().nextInt(2);
				column++;
			}
			row++;
			column = 0;
		}
	}
	
	// ASCOLTATORE (MOUSE)
	
	/**
	 * Classe interna che realizza l'ascoltatore del click mouse.
	 */
	
	public class AscMouse extends MouseAdapter {
		
		protected int cx;
		protected int cy;
		
		/**
		 * Memorizza nei campi cx e cy le coordinate del click del mouse.
		 */
		
		public void mouseClicked(MouseEvent ev) {
			cx = ev.getX();
			cy = ev.getY();
			
			cellONcellOFF();
			ev.getComponent().repaint();
		}
		
	}
	
	/**
	 * Accende o spegne la cellula corrispondente alle coordinate del click del mouse.<br><br>
	 * Se il gioco &egrave; fermo e la cellula cliccata &egrave; morta (0),
	 * allora la cellula si accende e diventa viva (1).<br>
	 * Se il giorco &egrave; fermo e la cellula cliccata &egrave; viva (1),
	 * allora la cellula si spegne e diventa morta (0).<br>
	 * Se il gioco &egrave; attivo e la cellula cliccata &egrave; morta (0) o viva (1),
	 * allora la cellula diventa rossa
	 * e rimane morta (2) fino al termine del gioco o al momento delle pressione del tasto <i>RESET</i>.<br>
	 */
	
	private void cellONcellOFF() {
		
		if(menuON == false) {
		
			int coordinateX = ascMouse.cx;
			int coordinateY = ascMouse.cy;
		
			if(coordinateY > 150) {
		
				int x = coordinateX / 10;
				int y = (coordinateY - 150) / 10;
		
				int state = world[x][y];
		
				if(nextGeneration == true)
					world[x][y] = 2;
				else {
					if(state == 0)
						world[x][y] = 1;
					else if(state == 1)
						world[x][y] = 0;
					else
						world[x][y] = 2;
				}
		
			}
		
		}
		
	}
	
	// ASCOLTATORI (BARRA DEL MENU)
	
	/**
	 * Classe interna che realizza l'ascoltatore del menu <i>File</i>.
	 */
	
	public class AscFileMenu implements ActionListener {
		
		/**
		 * Quando viene premuta una qualsiasi voce del menu, <i>menuON</i> diventa true.<br>
		 * Quando viene eseguita l'azione relativa alla voce del menù selezionata, <i>menuON</i> diventa false.<br><br>
		 * 
		 * Alla pressione di<br>
		 * <ul>
		 * <li><i>Colors ON</i> fa apparire la finestra dei colori.</li>
		 * <li><i>Colors OFF</i> fa scomparire la finestra dei colori.</li>
		 * <li><i>Slider ON</i> fa apparire la finestra dello slider.</li>
		 * <li><i>Slider OFF</i> fa scomparire la finestra dello slider.</li>
		 * <li><i>Exit</i> chiude la finestra di gioco.</li>
		 * </ul>
		 */

		@Override
		public void actionPerformed(ActionEvent ev) {
			JMenuItem target = (JMenuItem) ev.getSource();
			String file = target.getText();
			
			menuON = true;
			
			if(file.equals("Colors   ON") || file.equals("Colors   OFF")) {
				if(colors.colorMenuON == true) {
					colors.colorMenuON = false;
					colors.disappeare();
				}
				else {
					colors.colorMenuON = true;
					colors.appeare();
				}
				target.setText("Colors   " + colors.getONOFF());
			}
			else if(file.equals("Slider    ON") || file.equals("Slider    OFF")) {
				if(speedControl.sliderMenuON == true) {
					speedControl.sliderMenuON = false;
					speedControl.disappeare();
				}
				else {
					speedControl.sliderMenuON = true;
					speedControl.appeare();
				}
				target.setText("Slider    " + speedControl.getONOFF());
			}
			else if(file.equals(fileNames[2]))
				System.exit(0);
			
			menuON = false;
		}
		
	}
	
	/**
	 * Classe interna che realizza l'ascoltatore del menù delle figure da inserire nella griglia.
	 */
	
	public class AscPatterns implements ActionListener {
		
		/**
		 * L'ascoltatore del click del mouse necessario per inserire nella griglia la figura selezionata.
		 */
		
		private AscMousePatterns ascMousePatterns = new AscMousePatterns();
		
		/**
		 * Memorizza la stringa dell'oggetto del menu selezionato
		 * e avvisa che il menù &egrave; attivo.<br>
		 * Fa comparire una finestra contenente la figura corrispondente alla voce del menù selezionata.
		 */

		@Override
		public void actionPerformed(ActionEvent ev) {			
			JMenuItem target = (JMenuItem) ev.getSource();
			patternSelectedMenu = target.getText();
			
			if(menuON)
				pattern.removePattern();
			
			menuON = true;
			
			pattern = new Pattern(patternSelectedMenu);
			pattern.addPattern();
			
			addMouseListener(ascMousePatterns);
		}

	}
	
	/**
	 * Classe interna che realizza l'ascoltatore del click del mouse per posizionare le figure sulla griglia.
	 */
	
	public class AscMousePatterns extends MouseAdapter {
		
		protected int cx;
		protected int cy;
		
		/**
		 * Memorizza in cx e cy le coordinate del click del mouse.<br>
		 * Se queste coordinate corrispondono ad un punto sulla griglia,
		 * chiama il metodo opportuno che corrisponde alla stringa di menù selezionata.<br>
		 * Una volta posizionata la figura sulla griglia, <i>menuON</i> diventa false, segnalando che il menù non &egrave; più attivo
		 * e causando la scomparsa della finestra contenente l'immagine della figura inserita.
		 */
		
		public void mouseClicked(MouseEvent ev) {
			
			if(menuON) {
				
				cx = ev.getX();
				cy = ev.getY();
			
				if(cy > 150) {
				
					if(patternSelectedMenu.equals(stillLifesType[0]))
						insertBlock(cx, cy);
					else if(patternSelectedMenu.equals(stillLifesType[1]))
						insertBeehive(cx, cy);
					else if(patternSelectedMenu.equals(stillLifesType[2]))
						insertLoaf(cx, cy);
					else if(patternSelectedMenu.equals(stillLifesType[3]))
						insertBoat(cx, cy);
					
					else if(patternSelectedMenu.equals(oscillatorsType[0]))
						insertBlinker(cx, cy);
					else if(patternSelectedMenu.equals(oscillatorsType[1]))
						insertToad(cx, cy);
					else if(patternSelectedMenu.equals(oscillatorsType[2]))
						insertBeacon(cx, cy);
					else if(patternSelectedMenu.equals(oscillatorsType[3]))
						insertPulsar(cx, cy);
					
					else if(patternSelectedMenu.equals(spaceShipsType[0]))
						insertGlider(cx, cy);
					else if(patternSelectedMenu.equals(spaceShipsType[1]))
						insertLWSS(cx, cy);
					
					else if(patternSelectedMenu.equals("Gosper's Glider Gun"))
						insertGliderGun(cx, cy);

				}
				
			}
			
			menuON = false;
			
			pattern.removePattern();

			ev.getComponent().repaint();
		}
		
	}
	
	/**
	 * Stabilisce se la cella della matrice sia morta per sempre oppure no.
	 * 
	 * @param x la riga della matrice <i>world</i>
	 * @param y la colonna della matrice <i>world</i>
	 * @return true se e solo se cella individuata da x e y vale 2 e quindi &egrave; morta per sempre
	 */
	
	private boolean isNotDeadForever(int x, int y) {
		if(world[x][y] != 2)
			return true;
		return false;
	}
	
	/**
	 * Inserisce nel punto (x, y) la figura <i>BLOCK</i> a partire dall'estremo sinistro della figura stessa,
	 * controllando le posizioni particolari della griglia come l'estremit&agrave; superiore, inferiore, destra e sinistra.<br>
	 * Se la figura viene inserita in un'estremit&agrave; della griglia, verranno attivate solo le cellule visibili e quindi interne alla griglia.
	 * 
	 * @param coordinateX la coordinata x del click del mouse
	 * @param coordinateY la coordinata y del click del mouse
	 */
	
	private void insertBlock(int coordinateX, int coordinateY) {			
		int x = coordinateX / 10;
		int y = (coordinateY - 150) / 10;
		
		if(isNotDeadForever(x, y))
			world[x][y] = 1;
		if(y + 1 < 50 && isNotDeadForever(x, y + 1))
			world[x][y+1] = 1;
		if(x + 1 < 50 && isNotDeadForever(x + 1, y))
			world[x + 1][y] = 1;
		if(x + 1 < 50 && y + 1 < 50 && isNotDeadForever(x + 1, y + 1))
			world[x + 1][y + 1] = 1;

		repaint();	
	}

	/**
	 * Inserisce nel punto (x, y) la figura <i>BEEHIVE</i> a partire dall'estremo sinistro della figura stessa,
	 * controllando le posizioni particolari della griglia come l'estremit&agrave; superiore, inferiore, destra e sinistra.<br>
	 * Se la figura viene inserita in un'estremit&agrave; della griglia, verranno attivate solo le cellule visibili e quindi interne alla griglia.
	 * 
	 * @param coordinateX la coordinata x del click del mouse
	 * @param coordinateY la coordinata y del click del mouse
	 */
	
	private void insertBeehive(int coordinateX, int coordinateY) {
		int x = coordinateX / 10;
		int y = (coordinateY - 150) / 10;
		
		if(isNotDeadForever(x, y))
			world[x][y] = 1;
		if(x + 1 < 50 && y - 1 >= 0 && isNotDeadForever(x + 1, y - 1))
			world[x + 1][y - 1] = 1;
		if(x + 2 < 50 && y - 1 >= 0 && isNotDeadForever(x + 2, y - 1))
			world[x + 2][y - 1] = 1;
		if(x + 1 < 50 && y + 1 < 50 && isNotDeadForever(x + 1, y + 1))
			world[x + 1][y + 1] = 1;
		if(x + 2 < 50 && y + 1 < 50 && isNotDeadForever(x + 2, y + 1))
			world[x + 2][y + 1] = 1;
		if(x + 3 < 50 && isNotDeadForever(x + 3, y))
			world[x + 3][y] = 1;
		
		repaint();
	}
	
	/**
	 * Inserisce nel punto (x, y) la figura <i>LOAF</i> a partire dall'estremo sinistro della figura stessa,
	 * controllando le posizioni particolari della griglia come l'estremit&agrave; superiore, inferiore, destra e sinistra.<br>
	 * Se la figura viene inserita in un'estremit&agrave; della griglia, verranno attivate solo le cellule visibili e quindi interne alla griglia.
	 * 
	 * @param coordinateX la coordinata x del click del mouse
	 * @param coordinateY la coordinata y del click del mouse
	 */
	
	private void insertLoaf(int coordinateX, int coordinateY) {
		int x = coordinateX / 10;
		int y = (coordinateY - 150) / 10;
		
		if(isNotDeadForever(x, y))
			world[x][y] = 1;
		if(x + 1 < 50 && y - 1 >= 0 && isNotDeadForever(x + 1, y - 1))
			world[x + 1][y - 1] = 1;
		if(x + 2 < 50 && y - 1 >= 0 && isNotDeadForever(x + 2, y - 1))
			world[x + 2][y - 1] = 1;
		if(x + 1 < 50 && y + 1 < 50 && isNotDeadForever(x + 1, y + 1))
			world[x + 1][y + 1] = 1;
		if(x + 2 < 50 && y + 2 < 50 && isNotDeadForever(x + 2, y + 2))
			world[x + 2][y + 2] = 1;
		if(x + 3 < 50 && y + 1 < 50 && isNotDeadForever(x + 3, y + 1))
			world[x + 3][y + 1] = 1;
		if(x + 3 < 50 && isNotDeadForever(x + 3, y))
			world[x + 3][y] = 1;
		
		repaint();
	}
	
	/**
	 * Inserisce nel punto (x, y) la figura <i>BOAT</i> a partire dall'estremo sinistro della figura stessa,
	 * controllando le posizioni particolari della griglia come l'estremit&agrave; superiore, inferiore, destra e sinistra.<br>
	 * Se la figura viene inserita in un'estremit&agrave; della griglia, verranno attivate solo le cellule visibili e quindi interne alla griglia.
	 * 
	 * @param coordinateX la coordinata x del click del mouse
	 * @param coordinateY la coordinata y del click del mouse
	 */
	
	private void insertBoat(int coordinateX, int coordinateY) {
		int x = coordinateX / 10;
		int y = (coordinateY - 150) / 10;
		
		if(isNotDeadForever(x, y))
			world[x][y] = 1;
		if(y + 1 < 50 && isNotDeadForever(x, y + 1))
			world[x][y+1] = 1;
		if(x + 1 < 50 && isNotDeadForever(x + 1, y))
			world[x + 1][y] = 1;
		if(x + 1 < 50 && y + 2 < 50 && isNotDeadForever(x + 1, y + 2))
			world[x + 1][y + 2] = 1;
		if(x + 2 < 50 && y + 1 < 50 && isNotDeadForever(x + 2, y + 1))
			world[x + 2][y + 1] = 1;
		
		repaint();			
	}
	
	/**
	 * Inserisce nel punto (x, y) la figura <i>BLINKER</i> a partire dall'estremo sinistro della figura stessa,
	 * controllando le posizioni particolari della griglia come l'estremit&agrave; superiore, inferiore, destra e sinistra.<br>
	 * Se la figura viene inserita in un'estremit&agrave; della griglia, verranno attivate solo le cellule visibili e quindi interne alla griglia.
	 * 
	 * @param coordinateX la coordinata x del click del mouse
	 * @param coordinateY la coordinata y del click del mouse
	 */
	
	private void insertBlinker(int coordinateX, int coordinateY) {
		int x = coordinateX / 10;
		int y = (coordinateY - 150) / 10;
		
		if(isNotDeadForever(x, y))
			world[x][y] = 1;
		if(x + 1 < 50 && isNotDeadForever(x + 1, y))
			world[x + 1][y] = 1;
		if(x + 2 < 50 && isNotDeadForever(x + 2, y))
			world[x + 2][y] = 1;
		
		repaint();
	}
	
	/**
	 * Inserisce nel punto (x, y) la figura <i>TOAD</i> a partire dall'estremo sinistro della figura stessa,
	 * controllando le posizioni particolari della griglia come l'estremit&agrave; superiore, inferiore, destra e sinistra.<br>
	 * Se la figura viene inserita in un'estremit&agrave; della griglia, verranno attivate solo le cellule visibili e quindi interne alla griglia.
	 * 
	 * @param coordinateX la coordinata x del click del mouse
	 * @param coordinateY la coordinata y del click del mouse
	 */
	
	private void insertToad(int coordinateX, int coordinateY) {
		int x = coordinateX / 10;
		int y = (coordinateY - 150) / 10;
		
		if(isNotDeadForever(x, y))
			world[x][y] = 1;
		if(x + 1 < 50 && isNotDeadForever(x + 1, y))
			world[x + 1][y] = 1;
		if(x + 2 < 50 && isNotDeadForever(x + 2, y))
			world[x + 2][y] = 1;
		if(x + 1 < 50 && y - 1 >= 0 && isNotDeadForever(x + 1, y - 1))
			world[x + 1][y - 1] = 1;
		if(x + 2 < 50 && y - 1 >= 0 && isNotDeadForever(x + 2, y - 1))
			world[x + 2][y - 1] = 1;
		if(x + 3 < 50 && y - 1 >= 0 && isNotDeadForever(x + 3, y - 1))
			world[x + 3][y - 1] = 1;
		
		repaint();
	}
	
	/**
	 * Inserisce nel punto (x, y) la figura <i>BEACON</i> a partire dall'estremo sinistro della figura stessa,
	 * controllando le posizioni particolari della griglia come l'estremit&agrave; superiore, inferiore, destra e sinistra.<br>
	 * Se la figura viene inserita in un'estremit&agrave; della griglia, verranno attivate solo le cellule visibili e quindi interne alla griglia.
	 * 
	 * @param coordinateX la coordinata x del click del mouse
	 * @param coordinateY la coordinata y del click del mouse
	 */
	
	private void insertBeacon(int coordinateX, int coordinateY) {
		int x = coordinateX / 10;
		int y = (coordinateY - 150) / 10;
		
		if(isNotDeadForever(x, y))
			world[x][y] = 1;
		if(y + 1 < 50 && isNotDeadForever(x, y + 1))
			world[x][y+1] = 1;
		if(x + 1 < 50 && isNotDeadForever(x + 1, y))
			world[x + 1][y] = 1;
		if(x + 1 < 50 && y + 1 < 50 && isNotDeadForever(x + 1, y + 1))
			world[x + 1][y + 1] = 1;
		
		if(x + 2 < 50 && y + 2 < 50 && isNotDeadForever(x + 2, y + 2))
			world[x + 2][y + 2] = 1;

		if(x + 3 < 50 && y + 2 < 50 && isNotDeadForever(x + 3, y + 2))
			world[x + 3][y + 2] = 1;
		if(x + 2 < 50 && y + 3 < 50 && isNotDeadForever(x + 2, y + 3))
			world[x + 2][y + 3] = 1;
		if(x + 3 < 50 && y + 3 < 50 && isNotDeadForever(x + 3, y + 3))
			world[x + 3][y + 3] = 1;

		repaint();
	}
	
	/**
	 * Inserisce nel punto (x, y) la figura <i>PULSAR</i> a partire dall'estremo sinistro della figura stessa,
	 * controllando le posizioni particolari della griglia come l'estremit&agrave; superiore, inferiore, destra e sinistra.<br>
	 * Se la figura viene inserita in un'estremit&agrave; della griglia, verranno attivate solo le cellule visibili e quindi interne alla griglia.
	 * 
	 * @param coordinateX la coordinata x del click del mouse
	 * @param coordinateY la coordinata y del click del mouse
	 */

	private void insertPulsar(int coordinateX, int coordinateY) {
		int x = coordinateX / 10;
		int y = (coordinateY - 150) / 10;
		
		
		insertVerticalBlinker(x, y);
		insertVerticalBlinker(x, y + 6);
		
		insertHorizontalBlinker(x + 2, y - 2);
		insertHorizontalBlinker(x + 2, y + 3);
		insertHorizontalBlinker(x + 2, y + 5);
		insertHorizontalBlinker(x + 2, y + 10);
		
		insertVerticalBlinker(x + 5, y);
		insertVerticalBlinker(x + 5, y + 6);
		insertVerticalBlinker(x + 7, y);
		insertVerticalBlinker(x + 7, y + 6);
		
		insertHorizontalBlinker(x + 8, y - 2);
		insertHorizontalBlinker(x + 8, y + 3);
		insertHorizontalBlinker(x + 8, y + 5);
		insertHorizontalBlinker(x + 8, y + 10);
		
		insertVerticalBlinker(x + 12, y);
		insertVerticalBlinker(x + 12, y + 6);
		
		repaint();
	}
	
	/**
	 * Inserisce nel punto (x, y) la figura <i>BLINKER</i> con orientamento verticale a partire dall'estremo sinistro della figura stessa,
	 * controllando le posizioni particolari della griglia come l'estremit&agrave; superiore, inferiore, destra e sinistra.<br>
	 * Se la figura viene inserita in un'estremit&agrave; della griglia, verranno attivate solo le cellule visibili e quindi interne alla griglia.
	 * 
	 * @param coordinateX la coordinata x del click del mouse
	 * @param coordinateY la coordinata y del click del mouse
	 */
	
	private void insertVerticalBlinker(int x, int y) {
		if((x >= 0 && x < 50) && (y >= 0 && y < 50) && isNotDeadForever(x, y))
			world[x][y] = 1;
		if((x >= 0 && x < 50) && (y + 1 >= 0 && y + 1 < 50) && isNotDeadForever(x, y + 1))
			world[x][y + 1] = 1;
		if((x >= 0 && x < 50) && (y + 2 >= 0 && y + 2 < 50) && isNotDeadForever(x, y + 2))
			world[x][y + 2] = 1;
	}
	
	/**
	 * Inserisce nel punto (x, y) la figura <i>BLINKER</i> con orientamento orizzontale a partire dall'estremo sinistro della figura stessa,
	 * controllando le posizioni particolari della griglia come l'estremit&agrave; superiore, inferiore, destra e sinistra.<br>
	 * Se la figura viene inserita in un'estremit&agrave; della griglia, verranno attivate solo le cellule visibili e quindi interne alla griglia.
	 * 
	 * @param coordinateX la coordinata x del click del mouse
	 * @param coordinateY la coordinata y del click del mouse
	 */
	
	private void insertHorizontalBlinker(int x, int y) {
		if((x >= 0 && x < 50) && (y >= 0 && y < 50) && isNotDeadForever(x, y))
			world[x][y] = 1;
		if((x + 1 >= 0 && x + 1 < 50) && (y >= 0 && y < 50) && isNotDeadForever(x + 1, y))
			world[x + 1][y] = 1;
		if((x + 2 >= 0 && x + 2 < 50) && (y >= 0 && y < 50) && isNotDeadForever(x + 2, y))
			world[x + 2][y] = 1;
	}
	
	/**
	 * Inserisce nel punto (x, y) la figura <i>GLIDER</i> a partire dall'estremo sinistro della figura stessa,
	 * controllando le posizioni particolari della griglia come l'estremit&agrave; superiore, inferiore, destra e sinistra.<br>
	 * Se la figura viene inserita in un'estremit&agrave; della griglia, verranno attivate solo le cellule visibili e quindi interne alla griglia.
	 * 
	 * @param coordinateX la coordinata x del click del mouse
	 * @param coordinateY la coordinata y del click del mouse
	 */
	
	private void insertGlider(int coordinateX, int coordinateY) {
		int x = coordinateX / 10;
		int y = (coordinateY - 150) / 10;
		
		if(isNotDeadForever(x, y))
			world[x][y] = 1;
		if((x + 1 < 50 && y + 1 < 50) && isNotDeadForever(x + 1, y + 1))
			world[x + 1][y + 1] = 1;
		if((x + 2 < 50 && y + 1 < 50) && isNotDeadForever(x + 2, y + 1))
			world[x + 2][y + 1] = 1;
		if((x + 2 < 50 && y < 50) && isNotDeadForever(x + 2, y))
			world[x + 2][y] = 1;
		if((x + 2 < 50 && y - 1 >= 0) && isNotDeadForever(x + 2, y - 1))
			world[x + 2][y - 1] = 1;
		
		repaint();
	}
	
	/**
	 * Inserisce nel punto (x, y) la figura <i>LWSS</i> a partire dall'estremo sinistro della figura stessa,
	 * controllando le posizioni particolari della griglia come l'estremit&agrave; superiore, inferiore, destra e sinistra.<br>
	 * Se la figura viene inserita in un'estremit&agrave; della griglia, verranno attivate solo le cellule visibili e quindi interne alla griglia.
	 * 
	 * @param coordinateX la coordinata x del click del mouse
	 * @param coordinateY la coordinata y del click del mouse
	 */
	
	private void insertLWSS(int coordinateX, int coordinateY) {
		int x = coordinateX / 10;
		int y = (coordinateY - 150) / 10;
		
		if(isNotDeadForever(x,y))
			world[x][y] = 1;
		if((x < 50 && y + 2 < 50) && (isNotDeadForever(x,y+2)))
			world[x][y + 2] = 1;
		if((x + 3 < 50 && y < 50) && (isNotDeadForever(x+3,y)))
			world[x + 3][y] = 1;
		insertHorizontalBlinker(x + 1, y + 3);
		insertVerticalBlinker(x + 4, y + 1);
		
		repaint();
	}
	
	/**
	 * Inserisce nel punto (x, y) la figura <i>GOSPER'S GLIDER GUN</i> a partire dall'estremo sinistro della figura stessa,
	 * controllando le posizioni particolari della griglia come l'estremit&agrave; superiore, inferiore, destra e sinistra.<br>
	 * Se la figura viene inserita in un'estremit&agrave; della griglia, verranno attivate solo le cellule visibili e quindi interne alla griglia.
	 * 
	 * @param coordinateX la coordinata x del click del mouse
	 * @param coordinateY la coordinata y del click del mouse
	 */
	
	private void insertGliderGun(int coordinateX, int coordinateY) {
		int x = coordinateX / 10;
		int y = (coordinateY - 150) / 10;
		
		if(isNotDeadForever(x, y))
			world[x][y] = 1;
		if((x + 1 < 50 && y < 50) && isNotDeadForever(x + 1, y))
			world[x + 1][y] = 1;
		if((x < 50 && y + 1 < 50) && isNotDeadForever(x, y + 1))
			world[x][y + 1] = 1;
		if((x + 1 >= 0 && x + 1 < 50 && y + 1 < 50) && isNotDeadForever(x + 1, y + 1))
			world[x + 1][y + 1] = 1;
		if(x + 10 < 50 && y < 50)
			insertVerticalBlinker(x + 10, y);
		if(x + 11 < 50 && (y - 1 >= 0 && y - 1 < 50) && isNotDeadForever(x + 11, y - 1))
			world[x + 11][y - 1] = 1;
		if(x + 11 < 50 && y + 3 < 50 && isNotDeadForever(x + 11, y + 3))
			world[x + 11][y + 3] = 1;
		if((x + 12 < 50 && (y - 2 >= 0 && y - 1 < 50)) && isNotDeadForever(x + 12, y - 2))
			world[x + 12][y - 2] = 1;
		if((x + 13 < 50 && (y - 2 >= 0 && y - 1 < 50)) && isNotDeadForever(x + 13, y - 2))
			world[x + 13][y - 2] = 1;
		if((x + 12 < 50 && y + 4 < 50) && isNotDeadForever(x + 12, y + 4))
			world[x + 12][y + 4] = 1;
		if((x + 13 < 50 && y + 4 < 50) && isNotDeadForever(x + 13, y + 4))
			world[x + 13][y + 4] = 1;
		if((x + 14 < 50 && y + 1 < 50) && isNotDeadForever(x + 14, y + 1))
			world[x + 14][y + 1] = 1;
		if(x + 15 < 50 && (y - 1 >= 0 && y - 1 < 50) && isNotDeadForever(x + 15, y - 1))
			world[x + 15][y - 1] = 1;
		if((x + 15 < 50 && y + 3 < 50) && isNotDeadForever(x + 15, y + 3))
			world[x + 15][y + 3] = 1;
		if(x + 16 < 50 && y < 50)
			insertVerticalBlinker(x + 16, y);
		if((x + 17 < 50 && y + 1 < 50) && isNotDeadForever(x + 17, y + 1))
			world[x + 17][y + 1] = 1;
		if(x + 20 < 50 && (y - 2 >= 0 && y - 2 < 50))
			insertVerticalBlinker(x + 20, y - 2);
		if(x + 21 < 50 && (y - 2 >= 0 && y - 2 < 50))
			insertVerticalBlinker(x + 21, y - 2);
		if((x + 22 < 50 && (y - 3 >= 0 && y - 3 < 50)) && isNotDeadForever(x + 22, y - 3))
			world[x + 22][y - 3] = 1;
		if((x + 22 < 50 && y + 1 < 50) && isNotDeadForever(x + 22, y + 1))
			world[x + 22][y + 1] = 1;
		if(x + 24 < 50 && (y - 3 >= 0 && y - 3 < 50) && isNotDeadForever(x + 24, y - 3))
			world[x + 24][y - 3] = 1;
		if(x + 24 < 50 && (y - 4 >= 0 && y - 4 < 50) && isNotDeadForever(x + 24, y - 4))
			world[x + 24][y - 4] = 1;
		if((x + 24 < 50 && y + 1 < 50) && isNotDeadForever(x + 24, y + 1))
			world[x + 24][y + 1] = 1;
		if((x + 24 < 50 && y + 2 < 50) && isNotDeadForever(x + 24, y + 2))
			world[x + 24][y + 2] = 1;
		if(x + 34 < 50 && (y - 2 >= 0 && y - 2 < 50) && isNotDeadForever(x + 34, y - 2))
			world[x + 34][y - 2] = 1;
		if(x + 35 < 50 && (y - 2 >= 0 && y - 2 < 50) && isNotDeadForever(x + 35, y - 2))
			world[x + 35][y - 2] = 1;
		if(x + 34 < 50 && (y - 1 >= 0 && y - 1 < 50) && isNotDeadForever(x + 34, y - 1))
			world[x + 34][y - 1] = 1;
		if(x + 35 < 50 && (y - 1 >= 0 && y - 1 < 50) && isNotDeadForever(x + 35, y - 1))
			world[x + 35][y - 1] = 1;
		
		repaint();
	}
	
}