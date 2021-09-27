package ejercicio1;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase GestorCriticas Esta clase tiene los métodos necesarios para gestionar
 * las críticas y los usuarios.
 * 
 * @author Antonio Cabezas Jarabo
 * @version 1.0, 24/09/2021
 */
public class GestorCriticas {

	/**
	 * Instancia única del gestor de críticas.
	 */
	private static GestorCriticas instance = null;

	private ArrayList<Espectador> espectadores;
	private ArrayList<Critica> criticas;

	/**
	 * Constructor del gestor de críticas. Es privado para que sólo haya un
	 * único gestor.
	 */
	private GestorCriticas() {

	}

	/**
	 * Este método controla que haya únicamente un gestor de críticas.
	 * 
	 * @return El gestor de críticas.
	 * @author Noelia Hinojosa Sánchez
	 */
	public static GestorCriticas getInstance() {
		if (instance == null) {
			instance = new GestorCriticas();
		}
		return instance;
	}

	/**
	 * Este método da de alta a un usuario en el sistema.
	 * @param nombre Nombre del usuario.
	 * @param apellidos Apellidos del usuario.
	 * @param nick Nombre de usuario o nick.
	 * @param correo Correo eléctronico único del usuario.
	 * @return Nada.
	 * @author Noelia Hinojosa Sánchez
	 */
	public void altaUsuario(String nombre, String apellidos, String nick, String correo) {
		Espectador usuario = new Espectador();
		usuario.setNombre(nombre);
		usuario.setApellidos(apellidos);
		usuario.setUsuario(nick);
		usuario.setCorreo(correo);
	}

	/**
	 * Este método da de baja a un usuario del sistema.
	 * @param correo Correo eléctronico del usuario.
	 * @return Nada.
	 * @author Noelia Hinojosa Sánchez
	 */
	public void bajaUsuario(String correo) {
		Espectador usuario = new Espectador();
		usuario = getUsuario(correo);
		if (usuario != null) {
			espectadores.remove(usuario);
		}
	}

	public Espectador getUsuario(String correo) {
		Espectador usuario = new Espectador();
		for (Espectador e : espectadores) {
			if (e.getCorreo().equals(correo)) {
				usuario = e;
			}
		}
		return usuario;
	}

	public void actualizarDatosUsuario(String nombre, String apellidos, String nick, String correo) {
		for (Espectador e : espectadores) {
			if (e.getCorreo().equals(correo)) {
				e.setNombre(nombre);
				e.setApellidos(apellidos);
				e.setUsuario(nick);
			}
		}
	}

	/**
	 * Este método crea una nueva crítica de un espectáculo.
	 * @param titulo Título de la crítica.
	 * @param puntuacion Puntuación asignada al espectáculo.
	 * @param reseña Comentario del espectáculo.
	 * @param correo Correo eléctronico del usuario.
	 * @return Nada.
	 * @author Noelia Hinojosa Sánchez
	 */
	public void creaCritica(String titulo, int puntuacion, String reseña, String correo) {
		Critica critica = new Critica();
		critica.setTitulo(titulo);
		critica.setPuntuacion(puntuacion);
		critica.setReseña(reseña);
		critica.setCorreoPropietario(correo);
	}

	/**
	 * Este método devuelve todas las críticas guardadas en el sistema, para posteriormente mostrarselas al usuario.
	 * @return Lista con todas las críticas del sistema.
	 * @author Noelia Hinojosa Sánchez
	 */
	public ArrayList<Critica> getCriticas() {
		return criticas;
	}

	public void borraCritica() {

	}

	public void votaCritica() {

	}

	public ArrayList<Critica> buscaCritica() {
		return criticas; //Para que no de error mientras
	}

	/**
	 * Este método comprueba que el correo eléctronico pasado tenga un formato válido.
	 * @param correo Correo eléctronico a comprobar.
	 * @return True si el formato es correcto.
	 * @author Noelia Hinojosa Sánchez
	 */
	public Boolean comprobarFormatoCorreo(String correo) {
		Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z]{2,})$");
		Matcher comparador = patron.matcher(correo);

		return comparador.find();
	}

	/**
	 * Este método comprueba si un correo eléctronico ya se encuentra registrado en el sistema.
	 * @param correo Correo eléctronico a comprobar.
	 * @return True si el correo ya está registrado.
	 * @author Noelia Hinojosa Sánchez
	 */
	public Boolean correoRegistrado(String correo) {
		Boolean bandera = false;

		for (Espectador e : espectadores) {
			if (e.getCorreo().equals(correo)) {
				bandera = true;
			}
		}

		return bandera;
	}
}
