package ejercicio2.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import ejercicio2.business.Gestorcriticas;

import java.time.LocalDateTime;

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
	public void Cancelarespectaculosesion(String titulo/*, sesion*/) {
		
	}
	
	/**
	 * 
	 * @param titulo
	 * @param descripcion
	 * @param fechaHora
	 * @author Rafa
	 */
	public void Actualizardatosespectaculo(String titulo, String descripcion, LocalDateTime fechaHora ) {
		
		
	}
	
	public void ContabilizarVentaEntradas(){
		//Contabilizar la venta de entradas de 1 sesion
	}
	
	public void ConsultarLocalidadesDisponibles(/*Date fechaRepresentacion*/){
		//Consultar localidades disponibles para 1 espectaculo dando la fecha de representacion
	}
	
	/**
	 * 
	 * @param titulo
	 * @return
	 * @author Rafa
	 */
	public String Buscarespectaculo(String titulo) {
		StringBuffer buffer= new StringBuffer();
		  for(Espectaculo e: espectaculos) {
			  if(e.getTitulo().equals(titulo)) {
			  buffer.append("Titulo:" + e.getTitulo());
			  buffer.append("Descripcion:" + e.getDescripcion());
			  buffer.append("Categoria:" + e.getCategoria());
			  }
		  }
		  return buffer.toString();
	}

	/**
	 * 
	 * @param espectaculo
	 * @return
	 * @author Rafa
	 */
	public String Buscarespectaculo(Categoriaevento espectaculo) {
		StringBuffer buffer = new StringBuffer();
		for(Espectaculo e: espectaculos) {
			if(e.getCategoria().equals(espectaculo)) {
				buffer.append("Titulo:" + e.getTitulo());
				buffer.append("Descripcion:" + e.getDescripcion());
				buffer.append("Categoria:" + e.getCategoria());
			}
		}
		return buffer.toString();
	}
	
	public String BuscarEspectaculosEntradasDisponibles(){
		//Buscar los espectaculos con entradas disponibles, se pueden buscar por categoria o no indicar nada
		StringBuffer buffer = new StringBuffer();
		
		return buffer.toString();
	}
	
	//Pubicar critica sobre espectaculo ya celebrado
	public void PublicarCritica(){
		
	}

	/**
	 * 
	 * @param titulo
	 * @return
	 * @author Rafa
	 */
	public String Consultarcriticas(String titulo) {
		Gestorcriticas criticas = Gestorcriticas.getInstance();
		return criticas.getCriticasTitulo(titulo);
	}
	
	//Eliminar critica (el usuario que la creo)
	public void EliminarCritica(int index, String correo){
		Gestorcriticas gestorcriticas = Gestorcriticas.getInstance();
		if(gestorcriticas.comprobarPropietario(correo)){
			gestorcriticas.borraCritica(index);
		}
		else{
			System.out.println("No es el propietario de esta critica, no puede borrarla.");
		}
	}
	
	//Valorar una critica de otro usuario
	public void ValorarCritica(int index, String correo, int puntuacion){
		Gestorcriticas gestorcriticas = Gestorcriticas.getInstance();
		gestorcriticas.votaCritica(index, correo, puntuacion);
	}
	
	/**
	 * 
	 * @param fileEspectaculos
	 * @author Noelia Hinojosa Sanchez
	 */
	public void cargarFichero(String fileEspectaculos){
		File f = new File(fileEspectaculos);
		FileReader fr = null;
		BufferedReader buffer = null;
		
		String lineaFichero;
		
		try{
			if(!f.exists()){
				System.out.println("El fichero espectaculos.txt no existe, se va a crear.");
				f.createNewFile();
			}
			else{
				fr = new FileReader(f);
				buffer = new BufferedReader(fr);
				
				while((lineaFichero = buffer.readLine()) != null){
					//¿Como se van a guardar los datos?
				}
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
	
	/**
	 * 
	 * @param fileEspectaculos
	 * @author Noelia Hinojosa Sanchez
	 */
	public void guardarFichero(String fileEspectaculos){
		FileWriter f = null;
		PrintWriter buffer = null;
		
		try{
			f = new FileWriter(fileEspectaculos);
			buffer = new PrintWriter(f);
			
			for(Espectaculo e : espectaculos){
				//Guardar informacion ¿Como se van a guardar los datos?
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
				if(f != null)
					f.close();
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