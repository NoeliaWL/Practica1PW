package ejercicio2.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ejercicio2.data.Critica;
import ejercicio2.data.Espectador;
import ejercicio2.data.Valoraciones;
import ejercicio2.data.TipoUsuario;

/**
 * Clase GestorCriticas Esta clase tiene los metodos necesarios para gestionar
 * las criticas y los usuarios.
 * 
 * @author Antonio Cabezas Jarabo
 * @version 1.0, 24/09/2021
 */
public class Gestorcriticas {

	/**
	 * Instancia unica del gestor de criticas.
	 */
	private static Gestorcriticas instance = null;

	private ArrayList<Espectador> espectadores;
	private ArrayList<Critica> criticas;

	/**
	 * Constructor del gestor de criticas. Es privado para que solo haya un
	 * unico gestor.
	 */
	private Gestorcriticas() {
		espectadores = new ArrayList<Espectador>();
		criticas = new ArrayList<Critica>();
	}

	/**
	 * Este metodo controla que haya unicamente un gestor de criticas.
	 * 
	 * @return El gestor de criticas.
	 * @author Noelia Hinojosa Sanchez
	 */
	public static Gestorcriticas getInstance() {
		if (instance == null) {
			instance = new Gestorcriticas();
		}
		return instance;
	}

	/**
	 * Este metodo da de alta a un usuario en el sistema.
	 * 
	 * @param nombre
	 *            Nombre del usuario.
	 * @param apellidos
	 *            Apellidos del usuario.
	 * @param nick
	 *            Nombre de usuario o nick.
	 * @param correo
	 *            Correo electronico unico del usuario.
	 * @return Nada.
	 * @author Noelia Hinojosa Sanchez
	 */
	public void altaUsuario(String nombre, String apellidos, String nick, String correo, String contrasena,
			TipoUsuario tipo) {
		Espectador usuario = new Espectador();

		usuario.setNombre(nombre);
		usuario.setApellidos(apellidos);
		usuario.setUsuario(nick);
		usuario.setCorreo(correo);
		usuario.setContrasena(contrasena);
		usuario.setTipo(tipo);

		espectadores.add(usuario);
	}

	/**
	 * Este metodo da de baja a un usuario del sistema.
	 * 
	 * @param correo
	 *            Correo electronico del usuario.
	 * @return Nada.
	 * @author Noelia Hinojosa Sanchez
	 */
	public void bajaUsuario(String correo) {
		Espectador usuario = new Espectador();
		usuario = getUsuario(correo);
		if (usuario != null) {
			espectadores.remove(usuario);
		}
	}

	/**
	 * Este metodo devuelve los datos de un usuario concreto.
	 * 
	 * @param correo
	 *            Correo electronico del usuario.
	 * @return Datos del espectador encontrado.
	 * @author Antonio Cabezas Jarabo
	 */
	public Espectador getUsuario(String correo) {
		Espectador usuario = new Espectador();
		for (Espectador e : espectadores) {
			if (e.getCorreo().equals(correo)) {
				usuario = e;
			}
		}
		return usuario;
	}

	public String consultarDatosUsuarios() {
		StringBuffer buffer = new StringBuffer();

		for (Espectador e : espectadores) {
			buffer.append(e.getCorreo() + "\n");
			buffer.append(e.getNombre() + "\n");
			buffer.append(e.getApellidos() + "\n");
			buffer.append(e.getUsuario() + "\n");
			buffer.append("\n");
		}

		return buffer.toString();
	}

	/**
	 * Este metodo actualiza los datos de un usuario.
	 * 
	 * @param nombre
	 *            Nombre modificado del usuario.
	 * @param apellidos
	 *            Apellidos modificados del usuario.
	 * @param nick
	 *            Nickname modificado del usuario.
	 * @param correo
	 *            Correo electronico del usuario que se va a modificar.
	 * @return Nada.
	 * @author Antonio Cabezas Jarabo
	 */
	public void actualizarDatosUsuario(String nombre, String apellidos, String nick, String correo) {
		for (Espectador e : espectadores) {
			if (e.getCorreo().equals(correo)) {
				if (nombre != "") {
					e.setNombre(nombre);
				}
				if (apellidos != "") {
					e.setApellidos(apellidos);
				}
				if (nick != "") {
					e.setUsuario(nick);
				}
			}
		}
	}

