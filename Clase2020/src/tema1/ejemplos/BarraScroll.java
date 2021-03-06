package tema1.ejemplos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;

import tema1.VentanaGrafica;

/** Ejemplo de programación de una scroll vertical de una lista de datos a bajo nivel (dibujando directamente en pantalla)
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class BarraScroll {
	private static String[] ejemploDatos = new String[] {  // Datos de ejemplo
		"Avengers: Endgame", "Avatar", "Titanic", "Star Wars: Episodio VII - El despertar de la Fuerza", "Avengers: Infinity War", "Jurassic World", "El rey león", "The Avengers", "Furious 7", "Frozen II", "Avengers: Age of Ultron", "Black Panther", "Harry Potter y las Reliquias de la Muerte: parte 2", "Star Wars: Episodio VIII - Los últimos Jedi", "Jurassic World: El reino caído", "Frozen ", "La bella y la bestia", "Los Increíbles 2", "The Fate of the Furious"
	};
	

	private static VentanaGrafica vent; // Ventana gráfica
	private static Rectangle botonUp = new Rectangle( 10, 10, 30, 30 ); // Rectángulo sensible de flecha arriba
	private static Rectangle botonDown = new Rectangle( 10, 280, 30, 30 ); // Rectángulo sensible de flecha arriba
	private static int numOpcVisibles = 10;  // Elementos que se ven en pantalla
	private static int numPrimeraOpc = 0;    // Índice del primer elemento que se ve
	private static Font font = new Font( "Arial", Font.PLAIN, 14 );
	
	public static void main(String[] args) {
		vent = new VentanaGrafica( 1000, 800, "Ejemplo scroll" );
		cicloInteraccion();
	}
	
	private static void cicloInteraccion() {
		while (!vent.estaCerrada()) {
			dibujaTodo();
			Point puls = vent.getRatonPulsado();
			if (puls!=null) {
				while (vent.getRatonPulsado()!=null) {  // Esperamos a que el botón se suelte
					vent.espera(20);
				}
				// Comprobamos si la pulsación está en un botón de scroll
				if (botonUp.contains(puls)) {  // Sí, es el botón de arriba
					numPrimeraOpc--;
					if (numPrimeraOpc<0) numPrimeraOpc = 0;  // Que no se salga nunca
					dibujaTodo();
				} else if (botonDown.contains(puls)) {  // Sí, es el botón de abajo
					numPrimeraOpc++;
					if (numPrimeraOpc>=ejemploDatos.length) numPrimeraOpc = ejemploDatos.length-1;  // Que no se salga nunca
					dibujaTodo();
				}
			}
			vent.espera(20);
		}
	}
	
	private static void dibujaTodo() {
		vent.borra();
		// Dibuja flechas
		vent.dibujaFlecha( botonUp.x+botonUp.width/2, botonUp.y+botonUp.height, botonUp.x+botonUp.width/2, botonUp.y, 2.0f, Color.BLUE, 8 );
		vent.dibujaFlecha( botonDown.x+botonDown.width/2, botonDown.y, botonDown.x+botonDown.width/2, botonDown.y+botonDown.height, 2.0f, Color.BLUE, 8 );
		// Dibuja opciones 
		int yTexto = 30;
		for (int i=numPrimeraOpc; i<numPrimeraOpc+numOpcVisibles; i++) {
			if (i<ejemploDatos.length) {
				vent.dibujaTexto( 50, yTexto, ejemploDatos[i], font, Color.BLACK );
				yTexto += 30;
			}
		}
	}
}
