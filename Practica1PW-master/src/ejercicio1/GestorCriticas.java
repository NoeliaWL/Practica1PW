package ejercicio1;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase GestorCriticas
 * Esta clase tiene los métodos necesarios para gestionar las críticas y los usuarios.
 * @author 
 * @version 1.0, 24/09/2021 
 */
public class GestorCriticas {
	
	/**
	 * Instancia única del gestor de críticas.
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
	 * Constructor del gestor de críticas.
	 * Es privado para que sólo haya un único gestor.
	 */
	private GestorCriticas() {
		
	}
	
	/**
	 * Este método controla que haya únicamente un gestor de críticas.
	 * @return El gestor de críticas.
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
		//¿Borrar otro usuario o borrar el usuario propio?
		if(espectadores.size() == 0){
			System.out.println("No hay ningún usuario/espectador registrado");
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
	 * Este método comprueba si el formato del correo eléctronico es correcto
	 * @param correo Correo eléctronico a comprobar
	 * @return True si el formato es correcto
	 */
	public Boolean comprobarFormatoCorreo(String correo){
		Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z]{2,})$");
		Matcher comparador = patron.matcher(correo);
		
		return comparador.find();
	}
	
	/**
	 * Este método comprueba si el correo eléctronico pasado ya se encuentra registrado
	 * @param correo Correo eléctronico a comprobar
	 * @return True si se encuentra el correo eléctronico ya registrado
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
