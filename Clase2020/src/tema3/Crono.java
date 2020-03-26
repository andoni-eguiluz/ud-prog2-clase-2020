package tema3;

import java.util.ArrayList;

import tema3.VentanaConsolaConBotones;

/** Clase que permite crear cronómetros para medir tiempos con pausa, con precisión de milisegundo
 * Incluye equals y toString redefinidos (de Object)
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class Crono {

	private static VentanaConsolaConBotones vent;
	/** Prueba de la clase crono que permite crear interactivamente cronómetros y actuar con ellos
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Crono> misCronos = new ArrayList<>();
		ArrayList<String> botones = new ArrayList<>();
		botones.add( "Añade crono" );
		vent = new VentanaConsolaConBotones( "Prueba de cronómetro" );
		vent.init();
		String resp = vent.sacaBotonesYEsperaRespuesta( botones );
		while (resp!=null) {
			if (resp.equals("Añade crono")) {  // Añadir cronómetro
				String nombre = "Crono " + (misCronos.size()+1);
				misCronos.add( new Crono( nombre ) );
				botones.add( nombre );
			} else {  // Seleccionar cronómetro  ("Crono x")
				int numCrono = botones.indexOf( resp );
				manejarCrono( misCronos.get(numCrono-1) );
			}
			resp = vent.sacaBotonesYEsperaRespuesta( botones );
		}
	}

	// Maneja un crono específico
	private static void manejarCrono( Crono crono ) {
		vent.println( "Manejando " + crono );
		String resp = "";
		do {
			resp = vent.sacaBotonesYEsperaRespuesta( "Iniciar", "Pausa", "Reanudar", "Reset", "Consultar", "Volver" );
			switch (resp) {
				case "Iniciar":
					crono.activar();
					vent.println( "  " + crono.getNombre() + " activado." );
					break;
				case "Pausa":
					crono.pausar();
					vent.println( "  " + crono.getNombre() + " pausado." );
					break;
				case "Reanudar":
					crono.reanudar();
					vent.println( "  " + crono.getNombre() + " reanudado." );
					break;
				case "Reset":
					crono.reset();
					vent.println( "  " + crono.getNombre() + " reseteado." );
			}
			vent.println( "  " + crono );
		} while (!"Volver".equals(resp));
	}
	
	// Parte de objeto (no static)

	private String nombre;      // Nombre del cronómetro
	private boolean parado;     // Info de si el crono está parado (true) o activo (false)
	private long tiempoInicio;  // Marca de tiempo de inicio de crono
	private long tiempoAcum;    // Tiempo acumulado del cronómetro (para pausas)
	
	/** Crea un nuevo cronómetro inicializado a cero y parado
	 */
	public Crono( String nombre ) {
		this.nombre = nombre;
		reset();  // Por no repetir código se reutiliza el reset
	}
	
	/** Inicia el conteo de tiempo del cronómetro
	 */
	public void activar() {
		parado = false;
		tiempoInicio = System.currentTimeMillis();
	}
	
	/** Pausa el cronómetro. Si ya estaba pausado, no hace nada
	 */
	public void pausar() {
		if (parado) return;  // Si ya está pausado no hay nada que hacer
		tiempoAcum = tiempoAcum + System.currentTimeMillis() - tiempoInicio;  // Acumula el tiempo del crono hasta ahora
		parado = true; // Lo para
	}
	
	/** Reanuda el cronómetro. Si no estaba pausado, no hace nada
	 */
	public void reanudar() {
		if (!parado) return;  // Si no está pausado, nada que hacer
		tiempoInicio = System.currentTimeMillis();  // Siguiente tiempo marca
		parado = false;
	}
	
	/** Informa del estado del cronómetro 
	 * @return	true si está pausado, false en caso contrario
	 */
	public boolean isPausado() {
		return parado;
	}
	
	/** Pone el cronómetro a cero y lo deja pausado
	 */
	public void reset() {
		parado = true;
		tiempoInicio = 0;
		tiempoAcum = 0;
	}
	
	/** Devuelve el valor del cronómetro actual (sin cambiar su estado)
	 * @return	Valor del cronómetro en milisegundos
	 */
	public long getTiempo() {
		if (parado) return tiempoAcum;
		else return System.currentTimeMillis() - tiempoInicio + tiempoAcum;
	}
	
	/** Devuelve el valor del cronómetro actual (sin cambiar su estado)
	 * @return	Valor del cronómetro en formato hh:mm:ss
	 */
	public String getTiempoHHMMSS() {
		int segs = (int) Math.round(getTiempo() / 1000.0);
		int horas = segs / 3600;
		int mins = (segs % 3600) / 60;
		segs = segs % 60;
		return String.format( "%02d:%02d:%02d", horas, mins, segs );  // 2 dígitos rellenos a 0 para cada valor
	}
	
	/** Cambia el nombre del cronómetro
	 * @param nombre	Nuevo nombre del cronómetro
	 */
	public void setNombre( String nombre ) {
		this.nombre = nombre;
	}
	
	/** Devuelve el nombre del cronómetro
	 * @return	Nombre actual del cronómetro
	 */
	public String getNombre() {
		return nombre;
	}
	
	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Crono) {
			Crono c2 = (Crono) arg0;
			return nombre.equals(c2.getNombre()) && parado==c2.parado && tiempoInicio==c2.tiempoInicio && tiempoAcum==c2.tiempoAcum;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return nombre + ": " + getTiempoHHMMSS() + (parado?" (en pausa)":"");
	}
	
}
