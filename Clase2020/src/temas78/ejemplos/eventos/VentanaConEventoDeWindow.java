package temas78.ejemplos.eventos;

import java.awt.*;   // Abstract Window Toolkit
import javax.swing.*;  // Swing

import temas78.VentanaConEventos2;

import java.awt.event.*;  // Eventos

/** Clase de ejemplo de llamada entre ventanas con eventos de acción
 * Ventana principal que llama a otra ventana de login
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
@SuppressWarnings("serial")  // Evita el warning de serialización (no vamos a guardar esta ventana, no tiene mucho sentido serializar ventanas aunque se podría)
public class VentanaConEventoDeWindow extends JFrame {

	/** Método principal
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		VentanaConEventoDeWindow vent = new VentanaConEventoDeWindow( "Ventana de menú" );
		vent.setVisible( true );
	}

	
	// PARTE NO STATIC - Ventana
	
	private JLabel lMensaje;  // Label de mensaje
	private JTextArea taDatos; // Área de texto de datos
	private VentanaConEventos2 v2;
	
	public VentanaConEventoDeWindow( String titulo ) {
		super( titulo );  // Llama al constructor original de JFrame (clase padre)
		
		// 1.- Configuración de la ventana
		this.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );   // hace un ventana.dispose() cuando el usuario la cierra
		// this.setLocation( 2100, 100 );  // Coordenada de origen de la ventana con respecto al escritorio  (absoluta)

		// 2.- Creación de contenedores (paneles) y componentes
		JPanel panelInferior = new JPanel();  // Por defecto en un panel FlowLayout
		taDatos = new JTextArea( 10, 30 );  // 30 "columnas" (caracteres aproximados de ancho), 10 filas
		lMensaje = new JLabel( "Pulsa botones inferiores para actuar", JLabel.CENTER );
		JButton bLogin = new JButton( "Login" );
		JButton bAcceso = new JButton( "Acceso" );
		JButton bSalir = new JButton( "Salir" );
		
		// 3.- Asignación de componentes a contenedores
		JScrollPane spCentral = new JScrollPane( taDatos );
		this.add( spCentral, BorderLayout.CENTER );
		this.add( lMensaje, BorderLayout.NORTH );
		panelInferior.add( bLogin );
		panelInferior.add( bAcceso );
		panelInferior.add( bSalir );
		this.add( panelInferior, BorderLayout.SOUTH );

		// 4.- Decoraciones
		this.pack(); // Redefinir el tamaño para que ajuste al mínimo necesario para todos los componentes
		// this.setLocationRelativeTo(null);  // Si se quiere que se centre en la ventana
		
		// 5.- Eventos
		bLogin.addActionListener( new ActionListener() {
			// Atributos si necesitarámos
			@Override
			public void actionPerformed(ActionEvent e) {
				v2 = new VentanaConEventos2( "Login" );  // Si queréis completar, pasar la propia ventana a la de login
				v2.setLocation( getLocation().x, getLocation().y );
				v2.setVentanaAAbrirTrasCerrarEsta( VentanaConEventoDeWindow.this );  // Me paso a mí misma para que me vuelva a abrir la vent. de login cuando se acabe
				v2.setVisible( true );
				setVisible( false );
				// Nada que ver con el JOptionPane -> Ese ESPERA a que la entrada acabe
				System.out.println( "Cuándo" );
			}
		});
		bAcceso.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 
			}
		} );
		bSalir.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		this.addWindowListener( new WindowListener() {
			@Override
			public void windowIconified(WindowEvent e) {
				System.out.println( "Iconified" );
			}
			@Override
			public void windowDeiconified(WindowEvent e) {
				System.out.println( "Deiconified" );
			}
			@Override
			public void windowActivated(WindowEvent e) {
				System.out.println( "Activated" );
				if (v2!=null) {
					taDatos.setText( "Vuelto del login: " + v2.getInfoCierre() );
					v2 = null;
				}
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
				System.out.println( "Deactivated" );
			}
			@Override
			public void windowOpened(WindowEvent e) {
				System.out.println( "Opened" );
			}
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println( "Closing" );
				dispose();
			}
			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println( "Closed" );
			}
		});
	}

}