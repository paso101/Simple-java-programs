package it.univr.beedeedee.gameOfLife;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.juliasoft.beedeedee.bdd.Assignment;
import com.juliasoft.beedeedee.bdd.BDD;
import com.juliasoft.beedeedee.factories.Factory;

/**
 * GameOfLife.java
 * 
 * @author Damiano Pasotto VR397209 mailto: damiano.pasotto@studenti.univr.it
 * @author Lara Scarpari VR401241 mailto: lara.scarpari@studenti.univr.it
 * @version 1.0
 * 
 * <h2>GameOfLife</h2>
 * <p>La classe <i>GameOfLife</i> implementa l'algoritmo di raggiungibilit&agrave;.</p>
 */
public abstract class GameOfLife extends Thread {
	
	/** Matrice di BDD delle variabili dello stato corrente. */
	protected final BDD [][] world;
	/** Matrice di BDD delle variabili dello stato prossimo. */
	protected final BDD [][] world_p;
	/** Dimensione della matrice. */
	protected final int DIM;
	/** Pattern su cui testare la raggiungibilit&agrave;. */
	protected final String patternSelected;
	/** Numero di <i>thread</i>. */
	private int parallel;
	/** "Fabbrica" per la costruzione di BDD. */
	protected final Factory factory;
	/** Numero di generazioni (24), sufficiente per tutti i pattern. */
	private final int finalGen;
	/** Lista di BDD che rappresentano tutte le generazioni del pattern. */
	private ArrayList<BDD> totGenerations = new ArrayList<BDD>();
	/** Contatore di iterazioni del <i>do-while</i> della funzione di raggiungibilit&agrave;. */
	private int iteration;
	
	/**
	 * Inizializza i campi della classe.
	 * 
	 * @param patternSelected Il nome del <i>pattern</i> scelto.
	 */
	public GameOfLife(String patternSelected) {
		this.factory = Factory.mkResizingAndGarbageCollected(1000000, 100000);
		this.patternSelected = patternSelected;
		this.DIM = 10;
		this.world = new BDD[this.DIM][this.DIM];
		this.world_p = new BDD[this.DIM][this.DIM];
		this.finalGen = 24;
		this.parallel = Runtime.getRuntime().availableProcessors();
		this.iteration = 0;
	}

	/**
	 * Inizializza le variabili delle matrici di BDD.<br><br>
	 * (2 * (i * this.DIM + j) Valori 0,2,4,...,198 per la matrice <i>world</i><br>
	 * (2 * (i * this.DIM + j) + 1) Valori 1,3,5,...,199 per la matrice <i>world_p</i>.
	 */
	private void initVar() {
		for (int i = 0; i < this.DIM; i++) {
			for (int j = 0; j < this.DIM; j++) {
				world[i][j] = factory.makeVar(2 * (i * this.DIM + j));
				world_p[i][j] = factory.makeVar(2 * (i * this.DIM + j) + 1);
			}
		}		
	}
	
	/**
	 * <p>Inizializza il <i>pattern</i> selezionato.</p>
	 * 
	 * @return Il BDD relativo al <i>pattern</i>.
	 */
	public abstract BDD getPattern();

