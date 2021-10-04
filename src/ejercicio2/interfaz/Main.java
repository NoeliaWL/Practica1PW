package ejercicio2.interfaz;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

import ejercicio2.business.Gestorcriticas;
import ejercicio2.business.Gestorespectaculos;
import ejercicio2.data.TipoUsuario;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gestorespectaculos gestorespectaculos = Gestorespectaculos.getInstance();
		Gestorcriticas gestorcriticas = Gestorcriticas.getInstance();
		
		Properties prop = new Properties();
		String filename = "./fichero.properties";
		File f = new File(filename);
		BufferedReader lector;
		
		Scanner reader = new Scanner(System.in);
		int opcionInicio = 0, opcion = 0, tipo = 0;
		Boolean bandera = false;
		TipoUsuario tipoUsuario = TipoUsuario.ESPECTADOR;
		String nombre = "", apellidos = "", nick = "", correo = "";
		
		try{
			if(f.exists()){
				lector = new BufferedReader(new FileReader(f));
			}
			else{
				System.out.println("El fichero fichero.properties no existe, se va a crear.");
				PrintWriter escritor = new PrintWriter((new FileWriter(f)));
				escritor.println("CRITICAS: ./criticas.txt");
				escritor.println("ESPECTADORES: ./espectadores.txt");
				escritor.close();
				lector = new BufferedReader(new FileReader(f));
			}
			prop.load(lector);
			//cargar fichero espectaculos.txt desde el Gestor de Espectaculos
			gestorespectaculos.cargarFichero(prop.getProperty("ESPECTACULOS"));
			//cargar ficheros espectadores.txt - criticas.txt desde el Gestor de Criticas
			gestorcriticas.cargarFichero(prop.getProperty("CRITICAS"), prop.getProperty("ESPECTADORES"));
			
			//Menu login o registro
			do{
				menuInicio();
				opcionInicio = Integer.parseInt(reader.nextLine());
				
				switch(opcionInicio){ //Desde el Gestor de Criticas
					case 1:
						//Login ¿con usuario o correo + contrasena?
						//Login correcto -> bandera=true
						//TipoUsuario -> Administrador/Espectador
						break;
						
					case 2:
						//Registrarse
						System.out.println("Introduzca su nombre: ");
						nombre = reader.nextLine().toUpperCase();
						System.out.println("Introduzca sus apellidos: ");
						apellidos = reader.nextLine().toUpperCase();
						System.out.println("Introduzca su nick(nombre de usuario): ");
						nick = reader.nextLine();
						System.out.println("Introduzca su correo electronico: ");
						correo = reader.nextLine();
						System.out.println("Seleccione su tipo de usuario: ");
						System.out.println("1. Administrador");
						System.out.println("2. Espectador");
						tipo = Integer.parseInt(reader.nextLine());

						if (gestorcriticas.comprobarFormatoCorreo(correo)) {
							if (!gestorcriticas.correoRegistrado(correo)) {
								TipoUsuario tipoUsuarioRegistro = TipoUsuario.ADMINISTRADOR;
								if(tipo == 1){
									tipoUsuarioRegistro = TipoUsuario.ADMINISTRADOR;
								}
								else if(tipo == 2){
									tipoUsuarioRegistro = TipoUsuario.ESPECTADOR;
								}
								gestorcriticas.altaUsuario(nombre, apellidos, nick, correo, tipoUsuarioRegistro);
							} else {
								System.out.println("Correo electronico ya registrado");
							}
						} else {
							System.out.println("Formato de correo no valido");
						}
						break;
						
					case 3:
						//Salir del programa
						System.out.println("Programa finalizado.");
						break;
				}
			}while(opcionInicio != 3 || bandera == false);
			
			if(bandera && (tipoUsuario == TipoUsuario.ADMINISTRADOR)){
				do{
					menuPrincipalAdmin();
					opcion = Integer.parseInt(reader.nextLine());
					
					switch(opcion){
						case 1:
							break;
							
						case 2:
							break;
						
						case 3:
							break;
						
						case 4:
							break;
						
						case 5:
							break;
						
						case 6:
							break;
						
						case 7:
							break;
						
						case 8:
							break;
						
						case 9:
							break;
						
						case 10:
							break;
						
						case 11:
							break;
						
						case 12:
							System.out.println("Sesion cerrada.");
							break;
							
						default:
							System.out.println("Opcion no valida, vuelva a intentarlo.");
					}
				}while(opcion != 12);
			}
			else if(bandera && (tipoUsuario == TipoUsuario.ESPECTADOR)){
				do{
					menuPrincipalEspectador();
					opcion = Integer.parseInt(reader.nextLine());
					
					switch(opcion){
						case 1:
							break;
							
						case 2:
							break;
							
						case 3:
							break;
							
						case 4:
							break;
							
						case 5:
							break;
							
						case 6:
							break;
							
						case 7:
							break;
							
						case 8:
							System.out.println("Sesion cerrada.");
							break;
							
						default:
							System.out.println("Opcion no valida, vuelve a intentarlo.");
					}
				}while(opcion != 8);
			}
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
			//Cerrar lo que se necesite ficheros o scanner
			reader.close();
		}
	}
	
	public static void menuInicio(){
		System.out.println("Elige una opcion: ");
		System.out.println("1. Iniciar de sesion");
		System.out.println("2. Registrarse");
		System.out.println("3. Salir del programa");
	}

	public static void menuPrincipalAdmin(){
		System.out.println("Elige una opcion: ");
		System.out.println("1. Dar de alta un espectaculo");
		System.out.println("2. Cancelar un espectaculo");
		System.out.println("3. Actualizar datos de una espectaculo");
		System.out.println("4. Contabilizar venta de entradas para una sesión");
		System.out.println("5. Consultar localidades disponibles de un espectaculo");
		System.out.println("6. Buscar un espectaculo");
		System.out.println("7. Buscar espectaculos con entradas disponibles");
		System.out.println("8. Publicar una critica");
		System.out.println("9. Consultar criticas de un espectaculo");
		System.out.println("10. Eliminar una critica");
		System.out.println("11. Valorar la utilidad de una critica");
		System.out.println("12. Cerrar sesion");
	}
	
	public static void menuPrincipalEspectador(){
		System.out.println("Elige una opcion: ");
		System.out.println("1. Consultar localidades disponibles de un espectaculo");
		System.out.println("2. Buscar un espectaculo");
		System.out.println("3. Buscar espectaculos con entradas disponibles");
		System.out.println("4. Publicar una critica");
		System.out.println("5. Consultar criticas de un espectaculo");
		System.out.println("6. Eliminar una critica");
		System.out.println("7. Valorar la utilidad de una critica");
		System.out.println("8. Cerrar sesion");
	}
}
