package ejercicio2.data;

/**Clase Espectaculopuntual controla las sesiones y representaciones de 
/*esta clase de espectaculos.
/*
*/

public class Espectaculopuntual extends Espectaculo {

	Sesiones representacion;

	public Espectaculopuntual() {
		representacion = new Sesiones();
	}

	public Sesiones getRepresentacion() {
		return representacion;

	}

	public void setRepresentacion(Sesiones representacion) {
		this.representacion = representacion;
	}

}
