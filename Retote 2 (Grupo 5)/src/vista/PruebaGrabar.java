//package vista;
//
//import java.sql.Timestamp;
//import java.util.ArrayList;
//
//import controlador.ControladorFicheros;
//import controlador.Controlador;
//import controlador.ControladorBD;
//import modelo.Cliente;
//import modelo.Compra;
//import modelo.Entrada;
//
//public class PruebaGrabar {
//
//	public static void main(String[] args) {
//		Controlador controladorES = new Controlador();
//		ControladorBD controladorBD = new ControladorBD("cine_reto");
//		ControladorFicheros controladorFi = new ControladorFicheros("ficheros/");
//		conexionBD(controladorBD, controladorES, controladorFi);
//	}
//
//	public static void conexionBD(ControladorBD controladorBD, Controlador controladorES,
//			ControladorFicheros controladorFi) {
//
//		boolean conexionConExito = controladorBD.iniciarConexion();
//		PruebaGrabar prueba = new PruebaGrabar();
//		if (conexionConExito) {
//			System.out.println("Se realizo la conexion con exito");
//		} else {
//			System.out.println("No se realizo la conexion con exito");
//		}
//		prueba.pruebaDatos(controladorBD, controladorES, controladorFi);
//		controladorBD.cerrarConexion();
//
//	}
//
//	public void pruebaDatos(ControladorBD controladorBD, Controlador controladorES, ControladorFicheros controladorFi) {
//
//		ArrayList<Integer> partesEntradaPrueba = new ArrayList<Integer>();// 0-Precio 1-Descuento 2-Personas 3-Sesion
//		partesEntradaPrueba.add(30); // entrada 1
//		partesEntradaPrueba.add(20);
//		partesEntradaPrueba.add(1);
//		partesEntradaPrueba.add(9);
//
//		partesEntradaPrueba.add(30); // entrada 2
//		partesEntradaPrueba.add(20);
//		partesEntradaPrueba.add(2);
//		partesEntradaPrueba.add(7);
//
//		Timestamp fecha_hora = new Timestamp(System.currentTimeMillis());
//		Cliente cliente = new Cliente("01234567K", "marta.alvarez@example.com", "Marta Álvarez", "martita");
//		int precio_Compra = 60;
//		int descuento = 20;
//
//		juntarDatos(controladorBD, controladorES, controladorFi, partesEntradaPrueba, fecha_hora, cliente,
//				precio_Compra, descuento);
//	}
//
//	public void juntarDatos(ControladorBD controladorBD, Controlador controladorES, ControladorFicheros controladorFi,
//			ArrayList<Integer> partesEntrada, Timestamp fecha_hora, Cliente cliente, int precio_Compra, int descuento) {
//
//		int ultimoIDCompra = controladorBD.datosUltimoIDCompra() + 1;
//		int ultimoIDEntrada = controladorBD.datosUltimoIDEntrada();
//
//		Compra compraJuntada = new Compra();
//		ArrayList<Entrada> listaEntradasJuntada = new ArrayList<Entrada>();
//		String dniCliente = cliente.getDNI();
//
//		compraJuntada = crearCompra(compraJuntada, ultimoIDCompra, dniCliente, descuento, precio_Compra, fecha_hora);
//		listaEntradasJuntada = crearlistaEntrada(listaEntradasJuntada, partesEntrada, ultimoIDEntrada, ultimoIDCompra);
//		grabarCompra(controladorBD, controladorES, controladorFi, compraJuntada, listaEntradasJuntada, cliente);
//	}
//
//	public Compra crearCompra(Compra compraJuntada, int ultimoIDCompra, String dniCliente, int descuento,
//			int precio_Compra, Timestamp fecha_hora) {
//		compraJuntada.setId_Compra(ultimoIDCompra);
//		compraJuntada.setDni(dniCliente);
//		compraJuntada.setDescuento(descuento);
//		compraJuntada.setFecha_hora(fecha_hora);
//		compraJuntada.setPrecio_Compra(precio_Compra);
//		return compraJuntada;
//	}
//
//	public ArrayList<Entrada> crearlistaEntrada(ArrayList<Entrada> listaEntradasJuntada,
//			ArrayList<Integer> partesEntrada, int ultimoIDEntrada, int ultimoIDCompra) {
//		int contador = 1;
//		for (int i = 0; i < partesEntrada.size(); i += 4) {// 0-Precio 1-Descuento 2-Personas 3-Sesion
//			Entrada nuevaEntrada = new Entrada();
//			nuevaEntrada.setId_Entrada(ultimoIDEntrada + contador);
//			nuevaEntrada.setPrecio_Entrada(partesEntrada.get(i));
//			nuevaEntrada.setDescuento(partesEntrada.get(i + 1));
//			nuevaEntrada.setNumero_Personas(partesEntrada.get(i + 2));
//			nuevaEntrada.setId_Sesion(partesEntrada.get(i + 3));
//			nuevaEntrada.setId_Compra(ultimoIDCompra);
//			listaEntradasJuntada.add(nuevaEntrada);
//			contador++;
//		}
//		return listaEntradasJuntada;
//	}
//
//	public void grabarCompra(ControladorBD controladorBD, Controlador controladorES, ControladorFicheros controladorFi,
//			Compra compraAGrabar, ArrayList<Entrada> entradasAGrabar, Cliente cliente) {
//		controladorFi.escribirGrabarCompra("ComprasGrabadas", compraAGrabar, entradasAGrabar, cliente);
//	}
//
//}
