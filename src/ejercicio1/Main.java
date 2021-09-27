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
		int index;
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
						System.out.println("Introduzca su correo el�ctronico: ");
						correo = reader.nextLine();
						
						if(gestorcriticas.comprobarFormatoCorreo(correo)){
							if(gestorcriticas.correoRegistrado(correo)){
								gestorcriticas.altaUsuario(nombre, apellidos, nick, correo);
							}
							else{
								System.out.println("Correo el�ctronico ya registrado");
							}
						}
						else{
							System.out.println("Formato de correo no v�lido");
						}
						break;
						
					case 2:
						System.out.println("Introduzca el correo el�ctronico: ");
						correo = reader.nextLine();
						
						if(gestorcriticas.comprobarFormatoCorreo(correo)){
							if(gestorcriticas.correoRegistrado(correo)){
								gestorcriticas.bajaUsuario(correo);
							}
							else{
								System.out.println("Correo el�ctronico no registrado");
							}
						}
						else{
							System.out.println("Formato de correo no v�lido");
						}
						break;
						
					case 3:						
						System.out.println("Introduzca su nuevo nombre: ");
						nombre = reader.nextLine();
						System.out.println("Introduzca sus nuevos apellidos: ");
						apellidos = reader.nextLine();
						System.out.println("Introduzca su nuevo nick(nombre de usuario): ");
						nick = reader.nextLine();
						System.out.println("Introduzca su correo el�ctronico: ");
						correo = reader.nextLine();
						
						if(gestorcriticas.comprobarFormatoCorreo(correo)){
							if(gestorcriticas.correoRegistrado(correo)){
								gestorcriticas.actualizarDatosUsuario(nombre, apellidos, nick, correo);
							}
							else{
								System.out.println("Correo el�ctronico no registrado");
							}
						}
						else{
							System.out.println("Formato de correo no v�lido");
						}
						break;
						
					case 4:
						System.out.println("Introduzca su correo el�ctronico: ");
						correo = reader.nextLine();
						
						if(gestorcriticas.comprobarFormatoCorreo(correo)){
							if(gestorcriticas.correoRegistrado(correo)){
								user = gestorcriticas.getUsuario(correo);
								
								System.out.println("Nombre: " + user.getNombre());
								System.out.println("Apellidos: " + user.getApellidos());
								System.out.println("Nickname: " + user.getUsuario());
								System.out.println("Correo electr�nico: " + user.getCorreo());
							}
							else{
								System.out.println("Correo el�ctronico no registrado");
							}
						}
						else{
							System.out.println("Formato de correo no v�lido");
						}
						break;
						
					case 5:
						System.out.println("Introduzca su correo el�ctronico: ");
						correo = reader.nextLine();
						
						if(gestorcriticas.comprobarFormatoCorreo(correo)){
							if(gestorcriticas.correoRegistrado(correo)){
								System.out.println("Introduzca el t�tulo de la cr�tica: ");
								titulo = reader.nextLine();
								System.out.println("Introduzca la puntuaci�n dada al espect�culo: ");
								puntuacion = Integer.parseInt(reader.nextLine());
								System.out.println("Introduzca la rese�a del espect�culo: ");
								reseña = reader.nextLine();
								
								gestorcriticas.creaCritica(titulo, puntuacion, reseña, correo);
							}
							else{
								System.out.println("Correo el�ctronico no registrado");
							}
						}
						else{
							System.out.println("Formato de correo no v�lido");
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
								//Mostrar valoraciones �Como hacerlo?
							}
						}
						break;
						
					case 7:
						gestorcriticas.getCriticas();
						index = Integer.parseInt(reader.nextLine());
						gestorcriticas.borraCritica(index);
						break;
						
					case 8:
						System.out.println("Introduzca el correo el�ctronico: ");
						correo = reader.nextLine();
						
						if(gestorcriticas.comprobarFormatoCorreo(correo)){
							if(gestorcriticas.correoRegistrado(correo)){
								gestorcriticas.votaCritica(index,correo,puntuacion);
							}
							else{
								System.out.println("Correo el�ctronico no registrado");
							}
						}
						else{
							System.out.println("Formato de correo no v�lido");
						}
						break;
						
						
					case 9:
						System.out.println("Introduzca el usuario: ");
						correo = reader.nextLine();
						
						if(gestorcriticas.comprobarFormatoCorreo(correo)){
							if(gestorcriticas.correoRegistrado(correo)){
								gestorcriticas.buscaCritica(correo);
							}
							else{
								System.out.println("Usuario no registrado");
							}
						}
							else {
								System.out.println("Formato de usuario no valido");
							}
						}
						
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
		System.out.println("Elija una opci�n:");
		System.out.println("1. Dar de alta a un usuario");
		System.out.println("2. Dar de baja a un usuario");
		System.out.println("3. Actualizar los datos del usuario");
		System.out.println("4. Consultar los datos del usuario");
		System.out.println("5. Crear una cr�tica");
		System.out.println("6. Consultar todas las cr�ticas disponibles");
		System.out.println("7. Borrar una cr�tica");
		System.out.println("8. Votar la utilidad de una cr�tica");
		System.out.println("9. Buscar las cr�ticas de un usuario");
		System.out.println("10. Salir");
	}
}
