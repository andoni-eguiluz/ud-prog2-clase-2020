package tema1;

import java.awt.Color;

/** Ejemplo de array de objetos
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class PruebaArray {
	public static void main(String[] args) {
		Pelota[] v = null;
		// System.out.println( v[3] );  // Error
		v = new Pelota[6];
		System.out.println( v[5] );
		Pelota p = new Pelota();
		v[5] = p;
		p.setColor( Color.red );
		System.out.println( v[5] );
		// Duda de retorno
		System.out.println( devuelvoAlgo( 7 ));
	}
	
	// Ejemplo de devolución
	private static int devuelvoAlgo( int h ) {
		int calc = 0;
		// ...
		if (h%2==0) {
			return 0;
		} else {
			return 1;
		}
//		return 7 * calc + h;
		// int j = 5;  // Nada después del return
	}
}
