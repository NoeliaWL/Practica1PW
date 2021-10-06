package ejercicio2.data;

import java.time.LocalDate;
import java.time.LocalTime;

public class Sesiones {
	
	
	private LocalTime hora = LocalTime.now();
	
	private LocalDate fecha = LocalDate.now();

	public Sesiones() {
		
	}

	
	public LocalTime getHora() {
		return hora;
	}
	
	public void setHora(LocalTime hora) {
		this.hora = hora;
		
	}
	
	public LocalDate getFechaRepresentacion() {
		return fecha;
	}
	
	public void setFechaRepresentacion(LocalDate fecha) {
		this.fecha = fecha;
	}

}
