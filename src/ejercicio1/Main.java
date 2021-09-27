package ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
		String nombre, apellidos, nick, correo, titulo, reseña;
		int puntuacion;
		Espectador user;
		ArrayList<Critica> criticas;
		
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
				menu();
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
						
						if(gestorcriticas.comprobarFormatoCorreo(correo)){
							if(gestorcriticas.correoRegistrado(correo)){
								gestorcriticas.altaUsuario(nombre, apellidos, nick, correo);
							}
							else{
								System.out.println("Correo eléctronico ya registrado");
							}
						}
						else{
							System.out.println("Formato de correo no válido");
						}
						break;
						
					case 2:
						System.out.println("Introduzca el correo eléctronico: ");
						correo = reader.nextLine();
						
						if(gestorcriticas.comprobarFormatoCorreo(correo)){
							if(gestorcriticas.correoRegistrado(correo)){
								gestorcriticas.bajaUsuario(correo);
							}
							else{
								System.out.println("Correo eléctronico no registrado");
							}
						}
						else{
							System.out.println("Formato de correo no válido");
						}
						break;
						
					case 3:						
						System.out.println("Introduzca su nuevo nombre: ");
						nombre = reader.nextLine();
						System.out.println("Introduzca sus nuevos apellidos: ");
						apellidos = reader.nextLine();
						System.out.println("Introduzca su nuevo nick(nombre de usuario): ");
						nick = reader.nextLine();
						System.out.println("Introduzca su correo eléctronico: ");
						correo = reader.nextLine();
						
						if(gestorcriticas.comprobarFormatoCorreo(correo)){
							if(gestorcriticas.correoRegistrado(correo)){
								gestorcriticas.actualizarDatosUsuario(nombre, apellidos, nick, correo);
							}
							else{
								System.out.println("Correo eléctronico no registrado");
							}
						}
						else{
							System.out.println("Formato de correo no válido");
						}
						break;
						
					case 4:
						System.out.println("Introduzca su correo eléctronico: ");
						correo = reader.nextLine();
						
						if(gestorcriticas.comprobarFormatoCorreo(correo)){
							if(gestorcriticas.correoRegistrado(correo)){
								user = gestorcriticas.getUsuario(correo);
								
								System.out.println("Nombre: " + user.getNombre());
								System.out.println("Apellidos: " + user.getApellidos());
								System.out.println("Nickname: " + user.getUsuario());
								System.out.println("Correo electrónico: " + user.getCorreo());
							}
							else{
								System.out.println("Correo eléctronico no registrado");
							}
						}
						else{
							System.out.println("Formato de correo no válido");
						}
						break;
						
					case 5:
						System.out.println("Introduzca su correo eléctronico: ");
						correo = reader.nextLine();
						
						if(gestorcriticas.comprobarFormatoCorreo(correo)){
							if(gestorcriticas.correoRegistrado(correo)){
								System.out.println("Introduzca el título de la crítica: ");
								titulo = reader.nextLine();
								System.out.println("Introduzca la puntuación dada al espectáculo: ");
								puntuacion = Integer.parseInt(reader.nextLine());
								System.out.println("Introduzca la reseña del espectáculo: ");
								reseña = reader.nextLine();
								
								gestorcriticas.creaCritica(titulo, puntuacion, reseña, correo);
							}
							else{
								System.out.println("Correo eléctronico no registrado");
							}
						}
						else{
							System.out.println("Formato de correo no válido");
						}
						break;
						
					case 6:
						criticas = gestorcriticas.getCriticas();
						for(Critica c : criticas){
							System.out.println("Titulo: " + c.getTitulo());
							System.out.println("Puntuación: " + c.getPuntuacion());
							System.out.println("Reseña: " + c.getReseña());
							System.out.println("Valoraciones: ");
							for(Valoraciones v : c.getValoraciones()){
								//Mostrar valoraciones ¿Como hacerlo?
							}
						}
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
	
	public static void menu(){
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
	}
}
