package tema1.basico;

import java.util.Date;

// Esta clase realmente se llama
// tema1.basico.TiposBasicosVsClases
public class TiposBasicosVsClases {
	
	public static int i;  // Variable accesible en todos los métodos
	
	public static void main(java.lang.String[] args) {
		difPrimitivosYObjetos();
		pruebaParams();
	}
	
	private static void pruebaParams() {
		int i = 5;
		pasoEntero(i);
		System.out.println( i ); // Qué pasa aquí con i, cambia?
		Date fecha = new Date(); // Coge la fecha actual, hora de ahora
		System.out.println( fecha );
		pasoFecha( fecha );
		System.out.println( fecha ); // Y qué pasa aquí con fecha, cambia? 
	}
	
	private static void pasoFecha( Date miFecha ) {
		System.out.println( miFecha );
		miFecha = new Date();
		miFecha.setTime( System.currentTimeMillis() + 24*3600000L );
	}
	
	private static void pasoEntero( int i ) {
		i++;  // lo mismo que i = i + 1;   
		// No es lo mismo que ++i;
		// En expresiones del tipo  j + (k++)
		System.out.println( i );
		
	}
	
	private static void difPrimitivosYObjetos() {
		int i = 5; // Primitiva: se DECLARA
		int j;
		j = 7 - 2;
		String s = "hola";  // String está en java.lang
		// Lo que pasa de verdad es esto:
		String s2 = new String( "hola" );   // Objeto: se declara y se crea
		String s3 = new String( "ho" );
		s3 = s3 + "la";
		System.out.println( i == j );
		System.out.println( s2 == s3 );
		if ( s=="hola" ) {
			// ... ej estructura
		}
		String s4 = s3;  // Aliasing - referencias sinónimas (2 refs al mismo objeto)	
		System.out.println( s3 == s4 );
		
		// Si queremos comparar contenidos hacemos EQUALS:
		System.out.println( s2.equals( s3 ) );
		if (s.equals("hola")) {  // Esto sí
			// ...
		}
		if ("hola".equals(s)) {  // O esto también
			
		}
		s3 = "no hola";
		System.out.println( s2.equals(s3));
		
//		java.util.Date fecha = new Date();  // Si no haces el import
		Date fecha = new Date();
	}
}
