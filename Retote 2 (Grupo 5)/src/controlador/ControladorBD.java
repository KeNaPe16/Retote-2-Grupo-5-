package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Cliente;
import modelo.CompraID;
import modelo.EntradaID;

public class ControladorBD {

	private Connection conexion;
	private String nombreBD;

	public ControladorBD(String nombreBD) {
		this.nombreBD = nombreBD;
	}

	public boolean iniciarConexion() {
		boolean conexionRealizada = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + this.nombreBD, "root", "");
			conexionRealizada = true;

		} catch (ClassNotFoundException e) {

			System.out.println("No se encontro la libreria de sqlconnection.jar ");

		} catch (SQLException e) {

			System.out.println("No se encontro la BD " + this.nombreBD);

		}

		return conexionRealizada;
	}

	public boolean cerrarConexion() {
		boolean conexionCerrada = false;

		try {
			if (conexion != null && !conexion.isClosed()) {
				conexion.close();
				conexionCerrada = true;
			}
		} catch (SQLException e) {
			System.out.println("No hay conexion con la BD");

		}

		return conexionCerrada;

	}

	public ArrayList<Cliente> datosCliente() {
		String query = "SELECT DNI, Email, Nombre_Apellidos, CAST(AES_DECRYPT(Contraseña, 'cineadmin') AS CHAR(255)) AS Contraseña_Descifrada\r\n"
				+ "FROM Cliente";
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		try {
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery(query);

			while (resultado.next()) {
				String dni = resultado.getString(1);
				String email = resultado.getString(2);
				String nombre_Apellidos = resultado.getString(3);
				String contraseña = resultado.getString(4);

				Cliente nuevoCliente = new Cliente(dni, email, nombre_Apellidos, contraseña);
				clientes.add(nuevoCliente);
			}

			consulta.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}

	public ArrayList<CompraID> datosCompraID() {
		String query = "SELECT ID_Compra FROM Compra ORDER BY ID_Compra ASC";
		ArrayList<CompraID> idsCompra = new ArrayList<CompraID>();

		try {
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery(query);

			while (resultado.next()) {
				int id_Compra = resultado.getInt(1);

				CompraID nuevoIDCompra = new CompraID(id_Compra);
				idsCompra.add(nuevoIDCompra);
			}

			consulta.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idsCompra;
	}

	public ArrayList<EntradaID> datosEntradaID() {
		String query = "SELECT ID_Entrada FROM Entrada ORDER BY ID_Entrada ASC";
		ArrayList<EntradaID> idsEntradas = new ArrayList<EntradaID>();

		try {
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery(query);

			while (resultado.next()) {
				int id_Sesion = resultado.getInt(1);

				EntradaID nuevoIDSesion = new EntradaID(id_Sesion);
				idsEntradas.add(nuevoIDSesion);
			}

			consulta.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idsEntradas;
	}
}
