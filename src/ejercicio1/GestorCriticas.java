package ejercicio1;

import java.util.ArrayList;

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
	
	public void altaUsuario() {
		
	}
	
	public void bajaUsuario() {
		
	}
	
	public Espectador getUsuario() {
		Espectador user;
		return user;
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
	
}
