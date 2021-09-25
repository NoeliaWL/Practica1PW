package ejercicio1;

import java.util.Scanner;

/**
 * Clase Main
 * Esta clase prueba la funcionalidad del gestor.
 * @author  Antonio Cabezas Jarabo
 * @version 1.0, 25/09/2021 
 */

public class Main {
	public static void main(String[] args) {
		
		GestorCriticas gestorcriticas = GestorCriticas.getInstance();
		
		int numero = 0;
		Scanner reader = new Scanner(System.in);
			
		do {
			System.out.println("Elija una opción:");
			System.out.println("1. Dar de alta a un usuario");
			System.out.println("2. Dar de baja a un usuario");
			System.out.println("3. Actualizar los datos del usuario");
			System.out.println("4. Consultar los datos del usuario");
			System.out.println("5. Crear una crítica");
			System.out.println("6. Consultar todas las críticas disponibles");
			System.out.println("7. Borrar una crítica");
			System.out.println("8. Votar la utilidad de una crítica");
			System.out.println("9. Buscar las críticas de un usuario");
			System.out.println("10. Salir");
			numero = reader.nextInt();
			switch (numero) {
			case 1:
				gestorcriticas.altaUsuario();
				break;
			case 2:
				gestorcriticas.bajaUsuario();
				break;
			case 3:
				gestorcriticas.setUsuario();
				break;
			case 4:
				gestorcriticas.getUsuario();
				break;
			case 5:
				gestorcriticas.creaCritica();
				break;
			case 6:
				gestorcriticas.getCriticas();
				break;
			case 7:
				gestorcriticas.borraCritica();
				break;
			case 8:
				gestorcriticas.votaCritica();
				break;
			case 9:
				gestorcriticas.buscaCritica();
				break;
			}
		} while ((numero >= 1) && (numero <= 9));
		reader.close();
	}
}
