package temas78.ejemplos;

import java.awt.*;
import javax.swing.*;

/** Ventana de prueba con algunos elementos básicos incluyendo una imagen
 */
@SuppressWarnings("serial")
public class VentanaConImagen extends JFrame {
	
	/** Prueba de la clase
	 * @param args
	 */
	public static void main(String[] args) {
		VentanaConImagen v = new VentanaConImagen();
		v.setVisible( true );  
		// Swing lanza un gestor de eventos
		// Swing lanza un NUEVO HILO de ejecución
	}

	// NO STATIC
	
	private JTextArea taTexto;
	private JTextField tfNick;
	private JTextField tfPass;
	public VentanaConImagen() {
		// Formato de la ventana
		this.setTitle( "Mi ventana" );
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( 640, 480 );
		setLocation( 100, 100 );
		// Swing -> componentes y contenedores
		// Crear componentes
		taTexto = new JTextArea( "Hola", 5, 40 );
		JLabel lNick = new JLabel( "Nick:" );
		tfNick = new JTextField( "", 10 );
		JLabel lPass = new JLabel( "Pass:" );
		tfPass = new JTextField( "", 10 );
		JLabel lFoto = new JLabel( new ImageIcon( VentanaConImagen.class.getResource( "/tema3/img/bicho.png") ) ); // Opción 1: recurso (ventaja: se puede empaquetar en un jar)
		// JLabel lFoto = new JLabel( new ImageIcon("src/tema3/img/balon.png") );  // Opción 2: fichero - solo funciona si existe el fichero en el disco en la ruta correspondiente
		lFoto.setBorder( BorderFactory.createLineBorder(Color.red,2));
		JButton bBorrar = new JButton( "Borrar texto" );
		JButton bAceptar = new JButton( "Aceptar" );
		// Crear contenedores
		JPanel pIzquierdo = new JPanel();
		JPanel pInferior = new JPanel();
		JPanel pIzq1 = new JPanel();  // FlowLayout
		JPanel pIzq2 = new JPanel();
		// Asignar formatos (layouts)
		pIzquierdo.setLayout( new GridLayout( 2, 1 ) );
		// Asignar componentes a contenedores
		getContentPane().add( taTexto, BorderLayout.NORTH );
		getContentPane().add( lFoto, BorderLayout.CENTER );
		lFoto.setLocation( -200, 0 );
		pIzq1.add( lNick );  
		pIzq1.add( tfNick );
		pIzq2.add( lPass );
		pIzq2.add( tfPass );
		pIzquierdo.add( pIzq1 );
		pIzquierdo.add( pIzq2 );
		getContentPane().add( pIzquierdo, BorderLayout.WEST );
		pInferior.add( bAceptar );
		pInferior.add( bBorrar );
		getContentPane().add( pInferior, BorderLayout.SOUTH );
		// Configuración de componentes
		// Pruebas
		// lFoto.setLocation( 100, 100 );
		// lFoto.setSize( 100, 75 );
		// lFoto.setBounds( 100, 100, 200, 150 );
		// pInferior.setBounds( 10, 260, 100, 155 );
		// pInferior.setBorder( BorderFactory.createLineBorder( Color.blue, 1 ));
	}

}
