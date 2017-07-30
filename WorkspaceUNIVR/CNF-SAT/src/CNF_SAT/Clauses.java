/**
 * CNF-SAT, version 1.0, October 18 , 2014
 */

package CNF_SAT;

import java.util.ArrayList;

public class Clauses {
	
	String expression;
	ArrayList <String> clauses;

	public Clauses(String expression) {
		
		this.expression = expression;
		this.clauses = new ArrayList <String> ();
	}
	
	protected ArrayList <String> getClauses() {
		
		String[] part = expression.split("&");
		String result = new String();
		
		for( String s : part) {
			char [] chars = s.toCharArray();
			result = "";
			for(int i = 0; i < chars.length; i++)
				if(chars[i] >= 'A' && chars[i] <= 'z')
					result += chars[i];
			this.clauses.add(result);
		}
		
		return this.clauses;
	}
	
	/***
	 * Metodo per la stampa delle clausole.
	 * @return La stringa rappresentante le clausole.	 */
	
	@Override
	public String toString() {
		
		String res = new String();
		res = "{ ";
		for(int pos = 0; pos < this.clauses.size(); pos++)
			if(pos != this.clauses.size() - 1)
				res += this.clauses.get(pos) + ", ";
			else
				res += this.clauses.get(pos) + " }";
				
		return res;
	}

}
