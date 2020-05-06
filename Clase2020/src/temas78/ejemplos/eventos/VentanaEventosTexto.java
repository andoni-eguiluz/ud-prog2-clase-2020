package temas78.ejemplos.eventos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Ventana de prueba con algunos elementos básicos con eventos de teclado
 */
@SuppressWarnings("serial")
public class VentanaEventosTexto extends JFrame {
	
	/** Prueba de la clase
	 * @param args
	 */
	public static void main(String[] args) {
		VentanaEventosTexto v = new VentanaEventosTexto();
		v.setVisible( true );  
		// Swing lanza un gestor de eventos
		// Swing lanza un NUEVO HILO de ejecución
	}
	
	// NO STATIC

	private JTextArea taTexto;
	private JTextField tfNombre;
	private JTextField tfApellidos;
	public VentanaEventosTexto() {
		// Formato de la ventana
		this.setTitle( "Mi ventana" );
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( 640, 480 );
		setLocation( 100, 100 );

		// Crear componentes
		taTexto = new JTextArea( "Hola", 5, 40 );
		JLabel lNombre = new JLabel( "Nombre:" );
		tfNombre = new JTextField( "", 10 );
		JLabel lApellidos = new JLabel( "Apellidos:" );
		tfApellidos = new JTextField( "", 10 );
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
		getContentPane().add( new JScrollPane(taTexto), BorderLayout.NORTH );
		pIzq1.add( lNombre );  
		pIzq1.add( tfNombre );
		pIzq2.add( lApellidos );
		pIzq2.add( tfApellidos );
		pIzquierdo.add( pIzq1 );
		pIzquierdo.add( pIzq2 );
		getContentPane().add( pIzquierdo, BorderLayout.WEST );
		pInferior.add( bAceptar );
		pInferior.add( bBorrar );
		getContentPane().add( pInferior, BorderLayout.SOUTH );
		
		// Eventos
		tfApellidos.addKeyListener( new TecladoPass() );
		tfNombre.addKeyListener( new TecladoPass() );
	}

	class TecladoPass implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}
		@Override
		public void keyPressed(KeyEvent e) {
		}
		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
	
}
