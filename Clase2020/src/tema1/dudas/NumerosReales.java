package tema1.dudas;

/** El double y la reflexión de bajo nivel de los números reales
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class NumerosReales {

	public static void main(String[] args) {
		double d = 1.23456789012;
		System.out.println( "Fantástico el double, qué cosas representa: " + d );
		System.out.println( "Y todo esto en 64 bits." );
		System.out.println( "Dónde están sus límites? En su técnica de codificación." );
		d = 4503599627370496.0;
		System.out.println( "Veamos un límite:" );
		System.out.println( String.format( "  El double " + d + " (o lo que es lo mismo, %1$1.2f)", d ) );
		double incremento = 0.01;
		d = d + incremento;
		System.out.println( String.format( "  Si le sumamos %1$1.2f nos da...          ¿%2$1.2f?", incremento, d ) );
		incremento = 0.1;
		d = d + incremento;
		System.out.println( String.format( "  Y si le sumamos %1$1.2f nos da... también %2$1.2f", incremento, d ) );
		incremento = 1.0;
		d = d + incremento;
		System.out.println( String.format( "  Solo si le sumamos %1$1.2f entonces da    %2$1.2f", incremento, d ) );
		System.out.println( "¿Perdón?" );
		System.out.println( "Esto se debe a las limitaciones que tienen los 52 bits que representan la \"mantisa\" (los dígitos con significado). Otros 11 bits se dedican a exponente (la potencia por la que se multiplican)");
		d = 3;
		incremento = 0.00001;
		System.out.println( String.format( "  En números pequeños, la mantisa tiene precisión muy fina: " +
				"%1$1.5f + %2$1.5f = %3$1.5f", d, incremento, (d+incremento) ) );
		d = 10000000000000000000.0;
		incremento = 5.0;
		System.out.println( String.format( "  Pero en números grandes, la mantisa tiene precisión muy grosera y no es suficiente para pequeños cambios:\n  " +
				"%1$1.3f + %2$1.2f = %3$1.2f", d, incremento, (d+incremento) ) );
		System.out.println( "Podríamos decir que si la mantisa no tiene bits suficientes para representar el cambio, no se recoge el resultado exacto." );
		System.out.println();
		System.out.println( "¿Moraleja de todo esto? No comparéis nunca los doubles por ==!!!");
		d = 7.2;
		double d2 = 6;
		double d3 = 6;
		System.out.println( String.format( "  %1$1.1f / %2$1.1f * %3$1.1f = " + d/d2*d3, d, d2, d3 ) );
		System.out.println( String.format( "  %1$1.1f * %2$1.1f / %3$1.1f = " + d*d2/d3, d, d2, d3 ) );
		System.out.println( "  " + (d/d2)*d3 + " == " + (d*d2)/d3 + "? " + (d/d2*d3==d*d2/d3) );
		System.out.println();
		System.out.println( "Para entender más: https://es.wikipedia.org/wiki/Formato_en_coma_flotante_de_doble_precisi%C3%B3n");
		System.out.println( "  Y una bonita aproximación visual: http://fabiensanglard.net/floating_point_visually_explained/index.html");
	}

}