	/**
	 * Este metodo crea una nueva critica de un espectaculo.
	 * 
	 * @param titulo
	 *            Titulo de la critica.
	 * @param puntuacion
	 *            Puntuacion asignada al espectaculo.
	 * @param resena
	 *            Comentario del espectaculo.
	 * @param correo
	 *            Correo electronico del usuario.
	 * @return Nada.
	 * @author Noelia Hinojosa Sanchez
	 */
	public void creaCritica(String titulo, int puntuacion, String resena, String correo, String tituloEspectaculo) {
		Critica critica = new Critica();
		critica.setTitulo(titulo);
		critica.setPuntuacion(puntuacion);
		critica.setResena(resena);
		critica.setCorreoPropietario(correo);
		critica.setTituloEspectaculo(tituloEspectaculo);
		criticas.add(critica);
	}

	/**
	 * Este metodo devuelve todas las criticas guardadas en el sistema, para
	 * posteriormente mostrarselas al usuario.
	 * 
	 * @return Lista con todas las criticas del sistema.
	 * @author Noelia Hinojosa Sanchez
	 */
	public String getCriticas() {
		StringBuffer buffer = new StringBuffer();
		int media = 0;
		for (Critica c : criticas) {
			buffer.append("Indice: " + criticas.indexOf(c) + "\n");
			buffer.append("Titulo: " + c.getTitulo() + " ");
			buffer.append("Puntuacion: " + c.getPuntuacion());
			buffer.append("Titulo Espectaculo: " + c.getTituloEspectaculo() + "\n");
			buffer.append("Resena: " + c.getResena());
			if (c.getValoraciones().size() != 0) {
				for (Valoraciones v : c.getValoraciones()) {
					media += v.getValoracion();
				}
				media /= c.getValoraciones().size();
				buffer.append("\nMedia de las valoraciones: " + media);
			} else {
				buffer.append("\nNo tiene valoraciones actualmente.");
			}
			buffer.append("\n\n");
		}
		return buffer.toString();
	}

	/**
	 * Este metodo devuelve todas las criticas guardadas en el sistema de un
	 * usuario concreto, para posteriormente mostrarselas al usuario.
	 * 
	 * @return Lista con todas las criticas de un usuario del sistema.
	 * @author Noelia Hinojosa Sanchez
	 */
	public String getCriticasUsuario(String correo) {
		StringBuffer buffer = new StringBuffer();
		int media = 0;
		for (Critica c : criticas) {
			if (c.getCorreoPropietario().equals(correo)) {
				buffer.append("Indice: " + criticas.indexOf(c) + "\n");
				buffer.append("Titulo: " + c.getTitulo() + " ");
				buffer.append("Puntuacion: " + c.getPuntuacion());
				buffer.append("Titulo Espectaculo: " + c.getTituloEspectaculo() + "\n");
				buffer.append("Resena: " + c.getResena());
				if (c.getValoraciones().size() != 0) {
					for (Valoraciones v : c.getValoraciones()) {
						media += v.getValoracion();
					}
					media /= c.getValoraciones().size();
					buffer.append("\nMedia de las valoraciones: " + media);
				} else {
					buffer.append("\nNo tiene valoraciones actualmente.");
				}
				buffer.append("\n");
			}
		}

		return buffer.toString();
	}

	public String getCriticasTitulo(String titulo) {
		StringBuffer buffer = new StringBuffer();
		int media = 0;
		for (Critica c : criticas) {
			if (c.getTituloEspectaculo().equals(titulo)) {
				buffer.append("Indice: " + criticas.indexOf(c) + "\n");
				buffer.append("Titulo: " + c.getTitulo() + "\n");
				buffer.append("Puntuacion: " + c.getPuntuacion() + "\n");
				buffer.append("Titulo Espectaculo: " + c.getTituloEspectaculo() + "\n");
				buffer.append("Resena: " + c.getResena());
				if (c.getValoraciones().size() != 0) {
					for (Valoraciones v : c.getValoraciones()) {
						media += v.getValoracion();
					}
					media /= c.getValoraciones().size();
					buffer.append("\nMedia de las valoraciones: " + media);
				} else {
					buffer.append("\nNo tiene valoraciones actualmente.");
				}
				buffer.append("\n");
			}
		}

		return buffer.toString();
	}

	/**
	 * Este metodo borra una critica .
	 * 
	 * @param index
	 *            Borra una critica.
	 * @author Rafael Piqueras Espinar
	 */

	public void borraCritica(int index) {
		criticas.remove(index);
	}

