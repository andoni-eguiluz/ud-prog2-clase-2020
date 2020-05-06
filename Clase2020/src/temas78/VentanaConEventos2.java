package temas78;

import java.awt.*;   // Abstract Window Toolkit
import javax.swing.*;  // Swing
import java.awt.event.*;  // Eventos

/** Clase de ejemplo de creación de ventana sencilla en Swing con eventos sencillos con clases internas anónimas
 * Supuesta ventana de login
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
@SuppressWarnings("serial")  // Evita el warning de serialización (no vamos a guardar esta ventana, no tiene mucho sentido serializar ventanas aunque se podría)
public class VentanaConEventos2 extends JFrame {

	/** Método principal
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		VentanaConEventos2 vent = new VentanaConEventos2( "Ventana de login" );
		vent.setVisible( true );
		System.out.println( "Final de main" );
	}

	
	// PARTE NO STATIC - Ventana
	
	// Atributos: información que puede ser útil recuperar después de creado el objeto
	private JTextField tfUsuario;  // Cuadro de texto de nombre (tf de textfield, un prefijo ayuda cuando hay muchas variables distintas)
	private JTextField tfPassword;  // Cuadro de texto de password
	private JLabel lMensaje;  // Label de mensaje superior  (l de label)
	
	private boolean cierreAceptar;  // true si se cierra con aceptar y false si se cierra con cancelar
	
	private JFrame ventanaAAbrirTrasCerrarEsta;  // Ventana a "devolver" tras que se cierre esta
	
	public VentanaConEventos2( String titulo ) {
		super( titulo );  // Llama al constructor original de JFrame (clase padre)
		
		// 1.- Configuración de la ventana
		this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );   // hace un ventana.dispose() cuando el usuario la cierra
		this.setSize( 600, 150 );  // Tamaño de la ventana (la mayor parte de los atributos hay que cambiarlos con sets)
		// this.setLocation( 2100, 100 );  // Coordenada de origen de la ventana con respecto al escritorio  (absoluta)

		// 2.- Creación de contenedores (paneles) y componentes
		JPanel panelInferior = new JPanel();  // Por defecto en un panel FlowLayout
		JPanel panelCentral = new JPanel();
		tfUsuario  = new JTextField( 20 );
		tfPassword  = new JTextField( 15 );
		lMensaje = new JLabel( "Introduce tus datos para identificarte", JLabel.CENTER );  // JLabel con texto centrado en lugar de alineado a izquierdas (por defecto)
		JButton bAceptar = new JButton( "Login" );
		JButton bBorrar = new JButton( "Reset" );
		JButton bCancelar = new JButton( "Cancel" );
		
		// 3.- Asignación de componentes a contenedores
		panelCentral.add( new JLabel( "Usuario:" ) );
		panelCentral.add( tfUsuario );
		panelCentral.add( new JLabel( "Password:" ) );
		panelCentral.add( tfPassword );
		this.add( panelCentral, BorderLayout.CENTER );
		this.add( lMensaje, BorderLayout.NORTH );
		panelInferior.add( bAceptar );
		panelInferior.add( bBorrar );
		panelInferior.add( bCancelar );
		this.add( panelInferior, BorderLayout.SOUTH );

		// 4.- Decoraciones
		this.pack(); // Redefinir el tamaño para que ajuste al mínimo necesario para todos los componentes
		// this.setLocationRelativeTo(null);  // Si se quiere que se centre en la ventana
		
		// 5.- Eventos
		bCancelar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cierreAceptar = false;
				dispose();
				if (ventanaAAbrirTrasCerrarEsta!=null) {
					ventanaAAbrirTrasCerrarEsta.setVisible( true );
				}
			}
		} );
		bAceptar.addActionListener( new ActionListener() {  // Sintaxis clase anónima   new Interfaz() { ... } // new Clase() { ... }
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cierreAceptar = true;
				dispose();
				if (ventanaAAbrirTrasCerrarEsta!=null) {
					ventanaAAbrirTrasCerrarEsta.setVisible( true );
				}
			}
		});
		bBorrar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfUsuario.setText( "" );
				tfPassword.setText( "" );
			}
		});
	}
	
	/** Devuelve la información de cierre de la ventana
	 * @return	true si se ha pulsado el botón de aceptar, false si se ha cancelado
	 */
	public boolean getInfoCierre() {
		return cierreAceptar;
	}
	
	/** Activa la posibilidad de que al cerrarse esta ventana se visualice otra
	 * @param vent	Ventana a visualizar tras cerrarse esta (si es null, no se hará)
	 */
	public void setVentanaAAbrirTrasCerrarEsta( JFrame vent ) {
		ventanaAAbrirTrasCerrarEsta = vent;
	}

}