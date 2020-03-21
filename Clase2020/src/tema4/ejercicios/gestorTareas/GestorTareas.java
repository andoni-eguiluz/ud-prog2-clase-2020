package tema4.ejercicios.gestorTareas;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import tema3.VentanaGrafica;

/** Clase principal del gestor de tareas, añadidos los interfaces Movible y Serializable para almacenamiento
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class GestorTareas {

	/** Método principal de ejecución del ejercicio
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		GestorTareas mgt = new GestorTareas();
		mgt.run();
	}
	
	// =================== Constantes static
	
	public static final int ANCHURA_VENTANA = 1000;
	public static final int ALTURA_VENTANA = 800;
	public static final String NOM_FICHERO = "GestorTareas.dat";
	
	// =================== Parte no static
	
	VentanaGrafica ventana;
	String nick;
	String password;
	ArrayList<ObjetoGT> listaObjetos;
	Boton botonNueva;
	Boton botonTrash;
	
	/** Crea el gestor de tareas con una ventana gráfica asociada de tamaño {@link #ANCHURA_VENTANA} x {@link #ALTURA_VENTANA}
	 */
	public GestorTareas() {
		ventana = new VentanaGrafica( ANCHURA_VENTANA, ALTURA_VENTANA, "Gestor de tareas" );
		ventana.setDibujadoInmediato( false );  // Preparar la ventana para doble buffer (evita el parpadeo en las animaciones)
		listaObjetos = new ArrayList<>();
	}
	
	public ArrayList<ObjetoGT> getListaObjetos() {
		return listaObjetos;
	}

	/** Finaliza el gestor de tareas, cerrando su ventana
	 */
	public void fin() {
		ventana.acaba();
	}
			
	/** Ejecuta el gestor de tareas, lanzando el proceso de ejecución principal
	 */
	public void run() {
		boolean okLogin = login();
		if (okLogin) {
			initDatos();
			interaccion();
			guardaDatosFichero( new File( NOM_FICHERO ) );
		}
	}

	// Proceso de login - devuelve true si es correcto
	private boolean login() {
		nick = JOptionPane.showInputDialog( "Bienvenido/a al gestor de tareas. ¿Nombre de usuario?" );
		if (nick==null) return false; // Si no hay nick se acaba
		password = JOptionPane.showInputDialog( "Hola " + nick + ". Introduce password:" );
		if (password==null) return false; // Si no hay password también 
		// TODO Gestión de validación de usuario
		return true;
	}
	
	// Proceso de inicializar datos de la ventana de trabajo
	private void initDatos() {
		File fichero = new File( NOM_FICHERO );
		if (fichero.exists()) { // Si hay guardado de datos previo
			cargaDatosFichero( fichero );
		} else { // Si no
			listaObjetos.add( new Trabajador( 450, 200, ventana, "Chris", "/tema3/ejercicios/gestorTareas/img/chris.png" ) );
			listaObjetos.add( new Trabajador( 800, 200, ventana, "Julia", "/tema3/ejercicios/gestorTareas/img/julia.png" ) );
			listaObjetos.add( new Trabajador( 450, 600, ventana, "Natalie", "/tema3/ejercicios/gestorTareas/img/natalie.png" ) );
			listaObjetos.add( new Trabajador( 800, 600, ventana, "Jim", "/tema3/ejercicios/gestorTareas/img/jim.png" ) );
		}
		// Los botones se añaden en cualquier caso
		botonNueva = new Boton( 40, 40, ventana, "NUEVA", "/tema3/ejercicios/gestorTareas/img/add.png" );
		botonTrash = new Boton( 40, 760, ventana, "PAPELERA", "/tema3/ejercicios/gestorTareas/img/trash.png" );
		listaObjetos.add( botonNueva );
		listaObjetos.add( botonTrash );
	}
		private void cargaDatosFichero( File f ) {
			try { // Estructura de gestión de errores (capítulo 6)
				ObjectInputStream ois = new ObjectInputStream( new FileInputStream( f ) );
				ObjetoGT ogt = (ObjetoGT) ois.readObject();
				while (ogt!=null) {
					ogt.setVentana( ventana ); // La ventana no se serializa
					listaObjetos.add( ogt );
					ogt = (ObjetoGT) ois.readObject();
				}
				ois.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		private void guardaDatosFichero( File f ) {
			try { // Estructura de gestión de errores (capítulo 6)
				ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( f ) );
				for (ObjetoGT ogt : listaObjetos) {
					if (!(ogt instanceof Boton)) {  // Los botones no se guardan
						oos.writeObject( ogt );
					}
				}
				oos.writeObject( null );  // Marca el final de los datos
				oos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	// Proceso de gestión de interacción de ratón por parte del usuario
	private void interaccion() {
		dibujaTodo();
		ventana.setMensaje( "Pulsa el botón de añadir si quieres añadir tarea, arrastra tarea para asignarla." );
		while (!ventana.estaCerrada()) {
			Point p = ventana.getRatonPulsado();
			if (p!=null) {
				for (int i=listaObjetos.size()-1; i>=0; i--) { // Atención: la recorremos al revés para que se detecten los clicks primero en los elementos más "ontop"
					ObjetoGT o = listaObjetos.get(i);
					if (o.contienePunto( p.x, p.y )) {
						pulsadoObjeto( o, p );
						break;
					}
				}
			}
			ventana.espera( 20 );
		}
	}
	
	// Acción si se pulsa un objeto con el ratón
	private void pulsadoObjeto( ObjetoGT objPulsado, Point pPulsado ) {
		if (objPulsado==botonNueva) {  // Tratamiento de botón: pulsado espera al click = pulsación
			Point p = ventana.getRatonPulsado();
			boolean click = true;
			while (p!=null) {
				ventana.espera( 20 ); // Pequeña espera
				if (!p.equals(pPulsado)) {
					click = false;
					break;
				}
				p = ventana.getRatonPulsado();
			}
			if (click) {
				ventana.setMensaje( "Creada nueva tarea" );
				((Boton)botonNueva).ejecuta( this );
				dibujaTodo();
			}
		} else if (objPulsado instanceof Movible) {  // Tratamiento de tarea o trabajador: puede ser un click o un drag
			Point p = ventana.getRatonPulsado();
			while (p!=null && p.equals(pPulsado)) {  // Esperar a que se mueva o se suelte
				ventana.espera( 20 ); // Pequeña espera
				p = ventana.getRatonPulsado();
			}
			if (p==null) { // Se ha soltado: es un click - solo se considera si es tarea
				if (objPulsado instanceof Tarea) {
					Tarea tarea = (Tarea) objPulsado;
					if (ventana.isBotonIzquierdo()) { // Es botón izquierdo - click: cambio de nombre de tarea
						String nuevoTexto = JOptionPane.showInputDialog( "Cambia el texto de la tarea " + tarea.getDescripcion(), tarea.getDescripcion() );
						if (nuevoTexto==null) return; // Si no hay texto no se cambia nada y se acaba
						tarea.setDescripcion( nuevoTexto );
						ventana.setMensaje( "Cambiada descripción de tarea a " + nuevoTexto );
					} else { // Es botón derecho - click: cambio de color de tarea
						String[] opciones = { "Verde", "Azul", "Rojo", "Naranja", "Amarillo" };
						Color[] opcionesColor = { Color.GREEN, Color.CYAN, Color.RED, Color.ORANGE, Color.YELLOW };
						String seleccion = (String) JOptionPane.showInputDialog( null, "Elige un color:", "Selección", JOptionPane.QUESTION_MESSAGE, null, opciones, "Amarillo" );
						if (seleccion!=null) {
							int donde = Arrays.asList( opciones ).indexOf( seleccion );
							tarea.setColor( opcionesColor[donde] );
						}
					}
					dibujaTodo();
				}
			} else { // Se ha movido: es un drag
				dragObjeto( (Movible)objPulsado, pPulsado, p );
			}
		}
	}
	
	private void dragObjeto( Movible obj, Point pInicial, Point pActual ) {
		if (obj instanceof Trabajador || obj instanceof Tarea) {  // Solo se puede hacer drag para tarea o trabajador
			int xInicial = obj.getX();
			int yInicial = obj.getY();
			Trabajador trabObjetivo = null;
			// Mientras haya drag, visualizarlo
			while (pActual!=null) {
				int difX = pActual.x - pInicial.x;
				int difY = pActual.y - pInicial.y;
				obj.mover( obj.getX()+difX, obj.getY()+difY );
				dibujaTodo( obj );  // Dibuja el objeto móvil "encima" para que se vea siempre mientas se hace el drag
				ventana.espera( 20 ); // Pequeña espera
				pInicial = pActual;
				pActual = ventana.getRatonPulsado();
			}
			// Al final del drag, si es una tarea hay que moverla
			if (obj instanceof Tarea) {
				Tarea tarea = (Tarea) obj;
				// comprobar si está dentro de algún trabajador o de la papelera
				boolean dragCorrecto = false;
				for (ObjetoGT gt : listaObjetos) {
					if (gt.contienePunto( tarea.getX(), tarea.getY() )) {
						if (gt instanceof Trabajador) {  // Es un trabajador: asignarla
							trabObjetivo = (Trabajador) gt;
							dragCorrecto = true;
							break;
						} else if (gt==botonTrash) {  // Es la papelera: borrarla
							listaObjetos.remove( tarea );
							dragCorrecto = true;
							break;
						}
					}
				}
				if (!dragCorrecto) {  // Si el drag no es correcto, volver a su sitio
					tarea.mover( xInicial, yInicial );  // Podría hacerse con animación: lo hacemos de golpe (cambiar si se desea animado)
				}
				// Reasignar la tarea al trabajador que corresponda quitándosela a otro si la tuviera
				if (dragCorrecto) {
					for (ObjetoGT gt : listaObjetos) {
						if (gt instanceof Trabajador) {
							Trabajador trab = (Trabajador) gt;
							if (trab.getTareasAsignadas().contains( tarea )) {
								trab.getTareasAsignadas().remove( tarea );
								break;
							}
						}
					}
					if (trabObjetivo!=null) {
						trabObjetivo.getTareasAsignadas().add( tarea );
					}
				}
			}
			dibujaTodo();
		}
	}

	// Dibuja todo
	private void dibujaTodo( ) {
		dibujaTodo( null );
	}
	
	// Dibuja todo y el objeto indicado lo dibuja el último (para que quede por encima)
	private void dibujaTodo( Movible objetoFinal ) {
		ventana.borra();
		for (ObjetoGT o : listaObjetos) {
			o.dibujar();
		}
		if (objetoFinal!=null) {
			((ObjetoGT)objetoFinal).dibujar();
		}
		ventana.repaint();  // Dibuja por doble buffer (evitando el parpadeo)
	}

}
