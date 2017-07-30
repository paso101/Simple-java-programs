package it.univr.stack;

public class Stack<T> {
	private Node top;
	private int nodesSoFar;

	public class Node {
		private final T value;
		private final Node next;

		public Node(T value, Node next) {
			this.value = value;
			this.next = next;
			Stack.this.nodesSoFar++;
		}
	}

	public Stack() {
		this.top = null;
	}

	public int nodesSoFar() {
		return nodesSoFar;
	}

	public void push(T value) {
		top = new Node(value, top);
	}
	
	public T pop() {
		T value = top.value;
		top = top.next;

		return value;
	}

	public Node nodeOfTop() {
		return top;
	}

	public String toString() {
		String result = "";

		for (Node cursor = top; cursor != null; cursor = cursor.next)
			result += cursor.value + " ";

		return result;
	}
}