package ejercicio2.data;

import java.time.LocalDate;
import java.time.LocalTime;

/** Clase sesiones controla todo lo relativo a la
/* hora, fecha y entrada de un espectaculo.
/*
/* @authores Noelia Hinojosa Sanchez, Rafael Piqueras Espinar
*/

public class Sesiones {

	
	private LocalTime hora = LocalTime.now();

	private LocalDate fecha = LocalDate.now();

	private int totalEntradas = 30;

	private int entradasVendidas;
	
	/** Este metodo inicializa las entradasVendidas
	/* en el constructor de la clase
	/*
	/* @param entradasVendidas
	*/
	public Sesiones() {
		entradasVendidas = 0;
	}
	
	/** Este metodo devuelve la hora de una 
	/* sesion.
	/*
	/* @return hora
	*/
	public LocalTime getHora() {
		return hora;
	}

	/** Este metodo asigna la hora de una 
	/* sesion.
	/*
	/* @param hora
	*/
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	
	/** Este metodo devuelve la fecha de una 
	/* sesion.
	/*
	/* @return fecha
	*/
	public LocalDate getFecha() {
		return fecha;
	}
	
	/** Este metodo asigna la fecha a de una 
	/* sesion.
	/*
	/* @param fecha
	*/
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	/** Este metodo devuelve el total de entradas de una 
	/* sesion.
	/*
	/* @return totalEntradas
	*/
	public int getTotalEntradas() {
		return totalEntradas;
	}
	
	/** Este metodo asigna el total de entradas de una 
	/* sesion.
	/*
	/* @param totalEntradas
	*/
	public void setTotalEntradas(int totalEntradas) {
		this.totalEntradas = totalEntradas;
	}
	
	/** Este metodo devuelve las entradas vendidas de una 
	/* sesion.
	/*
	/* @return entradasVendidas
	*/
	public int getEntradasVendidas() {
		return entradasVendidas;
	}
	
	/** Este metodo asigna las entradas vendidas de una
	/* sesion.
	/*
	/* @param entradasVendidas
	*/
	public void setEntradasVendidas(int entradasVendidas) {
		this.entradasVendidas = entradasVendidas;
	}
}
