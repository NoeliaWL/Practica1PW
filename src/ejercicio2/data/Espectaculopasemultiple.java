package ejercicio2.data;

import java.util.ArrayList;


/**Esta clase 
/*
/*@authores Noelia Hinojosa Sanchez, Rafael Piqueras Espinar
*/
public class Espectaculopasemultiple extends Espectaculo {

	ArrayList<Sesiones> pasemultiple;
	
	/**Este es el constructor de la clase
	/*
	/*
	/*@param pasemultiple inicia un array de sesiones.
	*/
	public Espectaculopasemultiple() {
		pasemultiple = new ArrayList<Sesiones>();
	}
	
	/**Este metodo devuelve las sesiones de pase multiple
	/*
	/*
	/*@return pasemultiple
	*/
	public ArrayList<Sesiones> getPasemultiple() {
		return pasemultiple;
	}
	/**Este metodo asigna una lista de pase multiple
	/*
	/*@param pasemultiple
	*/

	public void setPasemultiple(ArrayList<Sesiones> pasemultiple) {
		this.pasemultiple = pasemultiple;
	}

}
