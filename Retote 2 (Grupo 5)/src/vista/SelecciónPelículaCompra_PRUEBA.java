package vista;

import controlador.Controlador;
import controlador.ControladorBD;
import modelo.Pelicula;
import modelo.Sesion;

import java.util.ArrayList;
import java.util.Iterator;

public class SelecciónPelículaCompra_PRUEBA {
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
			System.out.println("Se realizó la conexión con exito");
		} else {
			System.out.println("No se realizó la conexión con exito");
		}
		MostrarMenuPelis(controladorBD, controladorES, sesionesElegidas, NumEspectadores);
		controladorBD.cerrarConexion();

	}

	public static void MostrarMenuPelis(ControladorBD controladorBD, Controlador controladorES,
			ArrayList<Sesion> sesionesElegidas, ArrayList<Integer> NumEspectadores) {
		ArrayList<Pelicula> peliculas = controladorBD.datosPelicula();
		System.out.println("\n--------------------");
		System.out.println("SELECCIONE PELÍCULA: ");
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
		opcion = peliculas.get(opcion - 1).getId_Pelicula();
		MostrarFecha(controladorBD, controladorES, opcion, sesionesElegidas, NumEspectadores, peliculas);

	}

	private static void MostrarFecha(ControladorBD controladorBD, Controlador controladorES, int numero_Pelicula,
			ArrayList<Sesion> sesionesElegidas, ArrayList<Integer> NumEspectadores, ArrayList<Pelicula> peliculas) {
		ArrayList<Sesion> sesiones = controladorBD.datosSesion_Fecha(numero_Pelicula);
		for (int i = 0; i < sesiones.size(); i++) {

			System.out.println((i + 1) + ".-	Fecha: " + sesiones.get(i).getFecha() + "\n	Hora Inicio: "
					+ sesiones.get(i).getHora_Inicio() + "\n	Precio: " + sesiones.get(i).getPrecio_Sesion() + "\n ");
		}
		PreguntarContinuar(controladorBD, controladorES, sesiones, sesionesElegidas, NumEspectadores, peliculas);
	}

	private static void PreguntarContinuar(ControladorBD controladorBD, Controlador controladorES,
			ArrayList<Sesion> sesiones, ArrayList<Sesion> sesionesElegidas, ArrayList<Integer> NumEspectadores,
			ArrayList<Pelicula> peliculas) {
		boolean salir = false;
		while (salir == false) {
			System.out.println("\n¿Desea seleccionar fecha o volver a menú películas?\n");
			System.out.println("1: Seleccionar fecha.");
			System.out.println("0: Volver a menú películas.\n");

			int elegir = controladorES.pedirNumeroEnteroRango(0, 1);

			if (elegir == 1) {
				System.out.println("Seleccione fecha por número:\n");
				sesionesElegidas = SeleccionarFecha(controladorBD, controladorES, sesiones, sesionesElegidas,
						NumEspectadores, peliculas);
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
			ArrayList<Sesion> sesiones, ArrayList<Sesion> sesionesElegidas, ArrayList<Integer> NumEspectadores,
			ArrayList<Pelicula> peliculas) {

		int opcion = controladorES.pedirNumeroEnteroRango(1, sesiones.size()) - 1;
		Sesion eleccion = sesiones.get(opcion);
		sesionesElegidas.add(eleccion);
		PedirNumEspectadores(controladorBD, controladorES, sesionesElegidas, NumEspectadores, peliculas);
		return sesionesElegidas;

	}

	private static ArrayList<Integer> PedirNumEspectadores(ControladorBD controladorBD, Controlador controladorES,
			ArrayList<Sesion> sesionesElegidas, ArrayList<Integer> NumEspectadores, ArrayList<Pelicula> peliculas) {
		System.out.println("\nPor favor, elija número de espectadores");

		// Número espectadores:
		NumEspectadores.add(controladorES.pedirNumeroEntero());
		AgregarMasPelis(controladorBD, controladorES, sesionesElegidas, NumEspectadores, peliculas);
		return NumEspectadores;
	}

	private static void AgregarMasPelis(ControladorBD controladorBD, Controlador controladorES,
			ArrayList<Sesion> sesionesElegidas, ArrayList<Integer> NumEspectadores, ArrayList<Pelicula> peliculas) {
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
			ObtenerDatos(controladorBD, controladorES, sesionesElegidas, NumEspectadores, peliculas);

			// Siguiente clase aquí:
			// vista.ResumenCompra_PRUEBA;
		}
	}

	private static void ObtenerDatos(ControladorBD controladorBD, Controlador controladorES,
			ArrayList<Sesion> sesionesElegidas, ArrayList<Integer> NumEspectadores, ArrayList<Pelicula> peliculas) {
		System.out.println("Confirme los datos de la compra.");
		ArrayList<String> nombresSala = new ArrayList<String>();
		for (int i = 0; i < sesionesElegidas.size(); i++) {
			int id_Sala = sesionesElegidas.get(i).getId_Sala();
			nombresSala.add(controladorBD.datosSala(id_Sala));
		}
		IntroducirDatos(controladorBD, controladorES, sesionesElegidas, NumEspectadores, nombresSala, peliculas);
	}

	private static void IntroducirDatos(ControladorBD controladorBD, Controlador controladorES,
			ArrayList<Sesion> sesionesElegidas, ArrayList<Integer> NumEspectadores, ArrayList<String> nombresSala,
			ArrayList<Pelicula> peliculas) {
		System.out.println("\nPelícula/s elegida/s:");
		System.out.println("----------------");

		ArrayList<Integer> descuentos = new ArrayList<Integer>();
		ArrayList<String> peliculasElegidas = new ArrayList<String>();
		for (int i = 0; i < sesionesElegidas.size(); i++) {
			int peliculaElegida = sesionesElegidas.get(i).getId_Pelicula();
			for (int j = 0; j < peliculas.size(); j++) {
				if (peliculaElegida == peliculas.get(j).getId_Pelicula()) {
					peliculasElegidas.add(peliculas.get(j).getNombre());
					System.out.println("\nPelícula " + (i + 1) + ": " + peliculas.get(j).getNombre());
				}
			}
		}

		for (int i = 0; i < sesionesElegidas.size(); i++) {
			System.out.println("\n----------------\n");
			System.out.println("ENTRADA " + (i + 1) + " para PELÍCULA " + (i + 1) + ": " + peliculasElegidas.get(i));
			System.out.println("\nFecha: " + sesionesElegidas.get(i).getFecha());
			System.out.println("\nSesión: " + sesionesElegidas.get(i).getHora_Inicio());
			System.out.println("\n" + nombresSala.get(i));
			System.out.println("\nNúmero de personas: " + NumEspectadores.get(i));
			System.out.println("\nPrecio por persona " + sesionesElegidas.get(i).getPrecio_Sesion());
			System.out.println("\nPrecio total sin descuento: "
					+ sesionesElegidas.get(i).getPrecio_Sesion() * NumEspectadores.get(i));

			// Descuento:
			double precioDescuento;
			System.out.println("\nPrecio total con descuento: ");
			if (peliculasElegidas.size() == 2) {
				descuentos.add(20);
				precioDescuento = (sesionesElegidas.get(i).getPrecio_Sesion() * NumEspectadores.get(i)
						- (sesionesElegidas.get(i).getPrecio_Sesion() * NumEspectadores.get(i)) * 0.2);
				controladorES.redondear(precioDescuento, 2);
				System.out.print("\n" + precioDescuento + "(%20)");
				
			} else if (peliculasElegidas.size() + 1 > 2) {
				descuentos.add(30);
				precioDescuento = (sesionesElegidas.get(i).getPrecio_Sesion() * NumEspectadores.get(i)
						- (sesionesElegidas.get(i).getPrecio_Sesion() * NumEspectadores.get(i)) * 0.3);
				controladorES.redondear(precioDescuento, 2);
				System.out.print("\n" + precioDescuento + "(%30)\n");
				
			}else {
				System.out.print("\nNo hay descuento");
			}

			System.out.println("\n----------------\n");
		}

	}

}
