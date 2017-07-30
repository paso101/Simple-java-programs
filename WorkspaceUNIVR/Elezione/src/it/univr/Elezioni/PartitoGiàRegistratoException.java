package it.univr.Elezioni;

public class PartitoGiàRegistratoException extends IllegalArgumentException {
	public PartitoGiàRegistratoException(Partito partito) {
		super(partito.getNome());
	}
}