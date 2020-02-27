package tema1.ver2;

import java.awt.Color;
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
	
	public void dibuja( VentanaGrafica v ) {
		// Log de dibujo de pelota en consola
		System.out.println( "Dibuja " + this );
		// Dibujo en ventana
		v.dibujaCirculo( x, y, radio, 2.0f, color);
	}
	
	/** Mueve la pelota 50 píxels a la derecha y dibuja el movimiento
	 */
	public void mover50ADerecha( VentanaGrafica v ) {
		this.borra( v );
		for (int inc=0; inc<50; inc++) {
			x += 1; // x++; x = x + 1;
			this.dibuja( v );
			v.espera( 40 );   // 2000 milisegundos / 50 movimientos
			this.borra( v );
		}
		this.dibuja( v );
	}
	
	public void borra( VentanaGrafica v ) {
		v.dibujaCirculo( x, y, radio, 2.0f, Color.WHITE );
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ") R" + radio + " C<" + color.getRed()+","+color.getGreen()+","+color.getBlue()+">";
	}
	
}
