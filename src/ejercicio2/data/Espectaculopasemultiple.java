package ejercicio2.data;

import java.util.ArrayList;

public class Espectaculopasemultiple extends Espectaculo {

	ArrayList<Sesiones> pasemultiple;

	public Espectaculopasemultiple() {
		pasemultiple = new ArrayList<Sesiones>();
	}

	public ArrayList<Sesiones> getPasemultiple() {
		return pasemultiple;
	}

	public void setPasemultiple(ArrayList<Sesiones> pasemultiple) {
		this.pasemultiple = pasemultiple;
	}

}
