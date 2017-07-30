package it.univr.Reflection;

public class Somma implements Operazione {
	
	public void qualeOpEsegui(){
		System.out.println("SOMMA" );
	}
	
	public void execute (int a, int b) {
		System.out.println ((a+b) );
	}
}