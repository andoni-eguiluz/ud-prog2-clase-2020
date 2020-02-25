package tema1;

import java.awt.Color;

public class PruebaVentana {

	public static void main(String[] args) {
		VentanaGrafica v = new VentanaGrafica( 800, 600, "Prueba" );
		Color miColor = new Color( 127, 2, 3 );
		v.dibujaRect( 20, 20, 50, 50, 5.0f, miColor, Color.LIGHT_GRAY );
		Pelota miPelota = new Pelota();
		miPelota.setX( 100.0 );
		miPelota.setY( 200.0 );
		miPelota.setColor( Color.BLUE );
		miPelota.setRadio( 40 );
		GrupoPelotas g = new GrupoPelotas( 10 );
		g.anyadePelota( miPelota );
		Pelota miPelota2 = new Pelota();
		miPelota2.setX( 400.0 );
		miPelota2.setY( 350.0 );
		miPelota2.setColor( Color.GREEN );
		miPelota2.setRadio( 50 );
		g.anyadePelota( miPelota2 );
		System.out.println( "Grupo de pelotas: " + g );
		g.dibuja( v );
	}

}
