package ejercicio2.interfaz;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

import ejercicio2.business.Gestorcriticas;
import ejercicio2.business.Gestorespectaculos;

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
		int opcionInicio = 0, opcion = 0;
		Boolean bandera = false;
		
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
			//gestorespectaculos.cargarFichero(prop.get("ESPECTACULOS"));
			//cargar ficheros espectadores.txt - criticas.txt desde el Gestor de Criticas
			//gestorcriticas.cargarFichero(prop.get("CRITICAS"), prop.get("ESPECTADORES"));
			
			//Menu login o registro
			do{
				menuInicio();
				opcionInicio = Integer.parseInt(reader.nextLine());
				
				switch(opcionInicio){ //Desde el Gestor de Criticas
					case 1:
						//Login ¿con usuario o correo + contrasena?
						//Login correcto -> bandera=true
						break;
						
					case 2:
						//Registrarse
						break;
						
					case 3:
						//Salir del programa
						System.out.println("Programa finalizado.");
						break;
				}
			}while(opcionInicio != 3 || bandera == false);
			
			if(bandera){
				do{
					//menuPrincipal();
					opcion = Integer.parseInt(reader.nextLine());
				}while(opcion != 12);
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
		System.out.println("8. Publicar una critica ");
	}
}
