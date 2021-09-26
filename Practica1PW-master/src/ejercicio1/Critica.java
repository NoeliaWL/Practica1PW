package ejercicio1;

import java.util.ArrayList;

/**
 * Clase Critica
 * Esta clase maneja la informaci�n necesario sobre la cr�tica de un espect�culo.
 * @author Noelia Hinojosa S�nchez
 * @version 1.0, 24/09/2021 
 */
public class Critica {
	
	/**
	 * T�tulo asociado a la cr�tica de un espect�culo.
	 */
	private String titulo;
	
	/**
	 * Puntuaci�n del espect�culo.
	 * Los valores v�lidos son de 1 a 10, siendo el 1 la puntuaci�n menor.
	 * No puede ser NULL.
	 */
	private int puntuacion;
	
	/**
	 * Comentario asociado a la cr�tica de un espect�culo.
	 */
	private String rese�a;
	
	/**
	 * Valoraciones de la cr�tica.
	 * Lista con todas las valoraciones que ha recibido la cr�tica.
	 * Los valores v�lidos son de 1 a 5, siendo el 1 la valoraci�n menor.
	 */
	private ArrayList<Integer> valoraciones;

	
	/**
	 * Constructor vac�o.
	 * Genera una instancia de la clase.
	 */
	public Critica() {
		
	}
	
	/**
	 * Constructor parametrizado.
	 * Genera una instancia de la clase completa con los atributos pasados.
	 * @param titulo T�tulo de la cr�tica.
	 * @param puntuacion Puntuaci�n del espect�culo.
	 * @param rese�a Comentario sobre el epsect�culo.
	 * @param valoraciones Valoraciones de la cr�tica.
	 */
	public Critica(String titulo, int puntuacion, String rese�a, ArrayList<Integer> valoraciones) {
		super();
		this.titulo = titulo;
		this.puntuacion = puntuacion;
		this.rese�a = rese�a;
		this.valoraciones = valoraciones;
	}

	/**
	 * Este m�todo devuelve el t�tulo de la cr�tica.
	 * @return El t�tulo de la cr�tica.
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Este m�todo asigna el t�tulo de la cr�tica.
	 * @param titulo T�tulo de la cr�tica.
	 * @return Nada.
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Este m�todo devuelve la puntuaci�n del espect�culo.
	 * @return La puntuaci�n del espect�culo.
	 */
	public int getPuntuacion() {
		return puntuacion;
	}

	/**
	 * Este m�todo asigna la puntuaci�n del espect�culo.
	 * @param puntuacion Puntuaci�n del espect�culo.
	 * @return Nada.
	 */
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	/**
	 * Este m�todo devuelve el comentario del espect�culo.
	 * @return El comentario del epsect�culo.
	 */
	public String getRese�a() {
		return rese�a;
	}

	/**
	 * Este m�todo asigna el comentario del espect�culo.
	 * @param rese�a Comentario del espect�culo.
	 * @return Nada.
	 */
	public void setRese�a(String rese�a) {
		this.rese�a = rese�a;
	}

	/**
	 * Este m�todo devuelve todas las valoraciones de la cr�tica.
	 * @return Las valoraciones de la cr�tica.
	 */
	public ArrayList<Integer> getValoraciones() {
		return valoraciones;
	}

	/**
	 * Este m�todo asigna las valoraciones a la cr�tica.
	 * @param valoracion Valoraciones de la cr�tica.
	 * @return Nada.
	 */
	public void setValoraciones(ArrayList<Integer> valoraciones) {
		this.valoraciones = valoraciones;
	}
}
