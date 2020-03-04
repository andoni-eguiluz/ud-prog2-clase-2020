package tema1.resueltos;

import java.awt.Color;
import java.awt.Point;

import tema1.VentanaGrafica;
import tema1.ver2.GrupoPelotas;
import tema1.ver2.Pelota;

/** Resolución de alguno de los ejercicios propuestos en clase:
 * - Movimiento curvo
 * - Dibujado completo para evitar borrado accidental de otro elemento
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class PruebaVentana {

	public static void main(String[] args) {
		VentanaGrafica v = new VentanaGrafica( 800, 600, "Prueba" );
		Color miColor = new Color( 127, 2, 3 );
		Pelota miPelota = new Pelota( 100.0, 200.0, 40, Color.BLUE );
		GrupoPelotas g = new GrupoPelotas();
		g.anyadePelota( miPelota );
		Pelota miPelota2 = new Pelota( 400.0, 350.0, 50, Color.GREEN );
		g.anyadePelota( miPelota2 );
		System.out.println( "Grupo de pelotas: " + g );
		g.dibuja( v );
		// TODO Quitar esto si solo tienes una pantalla
		v.getJFrame().setLocation( 2000, 0 );
		moverEnCurva( v, miPelota, miPelota2 );
		moverConRaton( v, miPelota, g );
	}
	
	private static void moverEnCurva( VentanaGrafica v, Pelota p1, Pelota p2 ) {
		v.setMensaje( "Bola verde se mueve en y con el coseno 0-180, bola azul con el seno 0-180");
		for (int inc=0; inc<180; inc++) {
			p1.moverYDibujar( v, 1, Math.sin( Math.toRadians(inc) ) );
			p2.moverYDibujar( v, 1, Math.cos( Math.toRadians(inc) ) );
			v.espera( 40 );   // 2000 milisegundos / 50 movimientos
		}
	}
	
	private static void moverConRaton( VentanaGrafica v, Pelota p, GrupoPelotas g ) {
		v.setMensaje( "Haz drag para mover la bola azul. Ahora se borra toda la pantalla 50 veces por segundo. Ver cómo ya no se borra la bola verde.");
		while (!v.estaCerrada()) {
			java.awt.Point punto = v.getRatonPulsado();
			if (punto!=null) {
				p.borra( v );
				p.setX( punto.x );
				p.setY( punto.y );
				p.dibuja( v );
			}
			// v.borra();
			// g.dibuja( v );
			v.espera( 20 );
		}
	}

}
