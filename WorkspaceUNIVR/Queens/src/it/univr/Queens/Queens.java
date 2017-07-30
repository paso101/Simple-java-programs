package it.univr.Queens;
import com.juliasoft.beedeedee.bdd.Assignment;
import com.juliasoft.beedeedee.bdd.BDD;
import com.juliasoft.beedeedee.factories.Factory;
import com.juliasoft.utils.concurrent.Executors;

public class Queens {
	private static int N = 10;
	private static Factory factory;
	private static BDD[][] x;
	private static BDD accumulator;

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		factory = Factory.mkResizingAndGarbageCollected(1000000, 100000);
		accumulator = factory.makeOne();
		localAccumulator = factory.makeOne();

		initVars();
		Thread slave = atMostOneInEachDiagonal();

		System.out.println("time: " + (System.currentTimeMillis() - start));
		atLeastOneInEachRow();
		System.out.println("time1: " + (System.currentTimeMillis() - start));
		atMostOneInEachRow();
		System.out.println("time2: " + (System.currentTimeMillis() - start));
		atMostOneInEachColumn();
		System.out.println("time3: " + (System.currentTimeMillis() - start));
		atMostOneInEachInvertedDiagonal();
		System.out.println("time5: " + (System.currentTimeMillis() - start));

		try {
			slave.join();
		} catch (InterruptedException e) {
		}

		accumulator.andWith(localAccumulator);
		
		printSolution();

		factory.done();
		Executors.shutdown();

		System.out.println("time6: " + (System.currentTimeMillis() - start));
	}

	private static void printSolution() {
		System.out.println
			("There are " + accumulator.satCount(N * N - 1) + " solutions");

		Assignment assignment = accumulator.anySat();
	

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++)
				if (assignment.holds(x[i][j]))
					System.out.print("Q");
				else
					System.out.print("-");

			System.out.println();
		}
	}

	private static void initVars() {
		x = new BDD[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= N; j++)
				x[i][j] = factory.makeVar((i - 1) * N + (j - 1));
				
	}

	private static void accumulate(BDD bdd) {
		accumulator.andWith(bdd);
	}

	private static void atMostOneInEachRow() {
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= N; j++) {

				BDD rhs = factory.makeOne();
				for (int l = 1; l <= N; l++)
					if (l != j)
						rhs.andWith(x[i][l].not());

				accumulate(x[i][j].copy().impWith(rhs));
			}
	}

	private static void atMostOneInEachColumn() {
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= N; j++) {

				BDD rhs = factory.makeOne();
				for (int k = 1; k <= N; k++)
					if (k != i)
						rhs.andWith(x[k][j].not());

				accumulate(x[i][j].copy().impWith(rhs));
			}
	}

	private static BDD localAccumulator;

	private static Thread atMostOneInEachDiagonal() {
		Thread slave = new Thread() {

			public void run() {
				for (int i = 1; i <= N; i++)
					for (int j = 1; j <= N; j++) {

						BDD rhs = factory.makeOne();
						for (int k = 1; k <= N; k++)
							if (1 <= j + k - i && j + k - i <= N && k != i)
								rhs.andWith(x[k][j + k - i].not());

						localAccumulator.andWith(x[i][j].copy().impWith(rhs));
					}
			}
		};

		slave.start();

		return slave;
	}

	private static void atMostOneInEachInvertedDiagonal() {
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= N; j++) {

				BDD rhs = factory.makeOne();
				for (int k = 1; k <= N; k++)
					if (1 <= j + i - k && j + i - k <= N && k != i)
						rhs.andWith(x[k][j + i - k].not());

				accumulate(x[i][j].copy().impWith(rhs));
			}
	}

	private static void atLeastOneInEachRow() {
		for (int i = 1; i <= N; i++) {
			BDD or = factory.makeZero();

			for (int j = 1; j <= N; j++)
				or.orWith(x[i][j].copy());

			accumulate(or);
		}
	}
}