package vista;

import java.sql.Timestamp;
import java.util.ArrayList;

import controlador.Controlador;
import controlador.ControladorBD;
import controlador.ControladorFicheros;
import modelo.Cliente;
import modelo.Compra;
import modelo.Entrada;
import modelo.Sesion;

public class Principal {

	private Cliente usuario;
	private ArrayList<Integer> numEspectadores;
	private ArrayList<Sesion> sesionesElegidas;

	/**
	 * Constructor de la clase principal, hace que al iniciar el programa se creen
	 * las variables vacias. (usuario, numEspectadores, sesionesElegidas)
	 */
	public Principal() {
		this.usuario = null;
		this.numEspectadores = new ArrayList<>();
		this.sesionesElegidas = new ArrayList<>();
	}

	public static void main(String[] args) {
		Principal principal = new Principal();
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
		while (true) {
			for (int i = 0; i < 50; ++i)
				System.out.println(); // limpieza de consola
			System.out.println("Bienvenido, por favor pulse enter para continuar.");
			Login login = new Login();
			controladorES.pedirString();
			usuario = login.inicioLogin(controladorBD, controladorES);
			if (usuario != null) {
				Comprador compra = new Comprador(this);
				compra.MostrarMenuPelis(controladorBD, controladorES, this.usuario, this.sesionesElegidas,
						this.numEspectadores);
			}
		}
	}

	/**
	 * Metodo cuyo unico proposito es preguntar al usuario si quiere confirmar la
	 * compra si acepta pasa al siguiente metodo (crearDatos). En caso contrario no
	 * hace nada.
	 * 
	 * @param controladorBD
	 * @param controladorES
	 * @param descuento
	 * @param numEspectadores
	 * @param sesionesElegidas
	 * @param usuario
	 */
	public void confirmarCompra(ControladorBD controladorBD, Controlador controladorES, int descuento,
			ArrayList<Integer> numEspectadores, ArrayList<Sesion> sesionesElegidas, Cliente usuario) {

		System.out.println("Quieres confirmar la compra?");
		System.out.println("1-Si");
		System.out.println("0-No");
		int opcion = controladorES.pedirNumeroEnteroRango(0, 1);

		if (opcion == 1) {
			crearDatos(controladorBD, controladorES, descuento, numEspectadores, sesionesElegidas, usuario);
			limpiarCarrito();
		} else {
			System.out.println("Compra cancelada.");
			limpiarCarrito();
		}

	}

	/**
	 * Metodo simple para reiniciar el carrito (sesionesElegidas y numEspectadores),
	 * usado despues de la finalizacion de una compra o al cancelarla.
	 */
	public void limpiarCarrito() {
		sesionesElegidas.clear();
		numEspectadores.clear();
	}

