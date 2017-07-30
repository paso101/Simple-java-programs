package it.univr.Data;

public class Data {
	
	public int anno;
	public int mese;
	public int giorno;
	
	public Data(int g, int m, int a) {
		this.anno = a;
		this.mese = m;
		this.giorno = g;
	}
	
	public Data(int g, int m) {
		this.anno = 2008;
		this.mese = m;
		this.giorno = g;
	}
	
	public Data(int g) {
		this.anno = 2008;
		this.mese = 1;
		this.giorno = g;
	}
	
	public Data() {
		this.anno = 2008;
		this.mese = 1;
		this.giorno = 13;
	}
	
	boolean precede(Data altra) {
		if (this.anno < altra.anno)  //this.anno è quello a sx del puntino___altra.anno è a dx del puntino
			return true;
		
		else if ((this.anno == altra.anno) && (this.mese < altra.mese))
			return true;
		
		else if ((this.anno == altra.anno) && (this.mese == altra.mese) && (this.giorno < altra.giorno))
			return true;
		
		else return false;
	}
	
	public String toString() {
		String nomeMese;
		switch (this.mese) {
			
		case 1: 
			nomeMese = "Gennaio";break;
		case 2: 
			nomeMese = "Febbraio";break;
		case 3:
			nomeMese = "Marzo";break;
		case 4:
			nomeMese = "Aprile";break;
		case 5:
			nomeMese = "Maggio";break;
		case 6:
			nomeMese = "Giugno";break;
		case 7:
			nomeMese = "Luglio";break;
		case 8: 
			nomeMese = "Agosto";break;
		case 9:
			nomeMese = "Settembre";break;
		case 10:
			nomeMese = "Ottobre";break;
		case 11:
			nomeMese = "Novembre";break;
		case 12:
			 nomeMese = "Dicembre";break;
			
		default:
			nomeMese = "ERROR mese";break;
							
		}	

		return giorno + " / " + nomeMese + " / "  + anno;
	
	

	}
	
	public int passatiDallInizio() {
		int[] giorni = { 31,28,31,30,31,30,31,31,30,31,30,31 };
	
		// consideriamo i bisestili
		if (anno % 400 == 0 ||
		    (anno % 100 != 0 && anno % 4 == 0))
		    giorni[1] = 29;
	
		int risultato = giorno;
		for (int m = 1; m < mese; m++)
		    risultato += giorni[m - 1];
	
		return risultato;
    }
		
	}
	