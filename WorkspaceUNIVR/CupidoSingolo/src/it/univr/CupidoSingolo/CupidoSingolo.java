package it.univr.CupidoSingolo;

import java.util.HashMap;
import java.util.Map;

import jbook.util.Input;

/**
 * Un programma che gestisce delle coppie di fidanzati.
 * 
 * @author <A HREF="mailto:fausto.spoto@univr.it">Fausto Spoto</A>
 */

public class CupidoSingolo {

	/**
	 * Il database.
	 */

	private final Map<String, String> database = new HashMap<String, String>();

	/**
	 * Il punto di ingresso del programma.
	 *
	 * @param args
	 */

	public static void main(String[] args) {
		new CupidoSingolo();
	}

	private CupidoSingolo() {
		String frase;

		do {
			frase = Input.readString("Comando: ");
		}
		while (gestisciFidanza(frase) || gestisciSepara(frase)
				|| gestisciCosaSaiDi(frase) || gestisciStampa(frase)
				|| gestisciComandoIncomprensibile(frase));
	}

	private boolean gestisciComandoIncomprensibile(String frase) {
		if (!frase.equals("fine")) {
			System.out.println("comando incomprensibile");
			return true;
		}
		else
			return false;
	}

	private boolean gestisciStampa(String frase) {
		if (frase.equals("stampa")) {
			// stampiamo il database
			for (String romeo: database.keySet())
				System.out.println(romeo + " è fidanzato con " + database.get(romeo));

			return true;
		}
		else
			return false;
	}

	private boolean gestisciCosaSaiDi(String frase) {
		if (frase.startsWith("cosa sai di ") && frase.endsWith("?")) {
			String romeo = frase.substring(12, frase.length() - 1);
			if (database.get(romeo) == null)
				System.out.println("non so nulla di " + romeo);
			else
				System.out.println(romeo + " sta con " + database.get(romeo));

			return true;
		}
		else
			return false;
	}

	private boolean gestisciSepara(String frase) {
		if (frase.startsWith("separa ") && frase.indexOf(" da ") > 0) {
			// identifichiamo i due sfortunati
			String romeo = frase.substring(7, frase.indexOf(" da "));
			String giulietta = frase.substring(frase.indexOf(" da ") + 4);

			separa(romeo, giulietta);

			return true;
		}
		else
			return false;
	}

	private boolean gestisciFidanza(String frase) {
		if (frase.startsWith("fidanza ") && frase.indexOf(" con ") > 0) {
			// identifichiamo i due fortunati
			String romeo = frase.substring(8, frase.indexOf(" con "));
			String giulietta = frase.substring(frase.indexOf(" con ") + 5);

			creaRelazioneFra(romeo, giulietta);

			return true;
		}
		else
			return false;
	}

	private void creaRelazioneFra(String romeo, String giulietta) {
		// prima tornano un attimo ad essere single...
		separa(romeo, database.get(romeo));
		separa(giulietta, database.get(giulietta));

		// quindi i due piccioncini si fidanzano
		fidanza(romeo, giulietta);
	}

	private void separa(String chi, String daChi) {
		// erano realmente fidanzati?
		if (database.get(chi) != null && database.get(chi).equals(daChi)) {
			database.remove(chi);
			database.remove(daChi);
		}
	}

	private void fidanza(String chi, String conChi) {
		database.put(chi, conChi);
		database.put(conChi, chi);
	}
}