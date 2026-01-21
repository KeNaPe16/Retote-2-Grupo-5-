package vista;

import java.sql.Timestamp;
import java.util.ArrayList;

import controlador.Controlador;
import controlador.ControladorBD;
import controlador.ControladorFicheros;
import modelo.Cliente;
import modelo.Compra;
import modelo.CompraID;
import modelo.Entrada;
import modelo.EntradaID;

public class Pruebas {

	public static void main(String[] args) {

		conexionBD();
	}

	public static void conexionBD() {
		Controlador controladorES = new Controlador();
		ControladorBD controladorBD = new ControladorBD("cine_reto");
		boolean conexionConExito = controladorBD.iniciarConexion();
		if (conexionConExito) {
			System.out.println("Se realizo la conexion con exito");
		} else {
			System.out.println("No se realizo la conexion con exito");
		}
		menuEspera(controladorBD, controladorES);
		controladorBD.cerrarConexion();

	}

	public static void menuEspera(ControladorBD controladorBD, Controlador controladorES) {

		boolean usuarioEncontrado = false;

		while (usuarioEncontrado == false) {
			System.out.println("");
			System.out.println("Bienvenido, por favor pulse enter para continuar.");
			controladorES.pedirString();
			Cliente usuario = login(controladorBD, controladorES);
			if (usuario != null) {
				usuarioEncontrado = true;
				menuPeliculas(controladorBD, controladorES, usuario);
			}
		}
	}

	public static Cliente login(ControladorBD controladorBD, Controlador controladorES) {
		Cliente clienteCorrecto = null;
		ArrayList<Cliente> clientes = controladorBD.datosCliente();
		boolean reintentar = true;

		while (reintentar == true) {

			System.out.println("");
			System.out.println("Por favor, escriba su email.");
			String intentoEmail = controladorES.pedirString();
			System.out.println("Por favor, escriba su contraseña.");
			String intentoContraseña = controladorES.pedirString();
			boolean loginCorrecto = false;

			for (int i = 0; i < clientes.size(); i++) {
				if (clientes.get(i).getEmail().equals(intentoEmail)
						&& clientes.get(i).getContraseña().equals(intentoContraseña)) {
					loginCorrecto = true;
					reintentar = false;
					clienteCorrecto = clientes.get(i);
				}
			}

			if (loginCorrecto == false) {
				System.out.println("");
				System.out.println("Login incorrecto, intentarlo de nuevo?");
				System.out.println("1-Si");
				System.out.println("2-No");
				int opcion = controladorES.pedirNumeroEnteroRango(1, 2);
				if (opcion == 2) {
					reintentar = false;
				}
			}

		}
		return clienteCorrecto;
	}

	public static void menuPeliculas(ControladorBD controladorBD, Controlador controladorES, Cliente usuario) {

	}

	
	public static void juntarDatos(ControladorBD controladorBD, Controlador controladorES,
			ControladorFicheros controladorFi, ArrayList<Integer> partesEntrada, Timestamp fecha_hora,
			String dniCliente, int precio_Compra, int descuento) {

		ArrayList<CompraID> idsCompra = controladorBD.datosCompraID();
		ArrayList<EntradaID> idsEntrada = controladorBD.datosEntradaID();
		int ultimoIDCompra = idsCompra.getLast().getId_Compra() + 1;
		int ultimoIDEntrada = idsEntrada.getLast().getId_Entrada();

		Compra compraJuntada = new Compra();
		ArrayList<Entrada> listaEntradasJuntada = new ArrayList<Entrada>();

		crearCompra(compraJuntada, ultimoIDCompra, dniCliente, descuento, precio_Compra, fecha_hora);
		crearlistaEntrada(listaEntradasJuntada, partesEntrada, ultimoIDEntrada, ultimoIDCompra);
		grabarCompra(controladorBD, controladorES, controladorFi, compraJuntada, listaEntradasJuntada);
	}

	public static void crearCompra(Compra compraJuntada, int ultimoIDCompra, String dniCliente, int descuento,
			int precio_Compra, Timestamp fecha_hora) {
		compraJuntada.setId_Compra(ultimoIDCompra);
		compraJuntada.setDni(dniCliente);
		compraJuntada.setDescuento(descuento);
		compraJuntada.setFecha_hora(fecha_hora);
		compraJuntada.setPrecio_Compra(precio_Compra);
	}

	public static ArrayList<Entrada> crearlistaEntrada(ArrayList<Entrada> listaEntradasJuntada,
			ArrayList<Integer> partesEntrada, int ultimoIDEntrada, int ultimoIDCompra) {
		int contador = 1;
		for (int i = 0; i < partesEntrada.size(); i += 4) {// 0-Precio 1-Descuento 2-Personas 3-Sesion
			Entrada nuevaEntrada = new Entrada();
			nuevaEntrada.setId_Entrada(ultimoIDEntrada + contador);
			nuevaEntrada.setPrecio_Entrada(partesEntrada.get(i));
			nuevaEntrada.setDescuento(partesEntrada.get(i + 1));
			nuevaEntrada.setNumero_Personas(partesEntrada.get(i + 2));
			nuevaEntrada.setId_Sesion(partesEntrada.get(i + 3));
			nuevaEntrada.setId_Compra(ultimoIDCompra);
			listaEntradasJuntada.add(nuevaEntrada);
			contador++;
		}
		return listaEntradasJuntada;
	}

	public static void grabarCompra(ControladorBD controladorBD, Controlador controladorES,
			ControladorFicheros controladorFi, Compra compraAGrabar, ArrayList<Entrada> entradasAGrabar) {
		controladorFi.escribirGrabarCompra("ComprasGrabadas", compraAGrabar, entradasAGrabar);
	}

}