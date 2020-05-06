package tema9;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tema3.pong.Pong;
import temas78.ejemplos.layouts.EjemploAnimacionBalonJLabel;

public class EjemploHiloActualizacion {

	private static JLabel lReloj;
	public static void main(String[] args) {
		JFrame vent = new JFrame();
		vent.setTitle( "Reloj" );
		vent.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		vent.setSize( 400, 250 );
		vent.setLocation( 2000, 100 );
		lReloj = new JLabel( "00:00:00", JLabel.CENTER );
		lReloj.setFont( new Font( "Arial", Font.PLAIN, 40) );
		vent.add( lReloj, BorderLayout.NORTH );
		vent.add( new JLabel( "Ventana que muestra un reloj que se actualiza cuando se activa" ), BorderLayout.CENTER );
		JPanel pInferior = new JPanel();
		JButton bActivar = new JButton( "Activar" );
		JButton bAcabar = new JButton( "Acabar" );
		pInferior.add( bActivar );
		pInferior.add( bAcabar );
		vent.add( pInferior, BorderLayout.SOUTH );
		bAcabar.addActionListener( new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				vent.dispose();
			}
		});
		bActivar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Actualización de reloj
				Thread hilo = new Thread() {
					public void run() {
						funcionaReloj();
					}
				};
				hilo.start();
			}
		});
		vent.setVisible( true );
		vent.addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				pararHilo();
			}
		});
	}
	
	private static void pararHilo() {
		seguir = false;
	}
	private static SimpleDateFormat sdf = new SimpleDateFormat( "HH:mm:ss" );
	private static boolean seguir;
	private static void funcionaReloj() {
		seguir = true;
		while (seguir) {
			Date ahora = new Date();
			String formatoHora = sdf.format( ahora );
			System.out.println( formatoHora );
			lReloj.setText( formatoHora );
			try {
				Thread.sleep( 1000 );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
