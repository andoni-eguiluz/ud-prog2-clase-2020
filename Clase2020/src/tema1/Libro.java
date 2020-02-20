package tema1;

/** Clase de prueba del tipo Libro
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class Libro {
	
	// PARTE STATIC - de clase
	
	public static void main(String[] args) {
		Libro l = null;
		// System.out.println( l.getNumPaginas() );  // NULLPOINTER!!!
		l = new Libro();
		System.out.println( l.getNumPaginas() );  // Se podría pero solo en la clase ( l.numPaginas );
		l.setTitulo( "Sapiens" );
		l.setTexto( "blabla" );
		l.setNumPaginas( 350 );
		l.setISBN( "xxxxxxx" );
		l.vender( null );
	}
	
	
	// PARTE NO STATIC - de objeto
	
	private String texto;
	private String titulo;
	private String isbn;
	private int numPaginas;
	
	/**
	 * Visualiza el título del libro en consola estándar
	 */
	public void sacarAConsola() {
		System.out.println( titulo );
	}
	/** Devuelve el texto completo del libro
	 * @return	Texto del libro
	 */
	public String getTexto() {
		return this.texto;
	}
	/** Modifica el texto del libro
	 * @param texto	Nuevo texto del libro
	 */
	public void setTexto( String texto ) {
		this.texto = texto;
	}
	public String getISBN() {
		return this.isbn;
	}
	public void setISBN( String isbn ) {
		this.isbn = isbn;
	}
	public String getTitulo() {
		return titulo;  // Opcional this.titulo
	}
	public void setTitulo( String titulo ) {
		this.titulo = titulo;
	}
	public int getNumPaginas() {
		return numPaginas;
	}
	public void setNumPaginas( int numPaginas ) {
		this.numPaginas = numPaginas;
	}
	public void vender( Cliente cliente ) {
		// TODO pendiente de implementar bien
		System.out.println( "Venta realizada del libro " + this + " al cliente " + cliente );
	}
	
	public String toString() {
		return titulo + "(" + numPaginas + " páginas)";
	}
}
