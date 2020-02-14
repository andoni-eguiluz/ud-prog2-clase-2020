package tema1.basico;
public class TiposBasicos {
	public static void main(String[] args) {
		tiposPrimitivos();
		tiposNoPrimitivos();
	}
	
	static void tiposNoPrimitivos() {
		String s = "";
		System.out.println( "Longitud: " + s.length() );
		int i = 5;
		System.out.println( "Entero concatenado: " + i );  // Conversión implícita de i a String
	}
	
	static void tiposPrimitivos() {
		byte b = 5;  // 1 byte = 8 bits -128 a 127
		short s = 5; // 16 bits  -32768 a 32767
		int i = 5; // 32 bits
		System.out.println( Integer.MAX_VALUE );
		long l = 5; // 64 bits
		System.out.println( Long.MAX_VALUE );
		System.out.println( System.currentTimeMillis() );
		float f = 2; // 32 bits con decimales
		System.out.println( Float.MAX_VALUE );
		float f2 = 200000000000.03f;
		System.out.println( f2 );
		double d = 200000000000.03;
		System.out.println( d );
		System.out.println( Double.MAX_VALUE );
		System.out.println( Double.MIN_VALUE );
		char c = 'ñ';
		boolean bool = false; // true
		// Trabajo con tipos primitivos
		int i2 = 3;
		System.out.println( i + i2 );
		double d2 = d * f;  // Conversión implícita de float a double
		float f3 = (float) d * f;  // Conversión explícita (no se puede implícita de double a float)
		int d4 = (int) (i * d);  // Operación truncada
		int d5 = (int) Math.round( i * d );  // Operación redondeada
	}
}
