package ejercicio2.data;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

/**Clase Espectaculotemporada controla las fechas de 
/*este tipo de espectaculos.
/*
/*@autores Noelia Hinojosa Sanchez, Rafael Piqueras Espinar
*/
public class Espectaculotemporada extends Espectaculo {

	ArrayList<Sesiones> temporada;
	
	/**Este metodo del constructor iniciliza el
	/*array de la temporada
	/*
	/*@param temporada
	*/
	public Espectaculotemporada() {
		temporada = new ArrayList<Sesiones>();
	}
	
	/**Este metodo calcula la fecha de la sesion del espectaculo
	/*
	/*@param Calcularfecha
	*/
	public void Calcularfecha(LocalDate fechaInicio, LocalDate fechaFin, LocalTime hora) {
		Sesiones sesion = new Sesiones();
		sesion.setFecha(fechaInicio);
		sesion.setHora(hora);
		temporada.add(sesion);

		while (fechaInicio.compareTo(fechaFin) < 0){
			fechaInicio = fechaInicio.plusDays(7);
			sesion.setFecha(fechaInicio);
			sesion.setHora(hora);
			temporada.add(sesion);
		}

	}
	
	/**Este metodo devuelve una la lista de la
	/*temporada.
	/*
	/*@return temporada
	*/
	public ArrayList<Sesiones> getTemporada() {
		return temporada;
	}
	
	/**Este metodo asigna una temporada de este
	/*espectáculo
	/*
	/*@param temporada
	*/
	public void setTemporada(ArrayList<Sesiones> temporada) {
		this.temporada = temporada;
	}

}