	/**
	 * Este metodo es para votar una critica de usuario.
	 * 
	 * @param index
	 * @param correo
	 *            Correo electronico del usuario.
	 * @param puntuacion
	 *            Puntua una critica.
	 * @author Rafael Piqueras Espinar
	 */
	public void votaCritica(int index, String correo, int puntuacion) {
		if (criticas.get(index).getCorreoPropietario().equals(correo)) {
			System.out.println("No puede votar su propia critica");
		} else if (comprobarValoraciones(index, correo)) {
			System.out.println("No se puede votar dos veces la misma critica.");
		} else {
			ArrayList<Valoraciones> valoraciones = criticas.get(index).getValoraciones();
			Valoraciones valoracion = new Valoraciones();
			valoracion.setCorreo(correo);
			valoracion.setValoracion(puntuacion);
			valoraciones.add(valoracion);
			criticas.get(index).setValoraciones(valoraciones);
		}
	}

	/**
	 * Este metodo devuelve la lista de criticas de usuario.
	 * 
	 * @param correo
	 *            Correo electronico del usuario.
	 * @return lista con las criticas de un usuario.
	 * @autor Rafael Piquers Espinar
	 */

	public ArrayList<Critica> buscaCritica(String correo) {
		ArrayList<Critica> criticaUsuario = new ArrayList<Critica>();
		for (Critica e : criticas)
			if (e.getCorreoPropietario().equals(correo))
				criticaUsuario.add(e);

		return criticaUsuario;
	}

	/**
	 * Este metodo comprueba que no se valora la misma critica dos veces.
	 * 
	 * @param index
	 *            Indice de la critica a valorar.
	 * @param correo
	 *            Correo del usuario que vota.
	 * @return True si ya se valoro por ese usuario anteriormente la critica.
	 * @author Noelia Hinojosa Sanchez
	 */
	public Boolean comprobarValoraciones(int index, String correo) {
		Boolean bandera = false;

		for (Valoraciones v : criticas.get(index).getValoraciones()) {
			if (v.getCorreo().equals(correo)) {
				bandera = true;
			}
		}

		return bandera;
	}

