package tema1.ver2;

import java.awt.Color;
import java.util.Random;

import tema1.VentanaGrafica;

public class JuegoPelotas {

	private static int anchoCasilla = 200;
	private static int altoCasilla = 150;
	private static final int ALTO_CASILLAS = 5;
	private static final int ANCHO_CASILLAS = 5;
	private static final int NUM_PELOTAS = 20;
	private static VentanaGrafica vent;
	private static GrupoPelotas grupo;
	
	public static void main(String[] args) {
		vent = new VentanaGrafica( ANCHO_CASILLAS * anchoCasilla, ALTO_CASILLAS * altoCasilla, "Juego" );
		Random r = new Random();
		grupo = new GrupoPelotas( NUM_PELOTAS );
		for (int i=0; i<NUM_PELOTAS; i++) {
			int radio = 50;
			Color color = Color.magenta;
			Pelota p = new Pelota( 0, 0, radio, color );
			p.setX( r.nextInt( 5 ) * anchoCasilla + (anchoCasilla/2) );
			p.setY( r.nextInt( 5 ) * altoCasilla + (altoCasilla/2) );
			grupo.anyadePelota( p );
		}
		grupo.dibuja( vent );
	}

}











