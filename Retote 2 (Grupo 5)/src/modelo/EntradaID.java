package modelo;

public class EntradaID {

	private int id_Entrada;

	public EntradaID(int id_Entrada) {
		this.id_Entrada = id_Entrada;
	}

	public EntradaID() {
	}

	public int getId_Entrada() {
		return id_Entrada;
	}

	public void setId_Entrada(int id_Entrada) {
		this.id_Entrada = id_Entrada;
	}

	@Override
	public String toString() {
		return "EntradaID [id_Entrada=" + id_Entrada + "]";
	}

}
