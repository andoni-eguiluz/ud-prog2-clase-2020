package tema9;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import temas78.ejemplos.layouts.EjemploAnimacionBalonJLabel;

public class EjemploHiloTiempoReal {

	public static void main(String[] args) {
		JFrame vent = new JFrame();
		vent.setTitle( "Menú" );
		vent.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		vent.setSize( 200, 150 );
		// vent.setLocation( 2000, 100 );
		JButton bLanzar = new JButton( "Lanzar" );
		vent.add( bLanzar );
		bLanzar.addActionListener( new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread hilo = new Thread() {
					@Override
					public void run() {
						EjemploAnimacionBalonJLabel v = new EjemploAnimacionBalonJLabel();
						v.setVisible( true );
						v.mueveElBalon3();  // Animación de con rebotes
						System.out.println( "Fin animación" );
					}
				};
				hilo.start();  // no run!!!!!
				System.out.println( "Fin evento swing" );
			}
		});
		vent.setVisible( true );
		System.out.println( "Fin main" );
	}

}
