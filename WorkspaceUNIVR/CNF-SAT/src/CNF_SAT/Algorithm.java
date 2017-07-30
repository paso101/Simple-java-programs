/**
 * CNF-SAT, version 1.0, October 18 , 2014
 */

package CNF_SAT;

import java.util.ArrayList;

public class Algorithm {

	private int key = -1;
	private String tree = "digraph G { \n"; 
	
	public Algorithm() {
	}
	
	/**
	 * Metodo che costruisce l'albero.
	 * @param s ArrayList contenente i symboli letterali.
	 * @param C ArrayList contenente le clausole.
	 * @return Oggetto nodo.
	 */	
	
	protected Node makeTree(ArrayList<Character> s, ArrayList<String> C, int index) {
		
		if(index == s.size()) {
			if(hasEmptyClause(C))
				return new Node("FALSE");
			else
				return new Node("TRUE");
		}
		
		String literal = "" + s.get(index);
		
		
	
		if(contains(C, literal)) {
			ArrayList <String> C1 = eliminateClauses(C, literal.toUpperCase());
			ArrayList <String> C2 = eliminateSymbol(C1, "" + literal);
			
			ArrayList <String> C3 = eliminateClauses(C, literal);
			ArrayList <String> C4 = eliminateSymbol(C3, literal.toUpperCase());
			
			Node nodeNegative = makeTree(s, C2, index + 1);
			Node nodePositive = makeTree(s, C4, index + 1);
			Node nodeRoot = new Node(literal);
			nodeRoot.setChildFalse(nodeNegative);
			nodeRoot.setChildTrue(nodePositive);
			
			return nodeRoot;	
		}
		else
			return makeTree(s, C, index + 1);
	}
	
	/**
	 * Metodo per.
	 * @param literal letterale.
	 * @param c ArrayList contenente le clausole.
	 * @return true se contiene literal, false altrimenti.
	 */	
	

	private boolean contains(ArrayList<String> c, String literal) {
		for(String element : c)
			if(element.contains(literal))
				return true;
		return false;
	}
	
	/**
	 * Metodo che esegue l'algoritmo.
	 * @param str ArrayList contenente i symboli letterali.
	 * @param c ArrayList contenente le clausole.
	 * @return temp ArrayList<String> contenente le clausole dopo l'eliminazione.
	 */		

	private ArrayList<String> eliminateClauses(ArrayList<String> c, String str) {
		
		ArrayList<String> temp = new ArrayList<String> ();
		for(int pos = 0; pos < c.size(); pos++)
			if(!c.get(pos).contains(str))
				temp.add(c.get(pos));
		
		return temp;
	}

	/**
	 * Metodo che esegue l'algoritmo.
	 * @param str ArrayList contenente i symboli letterali.
	 * @param c ArrayList contenente le clausole.
	 * @return temp ArrayList<String> le clausole dopo l'eliminazione del simbolo str.
	 */	
	
	private ArrayList<String> eliminateSymbol(ArrayList<String> c, String str) {
		
		for(int pos = 0; pos < c.size(); pos++)
			if(c.get(pos).contains(str))
				c.set(pos, c.get(pos).replaceAll(str, ""));
					
		return c;
	}
	
	 /**
	  * Metodo per verificare se ci sono clausole vuote.
	  * @param C ArrayList contenente le clausole.
	  * @return true se vuoto, false altrimenti.
	  */

	private boolean hasEmptyClause(ArrayList<String> C) {
		for(String s : C)
			if(s.isEmpty())
				return true;
		return false;
	}	

	/**
	 * Metodo che aggiunge una key ad ogni nodo. s
	 * @param node albero analizzato.
	 */
	
	protected void prefixVisit(Node node) {
		if(node != null) {
			this.key++;
			node.setLabel(node.getLabel() + this.key);
			
			System.out.print(node.getLabel());
			System.out.print("[");

			prefixVisit(node.getChildFalse());
			prefixVisit(node.getChildTrue());
			System.out.print("]");	
		}
		
	}
	
	/**
	 * 
	 * @param n albero.
	 * @return this.tree la stringa rappresentante lo pseudocodice per la stampa dell'albero.
	 */
	protected String drawTree(Node n) {
		if(n != null) {
			if(n.getChildFalse() != null && n.getChildTrue() != null) {
				this.tree += n.getLabel() + "->" + n.getChildFalse().getLabel() + ";\n";
				this.tree += n.getLabel() + "->" + n.getChildTrue().getLabel() + ";\n";
			}
			drawTree(n.getChildFalse());
			drawTree(n.getChildTrue());
		}
		
		return this.tree + "}\n";
	}

}
