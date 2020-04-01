package tema5;

import java.util.*;

/** Clase de prueba de conceptos de las Java Collections más importantes
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class ConceptoJavaCollections {
	public static void main(String[] args) {
		// TODO Llamar a los métodos que se quieran probar
		// pruebaListas();
		// pruebaSets();
		pruebaMapas();
	}
	
	private static void pruebaListas() {
		ArrayList<String> l = new ArrayList<String>();
		l.add( "Hola" );
		l.add( "Adiós" );
		l.add( "Adiós" );
		l.add( 0, "Soy Andoni" );
		l.set( 1, "Hi!" );
		System.out.println( l  + " - tamaño " + l.size() );
		l.remove( "Adiós" );  // equals!!!!  no ==
		System.out.println( l.get(0) );
		int posi = l.indexOf( "Adiós" );
		if (posi!=-1) {
			System.out.println( "Elemento en " + posi + ": " + l.get(posi) );
		}
		if (l.contains( "Hi!" )) {
			System.out.println( "Contiene saludo" );
		}
		System.out.println( l  + " - tamaño " + l.size() );
	}
	
	private static void pruebaSets() {
		String[] palabras = { "Java", "método", "clase", "objeto", "método", "instancia", "interfaz", "herencia", "polimorfismo", "Java" };
		HashSet<String> conjHash = new HashSet<String>();
		TreeSet<String> conjTree = new TreeSet<String>();
		for (String palabra : palabras) {
			conjHash.add( palabra );  // equals!!!!
			conjTree.add( palabra );
		}
		conjHash.remove( "Java" );  // equals!!!
		System.out.println( "Hash: " + conjHash );
		System.out.println( "Tree: " + conjTree );
		if (conjHash.contains( "Java" )) {
			System.out.println( "Hashset contiene Java" );
		}
		if (conjTree.contains( "Java" )) {
			System.out.println( "Treeset contiene Java" );
		}
		// No hay posición...
		// ...Pero sí se puede hacer recorrido, p ej
		for (String palabra : conjTree) {
			System.out.println( palabra );
		}
	}

	private static void pruebaMapas() {
		HashMap<String,String> mapaH = new HashMap<>();
		TreeMap<String,String> mapaT = new TreeMap<>();
		// Añadir = put
		mapaH.put( "Andoni", "miclave" );
		mapaT.put( "Andoni", "miclave" );
		mapaH.put( "Luis", "suClave" );
		mapaT.put( "Luis", "suClave" );
		mapaH.put( "Ainhoa", "claveA" );
		mapaT.put( "Ainhoa", "claveA" );
		System.out.println( mapaH );
		System.out.println( mapaT );
		// Consultar = get (null / no null)
		System.out.println( mapaH.get( "Luis" ) );
		System.out.println( mapaH.get( "Luisa" ) );
		// 2 consulta = containsKey
		System.out.println( mapaH.containsKey( "Luis") );
		// Eliminar = remove
		mapaH.remove( "Luis" );
		System.out.println( mapaH );
		// Recorrido
		for (String clave : mapaT.keySet()) {
			String valor = mapaT.get( clave );
			System.out.println( clave + " - " + valor );
		}
		
		// Ejemplo de integración 2 JC -- mapa de listas
		// Usuarios con puntuaciones en un hipotético juego
		//   "andoni" -> (1, 17, 5, 23)
		//   "laura" -> (20, 15, 2, 18)
		// ...
		TreeMap<String,ArrayList<Integer>> mapaPuntuaciones = new TreeMap<>();
		anadirPunt( mapaPuntuaciones, "andoni", 1 );
		anadirPunt( mapaPuntuaciones, "andoni", 17 );
		anadirPunt( mapaPuntuaciones, "andoni", 5 );
		anadirPunt( mapaPuntuaciones, "andoni", 23 );
		anadirPunt( mapaPuntuaciones, "laura", 20 );
		anadirPunt( mapaPuntuaciones, "laura", 15 );
		anadirPunt( mapaPuntuaciones, "laura", 2 );
		anadirPunt( mapaPuntuaciones, "laura", 18 );
		System.out.println( mapaPuntuaciones );
	}
	
	// Añadir una puntuación a un usuario
	private static void anadirPunt( TreeMap<String,ArrayList<Integer>> mapaPuntuaciones, String clave, int sigPunt ) {
		// ¿Ya está?
		if (!mapaPuntuaciones.containsKey(clave)) {
			mapaPuntuaciones.put( clave, new ArrayList<Integer>() );
		}
		ArrayList<Integer> l = mapaPuntuaciones.get(clave);
		l.add( new Integer(sigPunt) );
	}
	
	
	
}






























