package ejercicio2.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
		File f = new File(fileEspectaculos);
		FileReader fr = null;
		BufferedReader buffer = null;
		
		try{
			if(!f.exists()){
				System.out.println("El fichero espectaculos.txt");
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(fr != null)
					fr.close();
				if(buffer != null){
					buffer.close();
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
