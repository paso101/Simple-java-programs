package it.univr.beedeedee.gameOfLife;

import jbook.util.Input;

/**
 * MenuSelect.java
 * 
 * @author Damiano Pasotto VR397209 mailto: damiano.pasotto@studenti.univr.it
 * @author Lara Scarpari VR401241 mailto: lara.scarpari@studenti.univr.it
 * @version 1.0
 * 
 * <h2>MenuSelect</h2>
 * <p>La classe <i>MenuSelect</i> permette di inserire
 * una stringa da tastiera e scegliere, quindi, il <i>pattern</i>
 * da posizionare sulla griglia.</p>
 */
public class MenuSelect {
	
	/**
	 * Legge una stringa acquisita tramite tastiera
	 * e controlla che sia compresa tra quelle indicate.
	 * Se la stringa non &egrave; tra quelle indicate,
	 * allora stampa un messaggio di errore.<br>
	 * Per il pattern scelto invoca il metodo <i>start()</i>.
	 */
	protected void selectPattern() {
		
		System.out.println(
				"###################################\n" +
				"#                                 #\n" +
				"#           Game of Life          #\n" +
				"#           BDD Edition!          #\n" +
				"#                                 #\n" +
				"###################################\n"
		);
		
		System.out.println(
				"   zero cell       " + "    one cell    \n" +
				"----------------   " + "----------------\n" +
				"|  |  |  |  |  |   " + "|  |  |  |  |  |\n" +
				"----------------   " + "----------------\n" +
				"|  |  |  |  |  |   " + "|  |  |##|  |  |\n" +
				"----------------   " + "----------------\n" +
				"|  |  |  |  |  |   " + "|  |  |  |  |  |\n" +
				"----------------   " + "----------------\n" +
				"|  |  |  |  |  |   " + "|  |  |  |  |  |\n" +
				"----------------   " + "----------------\n"
		);
		
		System.out.println(
				"      block        " + "  border block  \n" +
				"----------------   " + "----------------\n" +
				"|  |  |  |  |  |   " + "|  |  |  |  |  |\n" +
				"----------------   " + "----------------\n" +
				"|  |##|##|  |  |   " + "|  |  |  |  |  |\n" +
				"----------------   " + "----------------\n" +
				"|  |##|##|  |  |   " + "|  |  |  |##|##|\n" +
				"----------------   " + "----------------\n" +
				"|  |  |  |  |  |   " + "|  |  |  |##|##|\n" +
				"----------------   " + "----------------\n"
		);
		
		System.out.println(
				"     beacon        " + "    blinker     \n" +
				"----------------   " + "----------------\n" +
				"|##|##|  |  |  |   " + "|  |  |  |  |  |\n" +
				"----------------   " + "----------------\n" +
				"|##|##|  |  |  |   " + "|  |##|##|##|  |\n" +
				"----------------   " + "----------------\n" +
				"|  |  |##|##|  |   " + "|  |  |  |  |  |\n" +
				"----------------   " + "----------------\n" +
				"|  |  |##|##|  |   " + "|  |  |  |  |  |\n" +
				"----------------   " + "----------------\n"
		);
		
		System.out.println(
				"      line         " + "     glider     \n" +
				"----------------   " + "----------------\n" +
				"|  |  |##|  |  |   " + "|  |  |##|  |  |\n" +
				"----------------   " + "----------------\n" +
				"|  |  |##|  |  |   " + "|  |  |  |##|  |\n" +
				"----------------   " + "----------------\n" +
				"|  |  |##|  |  |   " + "|  |##|##|##|  |\n" +
				"----------------   " + "----------------\n" +
				"|  |  |##|  |  |   " + "|  |  |  |  |  |\n" +
				"----------------   " + "----------------\n"
		);
		
		System.out.println("Choose one of the patterns");
		
		String patternSelected = Input.readString("--> ");
		
		System.out.println();
		
		switch(patternSelected) {
		
			case "zero cell":
				new ZeroCell(patternSelected).start();
				break;
				
			case "one cell":
				new OneCell(patternSelected).start();
				break;
				
			case "block":
				new Block(patternSelected).start();
				break;
				
			case "border block":
				new BorderBlock(patternSelected).start();
				break;
			
			case "beacon":
				new Beacon(patternSelected).start();
				break;

			case "blinker":
				new Blinker(patternSelected).start();
				break;
				
			case "line":
				new Line(patternSelected).start();
				break;
				
			case "glider":
				new Glider(patternSelected).start();
				break;
				
			default:
				System.out.println("Pattern unknown!");
				break;

		}
		
	}
	
}
