package modelo;

public class Cliente {

	private String dni, email, nombre_Apellidos, contraseña;

	public Cliente(String dni, String email, String nombre_Apellidos, String contraseña) {
		this.dni = dni;
		this.email = email;
		this.nombre_Apellidos = nombre_Apellidos;
		this.contraseña = contraseña;
	}

	public Cliente() {

	}

	public String getDNI() {
		return dni;
	}

	public void setDNI(String DNI) {
		this.dni = DNI;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre_Apellidos() {
		return nombre_Apellidos;
	}

	public void setNombre_Apellidos(String nombre_Apellidos) {
		this.nombre_Apellidos = nombre_Apellidos;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	@Override
	public String toString() {
		return "Cliente [id_Cliente=" + dni + ", email=" + email + ", nombre_Apellidos=" + nombre_Apellidos
				+ ", contraseña=" + contraseña + "]";
	}

}
