package ejercicio1.interfaz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import ejercicio1.business.Gestorcriticas;
import ejercicio1.data.Critica;
import ejercicio1.data.Espectador;
import ejercicio1.data.Valoraciones;

/**
 * Clase Main Esta clase prueba la funcionalidad del gestor.
 * 
 * @author Antonio Cabezas Jarabo
 * @author Noelia Hinojosa Sanchez
 * @version 1.0, 25/09/2021
 */

public class Main {
	public static void main(String[] args) {
		Gestorcriticas gestorcriticas = Gestorcriticas.getInstance();

		int numero = 0, opcion = 0;
		Scanner reader = new Scanner(System.in);
		String nombre = "", apellidos = "", nick = "", correo, titulo, resena;
		String buffer = "";
		int puntuacion, media = 0;
		int index;
		Espectador user;
		ArrayList<Critica> listacriticas = new ArrayList<Critica>();

		Properties prop = new Properties();
		String filename = "./fichero.properties";
		File f = new File(filename);
		BufferedReader lector;

		try {
			if (f.exists()) {
				lector = new BufferedReader(new FileReader(f));
			} else {
				System.out.println("El fichero fichero.properties no existe, se va a crear.");
				PrintWriter escritor = new PrintWriter(new FileWriter(f));
				escritor.println("CRITICAS: ./criticas.txt");
				escritor.println("ESPECTADORES: ./espectadores.txt");
				escritor.close();
				lector = new BufferedReader(new FileReader(f));
			}
			prop.load(lector);
			gestorcriticas.cargarFichero(prop.getProperty("CRITICAS"), prop.getProperty("ESPECTADORES"));

			do {
				menu();
				numero = Integer.parseInt(reader.nextLine());
				switch (numero) {
				case 1:
					System.out.println("Introduzca su nombre: ");
					nombre = reader.nextLine().toUpperCase();
					System.out.println("Introduzca sus apellidos: ");
					apellidos = reader.nextLine().toUpperCase();
					System.out.println("Introduzca su nick(nombre de usuario): ");
					nick = reader.nextLine();
					System.out.println("Introduzca su correo electronico: ");
					correo = reader.nextLine();

					if (gestorcriticas.comprobarFormatoCorreo(correo)) {
						if (!gestorcriticas.correoRegistrado(correo)) {
							gestorcriticas.altaUsuario(nombre, apellidos, nick, correo);
						} else {
							System.out.println("Correo electronico ya registrado");
						}
					} else {
						System.out.println("Formato de correo no valido");
					}
					break;

				case 2:
					System.out.println("Introduzca el correo electronico: ");
					correo = reader.nextLine();

					if (gestorcriticas.comprobarFormatoCorreo(correo)) {
						if (gestorcriticas.correoRegistrado(correo)) {
							gestorcriticas.bajaUsuario(correo);
						} else {
							System.out.println("Correo electronico no registrado");
						}
					} else {
						System.out.println("Formato de correo no valido");
					}
					break;

				case 3:
					System.out.println("Introduzca su correo electronico: ");
					correo = reader.nextLine();

					if (gestorcriticas.comprobarFormatoCorreo(correo)) {
						if (gestorcriticas.correoRegistrado(correo)) {
							nombre = ""; apellidos = ""; nick = "";
							do{
								menuActualizar();
								opcion = Integer.parseInt(reader.nextLine());
								
								switch(opcion){
									case 1:
										System.out.println("Introduzca su nuevo nombre: ");
										nombre = reader.nextLine().toUpperCase();
										System.out.println(nombre);
										break;
										
									case 2:
										System.out.println("Introduzca sus nuevos apellidos: ");
										apellidos = reader.nextLine().toUpperCase();
										break;
										
									case 3:
										System.out.println("Introduzca su nuevo nick(nombre de usuario): ");
										nick = reader.nextLine();
										System.out.println(nick);
										break;
										
									case 4:
										System.out.println("Actualizacion terminada.");
										break;
									
									default:
										System.out.println("Opcion no valida, introduzca otra opcion.");
								}
							}while(opcion != 4);
							gestorcriticas.actualizarDatosUsuario(nombre, apellidos, nick, correo);
						} else {
							System.out.println("Correo electronico no registrado");
						}
					} else {
						System.out.println("Formato de correo no valido");
					}
					break;

				case 4:
					System.out.println("Introduzca su correo electronico: ");
					correo = reader.nextLine();

					if (gestorcriticas.comprobarFormatoCorreo(correo)) {
						if (gestorcriticas.correoRegistrado(correo)) {
							user = gestorcriticas.getUsuario(correo);
							System.out.println("Nombre: " + user.getNombre());
							System.out.println("Apellidos: " + user.getApellidos());
							System.out.println("Nickname: " + user.getUsuario());
							System.out.println("Correo electronico: " + user.getCorreo());
						} else {
							System.out.println("Correo electronico no registrado");
						}
					} else {
						System.out.println("Formato de correo no valido");
					}
					break;

				case 5:
					buffer = gestorcriticas.consultarDatosUsuarios();
					System.out.println(buffer);
					break;
					
				case 6:
					System.out.println("Introduzca su correo electronico: ");
					correo = reader.nextLine();

					if (gestorcriticas.comprobarFormatoCorreo(correo)) {
						if (gestorcriticas.correoRegistrado(correo)) {
							System.out.println("Introduzca el titulo de la critica: ");
							titulo = reader.nextLine();
							if(!gestorcriticas.tituloCriticaRegistrado(titulo)){
								System.out.println("Introduzca la puntuacion dada al espectaculo: ");
								puntuacion = Integer.parseInt(reader.nextLine());
								System.out.println("Introduzca la resena del espectaculo: ");
								resena = reader.nextLine();

								gestorcriticas.creaCritica(titulo, puntuacion, resena, correo);
							}
							else{
								System.out.println("Titulo de critica ya registrado.");
							}
						} else {
							System.out.println("Correo electronico no registrado");
						}
					} else {
						System.out.println("Formato de correo no valido");
					}
					break;

				case 7:
					buffer = gestorcriticas.getCriticas();
					System.out.println(buffer);
					break;

				case 8:
					System.out.println("Introduzca su correo electronico: ");
					correo = reader.nextLine();
					
					if (gestorcriticas.comprobarFormatoCorreo(correo)) {
						if (gestorcriticas.correoRegistrado(correo)) {
							buffer = gestorcriticas.getCriticasUsuario(correo);
							if(buffer == null){
								System.out.println("No tiene criticas creadas.");
							}
							else{
								System.out.println(buffer);
								System.out.println("\nIntroduce el indice de la critica a borrar: ");
								index = Integer.parseInt(reader.nextLine());
								gestorcriticas.borraCritica(index);
							}
						} else {
							System.out.println("Correo electronico no registrado");
						}
					} else {
						System.out.println("Formato de correo no valido");
					}
					break;

				case 9:
					buffer = gestorcriticas.getCriticas();
					System.out.println(buffer);
					System.out.println("Introduzca el indice de la critica que desea votar: ");
					index = Integer.parseInt(reader.nextLine());
					System.out.println("Introduzca la puntuacion de su votacion: ");
					puntuacion = Integer.parseInt(reader.nextLine());
					System.out.println("Introduzca su correo electronico: ");
					correo = reader.nextLine();

					if (gestorcriticas.comprobarFormatoCorreo(correo)) {
						if (gestorcriticas.correoRegistrado(correo)) {
							gestorcriticas.votaCritica(index, correo, puntuacion);
						} else {
							System.out.println("Correo electronico no registrado");
						}
					} else {
						System.out.println("Formato de correo no valido");
					}
					break;

				case 10:
					System.out.println("Introduzca el usuario (correo): ");
					correo = reader.nextLine();

					if (gestorcriticas.comprobarFormatoCorreo(correo)) {
						if (gestorcriticas.correoRegistrado(correo)) {
							listacriticas = gestorcriticas.buscaCritica(correo);
						} else {
							System.out.println("Usuario no registrado");
						}
					} else {
						System.out.println("Formato de usuario no valido");
					}
					
					for(Critica c : listacriticas){
						System.out.print(listacriticas.indexOf(c) + " ");
						System.out.print("Titulo: " + c.getTitulo() + " ");
						System.out.println("Puntuacion: " + c.getPuntuacion());
						System.out.println("Resena: " + c.getResena());
						if(c.getValoraciones().size() != 0){
							for (Valoraciones v : c.getValoraciones()) {
								media += v.getValoracion();
							}
							media /= c.getValoraciones().size();
							System.out.println("Media de las valoraciones: " + media);
						}
						else{
							System.out.println("No tiene valoraciones actualmente.");
						}
					}
					
					break;
					
				case 11:
					System.out.println("Programa terminado.");
					break;
				}
			} while ((numero >= 1) && (numero <= 10) || (numero != 11));
			
			gestorcriticas.guardarFichero(prop.getProperty("CRITICAS"), prop.getProperty("ESPECTADORES"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

	public static void menu() {
		System.out.println("\nElija una opcion:");
		System.out.println("1. Dar de alta a un usuario");
		System.out.println("2. Dar de baja a un usuario");
		System.out.println("3. Actualizar los datos del usuario");
		System.out.println("4. Consultar los datos del 1 usuario");
		System.out.println("5. Consultar los datos de todos los usuarios");
		System.out.println("6. Crear una critica");
		System.out.println("7. Consultar todas las criticas disponibles");
		System.out.println("8. Borrar una critica");
		System.out.println("9. Votar la utilidad de una critica");
		System.out.println("10. Buscar las criticas de un usuario");
		System.out.println("11. Salir");
	}
	
	public static void menuActualizar(){
		System.out.println("\nElige que dato desea modificar:");
		System.out.println("1. Nombre");
		System.out.println("2. Apellidos");
		System.out.println("3. Nick");
		System.out.println("4. Salir");
	}
}
