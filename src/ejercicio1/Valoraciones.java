package ejercicio1;

public class Valoraciones {
	/**
	 * Correo el�ctronico del usuario que valora una cr�tica
	 */
	private String correo;

	/**
	 * Puntuaci�n asignada a una cr�tica
	 */
	private int valoracion;

	/**
	 * Este m�todo devuelve el correo el�ctronico del usuario que valor� una
	 * cr�tica.
	 * 
	 * @return Correo el�ctronico del usuario
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * Este m�todo asigna el correo el�ctronico del usuario que valor� una
	 * cr�tica.
	 * 
	 * @param correo
	 *            Correo el�ctronico del usuario
	 * @return Nada.
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * Este m�todo devuelve la valoraci�n de la cr�tica.
	 * 
	 * @return Puntuaci�n de la cr�tica
	 */
	public int getValoracion() {
		return valoracion;
	}

	/**
	 * Este m�todo asigna la puntuaci�n a la cr�tica.
	 * 
	 * @param valoracion
	 *            Puntuaci�n de la cr�tica.
	 * @return Nada.
	 */
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
}
