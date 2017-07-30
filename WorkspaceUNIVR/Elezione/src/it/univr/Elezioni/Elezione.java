package it.univr.Elezioni;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Elezione {
/**
* Le coalizioni registrate per questa elezione. Se un partito si presenta da solo,
* stara' in una coalizione in cui e' presente solo esso stesso
*/
private final Set<Coalizione> coalizioni = new HashSet<Coalizione>();


/**
* Una mappa che associa a ciascun partito i voti che ha preso fino ad ora
*/
private final Map<Partito, Integer> votiPerPartito = new HashMap<Partito, Integer>();


/**
* Registra il partito indicato come un partecipante all'elezione, dentro una
* coalizione di cui fa parte solo esso stesso. Se il partito e' gia' registrato,
* genera una PartitoGiaRegistratoException.
*/
public void registra(Partito partito) {
	
	if (votiPerPartito.containsKey(partito))
		throw new PartitoGiàRegistratoException(partito);

	votiPerPartito.put(partito, 0);

	// all'inizio, un partito fa parte di una coalizione di cui è l'unico rappresentante
	// e che ha lo stesso nome del partito
	coalizioni.add(new Coalizione(partito.getNome(), partito));
	
}


/**
* Registra la coalizione indicata come partecipante all'elezione. Se esiste gia' una
* coalizione uguale, deve generare una eccezione di classe CoalizioneGiaPresenteException.
* Altrimenti i partiti della coalizione vengono eliminati da tutte le altre coalizioni,
* se ne facevano parte. Tali coalizioni, se in tal modo diventassero vuote, devono venire
* de-registrate (eliminate) dall'elezione.
* Tutti i partiti della coalizione registrata sono automaticamente registrati all'elezione
*/
public void registra(Coalizione coalizione) {
	
	if(coalizioni.contains(coalizione))
		throw new CoalizioneGiàPresenteException(coalizione);
	
	for (Coalizione altraCoalizione: new HashSet<Coalizione>(coalizioni))
		for (Partito partito: coalizione)
			if (altraCoalizione.rimuovi(partito))
				// se un'altra coalizione è diventata vuota, la rimuoviamo
				coalizioni.remove(altraCoalizione);

	coalizioni.add(coalizione);

	for (Partito partito: coalizione)
		if (!votiPerPartito.containsKey(partito))
			votiPerPartito.put(partito, 0);
			
		
}


/**
* Registra un nuovo voto per il partito indicato (e quindi anche per la coalizione di cui esso fa parte).
* Se il partito non e' registrato all'elezione, genera una PartitoMaiRegistratoException.
*/
public void registraVotoPer(Partito partito) { 

	if (!votiPerPartito.containsKey(partito))
		throw new PartitMaiRegistratoException(partito);

	votiPerPartito.put(partito, votiPerPartito.get(partito) + 1);
	
}
@Override public String toString() {
	String risultato = "";
	
	int votiTotali = sommaDiTuttiIVoti();

	for (Coalizione coalizione: this.coalizioni) {//toString(coalizione, votiTotali) + "\n";
		risultato += "Coalizione \"" + coalizione.getNome() + "\":\n";
		
		int votiAllaCoalizione = 0;
		
		for(Partito partito: coalizione) {
			int votiOttenuti = votiPerPartito.get(partito);
			votiAllaCoalizione+=votiOttenuti;
			
			risultato += "  " + partito.getNome() + ": voti " + votiOttenuti + " (" + percentuale(votiOttenuti, votiTotali) + ")\n";
		}
		risultato += "Totale voti alla coalizione: " + votiAllaCoalizione + " (" + percentuale(votiAllaCoalizione, votiTotali) + ")\n";
	}

	return risultato;
}

private static String percentuale(int votiOttenuti, int votiTotali) {
	return String.valueOf((int) (votiOttenuti * 10000.0 / votiTotali) / 100.0) + "%";
}

private int sommaDiTuttiIVoti() {
	int somma = 0;

	for (Partito partito: votiPerPartito.keySet())
	    somma += votiPerPartito.get(partito);
	
	return somma;
}
	
}