package ejercicio1;

public class Valoraciones {
	/**
	 * Correo eléctronico del usuario que valora una crítica
	 */
	private String correo;

	/**
	 * Puntuación asignada a una crítica
	 */
	private int valoracion;

	/**
	 * Este método devuelve el correo eléctronico del usuario que valoró una
	 * crítica.
	 * 
	 * @return Correo eléctronico del usuario
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * Este método asigna el correo eléctronico del usuario que valoró una
	 * crítica.
	 * 
	 * @param correo
	 *            Correo eléctronico del usuario
	 * @return Nada.
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * Este método devuelve la valoración de la crítica.
	 * 
	 * @return Puntuación de la crítica
	 */
	public int getValoracion() {
		return valoracion;
	}

	/**
	 * Este método asigna la puntuación a la crítica.
	 * 
	 * @param valoracion
	 *            Puntuación de la crítica.
	 * @return Nada.
	 */
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
}
