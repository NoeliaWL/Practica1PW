package pw;

public class Espectador {

	private String nombre;
	private String apellidos;
	private String usuario;
	private String  email;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre=nombre;
		
	}
	public String getApellidos() {
		return apellidos;
		
	}
	public void setApellidos(String apellidos) {
		this.apellidos=apellidos;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario=usuario;
	}
	public String getCorreo() {
		return email;
	}
	public  void setCorreo(String email)
	{
			this.email=email;
		
		
	}
}
