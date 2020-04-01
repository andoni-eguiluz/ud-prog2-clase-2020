package tema4;

/** Clase de prueba de concepto de interfaz
 * Con clases de prueba dentro del mismo fichero
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class ConceptoInterfaz {
	public static void main(String[] args) {
		A a = new B();  // Polimorfismo de clase padre (puede contener cualquier instancia de clase hija)
		Cantarin c = new Cantarin();
		c.cantar( "La la la" );
		Bailarin b = new Bailarin();
		b.bailar();
		Juerguista j = new Juerguista();
		j.cantar( "Lo lo lo" );
		// Polimorfismo de interfaces, igual que el de clases
		Cantable var1 = j;  
		Cantable var2 = c;
		Cantable[] listaCantables = new Cantable[10];
		listaCantables[0] = j;
		listaCantables[1] = c;
		if (var1 instanceof ActorMusical) {
			ActorMusical am = (ActorMusical) var1;
			am.haceCoros();
		}
		if (var2 instanceof Bailable) {
			Bailable am = (Bailable) var2;
			am.bailar();
		}
	}
}

class Juerguista extends Cantarin implements Bailable, ActorMusical {
	public void bailar() {
		System.out.println( "Baila" );
	}
	public void haceCoros() {
		System.out.println( "aaa" );
	}
}

class Cantarin implements Cantable {
	String ultimoCantado;
	public void cantar( String letra ) {
		System.out.println( letra );
		ultimoCantado = letra;
	}
}

class Bailarin implements Bailable {
	public void bailar() {
		System.out.println( "Baila" );
	}
}

interface Cantable {
	public void cantar( String letra );
}

interface Bailable {
	public void bailar();
}

interface ActorMusical extends Bailable, Cantable {
	// bailar, cantar
	public void haceCoros();
}



abstract class A {
	int a;
	void m() {
		System.out.println( "a" );
	}
}

class B extends A {
	// a, m
}

// Abstracta = interfaz si no atributos ni código (métodos abstractos)
abstract class I {
	abstract void m1();
	abstract int m2( String s );
}