	/**
	 * Il metodo <i>start()</i> prevede
	 * le seguenti fasi di esecuzione:<br>
	 * <ol>
	 * <li>Inizializzazione delle variabili delle matrici di BDD <i>world</i> e <i>world_p</i>.</li>
	 * <li>Calcolo della prima generazione.</li>
	 * <li>Creazione di una lista delle generazioni successive.</li>
	 * <li>Calcolo della transizione.</li>
	 * <li>Creazione di un BDD con gli elementi della matrice <i>world</i>.</li>
	 * <li>Ridenominazione della funzione.</li>
	 * <li>Calcolo dell'algoritmo degli stati raggiungibili.</li>
	 * <li>Stampa della soluzione.</li>
	 * <li>Liberazione della memoria.</li>
	 * </ol>
	 */
	public void start() {

		initVar();
		// System.out.println(">>>>>>   " + this.patternSelected + "   <<<<<<\n");

		long startTime = System.currentTimeMillis();

		BDD I = getPattern();
		buildI(I.copy());
		System.out.println("Time I: " + (System.currentTimeMillis() - startTime) + " milliseconds.");

		BDD T = buildT();
		System.out.println("Time T : " + (System.currentTimeMillis() - startTime) + " milliseconds.");

		BDD X = buildX();
		System.out.println("Time X: " + (System.currentTimeMillis() - startTime) + " milliseconds.");
		
		Map<Integer, Integer> renaming = buildRenaming();
		System.out.println("Time renaming: " + (System.currentTimeMillis() - startTime) + " milliseconds.");
		
		BDD RS = reachableStates(I, T, X, renaming);
		System.out.println("Time reachableStates: " + (System.currentTimeMillis() - startTime) + " milliseconds.\n");

		printSolution(RS);

		I.free();
		T.free();
		X.free();
		RS.free();

		factory.done();
		System.out.println("Time: " + (System.currentTimeMillis() - startTime) + " milliseconds.");
		System.out.println();

	}

	/**
	 * Inizializza la lista dei BDD delle generazioni.
	 * 
	 * @param initGenPatternSelectedCopy Il BDD della prima generazione.
	 */
	private void buildI(BDD initGenPatternSelectedCopy) {
		BDD thisGen = initGenPatternSelectedCopy;
		this.totGenerations.add(thisGen.copy());

		for(int i = 1; i < finalGen; i++) {
			BDD nextGen = getNextGen(thisGen);
			this.totGenerations.add(nextGen);
			thisGen.free();
			thisGen = nextGen.copy();
		}				
	}

	/**
	 * Calcola e ritorna la generazione sucessiva.
	 * 
	 * @param thisGen Il BDD della generazione corrente.
	 * @return Il BDD della generazione successiva.
	 */
	private BDD getNextGen(BDD thisGen) {
		BDD temp = this.factory.makeOne();
		Assignment assignment = thisGen.anySat();
		
		for(int i = 0; i < this.DIM; i++)
			
			for(int j = 0; j < this.DIM; j++) {
				
				if(assignment.holds(this.world[i][j])) {
					
					if(nearLifeCell(i, j, assignment))
						temp.andWith(this.world[i][j].not());
					else
						temp.andWith(this.world[i][j].copy());
					
				}
				
				else {

					if(nearDeadCell(i, j, assignment))
						temp.andWith(this.world[i][j].copy());
					else
						temp.andWith(this.world[i][j].not());

				}
				
			}
		
		return temp;
	}

	/**
	 * Verifica che il numero di celle vive
	 * adiacenti a quella corrente sia uguale a 3.
	 * 
	 * @param i Coordinata <i>riga</i> della cella corrente nella matrice di BDD.
	 * @param j Coordinata <i>colonna</i> della cella corrente nella matrice di BDD.
	 * @param a Assegnamento di verit&agrave;.
	 * @return true se attorno alla cella corrente ci sono 3 celle vive,
	 * false altrimenti.
	 */
	private boolean nearDeadCell(int i, int j, Assignment a) {
		if(count(i, j, a) == 3)
			return true;
		return false;
	}

	/**
	 * Verifica la presenza di una delle seguenti condizioni:
	 * <ul>
	 * <li>Sovrappopolamento</li>
	 * <li>Isolamento</li>
	 * <li>Presenza di celle vive lungo il bordo</li>
	 * </ul>
	 * 
	 * @param i Coordinata <i>riga</i> della cella corrente nella matrice di BDD.
	 * @param j Coordinata <i>colonna</i> della cella corrente nella matrice di BDD.
	 * @param assignment Assegnamento di verit&agrave;.
	 * @return true se si verifica almeno una
	 * delle condizioni sopra elencate, false altrimenti.
	 */
	private boolean nearLifeCell(int i, int j, Assignment assignment) {
		if(overPopulation(i, j, assignment) || underPopulation(i, j, assignment) || borderline(i, j))
			return true;
		return false;
		
	}