	/**
	 * Metodo para la creacion de los datos de la forma exigida por juntarDatos
	 * 
	 * @param controladorBD
	 * @param controladorES
	 * @param descuento
	 * @param numEspectadores
	 * @param sesionesElegidas
	 * @param cliente
	 */
	public void crearDatos(ControladorBD controladorBD, Controlador controladorES, int descuento,
			ArrayList<Integer> numEspectadores, ArrayList<Sesion> sesionesElegidas, Cliente cliente) {

		ControladorFicheros controladorFi = new ControladorFicheros("ficheros/");
		ArrayList<Integer> partesEntrada = new ArrayList<Integer>(); // 0-Descuento 1-Personas 2-Sesion
		for (int i = 0; i < sesionesElegidas.size(); i++) {
			partesEntrada.add(descuento);
			partesEntrada.add(numEspectadores.get(i));
			partesEntrada.add(sesionesElegidas.get(i).getId_Sesion());
		}
		ArrayList<Double> preciosEntrada = new ArrayList<Double>(); // 0-Descuento 1-Personas 2-Sesion
		for (int i = 0; i < sesionesElegidas.size(); i++) {
			preciosEntrada.add(sesionesElegidas.get(i).getPrecio_Sesion() * numEspectadores.get(i));
		}

		Timestamp fecha_hora = new Timestamp(System.currentTimeMillis());

		int descuentoCompra = descuento;

		Double precio_Compra = 0.0;
		for (int i = 0; i < preciosEntrada.size(); i++) {
			precio_Compra = precio_Compra + preciosEntrada.get(i);
		}

		juntarDatos(controladorBD, controladorES, controladorFi, partesEntrada, preciosEntrada, fecha_hora, cliente,
				precio_Compra, descuentoCompra);
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
			ArrayList<Integer> partesEntrada, ArrayList<Double> preciosEntrada, Timestamp fecha_hora, Cliente cliente,
			double precio_Compra, int descuentoCompra) {

		int ultimoIDCompra = controladorBD.datosUltimoIDCompra() + 1;
		int ultimoIDEntrada = controladorBD.datosUltimoIDEntrada();

		Compra compraJuntada = new Compra();
		ArrayList<Entrada> listaEntradasJuntada = new ArrayList<Entrada>();
		String dniCliente = cliente.getDNI();

		compraJuntada = crearCompra(compraJuntada, ultimoIDCompra, dniCliente, descuentoCompra, precio_Compra,
				fecha_hora);
		listaEntradasJuntada = crearlistaEntrada(listaEntradasJuntada, partesEntrada, preciosEntrada, ultimoIDEntrada,
				ultimoIDCompra);
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
			double precio_Compra, Timestamp fecha_hora) {
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
			ArrayList<Integer> partesEntrada, ArrayList<Double> preciosEntrada, int ultimoIDEntrada,
			int ultimoIDCompra) {
		int contador = 0;
		for (int j = 0; j < preciosEntrada.size(); j++) {// 0-Descuento 1-Personas 2-Sesion
			Entrada nuevaEntrada = new Entrada();
			nuevaEntrada.setId_Entrada(ultimoIDEntrada + j + 1);
			nuevaEntrada.setPrecio_Entrada(preciosEntrada.get(j));
			nuevaEntrada.setDescuento(partesEntrada.get(contador));
			nuevaEntrada.setNumero_Personas(partesEntrada.get(contador + 1));
			nuevaEntrada.setId_Sesion(partesEntrada.get(contador + 2));
			nuevaEntrada.setId_Compra(ultimoIDCompra);
			listaEntradasJuntada.add(nuevaEntrada);
			contador += 3;
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
		preguntarTicket(controladorBD, controladorES, compraAGrabar, entradasAGrabar, cliente);
	}

	/**
	 * Metodo cuyo unico proposito es preguntar al usuario si quiere un ticket, si
	 * acepta pasa al siguiente metodo (mostrarTicket). En caso contrario no hace
	 * nada.
	 * 
	 * @param controladorBD
	 * @param controladorES
	 * @param compraAGrabar
	 * @param entradasAGrabar
	 * @param cliente
	 */
	public void preguntarTicket(ControladorBD controladorBD, Controlador controladorES, Compra compraAGrabar,
			ArrayList<Entrada> entradasAGrabar, Cliente cliente) {
		System.out.println("Quieres un ticket de la compra?");
		System.out.println("1-Si");
		System.out.println("0-No");
		int opcion = controladorES.pedirNumeroEnteroRango(0, 1);
		if (opcion == 1) {
			mostrarTicket(controladorBD, controladorES, compraAGrabar, entradasAGrabar, cliente);
		}
	}

	/**
	 * 
	 * Muestra ticket por cada Compra, Entradas y Cliente
	 * 
	 * @param compra   >>> Número de compras realizada del cliente con descuento de
	 *                 20% o 30%.
	 * @param entradas >>> Número de entradas para la sesión de pelicula
	 *                 seleccionada.
	 * @param cliente  >>> Datos del cliente: DNI, Email y Nombre.
	 */
	public void mostrarTicket(ControladorBD controladorBD, Controlador controladorES, Compra compra,
			ArrayList<Entrada> entradas, Cliente cliente) {

		System.out.println("-----------------------------------------------------");
		System.out.println("Compra (numero: " + compra.getId_Compra() + ") de " + compra.getPrecio_Compra()
				+ "€ con un descuento de " + compra.getDescuento() + "%");
		System.out.println("Realizada en el " + compra.getFecha_hora());
		System.out.println("\nPor el Cliente: " + cliente.getNombre_Apellidos() + "\nCon el Email: "
				+ cliente.getEmail() + "\nY el DNI: " + cliente.getDNI());
		System.out.println("\nQue incluye: ");
		System.out.println("-----------------------------------------------------\n");

		for (Entrada entrada : entradas) {
			System.out.println("Entrada (numero: " + entrada.getId_Entrada() + ") para la sesion numero: "
					+ entrada.getId_Sesion());
			System.out.println("Valida para " + entrada.getNumero_Personas() + " personas \nComprada por "
					+ entrada.getPrecio_Entrada() + "€ con un descuento de " + entrada.getDescuento() + "%");
			System.out.println("\n-----------------------------------------------------\n");
		}
		try {
			Thread.sleep(8000); // 8000 milisegundos = 8 segundos
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
