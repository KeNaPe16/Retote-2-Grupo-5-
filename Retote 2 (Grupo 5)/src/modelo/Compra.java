package modelo;

import java.sql.Timestamp;

public class Compra {

	private int id_Compra; 
	private double precio_Compra;
	private Timestamp fecha_hora;
	private int descuento;
	private String dni;

	public Compra(int id_Compra, double precio_Compra, Timestamp fecha_hora, int descuento, String dni) {
		this.id_Compra = id_Compra;
		this.precio_Compra = precio_Compra;
		this.fecha_hora = fecha_hora;
		this.descuento = descuento;
		this.dni = dni;
	}

	public Compra() {

	}

	public int getId_Compra() {
		return id_Compra;
	}

	public void setId_Compra(int id_Compra) {
		this.id_Compra = id_Compra;
	}

	public double getPrecio_Compra() {
		return precio_Compra;
	}

	public void setPrecio_Compra(double precio_Compra) {
		this.precio_Compra = precio_Compra;
	}

	public Timestamp getFecha_hora() {
		return fecha_hora;
	}

	public void setFecha_hora(Timestamp fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "Compra (numero: " + this.id_Compra + ") de " + this.precio_Compra + "€ con un descuento de "
				+ this.descuento + "\nRealizada en el " + this.fecha_hora;
	}

}
