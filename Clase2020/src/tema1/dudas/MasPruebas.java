package tema1.dudas;

import java.util.Scanner;

/** Pruebas de clase de dudas
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class MasPruebas {
	public static void main(String[] args) {
		double a = 5.3;
		int res = (int) Math.round( a );
		Integer i = new Integer(5);
		// lineas();
		comparaStrings();
	}
	
	private static void comparaStrings() {
		String s1 = "aa";
		String s2 = "AA";
		System.out.println( s1.compareTo(s2) );
	}
	
	private static void lineas() {
		Scanner entrada = new Scanner( System.in );
		double raiz = 5.0;
		double raiz2 = Math.sqrt( raiz );
		double d = entrada.nextDouble();
		System.out.println( "Valor = " + d );
		String ent = entrada.nextLine();
		ent = entrada.nextLine();
		System.out.println( ent );
		int i = Integer.parseInt( ent );
	}
}
