package it.univr.Elezioni;

public class PartitMaiRegistratoException extends IllegalArgumentException {

	public PartitMaiRegistratoException(Partito partito) {
		super(partito.getNome());
	}
}