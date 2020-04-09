package tema5.ejemplos;

import java.util.Arrays;

/** Ejemplo de uso de enum
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class EjemploEnums {

	public static void main(String[] args) {
		
		// Imaginemos que quiero usar colores de 4 tipos: rojo, verde, azul, blanco

		// Me lo podría currar con strings... pero es muy fácil cometer errores
		String color = "rojo";
		color = "verde";
		color = "berde";
		
		// Podría codificarlo con enteros... pero ¿quién es quién? ¿Por qué 0 es rojo y no verde?		
		int color2 = 0;  // 0 rojo
		color2 = 1; // 1 verde  ¿o era azul?
		// En informática nos hemos pasado décadas codificando valores con enteros, normalmente con constantes:.
		final int COLOR_ROJO = 0;
		final int COLOR_VERDE = 1;
		// De hecho Java todavía lo sigue usando mucho
		// Por ejemplo: https://docs.oracle.com/javase/8/docs/api/javax/swing/WindowConstants.html#DISPOSE_ON_CLOSE
        // Ahí puedes ver tres posibilidades (que comentaremos en ventanas) HIDE / DISPOSE / EXIT que son los enteros		
		
		// El problema de las constantes es que no hay relación entre el valor y su significado. 
		// Por ejemplo si escribimos a consola el rojo
		System.out.println( COLOR_ROJO );  // ¿Qué es 0 para el usuario?
		// Por ejemplo si le pedimos a un usuario que introduzca un color 0-1-2-3  ¿qué color es cada uno?
		
		// Solución de muchos lenguajes modernos: ENUMERACIONES
		// En java palabra clave enum usar enums.  (ver abajo: "MiColor")
		// Se pueden asignar directamente con información "simbólica" (identificadores)
		MiColor color3 = MiColor.ROJO;
		color3 = MiColor.VERDE;
		// Se pueden convertir desde strings (.valueOf)
		String colorEnString = "AZUL";
		color3 = MiColor.valueOf( colorEnString );  // Se convierte el string "AZUL" en el valor MiColor.AZUL
		// Se pueden convertir directamente a strings (toString por defecto de cualquier enum):
		System.out.println( color3 );
		// Se pueden recorrer muy fácil con un for each sobre el método .values():
		for (MiColor colorI : MiColor.values()) {
			System.out.println( colorI );
			System.out.println( colorI.ordinal() );   // .ordinal() saca el orden 0 a n-1 del enum en la lista
		}
		// Se pueden comparar
		System.out.println( "comparación blanco y rojo? " + MiColor.BLANCO.compareTo( MiColor.ROJO ) + " (>0 indica que blanco es mayor (posterior) que rojo)");
	}

}

// Ejemplo ENUM:
enum MiColor {
	ROJO, VERDE, AZUL, BLANCO
	// Java internamente hace algo así (no es exactamente eso, pero es la idea: un objeto nuevo con cada valor de enum, asociado al índice entero 0 a n-1):
	// public static final Color ROJO = new MiColor( 0 );
	// public static final Color VERDE = new MiColor( 1 );
	// public static final Color AZUL = new MiColor( 2 );
	// public static final Color BLANCO = new MiColor( 3 );
}
// Características de un enum:
// Solo valen los valores que se indican (se crea un objeto por valor)
// Cada valor se asocia a una constante con el mismo nombre indicado:  MiColor.ROJO, MiColor.VERDE, etc.  (son public static final, aunque no se indica)
// Tienen ORDEN y se pueden comparar: ROJO < VERDE < AZUL < BLANCO   (con el método compareTo) (O sea, un enum implementa el interfaz Comparable)
// El método estático values() devuelve un array de todas las constantes definidas para ese enum
