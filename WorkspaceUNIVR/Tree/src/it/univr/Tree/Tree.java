package it.univr.Tree;
import java.util.Vector;

public class Tree<T> {
	private Tree<T> left;
	private Tree<T> right;
	private T value;

	public Tree(Tree<T> left, T value, Tree<T> right) {
		this.left = left;
		this.value = value;
		this.right = right;
	}

	public int height() {
		int heightLeft, heightRight;
		if (left == null)
			heightLeft = 0;
		else
			heightLeft = left.height();

		if (right == null)
			heightRight = 0;
		else
			heightRight = right.height();

		return 1 + Math.max(heightLeft, heightRight);
	}

	public Vector<T> toVector() {
		Vector<T> risultato = new Vector<T>();
		risultato.add(value);
		if (left != null)
			risultato.addAll(left.toVector());
		if (right != null)
			risultato.addAll(right.toVector());

		return risultato;
	}
}
