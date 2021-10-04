package ejercicio2.business;

import java.util.ArrayList;

import ejercicio2.data.Categoriaevento;
import ejercicio2.data.Espectaculo;

public class Gestorespectaculos {
	private static Gestorespectaculos instance = null;

	private ArrayList<Espectaculo> espectaculos;

	private Gestorespectaculos() {

	}

	public static Gestorespectaculos getInstance() {
		if (instance == null) {
			instance = new Gestorespectaculos();
		}
		return instance;
	}

	/**
	 * 
	 * @param titulo
	 * @param descripcion
	 * @param categoria
	 * @author Rafael
	 */
	public void Altaespectaculo(String titulo, String descripcion, Categoriaevento categoria) {
		Espectaculo espectaculo1 = new Espectaculo();
		espectaculo1.setTitulo(titulo);
		espectaculo1.setDescripcion(descripcion);
		espectaculo1.setCategoriaevento(categoria);
		espectaculos.add(espectaculo1);
	}
	
	/**
	 * 
	 * @param titulo
	 * @author Rafael
	 */
	public void Cancelarespectaculotodos(String titulo) {
		for (Espectaculo e : espectaculos)
			if (e.getTitulo().equals(titulo))
				espectaculos.remove(e);
	}

	/**
	 * @author Rafael
	 */
	public void Cancelarespectaculosesion() {

	}
	
	public void cargarFichero(String fileEspectaculos){
		
	}
}
