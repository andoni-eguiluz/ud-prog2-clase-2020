package tema1.ver5;

import java.awt.Point;

import tema1.VentanaGrafica;

// TODO Pendiente actualizar comentarios y todas las referencias a pelotas
// porque ahora son estrellas

/** Clase que permite crear un grupo variable de pelotas y dibujarlo en pantalla
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class GrupoEstrellas {
	private Estrella[] estrellas = null;  // Array de pelotas
	private int numEstrellas = 0;    // Número actual de pelotas
	
	/** Crea un grupo de pelotas
	 * @param numMax	Número máximo de pelotas que tendrá ese grupo
	 */
	public GrupoEstrellas( int numMax ) {
		estrellas = new Estrella[numMax];
		numEstrellas = 0;
	}

	/** Crea un grupo de pelotas de tamaño 10
	 */
	public GrupoEstrellas() {
		estrellas = new Estrella[10];
	}
	
	/** Devuelve el número de las pelotas actualmente en el grupo
	 * @return	Número de pelotas (entre 0 y núm máximo)
	 */
	public int getNumEstrellas() {
		return numEstrellas;
	}
	
	/** Añade una pelota al grupo
	 * @param pelota	Nueva pelota a añadir
	 * @return	true si el añadido es correcto, false si no cabe (el grupo está lleno)
	 */
	public boolean anyadeEstrella( Estrella pelota ) {
		if (numEstrellas==estrellas.length) {
			return false;
		}
		estrellas[numEstrellas] = pelota;
		numEstrellas++;
		return true;
	}
	
	/** Borra una pelota del grupo
	 * @param numEstrella	Índice de la pelota a borrar. Debe estar en el rango 0 a (n-1) siendo n el número de pelotas existentes.
	 */
	public void borraEstrella( int numEstrella ) {
		// Al borrar una pelota se desplazan las siguientes
		// Por ejemplo si borramos p
		// [ 0 | 1 | ... | p |p+1|p+2| ... | n-1 | ... ]
		// Quedarán
		// [ 0 | 1 | ... |p+1|p+2| ... | n-1 | ...     ]
		for (int i=numEstrella+1; i<numEstrellas; i++) {
			estrellas[i-1] = estrellas[i];
		}
		numEstrellas--;
	}
	
	/** Borra una pelota del grupo
	 * @param pelota	Estrella a borrar. Si no está en el grupo, no se hace nada
	 */
	public void borraEstrella( Estrella pelota ) {
		int posi = buscaEstrella( pelota );
		if (posi!=-1) {
			borraEstrella( posi );
		}
	}
	
	/** Devuelve una pelota del grupo
	 * @param numEstrella	Índice de la pelota, de 0 a n-1
	 * @return	Estrella correspondiente (la devuelve pero no la quita del grupo)
	 */
	public Estrella getEstrella( int numEstrella ) {
		return estrellas[numEstrella];
	}
	
	/** Busca una pelota en el grupo
	 * @param pelota	Estrella a buscar (mismo objeto)
	 * @return	Posición donde está la pelota, -1 si no se encuentra
	 */
	public int buscaEstrella( Estrella pelota ) {
		for (int i=0; i<estrellas.length; i++) {
			if (pelota==estrellas[i]) return i;
		}
		return -1;
	}
	
	// Añadido para poder buscar pelotas iguales siendo distintos objetos
	/** Busca una pelota en el grupo
	 * @param pelota	Estrella a buscar, de acuerdo a sus ATRIBUTOS
	 * @return	Posición donde está la pelota, -1 si no se encuentra
	 */
	public int buscaEstrellaEquals( Estrella pelota ) {
		for (int i=0; i<numEstrellas; i++) {
			if (pelota.equals(estrellas[i])) return i;
		}
		return -1;
	}
	
	/** Dibuja todas las pelotas del grupo
	 * @param v	Ventana en la que dibujar
	 */
	public void dibuja( VentanaGrafica v ) {
		// for :
		// podría hacer otras cosas
		// for (int i=0; i<numEstrellas; i = i + 2)
		for (int i=0; i<numEstrellas; i++) {
			estrellas[i].dibuja( v );
		}
	}

	// Devuelve el grupo de pelotas en formato [pelota1|pelota2|...]  (solo devuelve el grupo con el tamaño que tenga)
	@Override
	public String toString() {
		String ret = "[";
		for (int i=0; i<numEstrellas; i++) {
			ret += estrellas[i];
			if (i<numEstrellas-1) ret += "|";
		}
		return ret + "]";
	}
	
	/* Posible prueba de clase
	public static void main(String[] args) {
		GrupoEstrellas g = new GrupoEstrellas(2);
		System.out.println( g.anyadeEstrella( new Estrella() ) );
		System.out.println( g.anyadeEstrella( new Estrella() ) );
		System.out.println( g.anyadeEstrella( new Estrella() ) );
		System.out.println( g );
		g.borraEstrella(1);
		System.out.println( g );
	}
	*/
}
