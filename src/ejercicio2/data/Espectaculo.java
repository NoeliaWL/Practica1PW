package ejercicio2.data;


/**
 * Clase Espectaculo tiene todos los metodos para gestionar los datos de un
 * espectaculo.
 * 
 * @author Rafael Piqueras Espinar
 *
 */

public class Espectaculo {

	private String titulo;

	private String descripcion;

	Categoriaevento categoria;

	public Espectaculo() {

	}

	/**
	 * Este metodo devuelve el titulo
	 * 
	 * @return titulo
	 */

	public String getTitulo() {
		return titulo;
	}

	/**
	 * Este metodo asigna el titulo
	 * 
	 * @param titulo
	 */

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Este metodo devuelve la descripcion del espectaculo
	 * 
	 * @return descripcion
	 */

	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Este metodo asigna la descripcion del espectaculo
	 * 
	 * @param descripcion
	 */

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Este metodo devuelve la categoria que tiene el espectaculo
	 * 
	 * @return
	 */

	public Categoriaevento getCategoria() {
		return categoria;
	}

	/**
	 * Este metodo asigna la categoria del espectaculo
	 * 
	 * @param categoria
	 */

	public void setCategoriaevento(Categoriaevento categoria) {
		this.categoria = categoria;
	}

}
