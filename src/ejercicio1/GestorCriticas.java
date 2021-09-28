package ejercicio1;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase GestorCriticas Esta clase tiene los metodos necesarios para gestionar
 * las criticas y los usuarios.
 * 
 * @author Antonio Cabezas Jarabo
 * @version 1.0, 24/09/2021
 */
public class GestorCriticas {

	/**
	 * Instancia unica del gestor de criticas.
	 */
	private static GestorCriticas instance = null;

	private ArrayList<Espectador> espectadores;
	private ArrayList<Critica> criticas;

	/**
	 * Constructor del gestor de criticas. Es privado para que solo haya un
	 * unico gestor.
	 */
	private GestorCriticas() {

	}

	/**
	 * Este metodo controla que haya unicamente un gestor de criticas.
	 * 
	 * @return El gestor de criticas.
	 * @author Noelia Hinojosa Sanchez
	 */
	public static GestorCriticas getInstance() {
		if (instance == null) {
			instance = new GestorCriticas();
		}
		return instance;
	}

	/**
	 * Este metodo da de alta a un usuario en el sistema.
	 * 
	 * @param nombre
	 *            Nombre del usuario.
	 * @param apellidos
	 *            Apellidos del usuario.
	 * @param nick
	 *            Nombre de usuario o nick.
	 * @param correo
	 *            Correo electronico unico del usuario.
	 * @return Nada.
	 * @author Noelia Hinojosa Sanchez
	 */
	public void altaUsuario(String nombre, String apellidos, String nick, String correo) {
		Espectador usuario = new Espectador();
		usuario.setNombre(nombre);
		usuario.setApellidos(apellidos);
		usuario.setUsuario(nick);
		usuario.setCorreo(correo);
	}

	/**
	 * Este metodo da de baja a un usuario del sistema.
	 * 
	 * @param correo
	 *            Correo electronico del usuario.
	 * @return Nada.
	 * @author Noelia Hinojosa Sanchez
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
	 * Este metodo crea una nueva critica de un espectaculo.
	 * 
	 * @param titulo
	 *            Titulo de la critica.
	 * @param puntuacion
	 *            Puntuacion asignada al espectaculo.
	 * @param resena
	 *            Comentario del espectaculo.
	 * @param correo
	 *            Correo electronico del usuario.
	 * @return Nada.
	 * @author Noelia Hinojosa Sanchez
	 */
	public void creaCritica(String titulo, int puntuacion, String resena, String correo) {
		Critica critica = new Critica();
		critica.setTitulo(titulo);
		critica.setPuntuacion(puntuacion);
		critica.setResena(resena);
		critica.setCorreoPropietario(correo);
	}

	/**
	 * Este metodo devuelve todas las criticas guardadas en el sistema, para
	 * posteriormente mostrarselas al usuario.
	 * 
	 * @return Lista con todas las criticas del sistema.
	 * @author Noelia Hinojosa Sanchez
	 */
	public void getCriticas() {
		int media = 0;
		for (Critica c : criticas) {
			System.out.print(criticas.indexOf(c) + " ");
			System.out.print("Titulo: " + c.getTitulo() + " ");
			System.out.println("Puntuacion: " + c.getPuntuacion());
			System.out.println("Resena: " + c.getResena());
			for (Valoraciones v : c.getValoraciones()) {
				media += v.getValoracion();
			}
			media /= c.getValoraciones().size();
			System.out.println("Media de las valoraciones: " + media);
		}
	}

	/**
	 * Este metodo devuelve todas las criticas guardadas en el sistema de un
	 * usuario concreto, para posteriormente mostrarselas al usuario.
	 * 
	 * @return Lista con todas las criticas de un usuario del sistema.
	 * @author Noelia Hinojosa Sanchez
	 */
	public void getCriticasUsuario(String correo) {
		int media = 0;
		for (Critica c : criticas) {
			if (c.getCorreoPropietario().equals(correo)) {
				System.out.print(criticas.indexOf(c) + " ");
				System.out.print("Titulo: " + c.getTitulo() + " ");
				System.out.println("Puntuacion: " + c.getPuntuacion());
				System.out.println("Resena: " + c.getResena());
				for (Valoraciones v : c.getValoraciones()) {
					media += v.getValoracion();
				}
				media /= c.getValoraciones().size();
				System.out.println("Media de las valoraciones: " + media);
			}
		}
	}

	/**
	 * Este metodo borra una critica .
	 * 
	 * @param index
	 *            Borra una critica.
	 * @author Rafael Piqueras Espinar
	 */

	public void borraCritica(int index) {
		criticas.remove(index);
	}

	/**
	 * Este metodo es para votar una critica de usuario.
	 * 
	 * @param index
	 * @param correo
	 *            Correo electronico del usuario.
	 * @param puntuacion
	 *            Puntua una critica.
	 * @author Rafael Piqueras Espinar
	 */

	public void votaCritica(int index, String correo, int puntuacion) {
		if (criticas.get(index).getCorreoPropietario().equals(correo)) {
			System.out.println("No puede votar su propia critica");
		} else {
			ArrayList<Valoraciones> valoraciones = criticas.get(index).getValoraciones();
			Valoraciones valoracion = new Valoraciones();
			valoracion.setCorreo(correo);
			valoracion.setValoracion(puntuacion);
			valoraciones.add(valoracion);
			criticas.get(index).setValoraciones(valoraciones);
		}
	}

	/**
	 * Este metodo devuelve la lista de criticas de usuario.
	 * 
	 * @param correo
	 *            Correo electronico del usuario.
	 * @return lista con las criticas de un usuario.
	 * @autor Rafael Piquers Espinar
	 */

	public ArrayList<Critica> buscaCritica(String correo) {
		ArrayList<Critica> criticaUsuario = new ArrayList<Critica>();
		for (Critica e : criticas)
			if (e.getCorreoPropietario().equals(correo))
				criticaUsuario.add(e);

		return criticaUsuario;
	}

	/**
	 * Este metodo comprueba que el correo electronico pasado tenga un formato
	 * valido.
	 * 
	 * @param correo
	 *            Correo electronico a comprobar.
	 * @return True si el formato es correcto.
	 * @author Noelia Hinojosa Sanchez
	 */
	public Boolean comprobarFormatoCorreo(String correo) {
		Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z]{2,})$");
		Matcher comparador = patron.matcher(correo);

		return comparador.find();
	}

	/**
	 * Este metodo comprueba si un correo electronico ya se encuentra registrado
	 * en el sistema.
	 * 
	 * @param correo
	 *            Correo electronico a comprobar.
	 * @return True si el correo ya esta registrado.
	 * @author Noelia Hinojosa Sanchez
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
