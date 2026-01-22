package vista;

import java.sql.Timestamp;
import java.util.ArrayList;

import controlador.Controlador;
import controlador.ControladorBD;
import controlador.ControladorFicheros;
import modelo.Cliente;
import modelo.Compra;
import modelo.Entrada;

public class Pruebas {

	public static void main(String[] args) {
		Pruebas principal = new Pruebas();
		principal.conexionBD();
	}

	public void conexionBD() {
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

	/**
	 * Menu de espera de la aplicacion, el cual envia el usuario al login al pulsar
	 * enter.
	 * 
	 * @param controladorBD
	 * @param controladorES
	 */
	public void menuEspera(ControladorBD controladorBD, Controlador controladorES) {

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

	public Cliente login(ControladorBD controladorBD, Controlador controladorES) {
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

	public void menuPeliculas(ControladorBD controladorBD, Controlador controladorES, Cliente usuario) {

	}

	/**
	 * Metodo que junta todos los datos necesarios para generar una compra y sus
	 * entradas
	 * 
	 * @param controladorBD
	 * @param controladorES
	 * @param controladorFi
	 * @param partesEntrada
	 * @param fecha_hora
	 * @param dniCliente
	 * @param precio_Compra
	 * @param descuento
	 */
	public void juntarDatos(ControladorBD controladorBD, Controlador controladorES, ControladorFicheros controladorFi,
			ArrayList<Integer> partesEntrada, Timestamp fecha_hora, Cliente cliente, int precio_Compra, int descuento) {

		int ultimoIDCompra = controladorBD.datosUltimoIDCompra() + 1;
		int ultimoIDEntrada = controladorBD.datosUltimoIDEntrada();

		Compra compraJuntada = new Compra();
		ArrayList<Entrada> listaEntradasJuntada = new ArrayList<Entrada>();
		String dniCliente = cliente.getDNI();

		compraJuntada = crearCompra(compraJuntada, ultimoIDCompra, dniCliente, descuento, precio_Compra, fecha_hora);
		listaEntradasJuntada = crearlistaEntrada(listaEntradasJuntada, partesEntrada, ultimoIDEntrada, ultimoIDCompra);
		grabarCompra(controladorBD, controladorES, controladorFi, compraJuntada, listaEntradasJuntada, cliente);
	}

	/**
	 * Metodo para crear una compra, el cual pide todos los componentes necesarios
	 * para crearla
	 * 
	 * @param compraJuntada
	 * @param ultimoIDCompra
	 * @param dniCliente
	 * @param descuento
	 * @param precio_Compra
	 * @param fecha_hora
	 * @return devuelve la compra que se crea con los datos proporcionados
	 */
	public Compra crearCompra(Compra compraJuntada, int ultimoIDCompra, String dniCliente, int descuento,
			int precio_Compra, Timestamp fecha_hora) {
		compraJuntada.setId_Compra(ultimoIDCompra);
		compraJuntada.setDni(dniCliente);
		compraJuntada.setDescuento(descuento);
		compraJuntada.setFecha_hora(fecha_hora);
		compraJuntada.setPrecio_Compra(precio_Compra);
		return compraJuntada;
	}

	/**
	 * Metodo de creacion de una lista de entradas, el cual pide todos los
	 * componentes necesarios para crearla.
	 * 
	 * @param listaEntradasJuntada
	 * @param partesEntrada
	 * @param ultimoIDEntrada
	 * @param ultimoIDCompra
	 * @return devuelve un ArrayList de entradas, debido a que en una compra puede
	 *         haber mas de una compra.
	 */
	public ArrayList<Entrada> crearlistaEntrada(ArrayList<Entrada> listaEntradasJuntada,
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

	/**
	 * Metodo que utiliza al controlador para crear el fichero, apartado para
	 * claridad.
	 * 
	 * @param controladorBD
	 * @param controladorES
	 * @param controladorFi
	 * @param compraAGrabar
	 * @param entradasAGrabar
	 */
	public void grabarCompra(ControladorBD controladorBD, Controlador controladorES, ControladorFicheros controladorFi,
			Compra compraAGrabar, ArrayList<Entrada> entradasAGrabar, Cliente cliente) {
		controladorFi.escribirGrabarCompra("ComprasGrabadas", compraAGrabar, entradasAGrabar, cliente);
	}

}