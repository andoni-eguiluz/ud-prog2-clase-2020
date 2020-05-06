package temas78.ejemplos.eventos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Ventana de prueba con algunos elementos básicos con eventos de componente
 */
@SuppressWarnings("serial")
public class VentanaEventosComponent extends JFrame {
	
	/** Prueba de la clase
	 * @param args
	 */
	public static void main(String[] args) {
		VentanaEventosComponent v = new VentanaEventosComponent();
		v.setVisible( true );  
		// Swing lanza un gestor de eventos
		// Swing lanza un NUEVO HILO de ejecución
	}

	// NO STATIC
	
	private JTextArea taTexto;
	public VentanaEventosComponent() {
		// Formato de la ventana
		this.setTitle( "Mi ventana" );
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( 640, 480 );
		setLocation( 100, 100 );
		
		// Crear componentes
		taTexto = new JTextArea( "", 5, 40 );
		JLabel lFoto = new JLabel( new ImageIcon( VentanaEventosComponent.class.getResource( "/tema3/img/balon.png") ) ); // Opción 1: recurso (ventaja: se puede empaquetar en un jar)
		// JLabel lFoto = new JLabel( new ImageIcon("src/tema3/img/balon.png") );  // Opción 2: fichero - solo funciona si existe el fichero en el disco en la ruta correspondiente
		JButton bBorrar = new JButton( "Borrar texto" );
		JButton bAceptar = new JButton( "Acabar" );
		
		// Crear contenedores
		JPanel pInferior = new JPanel();
		JPanel pCentral = new JPanel();
		
		// Asignar formatos (layouts)
		pCentral.setLayout( null );  // Nulo para mover la imagen libremente
		
		// Asignar componentes a contenedores
		getContentPane().add( new JScrollPane(taTexto), BorderLayout.NORTH );
		pCentral.add( lFoto );
		getContentPane().add( pCentral, BorderLayout.CENTER );
		lFoto.setLocation( 0, 0 );  // La foto en la esquina del panel central
		lFoto.setSize( 50, 50 );  // Tamaño de la foto: OJO - si no se indica la foto no se ve (por defecto el tamaño es 0 y el layout de este panel es nulo)
		pInferior.add( bAceptar );
		pInferior.add( bBorrar );
		getContentPane().add( pInferior, BorderLayout.SOUTH );
		
		// Asociar gestores de eventos a componentes
		pCentral.addComponentListener( new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				System.out.println( lFoto.getWidth() + "," + lFoto.getHeight() );
			}
		});
	}
	
	private void centrarBalon() {
		
	}
	
}
