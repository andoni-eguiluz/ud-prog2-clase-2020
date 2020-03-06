package tema3;

public class EjemploHerencia {

	public static void main(String[] args) {
		A a = new A();
		a.i = 5;
		System.out.println( a.i );
		B b = new B();
		b.i = 7;
		System.out.println( b.i );
		a.ver();
		b.ver();
		b.verB();
		b.quienSoy();
		A incognita = new B();
		incognita.quienSoy();
	}
}

class A {
	int i;
	int j;
	public void ver() {
		System.out.println(i+j);
	}
	public void quienSoy() {
		System.out.println( "soy un A" );
	}
}

class B extends A {
//	int i;
//	int j;
	int k;
	public void verB() {
		System.out.println( i+j+k );
	}
	// Redefinir un método
	public void quienSoy() {
		System.out.println( "soy un B");
	}
}
