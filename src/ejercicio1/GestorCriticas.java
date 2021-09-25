package ejercicio1;

import java.util.ArrayList;

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
