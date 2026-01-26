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

	/**
	 * 
	 * Método utilizado para mostrar las películas CON fecha de sesión al usuario
	 * 
	 * @param controladorBD    --> Controlador de la Base de Datos.
	 * 
	 * @param controladorES    --> Controlador de Entrada y Salida.
	 * 
	 * @param sesionesElegidas --> Sesiones elegidas por el usuario en el anterior
	 *                         método.
	 * 
	 * @param NumEspectadores  --> Número de espectadores elegido por el usuario.
	 */
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

	/**
	 * 
	 * Método utilizado para que el usuario a través del controlador de Entrada y
	 * Salida elija una de las películas mostradas en el anterior método
	 * 
	 * @param controladorBD    --> Controlador de la Base de Datos.
	 * 
	 * @param controladorES    --> Controlador de Entrada y Salida.
	 * 
	 * @param peliculas        --> Parámetro que guarda toda la información
	 *                         necesaria de las películas, como: su id, su nombre,
	 *                         su precio y su duración.
	 * 
	 * @param sesionesElegidas --> Sesiones elegidas por el usuario en el anterior
	 *                         método.
	 * 
	 * @param NumEspectadores  --> Número de espectadores elegido por el usuario.
	 */
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

	/**
	 * 
	 * Muestra la fecha de las películas o las fechas disponibles, las cuales en
	 * teoría siempre habrá por lo menos UNA fecha disponible en cada película
	 * 
	 * @param controladorBD    --> Controlador de la Base de Datos.
	 * 
	 * @param controladorES    --> Controlador de Entrada y Salida.
	 * 
	 * @param numero_Pelicula  --> Número por el cúal se identifica la película para
	 *                         su selección.
	 * 
	 * @param sesionesElegidas --> Sesiones elegidas por el usuario en el anterior
	 *                         método.
	 * 
	 * @param NumEspectadores  --> Número de espectadores elegido por el usuario.
	 * 
	 * @param peliculas        --> Parámetro que guarda toda la información
	 *                         necesaria de las películas, como: su id, su nombre,
	 *                         su precio y su duración.
	 */
	private static void MostrarFecha(ControladorBD controladorBD, Controlador controladorES, int numero_Pelicula,
			ArrayList<Sesion> sesionesElegidas, ArrayList<Integer> NumEspectadores, ArrayList<Pelicula> peliculas) {
		ArrayList<Sesion> sesiones = controladorBD.datosSesion_Fecha(numero_Pelicula);
		for (int i = 0; i < sesiones.size(); i++) {

			System.out.println((i + 1) + ".-	Fecha: " + sesiones.get(i).getFecha() + "\n	Hora Inicio: "
					+ sesiones.get(i).getHora_Inicio() + "\n	Precio: " + sesiones.get(i).getPrecio_Sesion() + "\n ");
		}
		PreguntarContinuar(controladorBD, controladorES, sesiones, sesionesElegidas, NumEspectadores, peliculas);
	}

	/**
	 * 
	 * Pregunta al usuario si desea seleccionar una fecha o si quiere volver a ver
	 * las películas disponibles
	 * 
	 * @param controladorBD    --> Controlador de la Base de Datos.
	 * 
	 * @param controladorES    --> Controlador de Entrada y Salida.
	 * 
	 * @param sesiones         --> Sesiones disponibles.
	 * 
	 * @param sesionesElegidas --> Sesiones elegidas por el usuario en el anterior
	 *                         método.
	 * 
	 * @param NumEspectadores  --> Número de espectadores elegido por el usuario.
	 * 
	 * @param peliculas        --> Parámetro que guarda toda la información
	 *                         necesaria de las películas, como: su id, su nombre,
	 *                         su precio y su duración.
	 */
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

				// Pide seleccionar una fecha y pasa al siguiente método
				System.out.println("Seleccione fecha por número:\n");
				sesionesElegidas = SeleccionarFecha(controladorBD, controladorES, sesiones, sesionesElegidas,
						NumEspectadores, peliculas);
			} else {
				System.out.println("Volviendo a menú películas...\n");
				salir = true;

				// Volver a menú películas
				MostrarMenuPelis(controladorBD, controladorES, sesionesElegidas, NumEspectadores);
			}
		}
	}

	/**
	 * 
	 * Pide al usuario seleccionar una fecha con un número tras ser preguntado con
	 * anterioridad por ello
	 * 
	 * @param controladorBD    --> Controlador de la Base de Datos.
	 * 
	 * @param controladorES    --> Controlador de Entrada y Salida.
	 * 
	 * @param sesiones         --> Sesiones disponibles.
	 * 
	 * @param sesionesElegidas --> Sesiones elegidas por el usuario en el anterior
	 *                         método.
	 * 
	 * @param NumEspectadores  --> Número de espectadores elegido por el usuario.
	 * 
	 * @param peliculas        --> Párametro que guarda toda la información
	 *                         necesaria de las películas, como: su id, su nombre,
	 *                         su precio y su duración.
	 * 
	 * @return --> devuelve el párametro sesionesElegidas.
	 */
	private static ArrayList<Sesion> SeleccionarFecha(ControladorBD controladorBD, Controlador controladorES,
			ArrayList<Sesion> sesiones, ArrayList<Sesion> sesionesElegidas, ArrayList<Integer> NumEspectadores,
			ArrayList<Pelicula> peliculas) {

		int opcion = controladorES.pedirNumeroEnteroRango(1, sesiones.size()) - 1;
		Sesion eleccion = sesiones.get(opcion);
		sesionesElegidas.add(eleccion);
		PedirNumEspectadores(controladorBD, controladorES, sesionesElegidas, NumEspectadores, peliculas);
		return sesionesElegidas;

	}

	/**
	 * 
	 * Pide al usuario el número de espectadores que van a asistir al día de la
	 * sesión
	 * 
	 * @param controladorBD    --> Controlador de la Base de Datos.
	 * 
	 * @param controladorES    --> Controlador de Entrada y Salida.
	 * 
	 * @param sesionesElegidas --> Sesiones elegidas por el usuario en el anterior
	 *                         método.
	 * 
	 * @param NumEspectadores  --> Número de espectadores elegido por el usuario.
	 * 
	 * @param peliculas        --> Párametro que guarda toda la información
	 *                         necesaria de las películas, como: su id, su nombre,
	 *                         su precio y su duración.
	 * 
	 * @return --> Devuelve NumEspectadores.
	 */
	private static ArrayList<Integer> PedirNumEspectadores(ControladorBD controladorBD, Controlador controladorES,
			ArrayList<Sesion> sesionesElegidas, ArrayList<Integer> NumEspectadores, ArrayList<Pelicula> peliculas) {
		System.out.println("\nPor favor, elija número de espectadores");

		// Número espectadores:
		NumEspectadores.add(controladorES.pedirNumeroEntero());
		AgregarMasPelis(controladorBD, controladorES, sesionesElegidas, NumEspectadores, peliculas);
		return NumEspectadores;
	}

	/**
	 * 
	 * Pregunta si deseas añadir más películas y te lleva al menú para que puedas
	 * escoger otra más
	 * 
	 * @param controladorBD    --> Controlador de la Base de Datos.
	 * 
	 * @param controladorES    --> Controlador de Entrada y Salida.
	 * 
	 * @param sesionesElegidas --> Sesiones elegidas por el usuario en el anterior
	 *                         método.
	 * 
	 * @param NumEspectadores  --> Número de espectadores elegido por el usuario.
	 * 
	 * @param peliculas        --> Párametro que guarda toda la información
	 *                         necesaria de las películas, como: su id, su nombre,
	 *                         su precio y su duración.
	 */
	private static void AgregarMasPelis(ControladorBD controladorBD, Controlador controladorES,
			ArrayList<Sesion> sesionesElegidas, ArrayList<Integer> NumEspectadores, ArrayList<Pelicula> peliculas) {
		System.out.println("¿Quieres añadir más películas?");
		System.out.println("");
		System.out.println("1: Sí.");
		System.out.println("0: No.");

		int ElegirAgregar = controladorES.pedirNumeroEnteroRango(0, 1);

		if (ElegirAgregar == 1) {
			// Te lleva de vuelta al menú de películas
			MostrarMenuPelis(controladorBD, controladorES, sesionesElegidas, NumEspectadores);

		} else {
			// Te lleva al resumen de la compra
			System.out.println("Llevando a resumen de la compra...\n");
			ObtenerDatos(controladorBD, controladorES, sesionesElegidas, NumEspectadores, peliculas);
		}
	}

	/**
	 * 
	 * Obtiene los datos seleccionados en una pantalla y los guarda
	 * 
	 * @param controladorBD    --> Controlador de la Base de Datos.
	 * 
	 * @param controladorES    --> Controlador de Entrada y Salida.
	 * 
	 * @param sesionesElegidas --> Sesiones elegidas por el usuario en el anterior
	 *                         método.
	 * 
	 * @param NumEspectadores  --> Número de espectadores elegido por el usuario.
	 * 
	 * @param peliculas        --> Párametro que guarda toda la información
	 *                         necesaria de las películas, como: su id, su nombre,
	 *                         su precio y su duración.
	 */
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

	/**
	 * 
	 * Muestra las películas seleccionadas por pantalla
	 * 
	 * @param controladorBD    --> Controlador de la Base de Datos.
	 * 
	 * @param controladorES    --> Controlador de Entrada y Salida.
	 * 
	 * @param sesionesElegidas --> Sesiones elegidas por el usuario en el anterior
	 *                         método.
	 * 
	 * @param NumEspectadores  --> Número de espectadores elegido por el usuario.
	 * 
	 * @param nombresSala      --> Nombre de las salas, la cual incluye su número.
	 * 
	 * @param peliculas        --> Párametro que guarda toda la información
	 *                         necesaria de las películas, como: su id, su nombre,
	 *                         su precio y su duración.
	 */
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
		SesionesElegidas(controladorBD, controladorES, sesionesElegidas, NumEspectadores, nombresSala, peliculas,
				peliculasElegidas, descuentos);
	}

	/**
	 * 
	 * Muestra los datos de la sesión o las sesiones
	 * 
	 * @param controladorBD     --> Controlador de la Base de Datos.
	 * 
	 * @param controladorES     --> Controlador de Entrada y Salida.
	 * 
	 * @param sesionesElegidas  --> Sesiones elegidas por el usuario en el anterior
	 *                          método.
	 * 
	 * @param numEspectadores   --> Número de espectadores elegido por el usuario.
	 * 
	 * @param nombresSala       --> Nombre de las salas, la cual incluye su número.
	 * 
	 * @param peliculas         --> Párametro que guarda toda la información
	 *                          necesaria de las películas, como: su id, su nombre,
	 *                          su precio y su duración.
	 * 
	 * @param peliculasElegidas --> Películas elegidas por el usuario.
	 * 
	 * @param descuentos        --> Párametro que guarda los descuentos aplicados
	 */
	private static void SesionesElegidas(ControladorBD controladorBD, Controlador controladorES,
			ArrayList<Sesion> sesionesElegidas, ArrayList<Integer> numEspectadores, ArrayList<String> nombresSala,
			ArrayList<Pelicula> peliculas, ArrayList<String> peliculasElegidas, ArrayList<Integer> descuentos) {

		for (int i = 0; i < sesionesElegidas.size(); i++) {
			System.out.println("\n----------------\n");
			System.out.println("ENTRADA " + (i + 1) + " para PELÍCULA " + (i + 1) + ": " + peliculasElegidas.get(i));
			System.out.println("\nFecha: " + sesionesElegidas.get(i).getFecha());
			System.out.println("\nSesión: " + sesionesElegidas.get(i).getHora_Inicio());
			System.out.println("\n" + nombresSala.get(i));
			System.out.println("\nNúmero de personas: " + numEspectadores.get(i));
			System.out.println("\nPrecio por persona " + sesionesElegidas.get(i).getPrecio_Sesion());
			System.out.println("\nPrecio total sin descuento: "
					+ sesionesElegidas.get(i).getPrecio_Sesion() * numEspectadores.get(i));

			Descuentos(controladorBD, controladorES, sesionesElegidas, numEspectadores, nombresSala, peliculas,
					peliculasElegidas, descuentos, i);
		}

	}

	/**
	 * 
	 * Se mostraría el descuento aplicado, y luego se calcula en otros dos métodos
	 * 
	 * @param controladorBD     --> Controlador de la Base de Datos.
	 * 
	 * @param controladorES     --> Controlador de Entrada y Salida.
	 * 
	 * @param sesionesElegidas  --> Sesiones elegidas por el usuario en el anterior
	 *                          método.
	 * 
	 * @param numEspectadores   --> Número de espectadores elegido por el usuario.
	 * 
	 * @param nombresSala       --> Nombre de las salas, la cual incluye su número.
	 * 
	 * @param peliculas         --> Párametro que guarda toda la información
	 *                          necesaria de las películas, como: su id, su nombre,
	 *                          su precio y su duración.
	 * 
	 * @param peliculasElegidas --> Películas elegidas por el usuario.
	 * 
	 * @param descuentos        --> Párametro que guarda los descuentos aplicados.
	 * 
	 * @param i                 --> Párametro que tiene en cuenta diferentes números
	 *                          ID.
	 */
	private static void Descuentos(ControladorBD controladorBD, Controlador controladorES,
			ArrayList<Sesion> sesionesElegidas, ArrayList<Integer> numEspectadores, ArrayList<String> nombresSala,
			ArrayList<Pelicula> peliculas, ArrayList<String> peliculasElegidas, ArrayList<Integer> descuentos, int i) {
		// Variable iniciada
		double precioDescuento = 0;
		System.out.println("\nPrecio total con descuento: ");
		if (peliculasElegidas.size() == 2) {
			DescuentoVeinte(controladorBD, controladorES, sesionesElegidas, numEspectadores, nombresSala, peliculas,
					peliculasElegidas, descuentos, i, precioDescuento);

		} else if (peliculasElegidas.size() + 1 > 2) {
			DescuentoTreinta(controladorBD, controladorES, sesionesElegidas, numEspectadores, nombresSala, peliculas,
					peliculasElegidas, descuentos, i, precioDescuento);

		} else {
			System.out.print("\nNo hay descuento");
		}

		System.out.println("\n----------------\n");

	}

	/**
	 * 
	 * Se calcula un 20% de descuento
	 * 
	 * @param controladorBD     --> Controlador de la Base de Datos.
	 * 
	 * @param controladorES     --> Controlador de Entrada y Salida.
	 * 
	 * @param sesionesElegidas  --> Sesiones elegidas por el usuario en el anterior
	 *                          método.
	 * 
	 * @param numEspectadores   --> Número de espectadores elegido por el usuario.
	 * 
	 * @param nombresSala       --> Nombre de las salas, la cual incluye su número.
	 * 
	 * @param peliculas         --> Párametro que guarda toda la información
	 *                          necesaria de las películas, como: su id, su nombre,
	 *                          su precio y su duración.
	 * 
	 * @param peliculasElegidas --> Películas elegidas por el usuario.
	 * 
	 * @param descuentos        --> Párametro que guarda los descuentos aplicados.
	 * 
	 * @param i                 --> Párametro que tiene en cuenta diferentes números
	 *                          ID.
	 * 
	 * @param precioDescuento   --> El precio con descuento.
	 */
	private static void DescuentoVeinte(ControladorBD controladorBD, Controlador controladorES,
			ArrayList<Sesion> sesionesElegidas, ArrayList<Integer> numEspectadores, ArrayList<String> nombresSala,
			ArrayList<Pelicula> peliculas, ArrayList<String> peliculasElegidas, ArrayList<Integer> descuentos, int i,
			double precioDescuento) {
		descuentos.add(20);
		precioDescuento = (sesionesElegidas.get(i).getPrecio_Sesion() * numEspectadores.get(i)
				- (sesionesElegidas.get(i).getPrecio_Sesion() * numEspectadores.get(i)) * 0.2);
		controladorES.redondear(precioDescuento, 2);
		System.out.print("\n" + precioDescuento + "(%20)");

	}

	/**
	 * 
	 * Se calcula un 30% de descuento
	 * 
	 * @param controladorBD     --> Controlador de la Base de Datos.
	 * 
	 * @param controladorES     --> Controlador de Entrada y Salida.
	 * 
	 * @param sesionesElegidas  --> Sesiones elegidas por el usuario en el anterior
	 *                          método.
	 * 
	 * @param numEspectadores   --> Número de espectadores elegido por el usuario.
	 * 
	 * @param nombresSala       --> Nombre de las salas, la cual incluye su número.
	 * 
	 * @param peliculas         --> Párametro que guarda toda la información
	 *                          necesaria de las películas, como: su id, su nombre,
	 *                          su precio y su duración.
	 * 
	 * @param peliculasElegidas --> Películas elegidas por el usuario.F
	 * 
	 * @param descuentos        --> Párametro que guarda los descuentos aplicados.
	 * 
	 * @param i                 --> Párametro que tiene en cuenta diferentes números
	 *                          ID.
	 * 
	 * @param precioDescuento   --> El precio con descuento.
	 */
	private static void DescuentoTreinta(ControladorBD controladorBD, Controlador controladorES,
			ArrayList<Sesion> sesionesElegidas, ArrayList<Integer> numEspectadores, ArrayList<String> nombresSala,
			ArrayList<Pelicula> peliculas, ArrayList<String> peliculasElegidas, ArrayList<Integer> descuentos, int i,
			double precioDescuento) {
		descuentos.add(30);
		precioDescuento = (sesionesElegidas.get(i).getPrecio_Sesion() * numEspectadores.get(i)
				- (sesionesElegidas.get(i).getPrecio_Sesion() * numEspectadores.get(i)) * 0.3);
		controladorES.redondear(precioDescuento, 2);
		System.out.print("\n" + precioDescuento + "(%30)\n");
	}
}
