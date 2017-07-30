package it.univr.Circuito;


import java.util.Set;

public final class And extends Circuit {

	private final Circuit left;
	private final Circuit right;

	public And(Circuit left, Circuit right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public boolean isTrueIn(Assignment assignment) {
		return left.isTrueIn(assignment) && right.isTrueIn(assignment);
	}

	@Override
	public Set<String> freeVariables() {
		Set<String> result = left.freeVariables();
		result.addAll(right.freeVariables());

		return result;
	}

	@Override
	public String toString() {
		return "and(" + left.toString() + "," + right.toString() + ")";
	}
}