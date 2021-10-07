package ejercicio2.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import ejercicio2.data.Sesiones;

import ejercicio2.business.Gestorcriticas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import ejercicio2.data.Categoriaevento;
import ejercicio2.data.Espectaculo;
import ejercicio2.data.Espectaculopasemultiple;
import ejercicio2.data.Espectaculopuntual;
import ejercicio2.data.Espectaculotemporada;
import ejercicio2.data.Factoriaconcreta;

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

	public void Altaespectaculopasepuntual(String titulo, String descripcion, Categoriaevento categoria,
			Sesiones sesion) {
		Factoriaconcreta factoria = new Factoriaconcreta();
		espectaculos.add(factoria.createEspectaculoPuntual(titulo, descripcion, categoria, sesion));
	}

	public void Altaespectaculotemporada(String titulo, String descripcion, Categoriaevento categoria,
			LocalDate fechaInicio, LocalDate fechaFin, LocalTime hora) {
		Factoriaconcreta factoria = new Factoriaconcreta();
		espectaculos
				.add(factoria.createEspectaculoTemporada(titulo, descripcion, categoria, fechaInicio, fechaFin, hora));
	}

	public void Altaespectaculopasemultiple(String titulo, String descripcion, Categoriaevento categoria,
			ArrayList<Sesiones> pasemultiple) {
		Factoriaconcreta factoria = new Factoriaconcreta();
		espectaculos.add(factoria.createEspectaculoPaseMultiple(titulo, descripcion, categoria, pasemultiple));
	}

	
	public void Cancelarespectaculotodos(String titulo) {
		for (Espectaculo e : espectaculos)
			if (e.getTitulo().equals(titulo))
				espectaculos.remove(e);
	}
	
	public void Cancelarespectaculosesion(int indexTitulo, int indexSesion){
		if(espectaculos.get(indexTitulo) instanceof Espectaculopuntual){
			Espectaculopuntual puntual = (Espectaculopuntual) espectaculos.get(indexTitulo);
			Sesiones sesion = new Sesiones();
			sesion.setFecha(null);
			sesion.setHora(null);
			puntual.setRepresentacion(sesion);
		}
		else if(espectaculos.get(indexTitulo) instanceof Espectaculopasemultiple){
			Espectaculopasemultiple multiple = (Espectaculopasemultiple) espectaculos.get(indexTitulo);
			multiple.getPasemultiple().remove(indexSesion);
		}
		else if(espectaculos.get(indexTitulo) instanceof Espectaculotemporada){
			Espectaculotemporada temporada = (Espectaculotemporada) espectaculos.get(indexTitulo);
			temporada.getTemporada().remove(indexSesion);
		}
	}
	
	//Funcion para mostrar sesiones y poder cancelarlas
	public String Mostrarsesiones() {
		StringBuffer buffer = new StringBuffer();
		for (Espectaculo e : espectaculos) {
			if (e instanceof Espectaculopuntual) {
				Espectaculopuntual puntual = new Espectaculopuntual();
				puntual = (Espectaculopuntual) e;
				buffer.append("Indice espectaculo: " + espectaculos.indexOf(e) + "\nTitulo: " + puntual.getTitulo() + "\n");
				buffer.append("\t" + puntual.getRepresentacion().getFecha() + " ");
				buffer.append(puntual.getRepresentacion().getHora() + "\n");

			} else if (e instanceof Espectaculopasemultiple) {
				Espectaculopasemultiple multiple = new Espectaculopasemultiple();
				multiple = (Espectaculopasemultiple) e;
				buffer.append("Indice espectaculo: " + espectaculos.indexOf(e) + "\nTitulo: " + multiple.getTitulo() + "\n");
				for (Sesiones s : multiple.getPasemultiple()) {
					buffer.append("Indice sesion: " + multiple.getPasemultiple().indexOf(s) + " - ");
					buffer.append(s.getFecha() + " ");
					buffer.append(s.getHora() + "\n");
				}
			} else if (e instanceof Espectaculotemporada) {
				Espectaculotemporada temporada = new Espectaculotemporada();
				temporada = (Espectaculotemporada) e;
				buffer.append("Indice espectaculo: " + espectaculos.indexOf(e) + "\nTitulo: " + temporada.getTitulo() + "\n");
				for (Sesiones s : temporada.getTemporada()) {
					buffer.append("Indice sesion: " + temporada.getTemporada().indexOf(s) + " - ");
					buffer.append(s.getFecha() + " ");
					buffer.append(s.getHora() + "\n");
				}
			}
		}
		
		return buffer.toString();

	}

	public void Actualizardatosespectaculos(String titulo, String descripcion, Sesiones sesion) {
		for (Espectaculo e : espectaculos) {
			if (e.getTitulo().equals(titulo) && e instanceof Espectaculopuntual) {
				((Espectaculopuntual) e).setDescripcion(descripcion);
				((Espectaculopuntual) e).setRepresentacion(sesion);
			}
		}
	}

	public void Actualizardatosespectaculo(String titulo, String descripcion, ArrayList<Sesiones> sesiones) {
		for (Espectaculo e : espectaculos) {
			if (e.getTitulo().equals(titulo) && e instanceof Espectaculopasemultiple) {
				((Espectaculopasemultiple) e).setDescripcion(descripcion);
				((Espectaculopasemultiple) e).setPasemultiple(sesiones);
			}
		}
	}

	public void Actualizardatosespectaculo(String titulo, String descripcion, LocalDate inicio, LocalDate fin, LocalTime hora) {
		for (Espectaculo e : espectaculos) {
			if (e.getTitulo().equals(titulo) && e instanceof Espectaculotemporada) {
				((Espectaculotemporada) e).setDescripcion(descripcion);
				((Espectaculotemporada) e).Calcularfecha(inicio, fin, hora);
			}
		}
	}

	public int ContabilizarVentaEntradas(String titulo, Sesiones sesion) {
		int entradas = -1;
		for(Espectaculo e : espectaculos){
			if(e.getTitulo().equals(titulo)){
				if(e instanceof Espectaculopuntual){
					Espectaculopuntual puntual = (Espectaculopuntual) e;
					if(puntual.getRepresentacion().getFecha().equals(sesion.getFecha()) && puntual.getRepresentacion().getHora().equals(sesion.getHora())){
						entradas = ((Espectaculopuntual) e).getRepresentacion().getEntradasVendidas();
					}
				}
				else if(e instanceof Espectaculopasemultiple){
					for(Sesiones s : ((Espectaculopasemultiple)e).getPasemultiple()){
						if(s.getFecha().equals(sesion.getFecha()) && s.getHora().equals(sesion.getHora())){
							entradas = s.getEntradasVendidas();
						}
					}
				}
				else if(e instanceof Espectaculotemporada){
					for(Sesiones s : ((Espectaculotemporada)e).getTemporada()){
						if(s.getFecha().equals(sesion.getFecha()) && s.getHora().equals(sesion.getHora())){
							entradas = s.getEntradasVendidas();
						}
					}
				}
			}
		}
		return entradas;
	}
	
	public int ConsultarEntradasDisponibles(String titulo, Sesiones sesion) {
		int disponibles = -1;
		for(Espectaculo e : espectaculos){
			if(e.getTitulo().equals(titulo)){
				if(e instanceof Espectaculopuntual){
					Espectaculopuntual puntual = (Espectaculopuntual) e;
					if(puntual.getRepresentacion().getFecha().equals(sesion.getFecha()) && puntual.getRepresentacion().getHora().equals(sesion.getHora())){
						disponibles = ((Espectaculopuntual) e).getRepresentacion().getTotalEntradas() - ((Espectaculopuntual) e).getRepresentacion().getEntradasVendidas();
					}
				}
				else if(e instanceof Espectaculopasemultiple){
					for(Sesiones s : ((Espectaculopasemultiple)e).getPasemultiple()){
						if(s.getFecha().equals(sesion.getFecha()) && s.getHora().equals(sesion.getHora())){
							disponibles = s.getTotalEntradas() - s.getEntradasVendidas();
						}
					}
				}
				else if(e instanceof Espectaculotemporada){
					for(Sesiones s : ((Espectaculotemporada)e).getTemporada()){
						if(s.getFecha().equals(sesion.getFecha()) && s.getHora().equals(sesion.getHora())){
							disponibles = s.getTotalEntradas() - s.getEntradasVendidas();
						}
					}
				}
			}
		}
		return disponibles;
	}

	public String Buscarespectaculo(String titulo) {
		StringBuffer buffer = new StringBuffer();
		for (Espectaculo e : espectaculos) {
			if (e.getTitulo().equals(titulo) && e instanceof Espectaculopuntual) {
				Espectaculopuntual puntual = (Espectaculopuntual) e;
				buffer.append("Titulo:" + puntual.getTitulo() + "\n");
				buffer.append("Descripcion:" + puntual.getDescripcion() + "\n");
				buffer.append("Categoria:" + puntual.getCategoria() + "\n");
				buffer.append("Sesiones: \n");
				buffer.append("\t" + puntual.getRepresentacion().getFecha() + " ");
				buffer.append(puntual.getRepresentacion().getHora() + "\n");
			}
			else if(e.getTitulo().equals(titulo) && e instanceof Espectaculopasemultiple){
				Espectaculopasemultiple multiple = (Espectaculopasemultiple) e;
				buffer.append("Titulo:" + multiple.getTitulo() + "\n");
				buffer.append("Descripcion:" + multiple.getDescripcion() + "\n");
				buffer.append("Categoria:" + multiple.getCategoria() + "\n");
				buffer.append("Sesiones: \n");
				for(Sesiones s : multiple.getPasemultiple()){
					buffer.append("\t" + s.getFecha() + " ");
					buffer.append(s.getHora() + "\n");
				}
			}
			else if(e.getTitulo().equals(titulo) && e instanceof Espectaculotemporada){
				Espectaculotemporada temporada = (Espectaculotemporada) e;
				buffer.append("Titulo:" + temporada.getTitulo() + "\n");
				buffer.append("Descripcion:" + temporada.getDescripcion() + "\n");
				buffer.append("Categoria:" + temporada.getCategoria() + "\n");
				buffer.append("Sesiones: \n");
				for(Sesiones s : temporada.getTemporada()){
					buffer.append("\t" + s.getFecha() + " ");
					buffer.append(s.getHora() + "\n");
				}
			}
		}
		return buffer.toString();
	}
	
	public String Buscarespectaculo(Categoriaevento categoria) {
		StringBuffer buffer = new StringBuffer();
		for (Espectaculo e : espectaculos) {
			if (e.getCategoria().equals(categoria) && e instanceof Espectaculopuntual) {
				Espectaculopuntual puntual = (Espectaculopuntual) e;
				buffer.append("Titulo:" + puntual.getTitulo() + "\n");
				buffer.append("Descripcion:" + puntual.getDescripcion() + "\n");
				buffer.append("Categoria:" + puntual.getCategoria() + "\n");
				buffer.append("Sesiones: \n");
				buffer.append("\t" + puntual.getRepresentacion().getFecha() + " ");
				buffer.append(puntual.getRepresentacion().getHora() + "\n");
			}
			else if(e.getCategoria().equals(categoria) && e instanceof Espectaculopasemultiple){
				Espectaculopasemultiple multiple = (Espectaculopasemultiple) e;
				buffer.append("Titulo:" + multiple.getTitulo() + "\n");
				buffer.append("Descripcion:" + multiple.getDescripcion() + "\n");
				buffer.append("Categoria:" + multiple.getCategoria() + "\n");
				buffer.append("Sesiones: \n");
				for(Sesiones s : multiple.getPasemultiple()){
					buffer.append("\t" + s.getFecha() + " ");
					buffer.append(s.getHora() + "\n");
				}
			}
			else if(e.getCategoria().equals(categoria) && e instanceof Espectaculotemporada){
				Espectaculotemporada temporada = (Espectaculotemporada) e;
				buffer.append("Titulo:" + temporada.getTitulo() + "\n");
				buffer.append("Descripcion:" + temporada.getDescripcion() + "\n");
				buffer.append("Categoria:" + temporada.getCategoria() + "\n");
				buffer.append("Sesiones: \n");
				for(Sesiones s : temporada.getTemporada()){
					buffer.append("\t" + s.getFecha() + " ");
					buffer.append(s.getHora() + "\n");
				}
			}
		}
		return buffer.toString();
	}

	public String BuscarEspectaculosEntradasDisponibles() {
		StringBuffer buffer = new StringBuffer();
		int imprimido;
		for (Espectaculo e : espectaculos) {
			if (e instanceof Espectaculopuntual) {
				Espectaculopuntual puntual = (Espectaculopuntual) e;
				if(ConsultarEntradasDisponibles(puntual.getTitulo(), puntual.getRepresentacion()) > 0){
					buffer.append("Titulo:" + puntual.getTitulo() + "\n");
					buffer.append("Descripcion:" + puntual.getDescripcion() + "\n");
					buffer.append("Categoria:" + puntual.getCategoria() + "\n");
					buffer.append("Sesiones: \n");
					buffer.append("\t" + puntual.getRepresentacion().getFecha() + " ");
					buffer.append(puntual.getRepresentacion().getHora() + "\n");
				}
			}
			else if(e instanceof Espectaculopasemultiple){
				imprimido = 0;
				Espectaculopasemultiple multiple = (Espectaculopasemultiple) e;
				for(Sesiones s : multiple.getPasemultiple()){
					if(ConsultarEntradasDisponibles(multiple.getTitulo(), s) > 0){
						if(imprimido == 0){
							buffer.append("Titulo:" + multiple.getTitulo() + "\n");
							buffer.append("Descripcion:" + multiple.getDescripcion() + "\n");
							buffer.append("Categoria:" + multiple.getCategoria() + "\n");
							buffer.append("Sesiones: \n");
							imprimido += 1;
						}
						buffer.append("\t" + s.getFecha() + " ");
						buffer.append(s.getHora() + "\n");
					}
				}
			}
			else if(e instanceof Espectaculotemporada){
				imprimido = 0;
				Espectaculotemporada temporada = (Espectaculotemporada) e;
				for(Sesiones s : temporada.getTemporada()){
					if(ConsultarEntradasDisponibles(temporada.getTitulo(), s) > 0){
						if(imprimido == 0){
							buffer.append("Titulo:" + temporada.getTitulo() + "\n");
							buffer.append("Descripcion:" + temporada.getDescripcion() + "\n");
							buffer.append("Categoria:" + temporada.getCategoria() + "\n");
							buffer.append("Sesiones: \n");
							imprimido += 1;
						}
						buffer.append("\t" + s.getFecha() + " ");
						buffer.append(s.getHora() + "\n");
					}
				}
			}
		}
		return buffer.toString();
	}
	
	public String BuscarEspectaculosEntradasDisponibles(Categoriaevento categoria) {
		StringBuffer buffer = new StringBuffer();
		int imprimido;
		for (Espectaculo e : espectaculos) {
			if(e.getCategoria().equals(categoria)){
				if (e instanceof Espectaculopuntual) {
					Espectaculopuntual puntual = (Espectaculopuntual) e;
					if(ConsultarEntradasDisponibles(puntual.getTitulo(), puntual.getRepresentacion()) > 0){
						buffer.append("Titulo:" + puntual.getTitulo() + "\n");
						buffer.append("Descripcion:" + puntual.getDescripcion() + "\n");
						buffer.append("Categoria:" + puntual.getCategoria() + "\n");
						buffer.append("Sesiones: \n");
						buffer.append("\t" + puntual.getRepresentacion().getFecha() + " ");
						buffer.append(puntual.getRepresentacion().getHora() + "\n");
					}
				}
				else if(e instanceof Espectaculopasemultiple){
					imprimido = 0;
					Espectaculopasemultiple multiple = (Espectaculopasemultiple) e;
					for(Sesiones s : multiple.getPasemultiple()){
						if(ConsultarEntradasDisponibles(multiple.getTitulo(), s) > 0){
							if(imprimido == 0){
								buffer.append("Titulo:" + multiple.getTitulo() + "\n");
								buffer.append("Descripcion:" + multiple.getDescripcion() + "\n");
								buffer.append("Categoria:" + multiple.getCategoria() + "\n");
								buffer.append("Sesiones: \n");
								imprimido += 1;
							}
							buffer.append("\t" + s.getFecha() + " ");
							buffer.append(s.getHora() + "\n");
						}
					}
				}
				else if(e instanceof Espectaculotemporada){
					imprimido = 0;
					Espectaculotemporada temporada = (Espectaculotemporada) e;
					for(Sesiones s : temporada.getTemporada()){
						if(ConsultarEntradasDisponibles(temporada.getTitulo(), s) > 0){
							if(imprimido == 0){
								buffer.append("Titulo:" + temporada.getTitulo() + "\n");
								buffer.append("Descripcion:" + temporada.getDescripcion() + "\n");
								buffer.append("Categoria:" + temporada.getCategoria() + "\n");
								buffer.append("Sesiones: \n");
								imprimido += 1;
							}
							buffer.append("\t" + s.getFecha() + " ");
							buffer.append(s.getHora() + "\n");
						}
					}
				}
			}
		}
		return buffer.toString();
	}

	// Pubicar critica sobre espectaculo ya celebrado
	public void PublicarCritica(int indexTitulo, int indexSesion, String titulo, int puntuacion, String resena, String correo) {
		Gestorcriticas gestorcriticas = Gestorcriticas.getInstance();
		if(espectaculos.get(indexTitulo) instanceof Espectaculopuntual){
			Espectaculopuntual puntual = (Espectaculopuntual) espectaculos.get(indexTitulo);
			if(puntual.getRepresentacion().getFecha().compareTo(LocalDate.now()) < 0){
				gestorcriticas.creaCritica(titulo, puntuacion, resena, correo, puntual.getTitulo());
			}
			else{
				System.out.println("La sesion seleccionada del espectaculo aun no se ha celebrado. No puede valorarlo.");
			}
		}
		else if(espectaculos.get(indexTitulo) instanceof Espectaculopasemultiple){
			Espectaculopasemultiple multiple = (Espectaculopasemultiple) espectaculos.get(indexTitulo);
			if(multiple.getPasemultiple().get(indexSesion).getFecha().compareTo(LocalDate.now()) < 0){
				gestorcriticas.creaCritica(titulo, puntuacion, resena, correo, multiple.getTitulo());
			}
			else{
				System.out.println("La sesion seleccionada del espectaculo aun no se ha celebrado. No puede valorarlo.");
			}
		}
		else if(espectaculos.get(indexTitulo) instanceof Espectaculotemporada){
			Espectaculotemporada temporada = (Espectaculotemporada) espectaculos.get(indexTitulo);
			if(temporada.getTemporada().get(indexSesion).getFecha().compareTo(LocalDate.now()) < 0){
				gestorcriticas.creaCritica(titulo, puntuacion, resena, correo, temporada.getTitulo());
			}
			else{
				System.out.println("La sesion seleccionada del espectaculo aun no se ha celebrado. No puede valorarlo.");
			}
		}
	}

	public String Consultarcriticas(String titulo) {
		Gestorcriticas criticas = Gestorcriticas.getInstance();
		return criticas.getCriticasTitulo(titulo);
	}

	public void EliminarCritica(int index, String correo) {
		Gestorcriticas gestorcriticas = Gestorcriticas.getInstance();
		if (gestorcriticas.comprobarPropietario(correo)) {
			gestorcriticas.borraCritica(index);
		} else {
			System.out.println("No es el propietario de esta critica, no puede borrarla.");
		}
	}

	public void ValorarCritica(int index, String correo, int puntuacion) {
		Gestorcriticas gestorcriticas = Gestorcriticas.getInstance();
		gestorcriticas.votaCritica(index, correo, puntuacion);
	}

	/**
	 * 
	 * @param fileEspectaculos
	 * @author Noelia Hinojosa Sanchez
	 */
	public void cargarFichero(String fileEspectaculos) {
		File f = new File(fileEspectaculos);
		FileReader fr = null;
		BufferedReader buffer = null;

		String lineaFichero;

		try {
			if (!f.exists()) {
				System.out.println("El fichero espectaculos.txt no existe, se va a crear.");
				f.createNewFile();
			} else {
				fr = new FileReader(f);
				buffer = new BufferedReader(fr);

				while ((lineaFichero = buffer.readLine()) != null) {
					// �Como se van a guardar los datos?
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null)
					fr.close();
				if (buffer != null) {
					buffer.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param fileEspectaculos
	 * @author Noelia Hinojosa Sanchez
	 */
	public void guardarFichero(String fileEspectaculos) {
		FileWriter f = null;
		PrintWriter buffer = null;

		try {
			f = new FileWriter(fileEspectaculos);
			buffer = new PrintWriter(f);

			for (Espectaculo e : espectaculos) {
				// Guardar informacion �Como se van a guardar los datos?
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (f != null)
					f.close();
				if (buffer != null) {
					buffer.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}