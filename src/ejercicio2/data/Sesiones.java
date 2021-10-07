package ejercicio2.data;

import java.time.LocalDate;
import java.time.LocalTime;

public class Sesiones {

	private LocalTime hora = LocalTime.now();

	private LocalDate fecha = LocalDate.now();

	private int totalEntradas = 30;

	private int entradasVendidas;

	public Sesiones() {
		entradasVendidas = 0;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getTotalEntradas() {
		return totalEntradas;
	}

	public void setTotalEntradas(int totalEntradas) {
		this.totalEntradas = totalEntradas;
	}

	public int getEntradasVendidas() {
		return entradasVendidas;
	}

	public void setEntradasVendidas(int entradasVendidas) {
		this.entradasVendidas = entradasVendidas;
	}
}
