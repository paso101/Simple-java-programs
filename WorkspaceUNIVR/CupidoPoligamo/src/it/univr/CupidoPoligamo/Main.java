package it.univr.CupidoPoligamo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jbook.util.Input;

/**
 * Un programma che gestisce delle coppie di fidanzati. Ammette che una
 * persona possa aver più fidanzati.
 * 
 * 
 */

public class Main {

	/**
	 * Il database.
	 */

	private final Map<String, Set<String>> database = new HashMap<String, Set<String>>();

	private Main() {
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
				if (!database.get(romeo).isEmpty()) {
					System.out.print(romeo + " è fidanzato con");
					boolean primo = true;
					for (String fidanzato: database.get(romeo))
						if (primo) {
							System.out.print(" " + fidanzato);
							primo = false;
						}
						else
							System.out.print(", " + fidanzato);

					System.out.println();
				}

			return true;
		}
		else
			return false;
	}

	private boolean gestisciCosaSaiDi(String frase) {
		if (frase.startsWith("cosa sai di ") && frase.endsWith("?")) {
			String romeo = frase.substring(12, frase.length() - 1);
			if (database.get(romeo) == null || database.get(romeo).isEmpty())
				System.out.println("non so nulla di " + romeo);
			else {
				System.out.print(romeo + " sta con");
				boolean primo = true;
				for (String fidanzato: database.get(romeo))
					if (primo) {
						System.out.print(" " + fidanzato);
						primo = false;
					}
					else
						System.out.print(", " + fidanzato);

				System.out.println();
			}

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
		// gli ex-fidanzati rimagono fidanzati!

		// in più fidanziamo i due piccioncini
		fidanza(romeo, giulietta);
	}

	private void separa(String chi, String daChi) {
		if (database.get(chi) != null)
			database.get(chi).remove(daChi);

		if (database.get(daChi) != null)
			database.get(daChi).remove(chi);
	}

	private void fidanza(String chi, String conChi) {
		Set<String> fidanzati = database.get(chi);
		if (fidanzati == null)
			// si sta fidanzando per la prima volta!
			database.put(chi, fidanzati = new HashSet<String>());

		fidanzati.add(conChi);

		fidanzati = database.get(conChi);
		if (fidanzati == null)
			// si sta fidanzando per la prima volta!
			database.put(conChi, fidanzati = new HashSet<String>());

		fidanzati.add(chi);
	}
	/**
	 * Il punto di ingresso del programma.
	 *
	 * @param args
	 */
	
	public static void main(String[] args) {
		new Main();
	}

}