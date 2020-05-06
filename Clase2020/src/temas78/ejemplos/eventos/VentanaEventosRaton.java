package temas78.ejemplos.eventos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Ventana de prueba con algunos elementos básicos con eventos de ratón
 * Visualización en ventana de los eventos
 */
@SuppressWarnings("serial")
public class VentanaEventosRaton extends JFrame {
	
	/** Prueba de la clase
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		VentanaEventosRaton v = new VentanaEventosRaton();
		v.setVisible( true );  
	}

	// NO STATIC
	
	private JTextArea taTextoIzq;
	private JTextArea taTextoDer;
	public VentanaEventosRaton() {
		// Formato de la ventana
		this.setTitle( "Mi ventana" );
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( 640, 480 );
		setLocation( 100, 100 );
		
		// Crear componentes
		taTextoIzq = new JTextArea( "Eventos de MouseListener:\n", 10, 40 );
		taTextoDer = new JTextArea( "Eventos de MouseMotionListener:\n", 10, 40 );
		JLabel lFoto = new JLabel( new ImageIcon( VentanaEventosRaton.class.getResource( "/tema3/img/balon.png") ) ); // Opción 1: recurso (ventaja: se puede empaquetar en un jar)
		// JLabel lFoto = new JLabel( new ImageIcon("src/tema3/img/balon.png") );  // Opción 2: fichero - solo funciona si existe el fichero en el disco en la ruta correspondiente
		JButton bBorrar = new JButton( "Borrar texto" );
		JButton bAceptar = new JButton( "Acabar" );
		
		// Crear contenedores
		JPanel pInferior = new JPanel();
		JPanel pCentral = new JPanel();
		JPanel pSuperior = new JPanel();
		
		// Asignar formatos (layouts)
		pSuperior.setLayout( new GridLayout( 1, 2 ) );
		pCentral.setLayout( null );  // Nulo para mover la imagen libremente
		pCentral.setBackground( Color.CYAN );
		
		// Asignar componentes a contenedores
		pSuperior.add( new JScrollPane(taTextoIzq) );
		pSuperior.add( new JScrollPane(taTextoDer) );
		getContentPane().add( pSuperior, BorderLayout.NORTH );
		pCentral.add( lFoto );
		getContentPane().add( pCentral, BorderLayout.CENTER );
		lFoto.setLocation( 0, 0 );  // La foto en la esquina del panel central
		lFoto.setSize( 50, 50 );  // Tamaño de la foto: OJO - si no se indica la foto no se ve (por defecto el tamaño es 0 y el layout de este panel es nulo)
		pInferior.add( bAceptar );
		pInferior.add( bBorrar );
		getContentPane().add( pInferior, BorderLayout.SOUTH );
		
		// Asociar gestores de eventos a componentes
		bBorrar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				taTextoIzq.setText( "" );
				taTextoDer.setText( "" );
			}
		} );
		pCentral.addMouseMotionListener( new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				taTextoDer.append( "mouseDragged: " + e.getX() + "," + e.getY() + "\n" );
				alFinal( taTextoDer );
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				taTextoDer.append( "mouseMoved: " + e.getX() + "," + e.getY() + "\n" );
				alFinal( taTextoDer );
			}
		} );
		pCentral.addMouseListener( new MouseListener() {
			@Override
			public void mouseEntered(MouseEvent e) {
				taTextoIzq.append( "mouseEntered: " + e.getX() + "," + e.getY() + "\n" );
				alFinal( taTextoIzq );
			}
			@Override
			public void mouseExited(MouseEvent e) {
				taTextoIzq.append( "mouseExited: " + e.getX() + "," + e.getY() + "\n" );
				alFinal( taTextoIzq );
			}
			@Override
			public void mousePressed(MouseEvent e) {
				taTextoIzq.append( "1 mousePressed: " + e.getX() + "," + e.getY() + "\n" );
				alFinal( taTextoIzq );
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				taTextoIzq.append( "2 mouseReleased: " + e.getX() + "," + e.getY() + "\n" );
				alFinal( taTextoIzq );
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				taTextoIzq.append( "  3 mouseClicked: " + e.getX() + "," + e.getY() + "\n" );
				alFinal( taTextoIzq );
			}
		});
	}
	
	private void alFinal( JTextArea ta ) {
		ta.setSelectionStart( ta.getText().length() );
	}
	
}
