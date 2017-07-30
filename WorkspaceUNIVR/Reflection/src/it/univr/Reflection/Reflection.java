package it.univr.Reflection;

public class Reflection {
	
	public static void main (String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		 
		String nomeClasseDaInstanziare = "Somma";
		
		Class<?> classe = Class.forName("it.univr.Reflection." + nomeClasseDaInstanziare);
		Operazione op = (Operazione)classe.newInstance();
		op.qualeOpEsegui();
		op.execute(3,5);	}

}