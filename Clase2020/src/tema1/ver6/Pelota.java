package tema1.ver6;

import java.awt.Color;
import java.awt.Point;

import tema1.VentanaGrafica;

/** Clase para creación de pelotas visibles en pantalla, con radio y color configurables
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class Pelota {
	private int radio;
	private double x;
	private double y;
		// private java.awt.Point centro;  // Se podría hacer un punto en lugar de x,y
	private Color color;
	
	private static final float GROSOR_PELOTA = 2.0f;
	
	/** Crea una nueva pelota
	 * @param x	Su coordenada x en píxels
	 * @param y	Su coordenada y en píxels
	 * @param radio	Su radio en píxels (positivo)
	 * @param color	Su color
	 */
	public Pelota( double x, double y, int radio, Color color ) {
		this.color = color;
		this.x = x;  // alternativa this.setX( x );
		this.y = y;
		this.radio = radio;
	}
	
	
	/** Devuelve radio de la pelota
	 * @return	Radio en píxels
	 */
	public int getRadio() {
		return radio;
	}
	/** Modifica el radio de la pelota
	 * @param radio	Nuevo radio en píxels
	 */
	public void setRadio(int radio) {
		this.radio = radio;
	}
	/** Devuelve la coordenada x del centro de la pelota, con respecto a la pantalla
	 * @return	Coordenada x en píxels
	 */
	public double getX() {
		return x;
	}
	/** Modifica la coordenada x del centro de la pelota, con respecto a la pantalla
	 * @param x	Nueva coordenada x
	 */
	public void setX(double x) {
		this.x = x;
	}
	/** Devuelve la coordenada y del centro de la pelota, con respecto a la pantalla
	 * @return	Coordenada y en píxels
	 */
	public double getY() {
		return y;
	}
	/** Modifica la coordenada y del centro de la pelota, con respecto a la pantalla
	 * @param y	Nueva coordenada y
	 */
	public void setY(double y) {
		this.y = y;
	}
	/** Devuelve el color de línea de la pelota
	 * @return	Color de la línea de la pelota
	 */
	public Color getColor() {
		return color;
	}
	/** Modifica el color de línea de la pelota
	 * @param color	Color nuevo
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/** Dibuja la pelota
	 * @param v	Ventana en la que dibujar
	 */
	public void dibuja( VentanaGrafica v ) {
		// Log de dibujo de pelota en consola
		// System.out.println( "Dibuja " + this );
		// Dibujo en ventana
		v.dibujaCirculo( x, y, radio, GROSOR_PELOTA, color);
	}
	
	/** Mueve la pelota una serie de píxels a la derecha y dibuja el movimiento
	 * @param v	Ventana en la que dibujar
	 * @param numPixels	Número de píxels a mover (debe ser positivo)
	 * @param duracionMovMs	Tiempo que tarda el movimiento en milisegundos (este es el tiempo que este método tardará en devolver el control)
	 */
	public void moverADerecha( VentanaGrafica v, int numPixels, int duracionMovMs ) {
		this.borra( v );
		long espera = duracionMovMs / duracionMovMs;
		for (int inc=0; inc<numPixels; inc++) {
			x += 1; // x++; x = x + 1;
			this.dibuja( v );
			v.espera( espera );   // M milisegundos / N movimientos = tiempo de espera tras mover cada píxel
			this.borra( v );
		}
		this.dibuja( v );
	}
	
	/** Mueve la pelota y dibuja el movimiento (borrando la posición anterior y dibujando la nueva)
	 * @param v	Ventana en la que dibujar
	 * @param incX	Incremento a aplicar en la coordenada x
	 * @param incY	Incremento a aplicar en la coordenada y
	 */
	public void moverYDibujar( VentanaGrafica v, double incX, double incY ) {
		borra( v );
		x += incX;  // x = x + incX;  (derecha positivo, izquierda negativo)
		y += incY;  // y = y + incY;  (abajo positivo, arriba negativo)
		dibuja( v );
	}
	
	
	/** Borra la pelota (dibujándola en blanco)
	 * @param v	Ventana en la que borrar
	 */
	public void borra( VentanaGrafica v ) {
		v.dibujaCirculo( x, y, radio, GROSOR_PELOTA, Color.WHITE );
	}
	
	/** Comprueba si un punto está dentro o no de la pelota
	 * @param p	punto a comprobar
	 * @return	true si está dentro, false si no
	 */
	public boolean contieneA( Point p ) {
//		double distancia = Math.sqrt( (p.x-x) * (p.x-x) + (p.y-y) * (p.y-y) );
//		return distancia <= radio;
		return contieneA( p.x, p.y );
	}
	
	/** Comprueba si un punto está dentro o no de la pelota
	 * @param x	Coordenada x del punto
	 * @param y	Coordenada y del punto
	 * @return	true si está dentro, false si no
	 */
	public boolean contieneA( int x, int y ) {
		double distancia = Math.sqrt( (x-this.x) * (x-this.x) + (y-this.y) * (y-this.y) );
		return distancia <= radio;
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ") R" + radio + " C<" + color.getRed()+","+color.getGreen()+","+color.getBlue()+">";
	}
	
	@Override
	public boolean equals(Object obj) {
		Pelota p2 = (Pelota) obj;
		return x==p2.x && y==p2.y;  
		// Si tuviera posibilidad de problemas de precisión
		// return Math.abs(x-p2.x)<0.00001 && ...
	}
	
}