	/**
	 * Verifica la condizione di sovrappopolamento,
	 * la quale consiste nella presenza di pi&ugrave; di 4 celle vive
	 * attorno a quella corrente.
	 * 
	 * @param i Coordinata <i>riga</i> della cella corrente nella matrice di BDD.
	 * @param j Coordinata <i>colonna</i> della cella corrente nella matrice di BDD.
	 * @param a Assegnamento di verit&agrave;.
	 * @return true se attorno alla cella corrente
	 * ci sono almeno 4 celle vive, false altrimenti.
	 */
	private boolean overPopulation(int i, int j, Assignment a) {
		int nearCells = count(i, j, a);
		if(nearCells >= 4)
			return true;
		return false;
	}

	/**
	 * Verifica la condizione di isolamento,
	 * la quale consiste nella presenza di meno di 2
	 * celle vive adiacenti a quella corrente.
	 * 
	 * @param i Coordinata <i>riga</i> della cella corrente nella matrice di BDD.
	 * @param j Coordinata <i>colonna</i> della cella corrente nella matrice di BDD.
	 * @param a Assegnamento di verit&agrave;.
	 * @return true se la cella corrente &egrave; circondata
	 * da 0 o 1 cella viva, false altrimenti.
	 */
	private boolean underPopulation(int i, int j, Assignment a) {
		int nearCells = count(i, j, a);
		if(nearCells == 0 || nearCells == 1)
			return true;
		return false;
	}

	/**
	 * Verifica la presenza di celle vive lungo il bordo della matrice.
	 * 
	 * @param i Coordinata <i>riga</i> della cella corrente nella matrice di BDD.
	 * @param j Coordinata <i>colonna</i> della cella corrente nella matrice di BDD.
	 * @return true se ci sono celle vive sul bordo, false altrimenti.
	 */
	private boolean borderline(int i, int j) {
		return j == this.DIM - 1 || i == this.DIM - 1 || i == 0 || j == 0;
	}

	/**
	 * Conta il numero di celle vive attorno alla cella corrente.
	 * 
	 * @param i Coordinata <i>riga</i> della cella corrente nella matrice di BDD.
	 * @param j Coordinata <i>colonna</i> della cella corrente nella matrice di BDD.
	 * @param assignment Assegnamento di verit&agrave;.
	 * @return Il numero di celle vive attorno a quella corrente.
	 */
	private int count(int i, int j, Assignment assignment) {
		int countLifeCells = 0;

		for(int dx = i - 1; dx <= i + 1; dx++)
			for(int dy = j - 1; dy <= j + 1; dy++)
				if(!(dx == i && dy == j) && (dx >= 0 && dx < this.DIM && dy >= 0 && dy < this.DIM))
					if(assignment.holds(this.world[dx][dy]))
						countLifeCells++;
		
		return countLifeCells;
	}

	/**
	 * Calcola la transizione.<br>
	 * Un <i>task</i> &egrave; un insieme di risorse di esecuzione.
	 * <i>Future</i> inserisce l'esecuzione in un oggetto
	 * che viene eseguito (parallelamente)
	 * e restituisce il risultato quando serve,
	 * attendendo la fine del task se questo non &egrave; completato.
	 * Un <i>Future</i> rappresenta il risultato di una computazione asincrona.
	 * Contiene dei metodi per controllare lo stato della computazione,
	 * attendere che la computazione sia completata,
	 * recuperare il risultato della computazione.
	 * 
	 * @return Il BDD che costituisce il lavoro
	 * totale dei task di esecuzione.
	 */
	private BDD buildT() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		while(this.finalGen % this.parallel != 0)
			this.parallel--;
		List<Future<BDD>> tasks = new ArrayList<Future<BDD>>(this.parallel);

		int thread = this.parallel;
		int start = this.finalGen / this.parallel;
		int stop = start;

