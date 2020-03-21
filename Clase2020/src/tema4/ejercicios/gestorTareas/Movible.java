package tema4.ejercicios.gestorTareas;

/** Comportamiento de objetos que pueden ser movidos en un espacio de 2D
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public interface Movible {
	/** Devuelve la coordenada X
	 * @return	posición horizontal (x)
	 */
	public int getX();
	/** Modifica la coordenada X
	 * @param x	nueva posición horizontal (x)
	 */
	public void setX(int x);
	/** Devuelve la coordenada Y
	 * @return	posición horizontal (y)
	 */
	public int getY();	
	/** Modifica la coordenada Y
	 * @param y	nueva posición vertical (y)
	 */
	public void setY(int y);
	/** Cambia la coordenada x,y
	 * @param x	Nueva posición horizontal (x)
	 * @param y	Nueva posición vertical (y)
	 */
	public void mover( int x, int y );
}
