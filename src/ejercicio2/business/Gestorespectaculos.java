package ejercicio2.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

import ejercicio2.data.Sesiones;
import ejercicio2.business.Gestorcriticas;
import ejercicio2.data.Categoriaevento;
import ejercicio2.data.Espectaculo;
import ejercicio2.data.Espectaculopasemultiple;
import ejercicio2.data.Espectaculopuntual;
import ejercicio2.data.Espectaculotemporada;
import ejercicio2.data.Factoriaconcreta;

/**
 * Clase Gestorespectaculos Esta clase tiene los metodos necesarios para
 * gestionar los espectaculos y las criticas de los espectaculos.
 * 
 * @author Noelia Hinojosa Sanchez
 * @author Rafael Piqueras Espinar
 * @version 1.0, 04/10/2021
 */
public class Gestorespectaculos {
	/**
	 * Instancia unica del gestor de espectaculos.
	 */
	private static Gestorespectaculos instance = null;

	private ArrayList<Espectaculo> espectaculos;

	/**
	 * Constructor del gestor de espectaculos. Es privado para que solo haya un
	 * unico gestor.
	 */
	private Gestorespectaculos() {
		espectaculos = new ArrayList<Espectaculo>();
	}

	/**
	 * Este metodo controla que haya unicamente un gestor de espectaculos.
	 * 
	 * @return El gestor de espectaculos.
	 * @author Noelia Hinojosa Sanchez
	 */
	public static Gestorespectaculos getInstance() {
		if (instance == null) {
			instance = new Gestorespectaculos();
		}
		return instance;
	}

	/**
	 * Este metodo da de alta a los espectaculos puntuales.
	 * 
	 * @param titulo
	 *            Titulo del espectaculo.
	 * @param descripcion
	 *            Descripcion del espectaculo.
	 * @param categoria
	 *            Categoria del espectaculo.
	 * @param sesion
	 *            Fecha y Hora de la sesion del espectaculo.
	 * @return Nada.
	 */
	public void Altaespectaculopasepuntual(String titulo, String descripcion, Categoriaevento categoria,
			Sesiones sesion) {
		Factoriaconcreta factoria = new Factoriaconcreta();
		espectaculos.add(factoria.createEspectaculoPuntual(titulo, descripcion, categoria, sesion));
	}

	/**
	 * Este metodo da de alta a los espectaculos de temporada.
	 * 
	 * @param titulo
	 *            Titulo del espectaculo.
	 * @param descripcion
	 *            Descripcion del espectaculo.
	 * @param categoria
	 *            Categoria del espectaculo.
	 * @param fechaInicio
	 *            Fecha de inicio de la temporada.
	 * @param fechaFin
	 *            Fecha de fin de la temporada.
	 * @param hora
	 *            Hora de representacion
	 * @return Nada.
	 */
	public void Altaespectaculotemporada(String titulo, String descripcion, Categoriaevento categoria,
			LocalDate fechaInicio, LocalDate fechaFin, LocalTime hora) {
		Factoriaconcreta factoria = new Factoriaconcreta();
		espectaculos
				.add(factoria.createEspectaculoTemporada(titulo, descripcion, categoria, fechaInicio, fechaFin, hora));
	}

	/**
	 * Este metodo da de alta a los espectaculos de pase multiple.
	 * 
	 * @param titulo
	 *            Titulo del espectaculo.
	 * @param descripcion
	 *            Descripcion del espectaculo.
	 * @param categoria
	 *            Categoria del espectaculo.
	 * @param pasemultiple
	 *            Array de las sesiones del espectaculo.
	 * @return Nada.
	 */
	public void Altaespectaculopasemultiple(String titulo, String descripcion, Categoriaevento categoria,
			ArrayList<Sesiones> pasemultiple) {
		Factoriaconcreta factoria = new Factoriaconcreta();
		espectaculos.add(factoria.createEspectaculoPaseMultiple(titulo, descripcion, categoria, pasemultiple));
	}

	/**
	 * Este metodo cancela un espectaculo.
	 * 
	 * @param index
	 *            Indice del espectaculo a cancelar.
	 * @return Nada.
	 */
	public void Cancelarespectaculotodos(int index) {
		espectaculos.remove(index);
	}

