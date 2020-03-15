package tema1.dudas;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import tema1.VentanaGrafica;

/** Cómo reescalar una ventana gráfica
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class ReescalarVentanaGrafica {
	public static void main(String[] args) {
		VentanaGrafica v = new VentanaGrafica( 600, 400, "Prueba imagen de fondo" );
		// Queremos una ventana gráfica con una imagen de fondo
		// De momento lo podemos hacer pintando lo primero una imagen:
		v.dibujaImagen( "ud-estrella.png", 300, 200, 600, 400, 1.0, 0.0, 1.0f );
		// Pero claro, si se redimensiona la ventana la imagen no cambia (probar a cambiar el tamaño)
		// TODO Descomentar esto para solución 1
		// solucion1(v);
		// TODO Descomentar esto para solución 2
		// solucion2(v);
	}
	
	// Solución 1: Polling (preguntarnos todo el rato si hay cambio)
	private static void solucion1(VentanaGrafica v) {
		int antAnchura = v.getAnchura();
		int antAltura = v.getAltura();
		while (!v.estaCerrada()) {   //
			v.espera( 100 ); // El tiempo de espera que quieras
			if (v.getAnchura()!=antAnchura || v.getAltura()!=antAltura) {
				v.borra();
				antAnchura = v.getAnchura();
				antAltura = v.getAltura();
				v.dibujaImagen( "ud-estrella.png", antAnchura/2, antAltura/2, antAnchura, antAltura, 1.0, 0.0, 1.0f );
			}
		}
	}
	
	// Solución 2: No siempre se puede hacer polling porque tenemos que hacer otras cosas...
	// Para eso se inventó la programación orientada a eventos 
	// Hablaremos de esto largo y tendido a partir del capítulo 8
	// Gran ventaja: nuestro programa sigue haciendo sus cosas, mientras este evento se queda
	// pacientemente esperando a ser necesario
	private static void solucion2(VentanaGrafica v) {
		// Asociamos un código a un evento
		v.getJFrame().addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {  // El evento se lanza cuando la ventana se redimensiona
				v.borra();
				int anchura = v.getAnchura();
				int altura = v.getAltura();
				v.dibujaImagen( "ud-estrella.png", anchura/2, altura/2, anchura, altura, 1.0, 0.0, 1.0f );
			}
		});
	}
}
