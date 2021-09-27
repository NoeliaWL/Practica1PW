package ejercicio1;

import java.util.ArrayList;

/**
 * Clase Critica Esta clase maneja la información necesario sobre la crítica de
 * un espectáculo.
 * 
 * @author Noelia Hinojosa Sánchez
 * @version 1.0, 24/09/2021
 */
public class Critica {

	/**
	 * Título asociado a la crítica de un espectáculo.
	 */
	private String titulo;

	/**
	 * Puntuación del espectáculo. Los valores válidos son de 1 a 10, siendo el
	 * 1 la puntuación menor. No puede ser NULL.
	 */
	private int puntuacion;

	/**
	 * Comentario asociado a la crítica de un espectáculo.
	 */
	private String reseña;

	/**
	 * Valoraciones de la crítica. Lista con todas las valoraciones que ha
	 * recibido la crítica. Los valores válidos son de 1 a 5, siendo el 1 la
	 * valoración menor.
	 */
	private ArrayList<Valoraciones> valoraciones;

	/**
	 * Correo eléctronico del usuario que creo la crítica.
	 */
	private String correoPropietario;

	/**
	 * Este método devuelve el título de la crítica.
	 * 
	 * @return El título de la crítica.
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Este método asigna el título de la crítica.
	 * 
	 * @param titulo
	 *            Título de la crítica.
	 * @return Nada.
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Este método devuelve la puntuación del espectáculo.
	 * 
	 * @return La puntuación del espectáculo.
	 */
	public int getPuntuacion() {
		return puntuacion;
	}

	/**
	 * Este método asigna la puntuación del espectáculo.
	 * 
	 * @param puntuacion
	 *            Puntuación del espectáculo.
	 * @return Nada.
	 */
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	/**
	 * Este método devuelve el comentario del espectáculo.
	 * 
	 * @return El comentario del epsectáculo.
	 */
	public String getReseña() {
		return reseña;
	}

	/**
	 * Este método asigna el comentario del espectáculo.
	 * 
	 * @param reseña
	 *            Comentario del espectáculo.
	 * @return Nada.
	 */
	public void setReseña(String reseña) {
		this.reseña = reseña;
	}

	/**
	 * Este método devuelve todas las valoraciones de la crítica.
	 * 
	 * @return Las valoraciones de la crítica.
	 */
	public ArrayList<Valoraciones> getValoraciones() {
		return valoraciones;
	}

	/**
	 * Este método asigna las valoraciones a la crítica.
	 * 
	 * @param valoracion
	 *            Valoraciones de la crítica.
	 * @return Nada.
	 */
	public void setValoraciones(ArrayList<Valoraciones> valoraciones) {
		this.valoraciones = valoraciones;
	}

	/**
	 * Este método devuelve el correo eléctronico del usuario que creo la
	 * crítica.
	 * 
	 * @return Correo eléctronico del usuario que genero la crítica.
	 */
	public String getCorreoPropietario() {
		return correoPropietario;
	}

	/**
	 * Este método asigna el correo eléctronico del usuario que creo la crítica.
	 * 
	 * @param correoPropietario
	 *            Correo eléctronico del usuario que creo la crítica.
	 * @return Nada.
	 */
	public void setCorreoPropietario(String correoPropietario) {
		this.correoPropietario = correoPropietario;
	}
}
