package vista;

import controlador.Controlador;
import controlador.ControladorBD;
import modelo.Pelicula;
import modelo.Sesion;

import java.util.ArrayList;

public class SelecciónPelícula_PRUEBA {
	public static void main(String[] args) {

		conexionBD();
	}

	public static void conexionBD() {
		ArrayList<Sesion> sesionesElegidas = new ArrayList<Sesion>();
		ArrayList<Integer> NumEspectadores = new ArrayList<Integer>();
		Controlador controladorES = new Controlador();
		ControladorBD controladorBD = new ControladorBD("cine_reto");
		boolean conexionConExito = controladorBD.iniciarConexion();
		if (conexionConExito) {
			System.out.println("Se realizó la conexion con exito");
		} else {
			System.out.println("No se realizó la conexion con exito");
		}
		MostrarMenuPelis(controladorBD, controladorES, sesionesElegidas, NumEspectadores);
		controladorBD.cerrarConexion();

	}

	public static void MostrarMenuPelis(ControladorBD controladorBD, Controlador controladorES,
			ArrayList<Sesion> sesionesElegidas, ArrayList<Integer> NumEspectadores) {
		ArrayList<Pelicula> peliculas = controladorBD.datosPelicula();

		System.out.println("Seleccione película: ");
		System.out.println("--------------------");
		System.out.println();
		for (int i = 0; i < peliculas.size(); i++) {
			System.out.println("Pelicula numero: " + (i + 1) + ".- " + peliculas.get(i).getNombre());
		}

		SeleccionarPeli(controladorBD, controladorES, peliculas, sesionesElegidas, NumEspectadores);

	}

	public static void SeleccionarPeli(ControladorBD controladorBD, Controlador controladorES,
			ArrayList<Pelicula> peliculas, ArrayList<Sesion> sesionesElegidas, ArrayList<Integer> NumEspectadores) {
		System.out.println("");

		System.out.println("Pulse 0 para salir");
		int opcion = controladorES.pedirNumeroEnteroRango(1, peliculas.size());

		if (opcion == 0) {
			System.out.println("Saliendo...");
		} else {
			System.out.println("Has escogido la opción " + opcion + ": " + peliculas.get(opcion - 1).getNombre());
		}
		opcion = peliculas.get(opcion-1).getId_Pelicula();
		MostrarFecha(controladorBD, controladorES, opcion, sesionesElegidas, NumEspectadores);

	}

	private static void MostrarFecha(ControladorBD controladorBD, Controlador controladorES, int numero_Pelicula,
			ArrayList<Sesion> sesionesElegidas, ArrayList<Integer> NumEspectadores) {
		ArrayList<Sesion> sesiones = controladorBD.datosSesion_Fecha(numero_Pelicula);
		for (int i = 0; i < sesiones.size(); i++) {

			System.out.println((i + 1) + ".- Fecha: " + sesiones.get(i).getFecha() + " - Precio: "
					+ sesiones.get(i).getPrecio_Sesion());
		}
		PreguntarContinuar(controladorBD, controladorES, sesiones, sesionesElegidas, NumEspectadores);
	}

	private static void PreguntarContinuar(ControladorBD controladorBD, Controlador controladorES,
			ArrayList<Sesion> sesiones, ArrayList<Sesion> sesionesElegidas, ArrayList<Integer> NumEspectadores) {
		boolean salir = false;
		while (salir == false) {
			System.out.println("\n¿Desea seleccionar fecha o volver a menú películas?\n");
			System.out.println("1: Seleccionar fecha.");
			System.out.println("0: Volver a menú películas.");

			int elegir = controladorES.pedirNumeroEnteroRango(0, 1);

			if (elegir == 1) {
				System.out.println("Seleccione fecha por número:\n");
				sesionesElegidas = SeleccionarFecha(controladorBD, controladorES, sesiones, sesionesElegidas,
						NumEspectadores);
			} else {
				System.out.println("Volviendo a menú películas...\n");
				salir = true;

				// Para futuro resumen compra:

//				for (int i = 0; i < sesionesElegidas.size(); i++) {
//					System.out.println(sesionesElegidas.get(i));
//				}

				MostrarMenuPelis(controladorBD, controladorES, sesionesElegidas, NumEspectadores);
			}
		}
	}

	private static ArrayList<Sesion> SeleccionarFecha(ControladorBD controladorBD, Controlador controladorES,
			ArrayList<Sesion> sesiones, ArrayList<Sesion> sesionesElegidas, ArrayList<Integer> NumEspectadores) {

		int opcion = controladorES.pedirNumeroEnteroRango(1, sesiones.size()) - 1;
		Sesion eleccion = sesiones.get(opcion);
		sesionesElegidas.add(eleccion);
		PedirNumEspectadores(controladorBD, controladorES, sesionesElegidas, NumEspectadores);
		return sesionesElegidas;

	}

	private static ArrayList<Integer> PedirNumEspectadores(ControladorBD controladorBD, Controlador controladorES,
			ArrayList<Sesion> sesionesElegidas, ArrayList<Integer> NumEspectadores) {
		System.out.println("Por favor, eliga número de espectadores");

		// Número espectadores:
		NumEspectadores.add(controladorES.pedirNumeroEntero());
		AgregarMasPelis(controladorBD, controladorES, sesionesElegidas, NumEspectadores);
		return NumEspectadores;
	}

	private static void AgregarMasPelis(ControladorBD controladorBD, Controlador controladorES,
			ArrayList<Sesion> sesionesElegidas, ArrayList<Integer> NumEspectadores) {
		System.out.println("¿Quieres añadir más películas?");
		System.out.println("");
		System.out.println("1: Sí.");
		System.out.println("0: No.");

		int ElegirAgregar = controladorES.pedirNumeroEnteroRango(0, 1);

		if (ElegirAgregar == 1) {
			// Te debería llevar de vuelta al menú de películas
			MostrarMenuPelis(controladorBD, controladorES, sesionesElegidas, NumEspectadores);

		} else {
			System.out.println("Llevando a resumen de la compra...\n");
			
			// Siguiente clase aquí:
		}
	}
}
