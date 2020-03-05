package tema1.ver5;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import tema1.VentanaGrafica;

/** Juego de alinear pelotas en un tablero imaginario de bolas de tres colores
 * Versión 4 - incluye ya jugabilidad mínima (se pueden mover pelotas, se quitan 3 pelotas o más seguidas)
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
	private static int numSeguidasAQuitar = 3;  // Nº mínimo de pelotas seguidas del mismo color que se quitan
	private static VentanaGrafica vent;
	private static GrupoPelotas grupo;
	
	public static void main(String[] args) {
		init();
		juego();
	}

	// Bucle principal de juego
	private static void juego() {
		boolean juegoActivo = true;
		while (!vent.estaCerrada() && juegoActivo) {  // Mientras siga jugando...
			Point puls = vent.getRatonPulsado(); // Controlamos la interacción:
			if (puls!=null) { // No hay interacción - nada que hacer
				Pelota pDentro = grupo.buscaPuntoEnPelotas( puls );
				if (pDentro!=null) { // El punto clicado está dentro de la pelota: a dibujar 
					Point origen = new Point( (int)pDentro.getX(), (int)pDentro.getY() );
					Point ultDrag = puls;
					Point drag = vent.getRatonPulsado();
					while (drag!=null) {  // Mientras no se acaba el drag se va moviendo y dibujando
						pDentro.setX( drag.x );
						pDentro.setY( drag.y );
						// pDentro.dibuja( vent );  // Dibujamos todo de nuevo para evitar borrados extraños
						dibujaTablero();
						vent.espera( 20 );  // 50 veces por segundo
						ultDrag = drag;
						drag = vent.getRatonPulsado();
					}
					// Final del movimiento:
					// Cálculo de fila-columna más cercana
					int fila = getFilaCercana( ultDrag.x );
					int col = getColCercana( ultDrag.y );
					// ¿Está vacía la casilla?
					Pelota pel = estaPelotaEnCasilla(fila, col);
					if (pel==null) { // Está vacía: mover al centro Y COMPROBAR BORRADOS PELOTAS
						recolocar( pDentro, xDeCasilla(fila), yDeCasilla(col) );
						quitarPelotasSiSeguidas();
					} else {  // No está vacía: retornar al origen - nada cambia en el tablero
						recolocar( pDentro, origen.x, origen.y );
					}
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
				int fila = r.nextInt(5);
				int col = r.nextInt(5);
				p.setX( xDeCasilla(fila) ); // O r.nextInt(5) * ANCHO_CASILLA + (ANCHO_CASILLA/2) );
				p.setY( yDeCasilla(col) ); // O r.nextInt(5) * ALTO_CASILLA + (ALTO_CASILLA/2) );
				// boolean estaYa = grupo.buscaPelota(p)==-1;
			} while (grupo.buscaPelotaEquals(p)!=-1); // Importante que este método compare con equals
			grupo.anyadePelota( p );
		}
		dibujaTablero();
	}

	
	// ===============
	// Métodos de utilidad
	// ===============
	
	// Dibuja el tablero completo (borrando la ventana antes)
	private static void dibujaTablero() {
		vent.borra();
		grupo.dibuja( vent );
	}
	// Devuelve la fila más cercana a la x indicada
	private static int getFilaCercana( double x ) {
		return (int)Math.round(x) / ANCHO_CASILLA;
	}
	// Devuelve la columna más cercana a la y indicada
	private static int getColCercana( double y ) {
		return (int)Math.round(y) / ALTO_CASILLA;
	}
	// Devuelve la coordenada x del centro de la casilla de fila indicada
	private static int xDeCasilla( int fila ) {
		return fila * ANCHO_CASILLA + (ANCHO_CASILLA/2);
	}
	// Devuelve la coordenada y del centro de la casilla de columna indicada
	private static int yDeCasilla( int col ) {
		return col * ALTO_CASILLA + (ALTO_CASILLA/2);
	}
	// Devuelve la pelota que esté en la casilla (fila,columna) indicada, null si no hay ninguna
	private static Pelota estaPelotaEnCasilla( int fila, int col ) {
		for (int i=0; i<grupo.getNumPelotas(); i++) {
			Pelota p = grupo.getPelota(i);
			if (Math.abs(p.getX()-xDeCasilla(fila))<0.001 && Math.abs(p.getY()-yDeCasilla(col))<0.001) {  // Calculamos cercanía con margen de precisión de doubles
				return p;
			}
		}
		return null;
	}
	// Recoloca en una animación de 0,5 segundos la pelota indicada en la posición x,y indicada
	private static void recolocar( Pelota p, double xDestino, double yDestino ) {
		// medio segundo (500 milisegundos) movimiento a 50 FPS = 25 movimimentos
		int numMovimientos = 500 / 20;
		double movX = (xDestino - p.getX()) / 25;
		double movY = (yDestino - p.getY()) / 25;
		for (int i=0; i<25; i++) {
			p.moverYDibujar( vent, movX, movY );
			dibujaTablero();
			p.dibuja( vent );  // Dibuja la pelota que se está moviendo por "encima" de todo
			vent.espera( 20 );
		}
		p.setX( xDestino );  // Coloca la pelota en su posición final exacta para evitar error de decimales
		p.setY( yDestino );
	}
	
	// Lógica de borrado de las pelotas
	// Quita las pelotas que cumplen líneas de 3 o más del mismo color
	private static void quitarPelotasSiSeguidas() {
		Pelota tableroLogica[][] = new Pelota[altoTablero][anchoTablero];  // Estructura de las pelotas
		boolean tableroAQuitar[][] = new boolean[altoTablero][anchoTablero];  // Marcas de las que hay que borrar
		for (int i=0; i<grupo.getNumPelotas(); i++) {
			Pelota pelota = grupo.getPelota( i );
			int fila = getFilaCercana( pelota.getX() );
			int col = getColCercana( pelota.getY() );
			tableroLogica[fila][col] = pelota;
		}
		// Busca filas a quitar
		for (int fila=0; fila<altoTablero; fila++) {
			int numSeguidas = 0;
			Color colorAnt = null;
			for (int col=0; col<anchoTablero; col++) {
				Color color = null;
				if (tableroLogica[fila][col]!=null) color = tableroLogica[fila][col].getColor();
				if (color==null) {
					quitaEnFilaSiProcede( fila, col-1, numSeguidas, tableroAQuitar );
					numSeguidas = 1;
				} else if (color==colorAnt) { // Seguidas!
					numSeguidas++;
				} else { // No seguidas
					quitaEnFilaSiProcede( fila, col-1, numSeguidas, tableroAQuitar );
					numSeguidas = 1;
				}
				colorAnt = color;
			}
			quitaEnFilaSiProcede( fila, anchoTablero-1, numSeguidas, tableroAQuitar );
		}
		// Busca columnas a quitar
		for (int col=0; col<anchoTablero; col++) {
			int numSeguidas = 0;
			Color colorAnt = null;
			for (int fila=0; fila<altoTablero; fila++) {
				Color color = null;
				if (tableroLogica[fila][col]!=null) color = tableroLogica[fila][col].getColor();
				if (color==null) {
					quitaEnColumnaSiProcede( fila-1, col, numSeguidas, tableroAQuitar );
					numSeguidas = 1;
				} else if (color==colorAnt) { // Seguidas!
					numSeguidas++;
				} else { // No seguidas
					quitaEnColumnaSiProcede( fila-1, col, numSeguidas, tableroAQuitar );
					numSeguidas = 1;
				}
				colorAnt = color;
			}
			quitaEnColumnaSiProcede( altoTablero-1, col, numSeguidas, tableroAQuitar );
		}
		// Quita las marcadas en el array de booleanos
		Pelota ultimaQuitada = null;
		int numQuitadas = 0;
		for (int fila=0; fila<altoTablero; fila++) {
			for (int col=0; col<anchoTablero; col++) {
				if (tableroAQuitar[fila][col]) {
					ultimaQuitada = tableroLogica[fila][col];
					grupo.borraPelota( ultimaQuitada );
					numQuitadas++;
				}
			}
		}
		if (numQuitadas>0) {  // Si se han quitado pelotas, se redibuja el tablero
			dibujaTablero(); 
		}
	}

	// Comprueba si hay que quitar en fila, dada la última fila-columna y el número de fichas seguidas que hay
	private static void quitaEnFilaSiProcede( int fila, int col, int numSeguidas, boolean tableroAQuitar[][] ) {
		if (numSeguidas>=numSeguidasAQuitar) { // 3 o más seguidas!  A quitar
			for (int colIni=col-numSeguidas+1; colIni<=col; colIni++)
				tableroAQuitar[fila][colIni] = true;
		}
	}
	// Comprueba si hay que quitar en columna, dada la última fila-columna y el número de fichas seguidas que hay
	private static void quitaEnColumnaSiProcede( int fila, int col, int numSeguidas, boolean tableroAQuitar[][] ) {
		if (numSeguidas>=numSeguidasAQuitar) { // 3 o más seguidas!  A quitar
			for (int filaIni=fila-numSeguidas+1; filaIni<=fila; filaIni++)
				tableroAQuitar[filaIni][col] = true;
		}
	}


}











