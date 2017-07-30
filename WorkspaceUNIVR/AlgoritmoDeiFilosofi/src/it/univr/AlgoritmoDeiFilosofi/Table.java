package it.univr.AlgoritmoDeiFilosofi;

/**
 * A table, where philosophers sit, think and eat.
 * 
 * @author <A HREF="mailto:fausto.spoto@univr.it">Fausto Spoto</A>
 */

public class Table {

	/**
	 * Builds a table where at least two philosophers are sitting and hold forks.
	 *
	 * @param names
	 * 			the names of the philosophers
	 * @throws IllegalArgumentException
	 * 			if fewer than two names are provided
	 */

	public Table(String... names) {
		if (names.length < 2)
			throw new IllegalArgumentException("at least two philosophers must sit at a table");

		int pos = 0;
		Fork firstFork = new Fork();
		Fork lastFork = firstFork;

		for (String name: names)
			new Philosopher(name, lastFork, ++pos < names.length ? (lastFork = new Fork()) : firstFork).start();
	}

	/**
	 * Builds a test table with six philosophers.
	 *
	 * @param args
	 * 			unused
	 */

	public static void main(String[] args) {
		new Table("Plato", "Socrates", "Aristoteles", "Parmenides", "Eraclitos", "Empedocles");
	}
}