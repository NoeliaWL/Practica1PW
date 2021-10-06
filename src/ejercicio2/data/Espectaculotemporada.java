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
		  Sesiones temporada1 = new Sesiones();
		  temporada1.setFechaRepresentacion(fechaInicio);
		  temporada1.setHora(hora);
		  temporada.add(temporada1);
		  
		  while(fechaInicio.compareTo(fechaFin) < 0);{
			  fechaInicio = fechaInicio.plusDays(7);
			  temporada1.setFechaRepresentacion(fechaInicio);
			  temporada1.setHora(hora);
			  temporada.add(temporada1);
		}
		  
	}
	
	public ArrayList<Sesiones> getTemporada(){
		return temporada;
	}
	
}
