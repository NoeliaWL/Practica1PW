package ejercicio2.data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Clase de factoria concreta que implementa la interfaz de factoria siguiendo el patron abstract factory.
 * @author Noelia Hinojosa Sanchez
 * @version 1.0, 04/10/2021
 */
public class Factoriaconcreta implements IFactoria {

	@Override
	/**
	 * Este metodo crea espectaculos puntuales.
	 */
	public Espectaculopuntual createEspectaculoPuntual(String titulo, String descripcion, Categoriaevento categoria,
			Sesiones sesion) {
		// TODO Auto-generated method stub
		Espectaculopuntual puntual = new Espectaculopuntual();
		puntual.setRepresentacion(sesion);
		puntual.setTitulo(titulo);
		puntual.setDescripcion(descripcion);
		puntual.setCategoriaevento(categoria);
		return puntual;
	}

	@Override
	/**
	 * Este metodo crea espectaculos de pase multiple.
	 */
	public Espectaculopasemultiple createEspectaculoPaseMultiple(String titulo, String descripcion,
			Categoriaevento categoria, ArrayList<Sesiones> pasemultiple) {
		// TODO Auto-generated method stub
		Espectaculopasemultiple multiple = new Espectaculopasemultiple();
		multiple.setTitulo(titulo);
		multiple.setDescripcion(descripcion);
		multiple.setCategoriaevento(categoria);
		multiple.setPasemultiple(pasemultiple);
		return multiple;
	}

	@Override
	/**
	 * Este metodo crea espectaculos de temporada.
	 */
	public Espectaculotemporada createEspectaculoTemporada(String titulo, String descripcion, Categoriaevento categoria,
			LocalDate fechaInicio, LocalDate fechaFin, LocalTime hora) {
		// TODO Auto-generated method stub
		Espectaculotemporada temporada = new Espectaculotemporada();
		temporada.setTitulo(titulo);
		temporada.setDescripcion(descripcion);
		temporada.setCategoriaevento(categoria);
		temporada.Calcularfecha(fechaInicio, fechaFin, hora);
		return temporada;
	}

}
