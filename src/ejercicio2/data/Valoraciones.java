package ejercicio2.data;

/**
 * Clase Valoraciones Esta clase maneja la informacion necesaria sobre las
 * valoraciones de una critica.
 * 
 * @author Noelia Hinojosa Sanchez
 * @version 1.0, 27/09/2021
 */
public class Valoraciones {
	/**
	 * Correo electronico del usuario que valora una critica.
	 */
	private String correo;

	/**
	 * Puntuacion asignada a una critica.
	 */
	private int valoracion;

	/**
	 * Este metodo devuelve el correo electronico del usuario que valoro una
	 * critica.
	 * 
	 * @return Correo electronico del usuario.
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * Este metodo asigna el correo electronico del usuario que valoro una
	 * critica.
	 * 
	 * @param correo
	 *            Correo electronico del usuario.
	 * @return Nada.
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * Este metodo devuelve la valoracion de la critica.
	 * 
	 * @return Puntuación de la critica.
	 */
	public int getValoracion() {
		return valoracion;
	}

	/**
	 * Este metodo asigna la puntuacion a la critica.
	 * 
	 * @param valoracion
	 *            Puntuacion de la critica.
	 * @return Nada.
	 */
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
}