		for (int i = 0; i < thread; i++) {
			tasks.add(executorService.submit(new PartialTaskProcessor(start - stop, start, i)));
			start = start + stop;
		}

		BDD result = factory.makeZero();
		
		for (int i = 0; i < parallel; i++) {
			try {
				result.orWith(tasks.get(i).get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		executorService.shutdown();

		return result;
	}

	/**
	 * L'interfaccia <i>Callable&lt;T&gt;</i> ha il metodo <i>&lt;T&gt; call()</i>,
	 * la cui esecuzione effettua un'attivit&agrave; asincrona
	 * e restituisce il risultato definito come parametro dell'interfaccia.
	 * <i>Callable</i> d&agrave; la possibilit&agrave; di avere un'esecuzione asincrona
	 * con un tipo di ritorno e una lista di eccezioni desiderate.
	 * In realt&agrave; di asincrono c'&egrave; ben poco,
	 * in quanto l'esecuzione del metodo <i>call()</i>
	 * arresta l'esecuzione del flusso del <i>main</i>, in attesa del risultato.
	 */
	public final class PartialTaskProcessor implements Callable<BDD> {

		/** La prima generazione calcolata da un singolo thread. */
		private final int start;
		/** L'ultima generazione calcolata da un singolo thread. */
		private final int stop;
		/** Il numero dei thread. */
		private final int thread;

		/**
		 * Inizializza i campi della classe.
		 * 
		 * @param start Il numero della generazione da cui un thread inizia.
		 * @param stop Il numero della genrazione in cui un thread termina.
		 * @param i Il numero della thread.
		 */
		public PartialTaskProcessor(int start, int stop, int i) {
			this.start = start;
			this.stop = stop;
			this.thread = i;
		}

		/**
		 * Chiama la funzione che costruisce la transizione allo stato prossimo.
		 */
		@Override
		public BDD call() throws Exception {
			return buildNextT(this.start, this.stop, this.thread);
		}

	}

	/**
	 * Divide la computazione in thread.
	 * 
	 * @param start La prima generazione calcolata da un singolo thread.
	 * @param stop L'ultima generazione calcolata da un singolo thread.
	 * @param thread Il numero dei thread.
	 * @return Il BDD che rappresenta l'unione dei BDD
	 * delle generazioni calcolate da un singolo thread.
	 */
	private BDD buildNextT(int start, int stop, int thread) {
		BDD temp = factory.makeZero();
		
		System.out.println(" THREAD: " + thread + " -> generations: [from " + start + " to " + (stop - 1) + "]" ); 

		for (int i = start; i < stop; i++) {
			BDD transition = nextGen(this.totGenerations.get(i));
			temp.orWith(transition);
		}

		return temp;
	}

	/**
	 * Calcola il BDD corrispondente alla generazione successiva
	 * partendo dalla generazione corrente.
	 * 
	 * @param gen Il BDD della generazione corrente.
	 * @return Il BDD corrispondente alla generazione successiva.
	 */
	private BDD nextGen(BDD gen){
		BDD temp = this.factory.makeOne();
		Assignment assignment = gen.anySat();

		for(int i = 0; i < this.DIM; i++)

			for(int j = 0; j < this.DIM; j++) {

				if(assignment.holds(this.world[i][j])) { 

					if(nearLifeCell(i, j, assignment)) {
						temp.andWith(this.world[i][j].copy());
						temp.andWith(this.world_p[i][j].not()); 
					}
					
					else {
						temp.andWith(this.world[i][j].copy());  
						temp.andWith(this.world_p[i][j].copy());
						// temp.andWith(this.world[i][j].biimp(this.world_p[i][j]));
					}

				}

				else {
					
					if(nearDeadCell(i, j, assignment)) {	
						temp.andWith(this.world[i][j].not());
						temp.andWith(this.world_p[i][j].copy());
					}

					else {
						temp.andWith(this.world[i][j].biimp(this.world_p[i][j]));
						// temp.andWith(this.world[i][j].not());  
						// temp.andWith(this.world_p[i][j].not());
					}

				}

			}


		return temp;
	}

	/**
	 * Costruisce un BDD considerando tutti
	 * i BDD delle celle della matrice.
	 * 
	 * @return Il BDD creato con l'AND dei BDD della matrice.
	 */
	private BDD buildX() {
		BDD result = factory.makeOne();

		for(int i = 0; i < this.DIM; i++) {
			for(int j = 0; j < this.DIM; j++) {
				result.andWith(world[i][j].copy());
			}
		}
		
		return result;
	}

	/**
	 * Realizza l'algoritmo di raggiungibilit&agrave;.
	 * L'algoritmo inizia con il ROBDD 0.
	 * Per ogni punto del calcolo dell'approssimazione successiva,
	 * calcola la disgiunzione di I e T composta
	 * con la precedente approssimazione di R'.
	 * L'algoritmo termina quando l'approssimazione
	 * corrente R coincide con quella precedente R'.
	 * 
	 * @param I Il BDD della generazione iniziale.
	 * @param T Il BDD della transizione.
	 * @param X Il BDD con le variabili di <i>world</i>.
	 * @param renaming L'<i>HashMap</i> per la ridenominazione delle variabili.
	 * @return Il BDD che rappresenta l'insieme degli stati raggiungibili.
	 */
	private BDD reachableStates(BDD I, BDD T, BDD X, Map<Integer, Integer> renaming) {
		BDD R = this.factory.makeZero();
		BDD Rp = null;

		do {

			this.iteration++;

			if(Rp != null)
				Rp.free();

			Rp = R;

			BDD and = T.and(R);
			BDD exist = and.exist(X);
			and.free();
			BDD replace = exist.replace(renaming);
			R = I.or(replace);
			replace.free();

		} while(!R.isEquivalentTo(Rp));

		Rp.free();

		return R;
	}

	/**
	 * Costruisce l'<i>HashMap</i> per la ridenominazione delle variabili.
	 * 
	 * @return L'<i>HashMap</i> che definisce la corrispondenza
	 * tra le variabili delle matrici di BDD <i>world</i> e <i>world_p</i>.
	 */
	private Map<Integer, Integer> buildRenaming() {
		Map<Integer, Integer> renaming = new HashMap<>();

		for (int i = 0; i < DIM; i++)
			for (int j = 0; j < DIM; j++)
				renaming.put(world_p[i][j].var(), world[i][j].var());

		return renaming;
	}

	/**
	 * Crea un BDD che rappresenta il mondo vuoto,
	 * cio&egrave; privo di celle vive.
	 * 
	 * @return Il BDD del mondo privo di celle vive.
	 */
	private BDD getFinalState() {
		BDD finalState= factory.makeOne();

		for(int i = 0; i < this.DIM; i++)
			for(int j = 0; j < this.DIM; j++)
				finalState.andWith(world[i][j].not());

		return finalState;
	}

	/**
	 * Controlla che tra gli stati raggiunti
	 * ci sia quello finale (matrice vuota)
	 * e stampa la soluzione.
	 * 
	 * @param rs Il BDD ritornato dal metodo <i>reachableStates()</i>
	 * contenente l'unione di tutti gli stati raggiunti.
	 */
	private void printSolution(BDD rs) {
		long totSol = rs.satCount(this.DIM * this.DIM - 1);
		BDD finalState = getFinalState();
		
		if(rs.andWith(finalState).satCount(this.DIM * this.DIM - 1) == 0)
			System.out.println(
					this.patternSelected +
					" -> UNREACHABLE.\nThere aren't solutions where the \"world\" becomes empty!\n");
		else
			System.out.println(
					this.patternSelected +
					" -> REACHABLE.\nThere is a solution where the \"world\" becomes empty!\n");
		
		System.out.println("Total generations -> " + this.totGenerations.size());
		System.out.println("Different generations reached -> " + totSol);	
		System.out.println("Reachable states -> " + totSol);
		System.out.println("Iteration do-while cycle -> " + this.iteration);
	}

}