	/**
	 * Este metodo cancela 1 sesion de un espectaculo.
	 * 
	 * @param indexTitulo
	 *            Indice del espectaculo.
	 * @param indexSesion
	 *            Indice de la sesion a cancelar.
	 * @return Nada.
	 */
	public void Cancelarespectaculosesion(int indexTitulo, int indexSesion) {
		if (espectaculos.get(indexTitulo) instanceof Espectaculopuntual) {
			espectaculos.remove(indexTitulo);
		} else if (espectaculos.get(indexTitulo) instanceof Espectaculopasemultiple) {
			Espectaculopasemultiple multiple = (Espectaculopasemultiple) espectaculos.get(indexTitulo);
			multiple.getPasemultiple().remove(indexSesion);
		} else if (espectaculos.get(indexTitulo) instanceof Espectaculotemporada) {
			Espectaculotemporada temporada = (Espectaculotemporada) espectaculos.get(indexTitulo);
			temporada.getTemporada().remove(indexSesion);
		}
	}

	/**
	 * Este metodo muestra todas las sesiones de todos los espectaculos.
	 * 
	 * @return String formateado con todos los datos.
	 */
	public String Mostrarsesiones() {
		StringBuffer buffer = new StringBuffer();
		for (Espectaculo e : espectaculos) {
			if (e instanceof Espectaculopuntual) {
				Espectaculopuntual puntual = new Espectaculopuntual();
				puntual = (Espectaculopuntual) e;
				buffer.append(
						"Indice espectaculo: " + espectaculos.indexOf(e) + "\nTitulo: " + puntual.getTitulo() + "\n");
				buffer.append("Descripcion: " + puntual.getDescripcion() + "\n");
				buffer.append("\tIndice sesion: 1 - " + puntual.getRepresentacion().getFecha() + " ");
				buffer.append(puntual.getRepresentacion().getHora() + "\n");

			} else if (e instanceof Espectaculopasemultiple) {
				Espectaculopasemultiple multiple = new Espectaculopasemultiple();
				multiple = (Espectaculopasemultiple) e;
				buffer.append(
						"Indice espectaculo: " + espectaculos.indexOf(e) + "\nTitulo: " + multiple.getTitulo() + "\n");
				buffer.append("Descripcion: " + multiple.getDescripcion() + "\n");
				for (Sesiones s : multiple.getPasemultiple()) {
					buffer.append("\tIndice sesion: " + multiple.getPasemultiple().indexOf(s) + " - ");
					buffer.append(s.getFecha() + " ");
					buffer.append(s.getHora() + "\n");
				}
			} else if (e instanceof Espectaculotemporada) {
				Espectaculotemporada temporada = new Espectaculotemporada();
				temporada = (Espectaculotemporada) e;
				buffer.append(
						"Indice espectaculo: " + espectaculos.indexOf(e) + "\nTitulo: " + temporada.getTitulo() + "\n");
				buffer.append("Descripcion: " + temporada.getDescripcion() + "\n");
				for (Sesiones s : temporada.getTemporada()) {
					buffer.append("\tIndice sesion: " + temporada.getTemporada().indexOf(s) + " - ");
					buffer.append(s.getFecha() + " ");
					buffer.append(s.getHora() + "\n");
				}
			}
		}

		return buffer.toString();

	}

	/**
	 * Este metodo muestra todas las sesiones de un espectaculo de pase
	 * multiple.
	 * 
	 * @param index
	 *            Indice del espectaculo.
	 * @return String formateado con los datos a mostrar.
	 */
	public String MostrarSesionesPaseMultiple(int index) {
		StringBuffer buffer = new StringBuffer();
		if (espectaculos.get(index) instanceof Espectaculopasemultiple) {
			Espectaculopasemultiple multiple = (Espectaculopasemultiple) espectaculos.get(index);
			buffer.append("Indice espectaculo: " + index + "\nTitulo: " + multiple.getTitulo() + "\n");
			buffer.append("Descripcion: " + multiple.getDescripcion() + "\n");
			for (Sesiones s : multiple.getPasemultiple()) {
				buffer.append("\nIndice sesion: " + multiple.getPasemultiple().indexOf(s) + " - ");
				buffer.append(s.getFecha() + " ");
				buffer.append(s.getHora() + "\n");
			}
		}

		return buffer.toString();

	}

	/**
	 * Este metodo actualiza la descripcion o sesiones de los espectaculos
	 * puntuales.
	 * 
	 * @param index
	 *            Indice del espectaculo.
	 * @param descripcion
	 *            Descripcion nueva del espectaculo.
	 * @param sesion
	 *            Sesion nueva del espectaculo.
	 * @return Nada.
	 */
	public void Actualizardatosespectaculos(int index, String descripcion, Sesiones sesion) {
		if (espectaculos.get(index) instanceof Espectaculopuntual) {
			Espectaculopuntual puntual = (Espectaculopuntual) espectaculos.get(index);
			if (descripcion.equals("")) {
				puntual.setRepresentacion(sesion);
			} else if (sesion.getFecha() == null) {
				puntual.setDescripcion(descripcion);
			} else {
				puntual.setDescripcion(descripcion);
				puntual.setRepresentacion(sesion);
			}
		}
	}

