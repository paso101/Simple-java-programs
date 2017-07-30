package it.univr.Circuito;


import java.util.Set;
import java.util.HashSet;

public class Variable extends Circuit {

	private final String name;

	public Variable(String name) {
		this.name = name;
	}

	@Override
	public boolean isTrueIn(Assignment assignment) {
		return assignment.valueOf(this);
	}

	@Override
	public Set<String> freeVariables() {
		// un singleton che contiene solo il nostro nome
		Set<String> result = new HashSet<String>();
		result.add(name);

		return result;
	}

	@Override
	public String toString() {
		return name;
	}
}