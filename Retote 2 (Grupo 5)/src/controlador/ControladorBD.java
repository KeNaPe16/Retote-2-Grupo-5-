package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
}