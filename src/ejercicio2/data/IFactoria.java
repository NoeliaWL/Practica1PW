package ejercicio2.data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Interfaz de Factoria con los metodos para crear los distintos tipos de espectaculos.
 * @author Noelia Hinojosa Sanchez
 * @version 1.0, 04/10/2021
 */
public interface IFactoria {
	public Espectaculopuntual createEspectaculoPuntual(String titulo, String descripcion, Categoriaevento categoria,
			Sesiones sesion);

	public Espectaculopasemultiple createEspectaculoPaseMultiple(String titulo, String descripcion,
			Categoriaevento categoria, ArrayList<Sesiones> pasemultiple);

	public Espectaculotemporada createEspectaculoTemporada(String titulo, String descripcion, Categoriaevento categoria,
			LocalDate fechaInicio, LocalDate fechaFin, LocalTime hora);
}
