package ejercicio1;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase GestorCriticas
 * Esta clase tiene los m�todos necesarios para gestionar las cr�ticas y los usuarios.
 * @author 
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
	 * Constructor del gestor de cr�ticas.
	 * Es privado para que s�lo haya un �nico gestor.
	 */
	private GestorCriticas() {
		
	}
	
	/**
	 * Este m�todo controla que haya �nicamente un gestor de cr�ticas.
	 * @return El gestor de cr�ticas.
	 */
	public static GestorCriticas getInstance() {
		if (instance == null) {
			instance = new GestorCriticas();
		}
		return instance;
	}
	
	public void altaUsuario(String nombre, String apellidos, String nick, String correo) {
		Espectador usuario = new Espectador();
		usuario.setNombre(nombre);
		usuario.setApellidos(apellidos);
		usuario.setUsuario(nick);
		usuario.setCorreo(correo);
	}
	
	public void bajaUsuario(String correo) {
		Espectador usuario = new Espectador();
		usuario = getUsuario(correo);
		if(usuario != null){
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
	
	public void creaCritica(String titulo, int puntuacion, String rese�a) {
		
	}
	
	public Critica getCriticas() {
		
	}
	
	public void borraCritica() {
		
	}
	
	public void votaCritica() {
		
	}
	
	public ArrayList<Critica> buscaCritica() {
		
	}
	
	public Boolean comprobarFormatoCorreo(String correo){
		Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z]{2,})$");
		Matcher comparador = patron.matcher(correo);
		
		return comparador.find();
	}
	
	public Boolean correoRegistrado(String correo){
		Boolean bandera = false;
		
		for(Espectador e : espectadores){
			if(e.getCorreo().equals(correo)){
				bandera = true;
			}
		}
		
		return bandera;
	}
}
