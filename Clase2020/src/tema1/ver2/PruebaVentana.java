package tema1.ver2;

import java.awt.Color;
import tema1.VentanaGrafica;

public class PruebaVentana {

	public static void main(String[] args) {
		VentanaGrafica v = new VentanaGrafica( 800, 600, "Prueba" );
		Color miColor = new Color( 127, 2, 3 );
		v.dibujaRect( 20, 20, 50, 50, 5.0f, miColor, Color.LIGHT_GRAY );
		Pelota miPelota = new Pelota( 100.0, 200.0, 40, Color.BLUE );
//		Pelota miPelota = new Pelota();
//		miPelota.setX( 100.0 );
//		miPelota.setY( 200.0 );
//		miPelota.setColor( Color.BLUE );
//		miPelota.setRadio( 40 );
		GrupoPelotas g = new GrupoPelotas();
		g.anyadePelota( miPelota );
		Pelota miPelota2 = new Pelota( 400.0, 350.0, 50, Color.GREEN );
//		Pelota miPelota2 = new Pelota();
//		miPelota2.setX( 400.0 );
//		miPelota2.setY( 350.0 );
//		miPelota2.setColor( Color.GREEN );
//		miPelota2.setRadio( 50 );
		g.anyadePelota( miPelota2 );
		System.out.println( "Grupo de pelotas: " + g );
		g.dibuja( v );
		v.getJFrame().setLocation( 2000, 0 ); // NOOOOO
		miPelota.mover50ADerecha( v );
	}

}
