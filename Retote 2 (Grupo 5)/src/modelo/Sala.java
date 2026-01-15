package modelo;

public class Sala {

	private int id_Sala;
	private String nombre;

	public Sala(int id_Sala, String nombre) {
		this.id_Sala = id_Sala;
		this.nombre = nombre;
	}

	public Sala() {

	}

	public int getId_Sala() {
		return id_Sala;
	}

	public void setId_Sala(int id_Sala) {
		this.id_Sala = id_Sala;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Sala [id_Sala=" + id_Sala + ", nombre=" + nombre + "]";
	}

}
