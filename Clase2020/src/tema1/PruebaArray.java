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
	}
}
