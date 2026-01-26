package modelo;

public class Pelicula {

	private int id_Pelicula, duracion;
	private String nombre;
	private double precio_Base;

	public Pelicula(int id_Pelicula, int duracion, String nombre, double precio_Base) {
		this.id_Pelicula = id_Pelicula;
		this.duracion = duracion;
		this.nombre = nombre;
		this.precio_Base = precio_Base;
	}

	public Pelicula() {

	}

	public int getId_Pelicula() {
		return id_Pelicula;
	}

	public void setId_Pelicula(int id_Pelicula) {
		this.id_Pelicula = id_Pelicula;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio_Base() {
		return precio_Base;
	}

	public void setPrecio_Base(double precio_Base) {
		this.precio_Base = precio_Base;
	}

	@Override
	public String toString() {
		return "Pelicula [id_Pelicula=" + id_Pelicula + ", duracion=" + duracion + ", nombre=" + nombre
				+ ", precio_Base=" + precio_Base + "]";
	}

}
