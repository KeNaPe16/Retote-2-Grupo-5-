package modelo;

import java.sql.Time;

public class Sesion {

	private int id_Sesion;
	private Time hora_Inicio, hora_Fin;
	private String fecha;
	private int numero_Espectadores; 
	private double precio_Sesion;
	private int id_Sala, id_Pelicula;

	public Sesion(int id_Sesion, Time hora_Inicio, Time hora_Fin, String fecha, int numero_Espectadores,
			double precio_Sesion, int id_Sala, int id_Pelicula) {
		this.id_Sesion = id_Sesion;
		this.hora_Inicio = hora_Inicio;
		this.hora_Fin = hora_Fin;
		this.fecha = fecha;
		this.numero_Espectadores = numero_Espectadores;
		this.precio_Sesion = precio_Sesion;
		this.id_Sala = id_Sala;
		this.id_Pelicula = id_Pelicula;
	}

	public Sesion() {

	}

	public int getId_Sesion() {
		return id_Sesion;
	}

	public void setId_Sesion(int id_Sesion) {
		this.id_Sesion = id_Sesion;
	}

	public Time getHora_Inicio() {
		return hora_Inicio;
	}

	public void setHora_Inicio(Time hora_Inicio) {
		this.hora_Inicio = hora_Inicio;
	}

	public Time getHora_Fin() {
		return hora_Fin;
	}

	public void setHora_Fin(Time hora_Fin) {
		this.hora_Fin = hora_Fin;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getNumero_Espectadores() {
		return numero_Espectadores;
	}

	public void setNumero_Espectadores(int numero_Espectadores) {
		this.numero_Espectadores = numero_Espectadores;
	}

	public double getPrecio_Sesion() {
		return precio_Sesion;
	}

	public void setPrecio_Sesion(double precio_Sesion) {
		this.precio_Sesion = precio_Sesion;
	}

	public int getId_Sala() {
		return id_Sala;
	}

	public void setId_Sala(int id_Sala) {
		this.id_Sala = id_Sala;
	}

	public int getId_Pelicula() {
		return id_Pelicula;
	}

	public void setId_Pelicula(int id_Pelicula) {
		this.id_Pelicula = id_Pelicula;
	}

	@Override
	public String toString() {
		return "Sesion [id_Sesion=" + id_Sesion + ", hora_Inicio=" + hora_Inicio + ", hora_Fin=" + hora_Fin + ", fecha="
				+ fecha + ", numero_Espectadores=" + numero_Espectadores + ", precio_Sesion=" + precio_Sesion
				+ ", id_Sala=" + id_Sala + ", id_Pelicula=" + id_Pelicula + "]";
	}

}
