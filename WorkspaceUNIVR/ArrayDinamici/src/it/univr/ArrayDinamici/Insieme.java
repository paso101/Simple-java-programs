package it.univr.ArrayDinamici;
public class Insieme {

	private double[] elementi;
	private int dimensione;

	public Insieme() {
		this.elementi = new double[1];
		this.dimensione = 0;
	}

	public String toString() {
		String risultato = "[";

		for (int pos = 0; pos < dimensione; pos++) {
			risultato += elementi[pos];
			if (pos != dimensione - 1)
				risultato += ",";
		}

		risultato += "]";

		return risultato;
	}

	public void aggiungi(double elemento) {
		if (!contiene(elemento)) {
			garantisciSpazio();
			aggiungiElemento(elemento);
		}
	}

	private void aggiungiElemento(double elemento) {
		elementi[dimensione++] = elemento;
	}

	private void garantisciSpazio() {
		if (dimensione == elementi.length) {
			double[] copia = new double[elementi.length * 2];
			System.arraycopy(elementi, 0, copia, 0, elementi.length);
			elementi = copia;
		}
	}

	private boolean contiene(double elemento) {
		for (int pos = 0; pos < dimensione; pos++)
			if (elementi[pos] == elemento)
				return true;

		return false;
	}

	public void unisci(Insieme altro) {
		for (int pos = 0; pos < altro.dimensione; pos++)
			this.aggiungi(altro.elementi[pos]);
	}

	public double max() {
		double max = Double.NEGATIVE_INFINITY;

		for (int pos = 0; pos < dimensione; pos++)
			if (elementi[pos] > max)
				max = elementi[pos];

		return max;
	}

	public double min() {
		double min = Double.POSITIVE_INFINITY;

		for (int pos = 0; pos < dimensione; pos++)
			if (elementi[pos] < min)
				min = elementi[pos];

		return min;
	}

	public double media() {
		double somma = 0.0;

		for (int pos = 0; pos < dimensione; pos++)
			somma += elementi[pos];

		return somma / dimensione;
	}

	public boolean vuoto() {
		return dimensione == 0;
	}
	

}