	/**
	 * Este metodo comprueba que el correo electronico pasado tenga un formato
	 * valido.
	 * 
	 * @param correo
	 *            Correo electronico a comprobar.
	 * @return True si el formato es correcto.
	 * @author Noelia Hinojosa Sanchez
	 */
	public Boolean comprobarFormatoCorreo(String correo) {
		Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z]{2,})$");
		Matcher comparador = patron.matcher(correo);

		return comparador.find();
	}

	/**
	 * Este metodo comprueba si un correo electronico ya se encuentra registrado
	 * en el sistema.
	 * 
	 * @param correo
	 *            Correo electronico a comprobar.
	 * @return True si el correo ya esta registrado.
	 * @author Noelia Hinojosa Sanchez
	 */
	public Boolean correoRegistrado(String correo) {
		Boolean bandera = false;

		for (Espectador e : espectadores) {
			if (e.getCorreo().equals(correo)) {
				bandera = true;
			}
		}

		return bandera;
	}

	/**
	 * Este metodo guardar en un fichero de texto plano los datos de las
	 * criticas y de los espectadores.
	 * 
	 * @param fileCriticas
	 *            Ubicacion del archivo donde guardar las criticas.
	 * @param fileEspectadores
	 *            Ubicacion del archivo donde guardar los espectadores.
	 * @return Nada.
	 */
	public void guardarFichero(String fileCriticas, String fileEspectadores) {
		FileWriter fc = null, fe = null;
		PrintWriter bufferc = null, buffere = null;

		try {
			fc = new FileWriter(fileCriticas);
			fe = new FileWriter(fileEspectadores);
			bufferc = new PrintWriter(fc);
			buffere = new PrintWriter(fe);

			for (Espectador e : espectadores) {
				buffere.println(e.getNombre() + ";" + e.getApellidos() + ";" + e.getUsuario() + ";" + e.getCorreo()
						+ ";" + e.getContrasena() + ";" + e.getTipo());
			}

			ArrayList<Valoraciones> val = new ArrayList<Valoraciones>();
			String valFichero = "";

			for (Critica c : criticas) {
				val = c.getValoraciones();
				for (Valoraciones v : val) {
					valFichero += v.getCorreo() + "/" + v.getValoracion() + ",";
				}

				bufferc.println(c.getCorreoPropietario() + ";" + c.getTitulo() + ";" + c.getPuntuacion() + ";"
						+ c.getResena() + ";" + c.getTituloEspectaculo() + ";" + valFichero);
				valFichero = "";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fe != null)
					fe.close();
				if (fc != null)
					fc.close();
				if (buffere != null)
					buffere.close();
				if (bufferc != null)
					bufferc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Este metodo cargar desde un fichero de texto plano los datos de las
	 * criticas y de los espectadores.
	 * 
	 * @param fileCriticas
	 *            Ubicacion del archivo donde guardar las criticas.
	 * @param fileEspectadores
	 *            Ubicacion del archivo donde guardar los espectadores.
	 * @return Nada.
	 */
	public void cargarFichero(String fileCriticas, String fileEspectadores) {
		File fe = new File(fileEspectadores);
		File fc = new File(fileCriticas);
		FileReader fre = null, frc = null;
		BufferedReader buffere = null, bufferc = null;
		String lineaFichero = null;
		String[] lineaCampos, lineaValoraciones, camposValoraciones;
		ArrayList<Valoraciones> vals = new ArrayList<Valoraciones>();
		Valoraciones val = new Valoraciones();

		try {
			if (!fe.exists()) {
				System.out.println("El fichero espectadores.txt no existe, se va a crear.");
				fe.createNewFile();
			} else {
				fre = new FileReader(fe);
				buffere = new BufferedReader(fre);

				System.out.println("buffer espectadores: " + buffere.readLine());
				Espectador espectador;

				while ((lineaFichero = buffere.readLine()) != null) {
				//lineaFichero = buffere.readLine();
					espectador = new Espectador();
					System.out.println("entra");

					lineaCampos = lineaFichero.split(";");

					espectador.setNombre(lineaCampos[0]);
					espectador.setApellidos(lineaCampos[1]);
					espectador.setUsuario(lineaCampos[2]);
					espectador.setCorreo(lineaCampos[3]);
					espectador.setContrasena(lineaCampos[4]);
					if(lineaCampos[5] == "ADMINISTRADOR"){
						espectador.setTipo(TipoUsuario.ADMINISTRADOR);
					}
					else if(lineaCampos[5] == "ESPECTADOR"){
						espectador.setTipo(TipoUsuario.ESPECTADOR);
					}

					espectadores.add(espectador);
				}
			}

			if (!fc.exists()) {
				System.out.println("El fichero criticas.txt no existe, se va a crear.");
				fc.createNewFile();
			} else {
				frc = new FileReader(fc);
				bufferc = new BufferedReader(frc);

				Critica critica;

				while ((lineaFichero = bufferc.readLine()) != null) {
					critica = new Critica();

					lineaCampos = lineaFichero.split(";");

					critica.setCorreoPropietario(lineaCampos[0]);
					critica.setTitulo(lineaCampos[1]);
					critica.setPuntuacion(Integer.parseInt(lineaCampos[2]));
					critica.setResena(lineaCampos[3]);
					critica.setTituloEspectaculo(lineaCampos[4]);

					lineaValoraciones = lineaCampos[5].split(",");
					for (int i = 0; i < lineaValoraciones.length; i++) {
						camposValoraciones = lineaValoraciones[i].split("/");
						val.setCorreo(camposValoraciones[0]);
						val.setValoracion(Integer.parseInt(camposValoraciones[1]));
						vals.add(val);
					}
					critica.setValoraciones(vals);

					criticas.add(critica);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fre != null)
					fre.close();
				if (frc != null)
					frc.close();
				if (buffere != null)
					buffere.close();
				if (bufferc != null)
					bufferc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public TipoUsuario Loginusuario(String correo, String contrasena) {
		TipoUsuario usuario = TipoUsuario.DEFAULT;
		for (Espectador e : espectadores) {
			if (e.getCorreo().equals(correo)) {
				if (e.getContrasena().equals(contrasena)) {
					usuario = e.getTipo();
				}
			}

		}
		return usuario;
	}

	public Boolean comprobarPropietario(String correo) {
		Boolean bandera = false;

		for (Critica c : criticas) {
			if (c.getCorreoPropietario().equals(correo)) {
				bandera = true;
			}
		}

		return bandera;
	}
	
	public Boolean tituloCriticaRegistrado(String titulo){
		Boolean bandera = false;
		
		for(Critica c : criticas){
			if(c.getTitulo().equals(titulo)){
				bandera = true;
			}
		}
		
		return bandera;
	}
}