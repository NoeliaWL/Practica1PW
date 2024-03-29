package ejercicio2.data;

import java.util.ArrayList;

/**
 * Clase Critica Esta clase maneja la informacion necesario sobre la critica de
 * un espectaculo.
 * 
 * @author Noelia Hinojosa Sanchez, Rafael Piqueras Espinar
 * @version 1.0, 24/09/2021
 */
public class Critica {

	/**
	 * Titulo asociado a la critica de un espectaculo.
	 */
	private String titulo;

	/**
	 * Puntuacion del espectaculo. Los valores validos son de 1 a 10, siendo el
	 * 1 la puntuacon menor. No puede ser NULL.
	 */
	private int puntuacion;

	/**
	 * Comentario asociado a la critica de un espectaculo.
	 */
	private String resena;

	/**
	 * Valoraciones de la critica. Lista con todas las valoraciones que ha
	 * recibido la critica. Los valores validos son de 1 a 5, siendo el 1 la
	 * valoracion menor.
	 */
	private ArrayList<Valoraciones> valoraciones;

	/**
	 * Correo electronico del usuario que creo la critica.
	 */
	private String correoPropietario;

	/**
	 * Titulo identificativo de un espectaculo Vincula la critica con un
	 * espectaculo concreto.
	 */
	private String tituloEspectaculo;

	/**
	 * Este metodo devuelve el titulo de la critica.
	 * 
	 * @return El titulo de la critica.
	 */
	public String getTitulo() {
		return titulo;
	}

	public Critica() {
		valoraciones = new ArrayList<Valoraciones>();
	}

	/**
	 * Este metodo asigna el titulo de la critica.
	 * 
	 * @param titulo
	 *            Titulo de la critica.
	 * @return Nada.
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Este metodo devuelve la puntuacion del espectaculo.
	 * 
	 * @return La puntuacion del espectaculo.
	 */
	public int getPuntuacion() {
		return puntuacion;
	}

	/**
	 * Este metodo asigna la puntuacion del espectaculo.
	 * 
	 * @param puntuacion
	 *            puntuacion del espectaculo.
	 * @return Nada.
	 */
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	/**
	 * Este metodo devuelve el comentario del espectaculo.
	 * 
	 * @return El comentario del epsectáculo.
	 */
	public String getResena() {
		return resena;
	}

	/**
	 * Este metodo asigna el comentario del espectaculo.
	 * 
	 * @param reseña
	 *            Comentario del espectaculo.
	 * @return Nada.
	 */
	public void setResena(String resena) {
		this.resena = resena;
	}

	/**
	 * Este metodo devuelve todas las valoraciones de la critica.
	 * 
	 * @return Las valoraciones de la critica.
	 */
	public ArrayList<Valoraciones> getValoraciones() {
		return valoraciones;
	}

	/**
	 * Este metodo asigna las valoraciones a la critica.
	 * 
	 * @param valoracion
	 *            Valoraciones de la critica.
	 * @return Nada.
	 */
	public void setValoraciones(ArrayList<Valoraciones> valoraciones) {
		this.valoraciones = valoraciones;
	}

	/**
	 * Este metodo devuelve el correo eléctronico del usuario que creo la
	 * critica.
	 * 
	 * @return Correo electronico del usuario que genero la critica.
	 */
	public String getCorreoPropietario() {
		return correoPropietario;
	}

	/**
	 * Este metodo asigna el correo electronico del usuario que creo la critica.
	 * 
	 * @param correoPropietario
	 *            Correo electronico del usuario que creo la critica.
	 * @return Nada.
	 */
	public void setCorreoPropietario(String correoPropietario) {
		this.correoPropietario = correoPropietario;
	}

	/**
	 * Este metodo devuelve el titulo del espectaculo valorado en la critica.
	 * 
	 * @return Titulo del espectaculo.
	 */
	public String getTituloEspectaculo() {
		return tituloEspectaculo;
	}

	/**
	 * Este metodo asigna el titulo del espectaculo que se esta valorando.
	 * 
	 * @param tituloEspectaculo
	 *            Titulo del espectaculo.
	 * @return Nada.
	 */
	public void setTituloEspectaculo(String tituloEspectaculo) {
		this.tituloEspectaculo = tituloEspectaculo;
	}
}
