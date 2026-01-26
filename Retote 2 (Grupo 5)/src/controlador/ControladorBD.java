package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import modelo.Cliente;
import modelo.Pelicula;
import modelo.Sesion;

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

	public int datosUltimoIDCompra() {
		String query = "SELECT ID_Compra FROM Compra ORDER BY ID_Compra DESC limit 1";
		int id_Compra = -1;
		try {
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery(query);

			while (resultado.next()) {
				id_Compra = resultado.getInt(1);

			}

			consulta.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id_Compra;
	}

	public int datosUltimoIDEntrada() {
		String query = "SELECT ID_Entrada FROM Entrada ORDER BY ID_Entrada DESC limit 1";
		int id_Sesion = -1;
		try {
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery(query);

			while (resultado.next()) {
				id_Sesion = resultado.getInt(1);

			}

			consulta.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id_Sesion;
	}

	public ArrayList<Pelicula> datosPelicula() {
		String query = "SELECT p.id_pelicula, p.duracion, p.Nombre_Pelicula, p.Precio_Base FROM Pelicula p INNER JOIN Sesion s on p.ID_Pelicula = s.ID_Pelicula GROUP BY p.ID_Pelicula ORDER BY s.Fecha";
		ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();

		try {
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery(query);

			while (resultado.next()) {
				int id_pelicula = resultado.getInt(1);
				int duracion = resultado.getInt(2);
				String nombre = resultado.getString(3);
				int precio_base = resultado.getInt(4);

				Pelicula nuevaPelicula = new Pelicula(id_pelicula, duracion, nombre, precio_base);
				peliculas.add(nuevaPelicula);
			}

			consulta.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return peliculas;
	}

	public ArrayList<Sesion> datosSesion_Fecha(int ID_Pelicula) {
		String query = "SELECT ID_Sesion, Hora_Inicio, Hora_Fin, Fecha, Numero_Espectadores_Actuales, Precio_Sesion, ID_Sala, ID_Pelicula\r\n"
				+ "FROM Sesion where ID_Pelicula = " + ID_Pelicula + " ORDER BY Fecha ASC";
		ArrayList<Sesion> sesiones = new ArrayList<Sesion>();

		try {
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery(query);

			while (resultado.next()) {
				int ID_Sesion = resultado.getInt(1);
				Time Hora_Inicio = resultado.getTime(2);
				Time Hora_Fin = resultado.getTime(3);
				String Fecha = resultado.getString(4);
				int Numero_Espectadores_Actuales = resultado.getInt(5);
				int Precio_Sesion = resultado.getInt(6);
				int ID_Sala = resultado.getInt(7);

				Sesion nuevaSesion = new Sesion(ID_Sesion, Hora_Inicio, Hora_Fin, Fecha, Numero_Espectadores_Actuales,
						Precio_Sesion, ID_Sala, ID_Pelicula);
				sesiones.add(nuevaSesion);
			}

			consulta.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sesiones;
	}

	public String datosSala(int ID_Sala) {
		String query = "SELECT Nombre FROM Sala where ID_Sala = " + ID_Sala;
		String nombreSala = "mal";
		try {
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery(query);

			while (resultado.next()) {
				nombreSala = resultado.getString(1);

			}

			consulta.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nombreSala;
	}
}