	/**
	 * Este metodo actualiza la descripcion o sesiones de los espectaculos de
	 * pase multiple.
	 * 
	 * @param index
	 *            Indice del espectaculo.
	 * @param descripcion
	 *            Descripcion nueva del espectaculo.
	 * @param sesiones
	 *            Array con las sesiones nuevas del espectaculo.
	 * @return Nada.
	 */
	public void Actualizardatosespectaculo(int index, String descripcion, ArrayList<Sesiones> sesiones) {
		if (espectaculos.get(index) instanceof Espectaculopasemultiple) {
			Espectaculopasemultiple multiple = (Espectaculopasemultiple) espectaculos.get(index);
			if (descripcion.equals("")) {
				multiple.setPasemultiple(sesiones);
			} else if (sesiones.isEmpty()) {
				multiple.setDescripcion(descripcion);
			} else {
				multiple.setDescripcion(descripcion);
				multiple.setPasemultiple(sesiones);
			}
		}
	}

	/**
	 * Este metodo actualiza la descripcion o sesiones de los espectaculos de
	 * temporada.
	 * 
	 * @param index
	 *            Indice del espectaculo.
	 * @param descripcion
	 *            Descripcion nueva del espectaculo.
	 * @param inicio
	 *            Fecha de inicio nueva de la temporada.
	 * @param fin
	 *            Fecha de fin nueva de la temporada.
	 * @param hora
	 *            Hora de representacion nueva de la temporada.
	 * @return Nada.
	 */
	public void Actualizardatosespectaculo(int index, String descripcion, LocalDate inicio, LocalDate fin,
			LocalTime hora) {
		if (espectaculos.get(index) instanceof Espectaculotemporada) {
			Espectaculotemporada temporada = (Espectaculotemporada) espectaculos.get(index);
			if (descripcion.equals("")) {
				temporada.Calcularfecha(inicio, fin, hora);
			} else if (inicio.equals(LocalDate.parse("1000-01-01"))) {
				temporada.setDescripcion(descripcion);
			} else {
				temporada.setDescripcion(descripcion);
				temporada.Calcularfecha(inicio, fin, hora);
			}
		}
	}

	/**
	 * Este metodo cuanta las entradas vendidas para una sesion de un
	 * espectaculo.
	 * 
	 * @param index
	 *            Indice del espectaculo.
	 * @param indexSesion
	 *            Indice de la sesion.
	 * @return Numero de entradas vendidas.
	 */
	public int ContabilizarVentaEntradas(int index, int indexSesion) {
		int entradas = -1;
		if (espectaculos.get(index) instanceof Espectaculopuntual) {
			Espectaculopuntual puntual = (Espectaculopuntual) espectaculos.get(index);
			entradas = puntual.getRepresentacion().getEntradasVendidas();
		} else if (espectaculos.get(index) instanceof Espectaculopasemultiple) {
			Espectaculopasemultiple multiple = (Espectaculopasemultiple) espectaculos.get(index);
			entradas = multiple.getPasemultiple().get(indexSesion).getEntradasVendidas();
		} else if (espectaculos.get(index) instanceof Espectaculotemporada) {
			Espectaculotemporada temporada = (Espectaculotemporada) espectaculos.get(index);
			entradas = temporada.getTemporada().get(indexSesion).getEntradasVendidas();
		}
		return entradas;
	}

	/**
	 * Este metodo consulta el numero de entradas disponibles para una sesion de
	 * un espectaculo.
	 * 
	 * @param index
	 *            Indice del espectaculo.
	 * @param indexSesion
	 *            Indice de la sesion.
	 * @return Numero disponible de entradas.
	 */
	public int ConsultarEntradasDisponibles(int index, int indexSesion) {
		int disponibles = -1;
		if (espectaculos.get(index) instanceof Espectaculopuntual) {
			Espectaculopuntual puntual = (Espectaculopuntual) espectaculos.get(index);
			disponibles = puntual.getRepresentacion().getTotalEntradas()
					- puntual.getRepresentacion().getEntradasVendidas();
		} else if (espectaculos.get(index) instanceof Espectaculopasemultiple) {
			Espectaculopasemultiple multiple = (Espectaculopasemultiple) espectaculos.get(index);
			disponibles = multiple.getPasemultiple().get(indexSesion).getTotalEntradas()
					- multiple.getPasemultiple().get(indexSesion).getEntradasVendidas();
		} else if (espectaculos.get(index) instanceof Espectaculotemporada) {
			Espectaculotemporada temporada = (Espectaculotemporada) espectaculos.get(index);
			disponibles = temporada.getTemporada().get(indexSesion).getTotalEntradas()
					- temporada.getTemporada().get(indexSesion).getEntradasVendidas();
		}
		return disponibles;
	}

