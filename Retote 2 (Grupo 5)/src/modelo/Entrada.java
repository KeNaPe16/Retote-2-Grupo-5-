package modelo;

public class Entrada {

	private int id_Entrada, precio_Entrada, descuento, numero_Personas, id_Sesion, id_Compra;

	public Entrada(int id_Entrada, int precio_Entrada, int descuento, int numero_Personas, int id_Sesion,
			int id_Compra) {
		this.id_Entrada = id_Entrada;
		this.precio_Entrada = precio_Entrada;
		this.descuento = descuento;
		this.numero_Personas = numero_Personas;
		this.id_Sesion = id_Sesion;
		this.id_Compra = id_Compra;
	}

	public Entrada() {

	}

	public int getId_Entrada() {
		return id_Entrada;
	}

	public void setId_Entrada(int id_Entrada) {
		this.id_Entrada = id_Entrada;
	}

	public int getPrecio_Entrada() {
		return precio_Entrada;
	}

	public void setPrecio_Entrada(int precio_Entrada) {
		this.precio_Entrada = precio_Entrada;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	public int getNumero_Personas() {
		return numero_Personas;
	}

	public void setNumero_Personas(int numero_Personas) {
		this.numero_Personas = numero_Personas;
	}

	public int getId_Sesion() {
		return id_Sesion;
	}

	public void setId_Sesion(int id_Sesion) {
		this.id_Sesion = id_Sesion;
	}

	public int getId_Compra() {
		return id_Compra;
	}

	public void setId_Compra(int id_Compra) {
		this.id_Compra = id_Compra;
	}

	@Override
	public String toString() {
		return "Entrada (numero: " + this.id_Entrada + ") para la sesion numero: " + this.id_Sesion + "\nValida para " + this.numero_Personas
				+ " personas \nComprada por " + this.precio_Entrada + "€ con un descuento de " + this.descuento + "%";
	}

}
