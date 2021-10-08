package ejercicio2.interfaz;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import ejercicio2.business.Gestorcriticas;
import ejercicio2.business.Gestorespectaculos;
import ejercicio2.data.Categoriaevento;
import ejercicio2.data.Sesiones;
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
		int opcionInicio = 0, opcion = 0, tipo = 0, numero = 0, subtipo = 0, subopcion = 0;
		Boolean bandera = false;
		TipoUsuario tipoUsuario = TipoUsuario.DEFAULT;
		String nombre = "", apellidos = "", nick = "", correo = "", contrasena = "", resena = "";
		String titulo = "", descripcion = "", fecha = "", fechaFin = "", hora = "";
		Categoriaevento categoria = Categoriaevento.DEFAULT;
		Sesiones sesion = new Sesiones();
		int index = 0, puntuacion = 0, indexSesion = 0, actualizar = 0, sesionOpcion, venta = 0;
		String buffer = "";
		ArrayList<Sesiones> sesiones = new ArrayList<Sesiones>();

		try {
			if (f.exists()) {
				lector = new BufferedReader(new FileReader(f));
			} else {
				System.out.println("El fichero fichero.properties no existe, se va a crear.");
				PrintWriter escritor = new PrintWriter((new FileWriter(f)));
				escritor.println("CRITICAS: ./criticas.txt");
				escritor.println("ESPECTADORES: ./espectadores.txt");
				escritor.println("ESPECTACULOSPUNTUALES: ./espectaculospuntuales.txt");
				escritor.println("ESPECTACULOSPASEMULTIPLE: ./espectaculospasemultiple.txt");
				escritor.println("ESPECTACULOSTEMPORADA: ./espectaculostemporada.txt");
				escritor.close();
				lector = new BufferedReader(new FileReader(f));
			}
			prop.load(lector);
			gestorespectaculos.cargarFichero(prop.getProperty("ESPECTACULOSPUNTUALES"),
					prop.getProperty("ESPECTACULOSPASEMULTIPLE"), prop.getProperty("ESPECTACULOSTEMPORADA"));
			gestorcriticas.cargarFichero(prop.getProperty("CRITICAS"), prop.getProperty("ESPECTADORES"));

			do {
				menuInicio();
				opcionInicio = Integer.parseInt(reader.nextLine());

				switch (opcionInicio) {
				case 1:
					System.out.println("Introduzca su correo electronico: ");
					correo = reader.nextLine();
					System.out.println("Introduzca su contrasena: ");
					contrasena = reader.nextLine();

					if (gestorcriticas.comprobarFormatoCorreo(correo)) {
						if (gestorcriticas.correoRegistrado(correo)) {
							tipoUsuario = gestorcriticas.Loginusuario(correo, contrasena);
						} else {
							System.out.println("Correo electronico no registrado");
							System.out.println("¿Desea registrarlo? (1.Si / 2.No)");
							numero = Integer.parseInt(reader.nextLine());

							if (numero == 1) {
								// Registrarse
								System.out.println("Introduzca su nombre: ");
								nombre = reader.nextLine().toUpperCase();
								System.out.println("Introduzca sus apellidos: ");
								apellidos = reader.nextLine().toUpperCase();
								System.out.println("Introduzca su nick(nombre de usuario): ");
								nick = reader.nextLine();
								System.out.println("Introduzca su contrasena: ");
								contrasena = reader.nextLine();
								System.out.println("Seleccione su tipo de usuario: ");
								System.out.println("1. Administrador");
								System.out.println("2. Espectador");
								tipo = Integer.parseInt(reader.nextLine());

								if (gestorcriticas.comprobarFormatoCorreo(correo)) {
									if (!gestorcriticas.correoRegistrado(correo)) {
										TipoUsuario tipoUsuarioRegistro = TipoUsuario.ADMINISTRADOR;
										if (tipo == 1) {
											tipoUsuarioRegistro = TipoUsuario.ADMINISTRADOR;
										} else if (tipo == 2) {
											tipoUsuarioRegistro = TipoUsuario.ESPECTADOR;
										}
										gestorcriticas.altaUsuario(nombre, apellidos, nick, correo, contrasena,
												tipoUsuarioRegistro);
									} else {
										System.out.println("Correo electronico ya registrado");
									}
								} else {
									System.out.println("Formato de correo no valido");
								}
							}
						}
					} else {
						System.out.println("Formato de correo no valido");
					}
					break;

				case 2:
					// Registrarse
					System.out.println("Introduzca su nombre: ");
					nombre = reader.nextLine().toUpperCase();
					System.out.println("Introduzca sus apellidos: ");
					apellidos = reader.nextLine().toUpperCase();
					System.out.println("Introduzca su nick(nombre de usuario): ");
					nick = reader.nextLine();
					System.out.println("Introduzca su correo electronico: ");
					correo = reader.nextLine();
					System.out.println("Introduzca su contrasena: ");
					contrasena = reader.nextLine();
					System.out.println("Seleccione su tipo de usuario: ");
					System.out.println("1. Administrador");
					System.out.println("2. Espectador");
					tipo = Integer.parseInt(reader.nextLine());

					if (gestorcriticas.comprobarFormatoCorreo(correo)) {
						if (!gestorcriticas.correoRegistrado(correo)) {
							TipoUsuario tipoUsuarioRegistro = TipoUsuario.ADMINISTRADOR;
							if (tipo == 1) {
								tipoUsuarioRegistro = TipoUsuario.ADMINISTRADOR;
							} else if (tipo == 2) {
								tipoUsuarioRegistro = TipoUsuario.ESPECTADOR;
							}
							gestorcriticas.altaUsuario(nombre, apellidos, nick, correo, contrasena,
									tipoUsuarioRegistro);
						} else {
							System.out.println("Correo electronico ya registrado");
						}
					} else {
						System.out.println("Formato de correo no valido");
					}
					break;

				case 3:
					// Salir del programa
					System.out.println("Programa finalizado.");
					break;
				}

				if (bandera && (tipoUsuario == TipoUsuario.ADMINISTRADOR)) {
					do {
						menuPrincipalAdmin();
						opcion = Integer.parseInt(reader.nextLine());

						switch (opcion) {
						case 1:
							//Alta de espectaculo
							menuEspectaculos();
							tipo = Integer.parseInt(reader.nextLine());

							switch (tipo) {
							case 1:
								// Puntual
								System.out.println("Introduzca el titulo del espectaculo: ");
								titulo = reader.nextLine();
								System.out.println("Introduzca la descripcion del espectaculo: ");
								descripcion = reader.nextLine();

								menuCategoria();
								switch (tipo) {
								case 1:
									categoria = Categoriaevento.MONOLOGO;
									break;

								case 2:
									categoria = Categoriaevento.CONCIERTO;
									break;

								case 3:
									categoria = Categoriaevento.OBRADETEATRO;
									break;
								}

								System.out.println("Introduzca una fecha de representacion del espectaculo: ");
								fecha = reader.nextLine();
								System.out.println("Introduzca una hora de representacion del espectaculo: ");
								hora = reader.nextLine();
								sesion.setFecha(LocalDate.parse(fecha));
								sesion.setHora(LocalTime.parse(hora));
								gestorespectaculos.Altaespectaculopasepuntual(titulo, descripcion, categoria, sesion);
								break;

							case 2:
								// Pase multiple
								System.out.println("Introduzca el titulo del espectaculo: ");
								titulo = reader.nextLine();
								System.out.println("Introduzca la descripcion del espectaculo: ");
								descripcion = reader.nextLine();

								menuCategoria();
								switch (tipo) {
								case 1:
									// Monologo
									categoria = Categoriaevento.MONOLOGO;
									break;

								case 2:
									// Concierto
									categoria = Categoriaevento.CONCIERTO;
									break;

								case 3:
									// obra de teatro
									categoria = Categoriaevento.OBRADETEATRO;
									break;
								}

								do {
									System.out.println("Introduzca una fecha de representacion del espectaculo: ");
									fecha = reader.nextLine();
									System.out.println("Introduzca una hora de representacion del espectaculo: ");
									hora = reader.nextLine();
									sesion.setFecha(LocalDate.parse(fecha));
									sesion.setHora(LocalTime.parse(hora));
									sesiones.add(sesion);
									System.out.println("¿Desea introducir otra fecha de representacion? (1.Si / 2.No)");
									subopcion = Integer.parseInt(reader.nextLine());
								} while (subopcion != 2);
								gestorespectaculos.Altaespectaculopasemultiple(titulo, descripcion, categoria,
										sesiones);
								break;

							case 3:
								// De temporada
								System.out.println("Introduzca el titulo del espectaculo: ");
								titulo = reader.nextLine();
								System.out.println("Introduzca la descripcion del espectaculo: ");
								descripcion = reader.nextLine();

								menuCategoria();
								tipo = Integer.parseInt(reader.nextLine());
								switch (tipo) {
								case 1:
									// Monologo
									categoria = Categoriaevento.MONOLOGO;
									break;

								case 2:
									// Concierto
									categoria = Categoriaevento.CONCIERTO;
									break;

								case 3:
									// obra de teatro
									categoria = Categoriaevento.OBRADETEATRO;
									break;
								}

								System.out.println("Introduzca la fecha de inicio de representacion del espectaculo: ");
								fecha = reader.nextLine();
								System.out.println(
										"Introduzca la fecha de finalizacion de representacion del espectaculo: ");
								fechaFin = reader.nextLine();
								System.out.println("Introduzca una hora de representacion del espectaculo: ");
								hora = reader.nextLine();
								gestorespectaculos.Altaespectaculotemporada(titulo, descripcion, categoria,
										LocalDate.parse(fecha), LocalDate.parse(fechaFin), LocalTime.parse(hora));
								break;
							}
							break;

						case 2:
							//Cancelar sesion o sesiones de espectaculos
							menuCancelar();
							tipo = Integer.parseInt(reader.nextLine());

							System.out.println("Introduzca el titulo del espectaculo: ");
							titulo = reader.nextLine();

							switch (tipo) {
							case 1:
								gestorespectaculos.Mostrarsesiones();
								System.out.println("Introduzca el indice del espectaculo que desea cancelar: ");
								index = Integer.parseInt(reader.nextLine());
								gestorespectaculos.Cancelarespectaculotodos(index);
								break;

							case 2:
								gestorespectaculos.Mostrarsesiones();
								System.out.println("Introduzca el indice del espectaculo que desea cancelar: ");
								index = Integer.parseInt(reader.nextLine());
								System.out.println("Introduzca el indice de la sesion que desea cancelar: ");
								indexSesion = Integer.parseInt(reader.nextLine());
								gestorespectaculos.Cancelarespectaculosesion(index, indexSesion);
								break;
							}
							break;

						case 3:
							// Actualizar datos espectaculo
							gestorespectaculos.Mostrarsesiones();
							System.out.println("Introduzca el indice del espectaculo que desea actualizar: ");
							index = Integer.parseInt(reader.nextLine());
							
							menuActualizar();
							tipo = Integer.parseInt(reader.nextLine());
							sesion.setFecha(null);
							sesion.setHora(null);
							descripcion = "";
							sesiones.clear();
							fecha = "1000-01-01";
							fechaFin = "1000-01-01";
							hora = "00:00";
							if(gestorespectaculos.tipoEspectaculo(index) == 1){
								if(tipo == 1){
									System.out.println("Introduzca la nueva descripcion: ");
									descripcion = reader.nextLine();
									gestorespectaculos.Actualizardatosespectaculo(index, descripcion, sesiones);
								}
								else if(tipo == 2){
									System.out.println("Introduzca la nueva fecha: ");
									fecha = reader.nextLine();
									System.out.println("Introduzca la nueva hora: ");
									hora = reader.nextLine();
									sesion.setFecha(LocalDate.parse(fecha));
									sesion.setHora(LocalTime.parse(hora));
									gestorespectaculos.Actualizardatosespectaculo(index, descripcion, sesiones);
								}
								else if(tipo == 3){
									System.out.println("Introduzca la nueva descripcion: ");
									descripcion = reader.nextLine();
									System.out.println("Introduzca la nueva fecha: ");
									fecha = reader.nextLine();
									System.out.println("Introduzca la nueva hora: ");
									hora = reader.nextLine();
									sesion.setFecha(LocalDate.parse(fecha));
									sesion.setHora(LocalTime.parse(hora));
									gestorespectaculos.Actualizardatosespectaculo(index, descripcion, sesiones);
								}
							}
							else if(gestorespectaculos.tipoEspectaculo(index) == 2){
								if(tipo == 1){
									System.out.println("Introduzca la nueva descripcion: ");
									descripcion = reader.nextLine();
									gestorespectaculos.Actualizardatosespectaculo(index, descripcion, sesiones);
								}
								else if(tipo == 2){
									menuSesionesActualizar();
									actualizar = Integer.parseInt(reader.nextLine());
									
									gestorespectaculos.MostrarSesionesPaseMultiple(index);
									switch(actualizar){
									case 1:
										//Modificar
										System.out.println("Introduzca el indice de la sesion a modificar: ");
										sesionOpcion = Integer.parseInt(reader.nextLine());
										sesiones = gestorespectaculos.getSesionesMultiple(index);
										System.out.println("Introduzca la nueva fecha de la sesion: ");
										fecha = reader.nextLine();
										System.out.println("Introduzca la nueva hora de la sesion: ");
										hora = reader.nextLine();
										sesion.setFecha(LocalDate.parse(fecha));
										sesion.setHora(LocalTime.parse(hora));
										sesiones.remove(sesionOpcion);
										sesiones.add(sesion);
										gestorespectaculos.Actualizardatosespectaculo(index, descripcion, sesiones);
										break;
										
									case 2:
										//eliminar
										System.out.println("Introduzca el indice de la sesion a eliminar: ");
										sesionOpcion = Integer.parseInt(reader.nextLine());
										sesiones = gestorespectaculos.getSesionesMultiple(index);
										sesiones.remove(sesionOpcion);
										gestorespectaculos.Actualizardatosespectaculo(index, descripcion, sesiones);
										break;
										
									case 3:
										//añadir
										System.out.println("Introduzca el indice de la sesion a eliminar: ");
										sesionOpcion = Integer.parseInt(reader.nextLine());
										sesiones = gestorespectaculos.getSesionesMultiple(index);
										System.out.println("Introduzca la fecha de la sesion: ");
										fecha = reader.nextLine();
										System.out.println("Introduzca la hora de la sesion: ");
										hora = reader.nextLine();
										sesion.setFecha(LocalDate.parse(fecha));
										sesion.setHora(LocalTime.parse(hora));
										sesiones.add(sesion);
										gestorespectaculos.Actualizardatosespectaculo(index, descripcion, sesiones);
										break;
									}
								}
								else if(tipo == 3){
									System.out.println("Introduzca la nueva descripcion: ");
									descripcion = reader.nextLine();
									menuSesionesActualizar();
									actualizar = Integer.parseInt(reader.nextLine());
									
									gestorespectaculos.MostrarSesionesPaseMultiple(index);
									switch(actualizar){
									case 1:
										//Modificar
										System.out.println("Introduzca el indice de la sesion a modificar: ");
										sesionOpcion = Integer.parseInt(reader.nextLine());
										sesiones = gestorespectaculos.getSesionesMultiple(index);
										System.out.println("Introduzca la nueva fecha de la sesion: ");
										fecha = reader.nextLine();
										System.out.println("Introduzca la nueva hora de la sesion: ");
										hora = reader.nextLine();
										sesion.setFecha(LocalDate.parse(fecha));
										sesion.setHora(LocalTime.parse(hora));
										sesiones.remove(sesionOpcion);
										sesiones.add(sesion);
										gestorespectaculos.Actualizardatosespectaculo(index, descripcion, sesiones);
										break;
										
									case 2:
										//eliminar
										System.out.println("Introduzca el indice de la sesion a eliminar: ");
										sesionOpcion = Integer.parseInt(reader.nextLine());
										sesiones = gestorespectaculos.getSesionesMultiple(index);
										sesiones.remove(sesionOpcion);
										gestorespectaculos.Actualizardatosespectaculo(index, descripcion, sesiones);
										break;
										
									case 3:
										//añadir
										System.out.println("Introduzca el indice de la sesion a eliminar: ");
										sesionOpcion = Integer.parseInt(reader.nextLine());
										sesiones = gestorespectaculos.getSesionesMultiple(index);
										System.out.println("Introduzca la fecha de la sesion: ");
										fecha = reader.nextLine();
										System.out.println("Introduzca la hora de la sesion: ");
										hora = reader.nextLine();
										sesion.setFecha(LocalDate.parse(fecha));
										sesion.setHora(LocalTime.parse(hora));
										sesiones.add(sesion);
										gestorespectaculos.Actualizardatosespectaculo(index, descripcion, sesiones);
										break;
									}
								}
							}
							else if(gestorespectaculos.tipoEspectaculo(index) == 3){
								if(tipo == 1){
									System.out.println("Introduzca la nueva descripcion: ");
									descripcion = reader.nextLine();
									gestorespectaculos.Actualizardatosespectaculo(index, descripcion, LocalDate.parse(fecha), LocalDate.parse(fechaFin), LocalTime.parse(hora));
								}
								else if(tipo == 2){
									System.out.println("Introduzca la nueva fecha de inicio: ");
									fecha = reader.nextLine();
									System.out.println("Introduzca la nueva fecha de fin: ");
									fechaFin = reader.nextLine();
									System.out.println("Introduzca la nueva hora: ");
									hora = reader.nextLine();
									gestorespectaculos.Actualizardatosespectaculo(index, descripcion, LocalDate.parse(fecha), LocalDate.parse(fechaFin), LocalTime.parse(hora));
								}
								else if(tipo == 3){
									System.out.println("Introduzca la nueva descripcion: ");
									descripcion = reader.nextLine();
									System.out.println("Introduzca la nueva fecha de inicio: ");
									fecha = reader.nextLine();
									System.out.println("Introduzca la nueva fecha de fin: ");
									fechaFin = reader.nextLine();
									System.out.println("Introduzca la nueva hora: ");
									hora = reader.nextLine();
									gestorespectaculos.Actualizardatosespectaculo(index, descripcion, LocalDate.parse(fecha), LocalDate.parse(fechaFin), LocalTime.parse(hora));
								}
							}
							break;

						case 4:
							// Contabilizar venta de entradas para una sesion
							buffer = gestorespectaculos.Mostrarsesiones();
							System.out.println(buffer);
							System.out.println("Introduzca indice del espectaculo: ");
							index = Integer.parseInt(reader.nextLine());
							System.out.println("Introduzca indice de la sesion: ");
							sesionOpcion = Integer.parseInt(reader.nextLine());
							System.out.println("Las entradas vendidas para esa sesion del espectaculo son: " + gestorespectaculos.ContabilizarVentaEntradas(index, sesionOpcion));
							break;

						case 5:
							// Consultar entradas disponibles de un espectaculo
							buffer = gestorespectaculos.Mostrarsesiones();
							System.out.println(buffer);
							System.out.println("Introduzca indice del espectaculo: ");
							index = Integer.parseInt(reader.nextLine());
							System.out.println("Introduzca indice de la sesion: ");
							sesionOpcion = Integer.parseInt(reader.nextLine());
							System.out.println("Las entradas disponibles para esa sesion del espectaculo son: " + gestorespectaculos.ConsultarEntradasDisponibles(index, sesionOpcion));
							break;

						case 6:
							menuBuscar();
							tipo = Integer.parseInt(reader.nextLine());

							switch (tipo) {
							case 1:
								// Buscar por titulo
								System.out.println("Introduzca el titulo del espectaculo: ");
								titulo = reader.nextLine();
								buffer = gestorespectaculos.Buscarespectaculo(titulo);
								System.out.println(buffer);
								break;

							case 2:
								// Buscar por categoria
								menuCategoria();
								subtipo = Integer.parseInt(reader.nextLine());

								switch (subtipo) {
								case 1:
									// monologo
									buffer = gestorespectaculos.Buscarespectaculo(Categoriaevento.MONOLOGO);
									System.out.println(buffer);
									break;

								case 2:
									// concierto
									buffer = gestorespectaculos.Buscarespectaculo(Categoriaevento.CONCIERTO);
									System.out.println(buffer);
									break;

								case 3:
									// obra de teatro
									buffer = gestorespectaculos.Buscarespectaculo(Categoriaevento.OBRADETEATRO);
									System.out.println(buffer);
									break;
								}
								break;
							}
							break;

						case 7:
							// Buscar espectaculos con entradas disponibles (con o sin indicar categoria)
							System.out.println("¿Buscar entradas disponibles segun categoria del espectaculo? (1.Si / 2.No)");
							subopcion = Integer.parseInt(reader.nextLine());
							if(subopcion == 1){
								menuCategoria();
								tipo = Integer.parseInt(reader.nextLine());
								switch (tipo) {
								case 1:
									// Monologo
									categoria = Categoriaevento.MONOLOGO;
									break;

								case 2:
									// Concierto
									categoria = Categoriaevento.CONCIERTO;
									break;

								case 3:
									// obra de teatro
									categoria = Categoriaevento.OBRADETEATRO;
									break;
								}
								buffer = gestorespectaculos.BuscarEspectaculosEntradasDisponibles(categoria);
								System.out.println(buffer);
							}
							else if(subopcion == 2){
								buffer = gestorespectaculos.BuscarEspectaculosEntradasDisponibles();
								System.out.println(buffer);
							}
							break;

							
						case 8:
							//Venta de entradas
							buffer = gestorespectaculos.Mostrarsesiones();
							System.out.println(buffer);
							System.out.println("Introduzca indice del espectaculo: ");
							index = Integer.parseInt(reader.nextLine());
							System.out.println("Introduzca indice de la sesion: ");
							sesionOpcion = Integer.parseInt(reader.nextLine());
							System.out.println("Introduzca el numero de entradas que desea comprar: ");
							venta = Integer.parseInt(reader.nextLine());
							gestorespectaculos.VentaEntradas(index, indexSesion, venta);
							break;
							
						case 9:
							// Publicar critica de un espectaculo celebrado
							System.out.println("Introduzca su correo de la critica: ");
							correo = reader.nextLine();
							
							if (gestorcriticas.comprobarFormatoCorreo(correo)) {
								if (gestorcriticas.correoRegistrado(correo)) {
									buffer = gestorespectaculos.Mostrarsesiones();
									System.out.println(buffer);
									System.out.println("Introduzca indice del espectaculo: ");
									index = Integer.parseInt(reader.nextLine());
									System.out.println("Introduzca indice de la sesion: ");
									sesionOpcion = Integer.parseInt(reader.nextLine());
									System.out.println("Introduzca el titulo de la critica: ");
									titulo = reader.nextLine();
									System.out.println("Introduzca la puntuacion: ");
									puntuacion = Integer.parseInt(reader.nextLine());
									System.out.println("Introduzca la resena de la critica: ");
									resena = reader.nextLine();
									
									gestorespectaculos.PublicarCritica(index, indexSesion, titulo, puntuacion, resena, correo);
								}
							}
							break;

						case 10:
							// Consultar criticas de un espectaculo segun su titulo
							System.out.println("Introduzca el titulo del espectaculo: ");
							titulo = reader.nextLine();
							buffer = gestorespectaculos.Consultarcriticas(titulo);
							System.out.println(buffer);
							break;

						case 11:
							// Eliminar critica de un espectaculo (solo el
							// propietario de la critica)
							System.out.println("Introduzca el titulo del espectaculo: ");
							titulo = reader.nextLine();
							buffer = gestorespectaculos.Consultarcriticas(titulo);
							System.out.println(buffer);
							System.out.println("Introduzca el indice de la critica que desea votar: ");
							index = Integer.parseInt(reader.nextLine());
							System.out.println("Introduzca su correo electronico: ");
							correo = reader.nextLine();

							if (gestorcriticas.comprobarFormatoCorreo(correo)) {
								if (gestorcriticas.correoRegistrado(correo)) {
									gestorespectaculos.EliminarCritica(index, correo);
								} else {
									System.out.println("Correo electronico no registrado");
								}
							} else {
								System.out.println("Formato de correo no valido");
							}
							break;

						case 12:
							// Valorar una critica de otro usuario
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

						case 13:
							System.out.println("Sesion cerrada.");
							break;

						default:
							System.out.println("Opcion no valida, vuelva a intentarlo.");
						}
					} while (opcion != 13);
				} else if (bandera && (tipoUsuario == TipoUsuario.ESPECTADOR)) {
					do {
						menuPrincipalEspectador();
						opcion = Integer.parseInt(reader.nextLine());

						switch (opcion) {
						case 1:
							// Consultar localidades disponibles de un
							// espectaculo
							break;

						case 2:
							menuBuscar();
							tipo = Integer.parseInt(reader.nextLine());

							switch (tipo) {
							case 1:
								// Buscar por titulo
								System.out.println("Introduzca el titulo del espectaculo: ");
								titulo = reader.nextLine();
								buffer = gestorespectaculos.Buscarespectaculo(titulo);
								System.out.println(buffer);
								break;

							case 2:
								// Buscar por categoria
								menuCategoria();
								subtipo = Integer.parseInt(reader.nextLine());

								switch (subtipo) {
								case 1:
									// monologo
									buffer = gestorespectaculos.Buscarespectaculo(Categoriaevento.MONOLOGO);
									System.out.println(buffer);
									break;

								case 2:
									// concierto
									buffer = gestorespectaculos.Buscarespectaculo(Categoriaevento.CONCIERTO);
									System.out.println(buffer);
									break;

								case 3:
									// obra de teatro
									buffer = gestorespectaculos.Buscarespectaculo(Categoriaevento.OBRADETEATRO);
									System.out.println(buffer);
									break;
								}
								break;
							}
							break;

						case 3:
							// Buscar espectaculos con entradas disponibles (con
							// o sin indicar categoria)
							break;

						case 4:
							// Publicar critica de un espectaculo celebrado
							break;

						case 5:
							// Consultar criticas de un espectaculo segun su
							// titulo
							System.out.println("Introduzca el titulo del espectaculo: ");
							titulo = reader.nextLine();

							buffer = gestorespectaculos.Consultarcriticas(titulo);
							System.out.println(buffer);
							break;

						case 6:
							// Eliminar critica de un espectaculo (solo el
							// propietario de la critica)
							break;

						case 7:
							// Valorar una critica de otro usuario
							break;

						case 8:
							System.out.println("Sesion cerrada.");
							break;

						default:
							System.out.println("Opcion no valida, vuelve a intentarlo.");
						}
					} while (opcion != 8);
				}
			} while (opcionInicio != 3 || bandera == false);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Cerrar lo que se necesite ficheros o scanner
			reader.close();
		}
	}

	public static void menuInicio() {
		System.out.println("Elige una opcion: ");
		System.out.println("1. Iniciar de sesion");
		System.out.println("2. Registrarse");
		System.out.println("3. Salir del programa");
	}

	public static void menuPrincipalAdmin() {
		System.out.println("Elige una opcion: ");
		System.out.println("1. Dar de alta un espectaculo");
		System.out.println("2. Cancelar un espectaculo");
		System.out.println("3. Actualizar datos de una espectaculo");
		System.out.println("4. Contabilizar venta de entradas para una sesión");
		System.out.println("5. Consultar localidades disponibles de un espectaculo");
		System.out.println("6. Buscar un espectaculo");
		System.out.println("7. Buscar espectaculos con entradas disponibles");
		System.out.println("8. Venta de entradas");
		System.out.println("9. Publicar una critica");
		System.out.println("10. Consultar criticas de un espectaculo");
		System.out.println("11. Eliminar una critica");
		System.out.println("12. Valorar la utilidad de una critica");
		System.out.println("13. Cerrar sesion");
	}

	public static void menuPrincipalEspectador() {
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

	public static void menuEspectaculos() {
		System.out.println("Elige el tipo de espectaculo que desea crear: ");
		System.out.println("1. Espectaculo puntual");
		System.out.println("2. Espectaculo de pase multiple");
		System.out.println("3. Espectaculo de temporada");
	}

	public static void menuCancelar() {
		System.out.println("Elige una opcion: ");
		System.out.println("1. Cancelar todas las sesiones del espectaculo");
		System.out.println("2. Cancelar una sesion del espectaculo");
	}

	public static void menuBuscar() {
		System.out.println("Elige una opcion: ");
		System.out.println("1. Buscar por titulo del espectaculo");
		System.out.println("2. Buscar por la categoria del espectaculo");
	}

	public static void menuCategoria() {
		System.out.println("Elige una opcion: ");
		System.out.println("1. Monologo");
		System.out.println("2. Concierto");
		System.out.println("3. Obra de teatro");
	}
	
	public static void menuActualizar(){
		System.out.println("Elige una opcion: ");
		System.out.println("1. Descripcion");
		System.out.println("2. Sesiones");
		System.out.println("3. Ambos");
	}
	
	public static void menuSesionesActualizar(){
		System.out.println("Elige una opcion: ");
		System.out.println("1. Modificar sesion");
		System.out.println("2. Eliminar sesion");
		System.out.println("3. Añadir sesion");
	}
}
