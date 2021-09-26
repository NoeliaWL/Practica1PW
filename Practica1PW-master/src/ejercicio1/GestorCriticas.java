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
	
	/**
	 * 
	 */
	private ArrayList<Espectador> espectadores;
	
	/**
	 * 
	 */
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
		
		espectadores.add(usuario);
	}
	
	public void bajaUsuario(String correo) {
		//�Borrar otro usuario o borrar el usuario propio?
		if(espectadores.size() == 0){
			System.out.println("No hay ning�n usuario/espectador registrado");
		}
		else{
			if(correoRegistrado(correo)){
				espectadores.remove(correo);
				System.out.println("Espectador/usuario borrado correctamente");
			}
		}
	}
	
	public Espectador getUsuario() {
		
	}
	
	public void setUsuario() {
		
	}
	
	public void creaCritica() {
		
	}
	
	public Critica getCriticas() {
		
	}
	
	public void borraCritica() {
		
	}
	
	public void votaCritica() {
		
	}
	
	public ArrayList<Critica> buscaCritica() {
		
	}
	
	/**
	 * Este m�todo comprueba si el formato del correo el�ctronico es correcto
	 * @param correo Correo el�ctronico a comprobar
	 * @return True si el formato es correcto
	 */
	public Boolean comprobarFormatoCorreo(String correo){
		Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z]{2,})$");
		Matcher comparador = patron.matcher(correo);
		
		return comparador.find();
	}
	
	/**
	 * Este m�todo comprueba si el correo el�ctronico pasado ya se encuentra registrado
	 * @param correo Correo el�ctronico a comprobar
	 * @return True si se encuentra el correo el�ctronico ya registrado
	 */
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
