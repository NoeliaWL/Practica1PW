package ejercicio2.data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/** Clase Factoria 
/*
/*
*/


public class Factoriaconcreta implements IFactoria {

	@Override
	/** Este metodo crea est
	/*
	/*
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
