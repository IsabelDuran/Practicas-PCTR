import java.io.Serializable;
import java.util.ArrayList;

public class Libro implements Serializable {
	private String autores;
	private String referencia;
	private String anno_publicacion;
	private String editorial;
	private static ArrayList<Libro> libros = new ArrayList<>();
	
	public Libro(String autores, String referencia, String anno_publicacion, String editorial) {
		this.autores = autores;
		this.referencia = referencia;
		this.anno_publicacion = anno_publicacion;
		this.editorial = editorial;
	}

	public String getAutores() {
		return autores;
	}

	public void setAutores(String autores) {
		this.autores = autores;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getAnno_publicacion() {
		return anno_publicacion;
	}

	public void setAnno_publicacion(String anno_publicacion) {
		this.anno_publicacion = anno_publicacion;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public static ArrayList<Libro> getLibros() {
		return libros;
	}

	public static void setLibros(ArrayList<Libro> libros) {
		Libro.libros = libros;
	}
	
	public static void addLibro(Libro libro) {
		libros.add(libro);
	}
	
	public static Libro buscarLibro(String referencia) {
		for(Libro libro : libros)
			if(libro.referencia == referencia)
				return libro;
		return null;
	}
	
	public static void eliminarLibro(String referencia) {
		for(Libro libro : libros)
			if(libro.referencia == referencia)
				libros.remove(libro);
	}

}
