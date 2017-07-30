import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MyTree {

	private int key = -1;
	private String tree = "digraph G { \n"; 
	private int[] array = {0,1,2,3};
	
	public static void main(String[] args) throws IOException {

		/** <p> Istanziazione dell'oggetto MyTree. </p>*/
		MyTree MT = new MyTree();
		
		/** <p> Costrzione dell'albero. </p>*/
		Node node = MT.makeMyTree(0);
		
		System.out.println();
		String drawTree = MT.drawTree(node);
		System.out.println(drawTree);
		/**<p> Creo file con "Tree.gv"</p>*/
	    FileWriter fileWriter = new FileWriter("Treeeee.gv");
	    BufferedWriter bW = new BufferedWriter (fileWriter);

	    /**<p> Scriv ul fle creato precedentemente il pseudocodice per rappresentare l'albero con grphivz.</p>*/
	    bW.write(drawTree);
	    
	    bW.flush();
	    bW.close();

	}

	private Node makeMyTree(int pos) {
		
		if(this.array.length == 1)
			return new Node(array[pos]);
		
		if(pos == this.array.length)
			return new Node(array[pos]);
		
		Node nodeRoot = new Node(pos);
		if(pos + 1 < this.array.length) {
			Node nodeNegative = makeMyTree(pos + 1);
			Node nodePositive = makeMyTree(pos + 1);
			nodeRoot.setChildFalse(nodeNegative);
			nodeRoot.setChildTrue(nodePositive);
		}
		
		return nodeRoot;
	}
	

	/**
	 * 
	 * @param n albero.
	 * @return this.tree la stringa rappresentante lo pseudocodice per la stampa dell'albero.
	 */
	private String drawTree(Node n) {
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
