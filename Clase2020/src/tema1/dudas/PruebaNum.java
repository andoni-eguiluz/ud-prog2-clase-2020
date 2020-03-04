package tema1.dudas;

/** Pruebas de clase de dudas
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class PruebaNum {
	public static void main(String[] args) {
		PruebaNum pn = new PruebaNum();
		pn.setDato( 1 );
		pn.ver();
		PruebaNum pn2 = new PruebaNum();
		pn2.setDato( 2 );
		pn2.ver();
	//	System.out.println( dato );  // Error : acceso static a algo de objeto
	}
	
	// ============================
	// No static
	
	private int dato = 2;
	
	public void setDato( int nuevoDato ) {
		dato = nuevoDato;
	}
	
	public void ver() {
		System.out.println( this.dato );
	}
	
	
}
