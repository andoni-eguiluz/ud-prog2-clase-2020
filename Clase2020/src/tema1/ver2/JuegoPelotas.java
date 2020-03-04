package tema1.ver2;

import java.awt.Color;
import java.util.Random;

import tema1.VentanaGrafica;

/** Juego de alinear pelotas en un tablero imaginario de bolas de tres colores
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class JuegoPelotas {

	private static final int ANCHO_CASILLA = 200;  // Ancho de casilla en píxels. Si no va a cambiar, atributos constantes
	private static final int ALTO_CASILLA = 150;  // Alto de casilla en píxels.
	private static int altoTablero = 5;  // Nº de casillas. Si va a cambiar, atributos variables
	private static int anchoTablero = 5;  // Nº de casillas en horizontal.
	private static int numPelotas = 20;  // Nº de pelotas a meter en el tablero
	private static VentanaGrafica vent;
	private static GrupoPelotas grupo;
	
	public static void main(String[] args) {
		vent = new VentanaGrafica( anchoTablero * ANCHO_CASILLA, altoTablero * ALTO_CASILLA, "Juego" );
		Random r = new Random();
		grupo = new GrupoPelotas( numPelotas );
		for (int i=0; i<numPelotas; i++) {
			int radio = 50;
			Color color = Color.magenta;
			Pelota p = new Pelota( 0, 0, radio, color );
			do { // Añadido para que no se repitan las pelotas
				p.setX( r.nextInt( 5 ) * ANCHO_CASILLA + (ANCHO_CASILLA/2) );
				p.setY( r.nextInt( 5 ) * ALTO_CASILLA + (ALTO_CASILLA/2) );
				// boolean estaYa = grupo.buscaPelota(p)==-1;
			} while (grupo.buscaPelotaEquals(p)!=-1);
			grupo.anyadePelota( p );
		}
		grupo.dibuja( vent );
	}

}











