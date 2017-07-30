package it.univr.stack;

import it.univr.stack.Stack.Node;

public class Main {

	public static void main(String[] args) {
		Stack<Integer> s1 = new Stack<Integer>();
		Stack<String> s2 = new Stack<String>();
		s1.push(13);
		s1.push(17);
		s1.push(-2);
		s2.push("ciao");
		s2.push("amico");
		s2.push("addio");
		s2.pop();
		System.out.println(s1);
		System.out.println("nodi creati per s1: " + s1.nodesSoFar());
		System.out.println(s2);
		System.out.println("nodi creati per s2: " + s2.nodesSoFar());
		Node nodeOfTop = s1.nodeOfTop();
		s1.new Node(13, null);
	}
}
