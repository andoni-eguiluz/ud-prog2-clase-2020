package tema1.ver3;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import tema1.VentanaGrafica;

/** Juego de alinear pelotas en un tablero imaginario de bolas de tres colores
 * Versión 3 - Primera interacción - mueve las pelotas pero a sitios arbitrarios (se montan, no comprueban huecos)
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class JuegoPelotas {

	private static final int ANCHO_CASILLA = 200;  // Ancho de casilla en píxels. Si no va a cambiar, atributos constantes
	private static final int ALTO_CASILLA = 150;  // Alto de casilla en píxels.
	private static int altoTablero = 5;  // Nº de casillas. Si va a cambiar, atributos variables
	private static int anchoTablero = 5;  // Nº de casillas en horizontal.
	private static int numPelotas = 20;  // Nº de pelotas a meter en el tablero
	private static final int RADIO_MINIMO = 10;
	private static final int RADIO_MAXIMO = 50;
	private static final Color[] COLORES_PELOTA =  { Color.RED, Color.GREEN, Color.BLUE };
	private static VentanaGrafica vent;
	private static GrupoPelotas grupo;
	
	public static void main(String[] args) {
		init();
		juego();
	}
	
	private static void juego() {
		boolean juegoActivo = true;
		while (!vent.estaCerrada() && juegoActivo) {
			Point puls = vent.getRatonPulsado();
			if (puls!=null) {
				Pelota pDentro = grupo.buscaPuntoEnPelotas( puls );
				if (pDentro!=null) {
					Point drag = vent.getRatonPulsado();
					while (drag!=null) {  // Esperar a que se acabe el drag
						// Mientras estoy haciendo drag
						pDentro.setX( drag.x );
						pDentro.setY( drag.y );
						// pDentro.dibuja( vent );
						vent.borra();
						grupo.dibuja( vent );
						vent.espera( 20 );
						drag = vent.getRatonPulsado();
					}
					// Final del movimiento
					
				}
			}
			vent.espera( 20 );
		}
	}
	
	private static void init() {
		vent = new VentanaGrafica( anchoTablero * ANCHO_CASILLA, altoTablero * ALTO_CASILLA, "Juego" );
		Random r = new Random();
		grupo = new GrupoPelotas( numPelotas );
		for (int i=0; i<numPelotas; i++) {
			int radio = r.nextInt(RADIO_MAXIMO - RADIO_MINIMO + 1) + RADIO_MINIMO;
			Color color = COLORES_PELOTA[ r.nextInt( COLORES_PELOTA.length ) ];
			Pelota p = new Pelota( 0, 0, radio, color );
			do { // Añadido para que no se repitan las pelotas
				p.setX( r.nextInt( 5 ) * ANCHO_CASILLA + (ANCHO_CASILLA/2) );
				p.setY( r.nextInt( 5 ) * ALTO_CASILLA + (ALTO_CASILLA/2) );
				// boolean estaYa = grupo.buscaPelota(p)==-1;
			} while (grupo.buscaPelotaEquals(p)!=-1); // Importante que este método compare con equals
			grupo.anyadePelota( p );
		}
		grupo.dibuja( vent );
	}

}











