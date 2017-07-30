package it.univr.Elezioni;

public class CoalizioneGiàPresenteException extends IllegalArgumentException {
	public CoalizioneGiàPresenteException(Coalizione coalizione) {
		super(coalizione.getNome());
	}
}