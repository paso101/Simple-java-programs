package it.univr.Circuito;


import java.util.Set;

public final class Not extends Circuit {

	private final Circuit inner;

	public Not(Circuit inner) {
		this.inner = inner;
	}

	@Override
	public boolean isTrueIn(Assignment assignment) {
		return !inner.isTrueIn(assignment);
	}

	@Override
	public Set<String> freeVariables() {
		return inner.freeVariables();
	}

	@Override
	public String toString() {
		return "not(" + inner.toString() + ")";
	}
}