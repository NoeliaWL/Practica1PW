package ejercicio2.data;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

public class Espectaculotemporada extends Espectaculo {

	ArrayList<Sesiones> temporada;

	public Espectaculotemporada() {
		temporada = new ArrayList<Sesiones>();

	}

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

	public ArrayList<Sesiones> getTemporada() {
		return temporada;
	}

}
