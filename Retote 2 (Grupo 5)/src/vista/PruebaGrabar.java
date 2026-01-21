//package vista;
//
//import java.sql.Timestamp;
//import java.util.ArrayList;
//
//import controlador.ControladorFicheros;
//import controlador.Controlador;
//import controlador.ControladorBD;
//import modelo.Compra;
//import modelo.CompraID; 
//import modelo.Entrada;
//import modelo.EntradaID;

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
//		if (conexionConExito) {
//			System.out.println("Se realizo la conexion con exito");
//		} else {
//			System.out.println("No se realizo la conexion con exito");
//		}
//		pruebaDatos(controladorBD, controladorES, controladorFi);
//		controladorBD.cerrarConexion();
//
//	}
//
//	public static void pruebaDatos(ControladorBD controladorBD, Controlador controladorES,
//			ControladorFicheros controladorFi) {
//		ArrayList<Integer> partesEntradaPrueba = new ArrayList<Integer>();// 0-Precio 1-Descuento 2-Personas 3-Sesion
//		partesEntradaPrueba.add(20); // entrada 1
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
//		String dniCliente = "12345678A";
//		int precio_Compra = 50;
//		int descuento = 20;
//
//		juntarDatos(controladorBD, controladorES, controladorFi, partesEntradaPrueba, fecha_hora, dniCliente,
//				precio_Compra, descuento);
//	}
//
//	public static void juntarDatos(ControladorBD controladorBD, Controlador controladorES,
//			ControladorFicheros controladorFi, ArrayList<Integer> partesEntrada, Timestamp fecha_hora,
//			String dniCliente, int precio_Compra, int descuento) {
//
//		ArrayList<CompraID> idsCompra = controladorBD.datosCompraID();
//		ArrayList<EntradaID> idsEntrada = controladorBD.datosEntradaID();
//		int ultimoIDCompra = idsCompra.getLast().getId_Compra() + 1;
//		int ultimoIDEntrada = idsEntrada.getLast().getId_Entrada();
//
//		Compra compraJuntada = new Compra();
//		ArrayList<Entrada> listaEntradasJuntada = new ArrayList<Entrada>();
//
//		crearCompra(compraJuntada, ultimoIDCompra, dniCliente, descuento, precio_Compra, fecha_hora);
//		crearlistaEntrada(listaEntradasJuntada, partesEntrada, ultimoIDEntrada, ultimoIDCompra);
//		grabarCompra(controladorBD, controladorES, controladorFi, compraJuntada, listaEntradasJuntada);
//	}
//
//	public static void crearCompra(Compra compraJuntada, int ultimoIDCompra, String dniCliente, int descuento,
//			int precio_Compra, Timestamp fecha_hora) {
//		compraJuntada.setId_Compra(ultimoIDCompra);
//		compraJuntada.setDni(dniCliente);
//		compraJuntada.setDescuento(descuento);
//		compraJuntada.setFecha_hora(fecha_hora);
//		compraJuntada.setPrecio_Compra(precio_Compra);
//	}
//
//	public static ArrayList<Entrada> crearlistaEntrada(ArrayList<Entrada> listaEntradasJuntada,
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
//	public static void grabarCompra(ControladorBD controladorBD, Controlador controladorES,
//			ControladorFicheros controladorFi, Compra compraAGrabar, ArrayList<Entrada> entradasAGrabar) {
//		controladorFi.escribirGrabarCompra("ComprasGrabadas", compraAGrabar, entradasAGrabar);
//	}
//
//}
