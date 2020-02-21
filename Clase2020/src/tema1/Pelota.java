package tema1;

import java.awt.Color;

/** Clase para creación de pelotas visibles en pantalla, con radio y color configurables
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class Pelota {
	private int radio;
	private double x;
	private double y;
		// private java.awt.Point centro;  // Se podría hacer un punto en lugar de x,y
	private Color color;
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
	
	public void dibuja( VentanaGrafica v ) {
		// Log de dibujo de pelota en consola
		System.out.println( "Dibuja en " + x + "," + y + " - radio " + radio + " - color " + color );
		// Dibujo en ventana
		v.dibujaCirculo( x, y, radio, 2.0f, color);
	}
	
}
