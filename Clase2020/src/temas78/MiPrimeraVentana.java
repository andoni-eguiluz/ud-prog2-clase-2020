package temas78;

import java.awt.*;   // Abstract Window Toolkit
import javax.swing.*;  // Swing
// import java.awt.event.*;  // Eventos

/** Clase de ejemplo de creación de ventana sencilla en Swing
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class MiPrimeraVentana {

	/** Método principal
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		// 1.- CONTENEDORES como la ventana
		JFrame ventana = new JFrame( "Título inicial" );
		ventana.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );   // hace un ventana.dispose() cuando el usuario la cierra
		ventana.setTitle( "Título definitivo" );  // Título de la ventana (se puede meter en el constructor y también se puede cambiar)
		ventana.setSize( 600, 400 );  // Tamaño de la ventana (la mayor parte de los atributos hay que cambiarlos con sets)
		ventana.setLocation( 100, 100 );  // Coordenada de origen de la ventana con respecto al escritorio  (absoluta)
		// 2.- COMPONENTES como un cuadro de texto, una etiqueta o un botón
		// contenedor.add( componente, opcional-posicionamiento en el contenedor )
		JTextField cuadro  = new JTextField( 20 );
		ventana.add( cuadro, BorderLayout.CENTER );
		JLabel etiqueta = new JLabel( "Introduce tu nombre:" );
		ventana.add( etiqueta, BorderLayout.NORTH );
		// Botonera con un panel contenedor
		JPanel panelInferior = new JPanel();  // Por defecto en un panel FlowLayout
		panelInferior.setLayout( new GridLayout( 1, 2 ) );
		JButton boton = new JButton( "Aceptar" );
		panelInferior.add( boton );
		JButton boton2 = new JButton( "Cancelar" );
		panelInferior.add( boton2 );
		ventana.add( panelInferior, BorderLayout.SOUTH );
		
		
		ventana.setVisible( true );  // Solo al final de la construcción
		
		System.out.println( "Final" );
	}

}

