package ejercicio2.data;

/**
 * Clase espectador Esta clase tiene los metodos necesarios para gestionar todos
 * los datos de un espectador.
 * 
 * @author rafael piqueras espinar
 * @version 1.0, 24/09/2021
 */
public class Espectador {

	private String nombre;
	private String apellidos;
	private String usuario;
	private String correo;
	private TipoUsuario tipo;

	/**
	 * Este metodo devuelve el nombre de usuario.
	 * 
	 * @return Nombre de usuario.
	 */

	public String getNombre() {
		return nombre;
	}

	/**
	 * Este metodo asigna el nombre de usuario.
	 * 
	 * @param nombre
	 *            Nombre de usuario.
	 */

	public void setNombre(String nombre) {
		this.nombre = nombre;

	}

	/**
	 * Este metodo devuelve los apellidos del usuario.
	 * 
	 * @return Apellidos del usuario.
	 */

	public String getApellidos() {
		return apellidos;

	}

	/**
	 * Este metodo asigna los apellidos de usuario
	 * 
	 * @param apellidos
	 *            Apellidos usuario
	 */

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Este metodo devuelve el usuario.
	 * 
	 * @return Usuario
	 */

	public String getUsuario() {
		return usuario;
	}

	/**
	 * Este metodo asigna un usuario
	 * 
	 * @param usuario
	 *            Usuario
	 */

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Este metodo devuelve el correo del usuario
	 * 
	 * @return Correo del usuario
	 */

	public String getCorreo() {
		return correo;
	}

	/**
	 * Este metodo asigna el correo de un usario
	 * 
	 * @param correo
	 *            Correo usuario
	 */

	public void setCorreo(String correo) {
		this.correo = correo;

	}

	/**
	 * Este metodo devuelve el tipo de usuario.
	 * 
	 * @return Tipo de usuario.
	 */
	public TipoUsuario getTipo() {
		return tipo;
	}

	/**
	 * Este metodo asigna el tipo de usuario.
	 * 
	 * @param tipo
	 *            Tipo de usuario.
	 * @return Nada.
	 */
	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
}
