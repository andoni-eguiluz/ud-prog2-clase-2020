package tema1.dudas;

import java.awt.Point;

/** Dudas y pruebas diversas
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class Pruebas {

	private static int dato = 5;
	
	public static void main(String[] args) {
		m();
		pruebaAccesoClaseYLocal();
	}
	
	private static void pruebaAccesoClaseYLocal() {
		int dato = 4;
		System.out.println( dato );
		System.out.println( Pruebas.dato );
	}
	
	private static void m() {
		System.out.println( dato );
		int i = 5;
		m2( i );
		System.out.println(i );
		i = m3( i );
		Point p = new Point( 5, 0 );
		m4 ( p );
		System.out.println( p );
	}
	
	private static void m2( int i ) {
		i++;
	}
	
	private static int m3( int k ) {
		return k+1;
	}
	
	private static void m4( Point p ) {
		p.setLocation( 6, 1 );
		p = null;
	}

}
