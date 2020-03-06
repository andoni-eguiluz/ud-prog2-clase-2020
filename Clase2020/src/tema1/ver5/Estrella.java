package tema1.ver5;

import tema1.VentanaGrafica;

public class Estrella {
	private double x;
	private double y;
	private int tamanyo;
	private int puntos;
	
	
	public Estrella(double x, double y, int tamanyo, int puntos) {
		this.x = x;
		this.y = y;
		this.tamanyo = tamanyo;
		this.puntos = puntos;
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public int getTamanyo() {
		return tamanyo;
	}
	public void setTamanyo(int tamanyo) {
		this.tamanyo = tamanyo;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	/** Dibuja la pelota
	 * @param v	Ventana en la que dibujar
	 */
	public void dibuja( VentanaGrafica v ) {
		v.dibujaImagen( "ud-estrella.png", x, y, tamanyo, tamanyo, 1.0, 0, 1.0f );
	}
	
}
