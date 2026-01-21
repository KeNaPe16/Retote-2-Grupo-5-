package modelo;

public class CompraID {

	private int id_Compra;

	public CompraID(int id_Compra) {
		this.id_Compra = id_Compra;
	}

	public CompraID() {

	}

	public int getId_Compra() {
		return id_Compra;
	}

	public void setId_Compra(int id_Compra) {
		this.id_Compra = id_Compra;
	}

	@Override
	public String toString() {
		return "CompraID [id_Compra=" + id_Compra + "]";
	}

	
}