	/**
	 * Este metodo busca un espectaculo indicando su titulo.
	 * 
	 * @param titulo
	 *            Titulo del espectaculo.
	 * @return String formateado con los datos del espectaculo.
	 */
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
			} else if (e.getTitulo().equals(titulo) && e instanceof Espectaculopasemultiple) {
				Espectaculopasemultiple multiple = (Espectaculopasemultiple) e;
				buffer.append("Titulo:" + multiple.getTitulo() + "\n");
				buffer.append("Descripcion:" + multiple.getDescripcion() + "\n");
				buffer.append("Categoria:" + multiple.getCategoria() + "\n");
				buffer.append("Sesiones: \n");
				for (Sesiones s : multiple.getPasemultiple()) {
					buffer.append("\t" + s.getFecha() + " ");
					buffer.append(s.getHora() + "\n");
				}
			} else if (e.getTitulo().equals(titulo) && e instanceof Espectaculotemporada) {
				Espectaculotemporada temporada = (Espectaculotemporada) e;
				buffer.append("Titulo:" + temporada.getTitulo() + "\n");
				buffer.append("Descripcion:" + temporada.getDescripcion() + "\n");
				buffer.append("Categoria:" + temporada.getCategoria() + "\n");
				buffer.append("Sesiones: \n");
				for (Sesiones s : temporada.getTemporada()) {
					buffer.append("\t" + s.getFecha() + " ");
					buffer.append(s.getHora() + "\n");
				}
			}
		}
		return buffer.toString();
	}

	/**
	 * Este metodo busca espectaculos segun la categoria indicada.
	 * 
	 * @param categoria
	 *            Categoria del espectaculo.
	 * @return String formateado con los datos de los espectaculos.
	 */
	public String Buscarespectaculo(Categoriaevento categoria) {
		StringBuffer buffer = new StringBuffer();
		for (Espectaculo e : espectaculos) {
			if (e.getCategoria() == categoria && e instanceof Espectaculopuntual) {
				Espectaculopuntual puntual = (Espectaculopuntual) e;
				buffer.append("Titulo:" + puntual.getTitulo() + "\n");
				buffer.append("Descripcion:" + puntual.getDescripcion() + "\n");
				buffer.append("Categoria:" + puntual.getCategoria() + "\n");
				buffer.append("Sesiones: \n");
				buffer.append("\t" + puntual.getRepresentacion().getFecha() + " ");
				buffer.append(puntual.getRepresentacion().getHora() + "\n");
			} else if (e.getCategoria().equals(categoria) && e instanceof Espectaculopasemultiple) {
				Espectaculopasemultiple multiple = (Espectaculopasemultiple) e;
				buffer.append("Titulo:" + multiple.getTitulo() + "\n");
				buffer.append("Descripcion:" + multiple.getDescripcion() + "\n");
				buffer.append("Categoria:" + multiple.getCategoria() + "\n");
				buffer.append("Sesiones: \n");
				for (Sesiones s : multiple.getPasemultiple()) {
					buffer.append("\t" + s.getFecha() + " ");
					buffer.append(s.getHora() + "\n");
				}
			} else if (e.getCategoria().equals(categoria) && e instanceof Espectaculotemporada) {
				Espectaculotemporada temporada = (Espectaculotemporada) e;
				buffer.append("Titulo:" + temporada.getTitulo() + "\n");
				buffer.append("Descripcion:" + temporada.getDescripcion() + "\n");
				buffer.append("Categoria:" + temporada.getCategoria() + "\n");
				buffer.append("Sesiones: \n");
				for (Sesiones s : temporada.getTemporada()) {
					buffer.append("\t" + s.getFecha() + " ");
					buffer.append(s.getHora() + "\n");
				}
			}
		}
		return buffer.toString();
	}

	/**
	 * Este metodo busca todos los espectaculos con entradas disponibles.
	 * 
	 * @return String formateado con los datos de los espectaculos.
	 */
	public String BuscarEspectaculosEntradasDisponibles() {
		StringBuffer buffer = new StringBuffer();
		int imprimido;
		for (Espectaculo e : espectaculos) {
			if (e instanceof Espectaculopuntual) {
				Espectaculopuntual puntual = (Espectaculopuntual) e;
				if (ConsultarEntradasDisponibles(espectaculos.indexOf(e), 1) > 0) {
					buffer.append("Titulo:" + puntual.getTitulo() + "\n");
					buffer.append("Descripcion:" + puntual.getDescripcion() + "\n");
					buffer.append("Categoria:" + puntual.getCategoria() + "\n");
					buffer.append("Sesiones: \n");
					buffer.append("\t" + puntual.getRepresentacion().getFecha() + " ");
					buffer.append(puntual.getRepresentacion().getHora() + "\n");
				}
			} else if (e instanceof Espectaculopasemultiple) {
				imprimido = 0;
				Espectaculopasemultiple multiple = (Espectaculopasemultiple) e;
				for (Sesiones s : multiple.getPasemultiple()) {
					if (ConsultarEntradasDisponibles(espectaculos.indexOf(e),
							multiple.getPasemultiple().indexOf(s)) > 0) {
						if (imprimido == 0) {
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
			} else if (e instanceof Espectaculotemporada) {
				imprimido = 0;
				Espectaculotemporada temporada = (Espectaculotemporada) e;
				for (Sesiones s : temporada.getTemporada()) {
					if (ConsultarEntradasDisponibles(espectaculos.indexOf(e),
							temporada.getTemporada().indexOf(s)) > 0) {
						if (imprimido == 0) {
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

	/**
	 * Este metodo busca espectaculos con entradas disponibles segun la
	 * categoria.
	 * 
	 * @param categoria
	 *            Categoria de los espectaculos.
	 * @return String formateado con los datos de los espectaculos.
	 */
	public String BuscarEspectaculosEntradasDisponibles(Categoriaevento categoria) {
		StringBuffer buffer = new StringBuffer();
		int imprimido;
		for (Espectaculo e : espectaculos) {
			if (e.getCategoria().equals(categoria)) {
				if (e instanceof Espectaculopuntual) {
					Espectaculopuntual puntual = (Espectaculopuntual) e;
					if (ConsultarEntradasDisponibles(espectaculos.indexOf(e), 1) > 0) {
						buffer.append("Titulo:" + puntual.getTitulo() + "\n");
						buffer.append("Descripcion:" + puntual.getDescripcion() + "\n");
						buffer.append("Categoria:" + puntual.getCategoria() + "\n");
						buffer.append("Sesiones: \n");
						buffer.append("\t" + puntual.getRepresentacion().getFecha() + " ");
						buffer.append(puntual.getRepresentacion().getHora() + "\n");
					}
				} else if (e instanceof Espectaculopasemultiple) {
					imprimido = 0;
					Espectaculopasemultiple multiple = (Espectaculopasemultiple) e;
					for (Sesiones s : multiple.getPasemultiple()) {
						if (ConsultarEntradasDisponibles(espectaculos.indexOf(e),
								multiple.getPasemultiple().indexOf(s)) > 0) {
							if (imprimido == 0) {
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
				} else if (e instanceof Espectaculotemporada) {
					imprimido = 0;
					Espectaculotemporada temporada = (Espectaculotemporada) e;
					for (Sesiones s : temporada.getTemporada()) {
						if (ConsultarEntradasDisponibles(espectaculos.indexOf(e),
								temporada.getTemporada().indexOf(s)) > 0) {
							if (imprimido == 0) {
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

	/**
	 * Este metodo publica una critica de un espectaculo ya celebrado.
	 * 
	 * @param indexTitulo
	 *            Indice del espectaculo.
	 * @param indexSesion
	 *            Indice de la sesion del espectaculo.
	 * @param titulo
	 *            Titulo de la critica.
	 * @param puntuacion
	 *            Puntuacion del espectaculo.
	 * @param resena
	 *            Resena del espectaculo.
	 * @param correo
	 *            Correo electronico del propietario de la critica.
	 * @return Nada.
	 */
	public void PublicarCritica(int indexTitulo, int indexSesion, String titulo, int puntuacion, String resena,
			String correo) {
		Gestorcriticas gestorcriticas = Gestorcriticas.getInstance();
		if (espectaculos.get(indexTitulo) instanceof Espectaculopuntual) {
			Espectaculopuntual puntual = (Espectaculopuntual) espectaculos.get(indexTitulo);
			if (puntual.getRepresentacion().getFecha().compareTo(LocalDate.now()) < 0) {
				gestorcriticas.creaCritica(titulo, puntuacion, resena, correo, puntual.getTitulo());
			} else {
				System.out
						.println("La sesion seleccionada del espectaculo aun no se ha celebrado. No puede valorarlo.");
			}
		} else if (espectaculos.get(indexTitulo) instanceof Espectaculopasemultiple) {
			Espectaculopasemultiple multiple = (Espectaculopasemultiple) espectaculos.get(indexTitulo);
			if (multiple.getPasemultiple().get(indexSesion).getFecha().compareTo(LocalDate.now()) < 0) {
				gestorcriticas.creaCritica(titulo, puntuacion, resena, correo, multiple.getTitulo());
			} else {
				System.out
						.println("La sesion seleccionada del espectaculo aun no se ha celebrado. No puede valorarlo.");
			}
		} else if (espectaculos.get(indexTitulo) instanceof Espectaculotemporada) {
			Espectaculotemporada temporada = (Espectaculotemporada) espectaculos.get(indexTitulo);
			if (temporada.getTemporada().get(indexSesion).getFecha().compareTo(LocalDate.now()) < 0) {
				gestorcriticas.creaCritica(titulo, puntuacion, resena, correo, temporada.getTitulo());
			} else {
				System.out
						.println("La sesion seleccionada del espectaculo aun no se ha celebrado. No puede valorarlo.");
			}
		}
	}

	/**
	 * Este metodo consulta todas las criticas de un espectaculo segun su
	 * titulo.
	 * 
	 * @param titulo
	 *            Titulo del espectaculo.
	 * @return String formateado con los datos del espectaculo.
	 */
	public String Consultarcriticas(String titulo) {
		Gestorcriticas criticas = Gestorcriticas.getInstance();
		return criticas.getCriticasTitulo(titulo);
	}

	/**
	 * Este metodo elimina una critica de un espectaculo.
	 * 
	 * @param index
	 *            Indice del espectaculo.
	 * @param correo
	 *            Correo del que desea borrar la critica.
	 * @return Nada.
	 */
	public void EliminarCritica(int index, String correo) {
		Gestorcriticas gestorcriticas = Gestorcriticas.getInstance();
		if (gestorcriticas.comprobarPropietario(correo)) {
			gestorcriticas.borraCritica(index);
		} else {
			System.out.println("No es el propietario de esta critica, no puede borrarla.");
		}
	}

	/**
	 * Este metodo valora la critica de un espectaculo de otro usuario.
	 * 
	 * @param index
	 *            Indice del espectaculo.
	 * @param correo
	 *            Correo del que desea valorar la critica.
	 * @param puntuacion
	 *            Puntuacion de la critica.
	 * @return Nada.
	 */
	public void ValorarCritica(int index, String correo, int puntuacion) {
		Gestorcriticas gestorcriticas = Gestorcriticas.getInstance();
		gestorcriticas.votaCritica(index, correo, puntuacion);
	}

	/**
	 * Este metodo carga los ficheros de los distintos tipos de espectaculos.
	 * 
	 * @param fileEspectaculosPuntuales
	 *            Ruta espectaculos puntuales.
	 * @param fileEspectaculosPaseMultiple
	 *            Ruta espectaculos pase multiple.
	 * @param fileEspectaculosTemporada
	 *            Ruta espectaculos de temporada.
	 */
	public void cargarFichero(String fileEspectaculosPuntuales, String fileEspectaculosPaseMultiple,
			String fileEspectaculosTemporada) {
		File fp = new File(fileEspectaculosPuntuales);
		File fpm = new File(fileEspectaculosPaseMultiple);
		File ft = new File(fileEspectaculosTemporada);
		FileReader frp = null, frpm = null, frt = null;
		BufferedReader bufferp = null, bufferpm = null, buffert = null;
		String lineaFichero = "";
		String[] lineaCampos, lineaSesiones, sesionCampos;
		Sesiones sesion = new Sesiones();
		ArrayList<Sesiones> sesiones = new ArrayList<Sesiones>();

		try {
			if (!fp.exists()) {
				System.out.println("El fichero espectaculospuntuales.txt no existe, se va a crear.");
				fp.createNewFile();
			} else {
				frp = new FileReader(fp);
				bufferp = new BufferedReader(frp);

				Espectaculopuntual puntual;
				while ((lineaFichero = bufferp.readLine()) != null) {
					puntual = new Espectaculopuntual();

					lineaCampos = lineaFichero.split(";");

					puntual.setTitulo(lineaCampos[0]);
					puntual.setDescripcion(lineaCampos[1]);
					if (lineaCampos[2].equals("MONOLOGO")) {
						puntual.setCategoriaevento(Categoriaevento.MONOLOGO);
					} else if (lineaCampos[2].equals("CONCIERTO")) {
						puntual.setCategoriaevento(Categoriaevento.CONCIERTO);
					} else if (lineaCampos[2].equals("OBRADETEATRO")) {
						puntual.setCategoriaevento(Categoriaevento.OBRADETEATRO);
					}
					sesion.setFecha(LocalDate.parse(lineaCampos[3]));
					sesion.setHora(LocalTime.parse(lineaCampos[4]));
					puntual.setRepresentacion(sesion);

					espectaculos.add(puntual);
				}
			}

			if (!fpm.exists()) {
				System.out.println("El fichero espectaculospasemultiple.txt no existe, se va a crear.");
				fpm.createNewFile();
			} else {
				frpm = new FileReader(fpm);
				bufferpm = new BufferedReader(frpm);

				Espectaculopasemultiple multiple;
				while ((lineaFichero = bufferpm.readLine()) != null) {
					multiple = new Espectaculopasemultiple();

					lineaCampos = lineaFichero.split(";");

					multiple.setTitulo(lineaCampos[0]);
					multiple.setDescripcion(lineaCampos[1]);
					if (lineaCampos[2].equals("MONOLOGO")) {
						multiple.setCategoriaevento(Categoriaevento.MONOLOGO);
					} else if (lineaCampos[2].equals("CONCIERTO")) {
						multiple.setCategoriaevento(Categoriaevento.CONCIERTO);
					} else if (lineaCampos[2].equals("OBRADETEATRO")) {
						multiple.setCategoriaevento(Categoriaevento.OBRADETEATRO);
					}

					lineaSesiones = lineaCampos[3].split("/");
					for (int i = 0; i < lineaSesiones.length; i++) {
						sesionCampos = lineaSesiones[i].split(" ");

						sesion.setFecha(LocalDate.parse(sesionCampos[0]));
						sesion.setHora(LocalTime.parse(sesionCampos[1]));
						sesiones.add(sesion);
					}
					multiple.setPasemultiple(sesiones);
					espectaculos.add(multiple);
				}
			}

			if (!ft.exists()) {
				System.out.println("El fichero espectaculostemporada.txt no existe, se va a crear.");
				ft.createNewFile();
			} else {
				frt = new FileReader(ft);
				buffert = new BufferedReader(frt);

				Espectaculotemporada temporada;
				while ((lineaFichero = bufferpm.readLine()) != null) {
					temporada = new Espectaculotemporada();

					lineaCampos = lineaFichero.split(";");

					temporada.setTitulo(lineaCampos[0]);
					temporada.setDescripcion(lineaCampos[1]);
					if (lineaCampos[2].equals("MONOLOGO")) {
						temporada.setCategoriaevento(Categoriaevento.MONOLOGO);
					} else if (lineaCampos[2].equals("CONCIERTO")) {
						temporada.setCategoriaevento(Categoriaevento.CONCIERTO);
					} else if (lineaCampos[2].equals("OBRADETEATRO")) {
						temporada.setCategoriaevento(Categoriaevento.OBRADETEATRO);
					}

					lineaSesiones = lineaCampos[3].split("/");
					for (int i = 0; i < lineaSesiones.length; i++) {
						sesionCampos = lineaSesiones[i].split(" ");

						sesion.setFecha(LocalDate.parse(sesionCampos[0]));
						sesion.setHora(LocalTime.parse(sesionCampos[1]));
						sesiones.add(sesion);
					}
					temporada.setTemporada(sesiones);
					espectaculos.add(temporada);
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
				if (frp != null)
					frp.close();
				if (frpm != null)
					frpm.close();
				if (frt != null)
					frt.close();
				if (bufferp != null)
					bufferp.close();
				if (bufferpm != null)
					bufferpm.close();
				if (buffert != null)
					buffert.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Este metodo guarda los ficheros de los distintos tipos de espectaculos.
	 * 
	 * @param fileEspectaculosPuntuales
	 *            Ruta espectaculos puntuales.
	 * @param fileEspectaculosPaseMultiple
	 *            Ruta espectaculos pase multiple.
	 * @param fileEspectaculosTemporada
	 *            Ruta espectaculos de temporada.
	 */
	public void guardarFichero(String fileEspectaculosPuntuales, String fileEspectaculosPaseMultiple,
			String fileEspectaculosTemporada) {
		FileWriter fp = null, fpm = null, ft = null;
		PrintWriter bufferp = null, bufferpm = null, buffert = null;
		String fechaspm = "", fechast = "";

		try {
			fp = new FileWriter(fileEspectaculosPuntuales);
			bufferp = new PrintWriter(fp);

			fpm = new FileWriter(fileEspectaculosPaseMultiple);
			bufferpm = new PrintWriter(fpm);

			ft = new FileWriter(fileEspectaculosTemporada);
			buffert = new PrintWriter(ft);

			for (Espectaculo e : espectaculos) {
				if (e instanceof Espectaculopuntual) {
					Espectaculopuntual puntual = (Espectaculopuntual) e;
					bufferp.println(puntual.getTitulo() + ";" + puntual.getDescripcion() + ";" + puntual.getCategoria()
							+ ";" + puntual.getRepresentacion().getFecha() + ";"
							+ puntual.getRepresentacion().getHora());
				} else if (e instanceof Espectaculopasemultiple) {
					Espectaculopasemultiple multiple = (Espectaculopasemultiple) e;
					for (Sesiones s : multiple.getPasemultiple()) {
						fechaspm += s.getFecha().toString() + " " + s.getHora().toString() + "/";
					}
					bufferpm.println(multiple.getTitulo() + ";" + multiple.getDescripcion() + ";"
							+ multiple.getCategoria() + ";" + fechaspm);
				} else if (e instanceof Espectaculotemporada) {
					Espectaculotemporada temporada = (Espectaculotemporada) e;
					for (Sesiones s : temporada.getTemporada()) {
						fechast += s.getFecha().toString() + " " + s.getHora().toString() + "/";
					}
					buffert.println(temporada.getTitulo() + ";" + temporada.getDescripcion() + ";"
							+ temporada.getCategoria() + ";" + fechast);
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
				if (fp != null)
					fp.close();
				if (fpm != null)
					fpm.close();
				if (ft != null)
					ft.close();
				if (bufferp != null)
					bufferp.close();
				if (bufferpm != null)
					bufferpm.close();
				if (buffert != null)
					buffert.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Este metodo comprueba el tipo de espectaculo.
	 * 
	 * @param index
	 *            Indice del espectaculo.
	 * @return Tipo de espectaculo con un numero.
	 */
	public int tipoEspectaculo(int index) {
		int tipo = 0;
		if (espectaculos.get(index) instanceof Espectaculopuntual) {
			tipo = 1;
		} else if (espectaculos.get(index) instanceof Espectaculopasemultiple) {
			tipo = 2;
		} else if (espectaculos.get(index) instanceof Espectaculotemporada) {
			tipo = 3;
		}
		return tipo;
	}

	/**
	 * Este metodo devuelve el array de las sesiones de los espectaculos de pase
	 * multiple.
	 * 
	 * @param index
	 *            Indice del espectaculo.
	 * @return Array con las sesiones.
	 */
	public ArrayList<Sesiones> getSesionesMultiple(int index) {
		Espectaculopasemultiple multiple = new Espectaculopasemultiple();
		if (espectaculos.get(index) instanceof Espectaculopasemultiple) {
			multiple = (Espectaculopasemultiple) espectaculos.get(index);
		}
		return multiple.getPasemultiple();
	}

	/**
	 * Este metodo simula la venta de entradas.
	 * 
	 * @param index
	 *            Indice del espectaculo.
	 * @param indexSesion
	 *            Indice de la sesion.
	 * @param entradas
	 *            Numero de entradas que se desea comprar.
	 * @return Nada.
	 */
	public void VentaEntradas(int index, int indexSesion, int entradas) {
		if (entradas > ConsultarEntradasDisponibles(index, indexSesion)) {
			System.out.println("No existen tantas entradas disponibles. Comprueba las entradas en el menu.");
		} else {
			if (espectaculos.get(index) instanceof Espectaculopuntual) {
				Espectaculopuntual puntual = (Espectaculopuntual) espectaculos.get(index);
				puntual.getRepresentacion()
						.setEntradasVendidas(puntual.getRepresentacion().getEntradasVendidas() + entradas);
			} else if (espectaculos.get(index) instanceof Espectaculopasemultiple) {
				Espectaculopasemultiple multiple = (Espectaculopasemultiple) espectaculos.get(index);
				multiple.getPasemultiple().get(indexSesion).setEntradasVendidas(
						multiple.getPasemultiple().get(indexSesion).getEntradasVendidas() + entradas);
			} else if (espectaculos.get(index) instanceof Espectaculotemporada) {
				Espectaculotemporada temporada = (Espectaculotemporada) espectaculos.get(index);
				temporada.getTemporada().get(indexSesion).setEntradasVendidas(
						temporada.getTemporada().get(indexSesion).getEntradasVendidas() + entradas);
			}
		}
	}

	/**
	 * Este metodo comprueba la unicidad de los titulos de los espectaculos.
	 * 
	 * @param titulo
	 *            Titulo del espectaculo a comprobar.
	 * @return True si el titulo ya esta registrado.
	 */
	public Boolean tituloEspectaculoRegistrado(String titulo) {
		Boolean bandera = false;

		for (Espectaculo e : espectaculos) {
			if (e.getTitulo().equals(titulo)) {
				bandera = true;
			}
		}

		return bandera;
	}
}