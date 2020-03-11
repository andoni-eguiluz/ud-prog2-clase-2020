package tema3.juegoBolas;

import java.awt.Color;

import tema1.Pelota;
import tema1.VentanaGrafica;

public abstract class ObjetoJuego {
	protected double x;
	protected double y;
	
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
	
	/** Dibuja un objeto de juego
	 * @param v	Ventana en la que dibujar
	 */
	public abstract void dibuja( VentanaGrafica v );

	
	/** Método de prueba de conceptos de OO
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		// ObjetoJuego oj = new ObjetoJuego();  // Esto ya no se puede hacer cuando la clase es abstracta
		ObjetoJuego oj = new tema3.juegoBolas.Pelota(10, 10, 10, Color.red);  // Polimorfismo de datos
		System.out.println( oj );  // ¿A qué toString se llama?
	}

}
