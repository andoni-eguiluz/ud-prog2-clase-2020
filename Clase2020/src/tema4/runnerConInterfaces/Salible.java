package tema4.runnerConInterfaces;

import java.util.ArrayList;

import tema3.VentanaGrafica;

public interface Salible {
	/** Comprueba si el objeto se sale completamente por el lado izquierdo de la ventana
	 * @param v	Ventana de comprobación
	 * @return	true si se ha salido completamente por la izquierda, false en caso contrario
	 */
	public boolean seSalePorLaIzquierda( VentanaGrafica v );
	/** Gestiona la lógica de salida del elemento cuando salga por la izquierda
	 * @param lElementos	Lista de elementos (de la cual quitarlo, si procede)
	 */
	void sal( ArrayList<ObjetoEspacial> lElementos );
}
