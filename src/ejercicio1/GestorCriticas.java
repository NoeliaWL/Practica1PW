package ejercicio1;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase GestorCriticas Esta clase tiene los m�todos necesarios para gestionar
 * las cr�ticas y los usuarios.
 * 
 * @author Antonio Cabezas Jarabo
 * @version 1.0, 24/09/2021
 */
public class GestorCriticas {

	/**
	 * Instancia �nica del gestor de cr�ticas.
	 */
	private static GestorCriticas instance = null;

	private ArrayList<Espectador> espectadores;
	private ArrayList<Critica> criticas;

	



	/**
	 * Constructor del gestor de cr�ticas. Es privado para que s�lo haya un
	 * �nico gestor.
	 */
	private GestorCriticas() {

	}

	/**
	 * Este m�todo controla que haya �nicamente un gestor de cr�ticas.
	 * 
	 * @return El gestor de cr�ticas.
	 * @author Noelia Hinojosa S�nchez
	 */
	public static GestorCriticas getInstance() {
		if (instance == null) {
			instance = new GestorCriticas();
		}
		return instance;
	}

	/**
	 * Este m�todo da de alta a un usuario en el sistema.
	 * @param nombre Nombre del usuario.
	 * @param apellidos Apellidos del usuario.
	 * @param nick Nombre de usuario o nick.
	 * @param correo Correo el�ctronico �nico del usuario.
	 * @return Nada.
	 * @author Noelia Hinojosa S�nchez
	 */
	public void altaUsuario(String nombre, String apellidos, String nick, String correo) {
		Espectador usuario = new Espectador();
		usuario.setNombre(nombre);
		usuario.setApellidos(apellidos);
		usuario.setUsuario(nick);
		usuario.setCorreo(correo);
	}

	/**
	 * Este m�todo da de baja a un usuario del sistema.
	 * @param correo Correo el�ctronico del usuario.
	 * @return Nada.
	 * @author Noelia Hinojosa S�nchez
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
	 * Este m�todo crea una nueva cr�tica de un espect�culo.
	 * @param titulo T�tulo de la cr�tica.
	 * @param puntuacion Puntuaci�n asignada al espect�culo.
	 * @param rese�a Comentario del espect�culo.
	 * @param correo Correo el�ctronico del usuario.
	 * @return Nada.
	 * @author Noelia Hinojosa S�nchez
	 */
	public void creaCritica(String titulo, int puntuacion, String reseña, String correo) {
		Critica critica = new Critica();
		critica.setTitulo(titulo);
		critica.setPuntuacion(puntuacion);
		critica.setReseña(reseña);
		critica.setCorreoPropietario(correo);
	}

	/**
	 * Este m�todo devuelve todas las cr�ticas guardadas en el sistema, para posteriormente mostrarselas al usuario.
	 * @return Lista con todas las cr�ticas del sistema.
	 * @author Noelia Hinojosa S�nchez
	 */
	public ArrayList<Critica> getCriticas() {
		
		return criticas;
	}
	
	/**
	 * Este metodo borra una critica .
	 * @param index Borra una critica.
	 * @author Rafael Piqueras Espinar
	 */

	public void borraCritica(int index) {
		criticas.remove(index);

	}
	
	/**
	 * Este metodo es para votar una critica de usuario.
	 * @param index
	 * @param correo Correo electronico del usuario.
	 * @param puntuacion Puntua una critica.
	 * @author Rafael Piqueras Espinar
	 */

	public void votaCritica(int index, String correo, int puntuacion) {
			ArrayList<Valoraciones> valoracion = criticas.get(index).getValoraciones(); 
			Valoraciones valoracion1 = new Valoraciones();
			valoracion1.setCorreo(correo);
			valoracion1.setValoracion(puntuacion);
			

	}
	/**
	 * Este metodo devuelve la lista de criticas de usuario.
	 * @param correo Correo electronico del usuario.
	 * @return lista con las criticas de un usuario.
	 * @autor Rafael Piquers Espinar
	 */

	public ArrayList<Critica> buscaCritica(String correo) {
		ArrayList<Critica> criticaUsuario = new ArrayList<Critica>();
		for ( Critica e : criticas)
			if(e.getCorreoPropietario().equals(correo))
			criticaUsuario.add(e);
		
		return criticaUsuario;
	}

	/**
	 * Este m�todo comprueba que el correo el�ctronico pasado tenga un formato v�lido.
	 * @param correo Correo el�ctronico a comprobar.
	 * @return True si el formato es correcto.
	 * @author Noelia Hinojosa S�nchez
	 */
	public Boolean comprobarFormatoCorreo(String correo) {
		Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z]{2,})$");
		Matcher comparador = patron.matcher(correo);

		return comparador.find();
	}

	/**
	 * Este m�todo comprueba si un correo el�ctronico ya se encuentra registrado en el sistema.
	 * @param correo Correo el�ctronico a comprobar.
	 * @return True si el correo ya est� registrado.
	 * @author Noelia Hinojosa S�nchez
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
