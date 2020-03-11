package tema3;

public class EjemploHerencia {

	public static void main(String[] args) {
		APadre a = new APadre();
		a.i = 5;
		System.out.println( a.i );
		BHija b = new BHija();
		b.i = 7;
		System.out.println( b.i );
		a.ver();
		b.ver();
		b.verB();
		b.quienSoy();
		APadre incognita = new BHija();
		incognita.quienSoy();
	}
}

class APadre {
	int i;
	int j;
	public void ver() {
		System.out.println(i+j);
	}
	public void quienSoy() {
		System.out.println( "soy un A" );
	}
}

class BHija extends APadre {
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
