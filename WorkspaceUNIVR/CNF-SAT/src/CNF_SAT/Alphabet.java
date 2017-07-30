/**
 * CNF-SAT, version 1.0, October 18 , 2014
 */

package CNF_SAT;

import java.util.ArrayList;

/**
 * 
 * @author Damiano Pasotto mailto: damiano.pasotto@univr.it VR363785
 * @version 1.0
 * 
 * NOTE:<br>
 * <ul>
 * <li> Lettere MAIUSCOLE: letterali negati.
 * <li> Lettere minuscole: letterali non negato.
 * </ul>
 */
public class Alphabet {
	
	/**	 
	 * Stringa contenente la stringa cnf tutta in misucolo. 
	 * */
 	String cnfExpressionLowerCase; 
 	
	/**	 
	 * ArrayList che andr&agrave a memorizzare i symboli. 
	 * */
	ArrayList<Character> alphabet;
		
	public Alphabet(String expression) {
		this.cnfExpressionLowerCase = expression;
		this.alphabet = new ArrayList <Character>();
			
	}
		
	/**	 
	 * Metodo per scandire la stringa cnf e capire di quanti symboli è composta.
	 *  @return this.alphabet ArrayList con i simboli. 
	 * */
	protected ArrayList<Character> getAlphabet() {
	
		char[] expessionCharArray = this.cnfExpressionLowerCase.toCharArray();
		
		for(char character : expessionCharArray) {
			if (character >= 97 && character <= 122) {
										
				char c = character;
					if(!(this.alphabet.contains(c))) 		
						this.alphabet.add(c);
	
			}
		}
		
		return this.alphabet;
			
	}
	
	/***
	 * Metodo per la stampa dell'alfabeto.
	 * @return La stringa rappresentante l'alfabeto da stampare.	 */
	@Override
	public String toString() {
		
		String res = new String();
		res = "{ ";
		for(int pos = 0; pos < this.alphabet.size(); pos++)
			if(pos != this.alphabet.size() - 1)
				res += this.alphabet.get(pos) + ", ";
			else
				res += this.alphabet.get(pos) + " }";
				
		return res;
	}
}
