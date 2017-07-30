/**
 * CNF-SAT, version 1.0, October 18 , 2014
 */


import java.util.ArrayList;

public class Node {
	
	private int label;
	private Node childFalse;
	private Node childTrue;
/**
 * Costruttore del nodo.
 * @param label l'etichetta
 */
	public Node(int label) {

		setLabel(label);
	}
	
	/**
	 * Metodo per settare il valore della label.
	 * @param label l'eticetta
	 */
	protected void setLabel(int label) {
		this.label = label;
	}
	
/**
 * Metodo che ritorna la label del nodo.
 * @return this.label l valore ritornato del nodo.
 */
	protected int getLabel() {
		return this.label;
	}
	
	/**
	 * Setta il figlio falso.
	 * @param childFalse il figlio falso.
	 */
	protected void setChildFalse(Node childFalse) {
		this.childFalse = childFalse;
	}
	
	/**
	 * Setta il figlio vero.
	 * @param childTrue il figlio vero.
	 */
	protected void setChildTrue(Node childTrue) {
		this.childTrue = childTrue;
	}
	
	/**
	 * Ritorna il figlio falso.
	 * @return il figlio falso.
	 */
	protected Node getChildFalse() {
		return this.childFalse;
	}
	
	/**
	 * Ritorna il figlio vero.
	 * @return il figlio vero.
	 */
	protected Node getChildTrue() {
		return this.childTrue;
	}
	
	/**
	 * Converte l'albero in un ArrayList
	 * @return risultato l'albero in formato ArrayList.
	 */
	protected ArrayList<Integer> toArrayList() {
		ArrayList<Integer> risultato = new ArrayList<Integer>();
		risultato.add(label);
		if (childFalse != null)
			risultato.addAll(childFalse.toArrayList());
		if (childTrue != null)
			risultato.addAll(childTrue.toArrayList());

		return risultato;
	}
	
	
}
