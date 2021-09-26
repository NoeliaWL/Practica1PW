package ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
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
		String nombre, apellidos, nick, correo;
		
		Properties prop = new Properties();
		String filename = "fichero.properties";
		File f = new File(filename);
		BufferedReader lector;
			
		try{
			if(f.exists()){
				lector = new BufferedReader(new FileReader(f));
			}
			else{
				BufferedWriter escritor = new BufferedWriter(new FileWriter(f));
				escritor.close();
				lector = new BufferedReader(new FileReader(f));
			}
			prop.load(lector);
			//cargarFichero Espectadores.txt
			//cargarFichero Criticas.txt
			
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
						System.out.println("Introduzca su nombre: ");
						nombre = reader.nextLine();
						System.out.println("Introduzca sus apellidos: ");
						apellidos = reader.nextLine();
						System.out.println("Introduzca su nick(nombre de usuario): ");
						nick = reader.nextLine();
						System.out.println("Introduzca su correo eléctronico: ");
						correo = reader.nextLine();
						
						if(gestorcriticas.comprobarFormatoCorreo(correo) && !gestorcriticas.correoRegistrado(correo)){
							gestorcriticas.altaUsuario(nombre, apellidos, nick, correo);
						}
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
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(reader != null){
				reader.close();
			}
		}
	}
}
