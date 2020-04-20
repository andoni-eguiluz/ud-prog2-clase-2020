package temas78;

import java.awt.*;   // Abstract Window Toolkit
import javax.swing.*;  // Swing
// import java.awt.event.*;  // Eventos

/** Clase de ejemplo de creación de ventana sencilla en Swing
 * Es más conveniente hacer que las ventanas sean objetos con sus propios atributos
 * y así será fácil a acceder a información útil de la ventana (sus cuadros de texto, sus botones...)
 * Ejemplo de MiPrimeraVentana reimplementado con clase que hereda de JFrame
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
@SuppressWarnings("serial")  // Evita el warning de serialización (no vamos a guardar esta ventana, no tiene mucho sentido serializar ventanas aunque se podría)
public class MiSegundaVentana extends JFrame {

	/** Método principal
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		MiSegundaVentana vent = new MiSegundaVentana( "Título" );
		vent.setVisible( true );
		System.out.println( "Final" );
	}
	
	// PARTE NO STATIC - Ventana
	
	// Atributos: información que puede ser útil recuperar después de creado el objeto
	private JTextField tfEntrada;  // Cuadro de texto  (tf de textfield, un prefijo ayuda cuando hay muchas variables distintas)
	private JLabel lMensaje;  // Label de mensaje superior  (l de label)
	
	public MiSegundaVentana( String titulo ) {
		super( titulo );  // Llama al constructor original de JFrame (clase padre)
		// 1.- Configuración de la ventana
		this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );   // hace un ventana.dispose() cuando el usuario la cierra
		this.setSize( 600, 400 );  // Tamaño de la ventana (la mayor parte de los atributos hay que cambiarlos con sets)
		this.setLocation( 100, 100 );  // Coordenada de origen de la ventana con respecto al escritorio  (absoluta)

		// 2.- Creación de contenedores (paneles) y componentes
		JPanel panelInferior = new JPanel();  // Por defecto en un panel FlowLayout
		tfEntrada  = new JTextField( 20 );
		lMensaje = new JLabel( "Introduce tu nombre:" );
		JButton bAceptar = new JButton( "Aceptar" );
		JButton bCancelar = new JButton( "Cancelar" );
		
		// 3.- Asignación de componentes a contenedores
		this.add( tfEntrada, BorderLayout.CENTER );
		this.add( lMensaje, BorderLayout.NORTH );
		panelInferior.setLayout( new GridLayout( 1, 2 ) );
		panelInferior.add( bAceptar );
		panelInferior.add( bCancelar );
		this.add( panelInferior, BorderLayout.SOUTH );
	}
	
}

