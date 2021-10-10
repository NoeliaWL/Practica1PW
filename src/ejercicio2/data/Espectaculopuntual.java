package ejercicio2.data;

/**Clase Espectaculopuntual controla las sesiones y representaciones de 
/*esta clase de espectaculos.
/*
/*@autores Noelia Hinojosa Sanchez, Rafael Piqueras Espinar
*/

public class Espectaculopuntual extends Espectaculo {

	Sesiones representacion;
	
	/**Este metodo que es el constructor inicia un 
	/*array de sesiones.
	/*
	/*@param sesiones
	*/
	public Espectaculopuntual() {
		representacion = new Sesiones();
	}
	
	/**Este metodo devuelve una lista de sesiones
	/*
	/*@return representacion
	*/
	public Sesiones getRepresentacion() {
		return representacion;

	}

	/**Este metodo asigna una lista de representaciones
	/*
	/*@param representacion
	*/
	public void setRepresentacion(Sesiones representacion) {
		this.representacion = representacion;
	}

}
