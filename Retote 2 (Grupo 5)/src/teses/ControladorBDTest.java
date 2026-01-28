package teses;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import controlador.ControladorBD;
import modelo.Cliente;
import modelo.Pelicula;
import modelo.Sesion;

public class ControladorBDTest {

	private static ControladorBD controlador;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Se comprueba la generacion del controlador correcto
	 */
	@Test
	public void testControladorBDCorrecto() {
		controlador = new ControladorBD("cine_reto");
		assertNotNull(controlador);
	}

	/**
	 * Se comprueba la generacion del controlador aunque la BD no exista
	 */
	@Test
	public void testControladorBDIncorrecto() {
		controlador = new ControladorBD(null);
		assertNotNull(controlador);
	}

	/**
	 * Se comprueba el inicio de una conexion funcional (BD existente)
	 */
	@Test
	public void testIniciarConexion() {
		controlador = new ControladorBD("cine_reto");
		boolean estadoConexion = controlador.iniciarConexion();
		assertTrue(estadoConexion);
		controlador.cerrarConexion();
	}

	/**
	 * Se comprueba el inicio incorrecto (fallo) de una conexion (BD no existente)
	 */
	@Test
	public void testIniciarConexionNoExistente() {
		controlador = new ControladorBD("no existo cruck");
		boolean estadoConexion = controlador.iniciarConexion();
		assertFalse(estadoConexion);
		controlador.cerrarConexion();
	}

	/**
	 * Se comprueba que se puedan establecer varias conexiones simultaneas
	 */

	@Test
	public void testIniciarConexionConUnaConexionExistente() {
		controlador = new ControladorBD("cine_reto");
		boolean estadoConexion1 = controlador.iniciarConexion();
		boolean estadoConexion2 = controlador.iniciarConexion();
		assertTrue(estadoConexion1 && estadoConexion2);
		controlador.cerrarConexion();
		controlador.cerrarConexion();
	}

	/**
	 * Se comprueba el correcto cierre de la BD
	 */
	@Test
	public void testCerrarConexion() {
		controlador = new ControladorBD("cine_reto");
		boolean estadoConexion = controlador.iniciarConexion();
		estadoConexion = controlador.cerrarConexion();
		assertTrue(estadoConexion);
	}

	/**
	 * Se comprueba que no se generen errores ni se cierren conexiones inexistentes
	 */
	@Test
	public void testCerrarConexionSinTenerUnaAbierta() {
		controlador = new ControladorBD("cine_reto");
		boolean estadoConexion = controlador.cerrarConexion();
		assertFalse(estadoConexion);
	}

	/**
	 * Comprueba que no cierre la conexion despues de estar cerrada
	 */
	@Test
	public void testCerrarConexionDosVeces() {
		controlador = new ControladorBD("cine_reto");
		controlador.iniciarConexion();
		assertTrue(controlador.cerrarConexion());
		assertFalse(controlador.cerrarConexion());
	}

	/**
	 * Se comprueba que se obtienen todos los clientes de la BD
	 */
	@Test
	public void testDatosCliente() {
		controlador = new ControladorBD("cine_reto");
		controlador.iniciarConexion();
		ArrayList<Cliente> clientes = controlador.datosCliente();
		if (clientes.size() != 59) {
			fail("Se esperaban 59 clientes");
		}
	}

	/**
	 * Se comprueba que los datos conseguidos sean correctos (Debido a que todos los
	 * datos del cliente son obligatorios en la BD, no deberia tener ningun apartado
	 * vacio)
	 */
	@Test
	public void testDatosClienteCorrectos() {
		controlador = new ControladorBD("cine_reto");
		controlador.iniciarConexion();
		ArrayList<Cliente> clientes = controlador.datosCliente();
		if (clientes.getFirst().getContraseña().isBlank() || clientes.getFirst().getDNI().isBlank()
				|| clientes.getFirst().getEmail().isBlank() || clientes.getFirst().getNombre_Apellidos().isBlank()) {
			fail("Algun apartado esta vacio");
		}
	}

	/**
	 * Se comprueba que devuelva un error al hacer una consulta sin iniciar conexion
	 */
	@Test(expected = NullPointerException.class)
	public void testDatosClienteSinIniciarConexion() {
		controlador = new ControladorBD("cine_reto");
		ArrayList<Cliente> clientes = controlador.datosCliente();
		if (clientes.size() != 59) {
			fail("Se esperaban 59 clientes");
		}
	}

	/**
	 * Se comprueba que la consulta verdaderamente devuelve el ultimo id
	 */
	@Test
	public void testDatosUltimoIDCompra() {
		controlador = new ControladorBD("cine_reto");
		controlador.iniciarConexion();
		int ultimaCompra = controlador.datosUltimoIDCompra();
		if (ultimaCompra != 55) {
			fail("Se esperaba que el ultimo id fuese 55");
		}
	}

	/**
	 * Se comprueba que devuelva un error al hacer una consulta sin iniciar conexion
	 */
	@Test(expected = NullPointerException.class)
	public void testDatosUltimoIDCompraSinConexion() {
		controlador = new ControladorBD("cine_reto");
		int ultimaCompra = controlador.datosUltimoIDCompra();
		if (ultimaCompra != 55) {
			fail("Se esperaba que el ultimo id fuese 55");
		}
	}

	/**
	 * Se comprueba que la consulta verdaderamente devuelve el ultimo id
	 */
	@Test
	public void testDatosUltimoIDEntrada() {
		controlador = new ControladorBD("cine_reto");
		controlador.iniciarConexion();
		int ultimaEntrada = controlador.datosUltimoIDEntrada();
		if (ultimaEntrada != 114) {
			fail("Se esperaba que el ultimo id fuese 114");
		}
	}

	/**
	 * Se comprueba que devuelva un error al hacer una consulta sin iniciar conexion
	 */
	@Test(expected = NullPointerException.class)
	public void testDatosUltimoIDEntradaSinConexion() {
		controlador = new ControladorBD("cine_reto");
		int ultimaEntrada = controlador.datosUltimoIDEntrada();
		if (ultimaEntrada != 84) {
			fail("Se esperaba que el ultimo id fuese 84");
		}
	}

	/**
	 * Se comprueba que se consigan todas las peliculas que tengan sesion
	 */
	@Test
	public void testDatosPelicula() {
		controlador = new ControladorBD("cine_reto");
		controlador.iniciarConexion();
		ArrayList<Pelicula> peliculas = controlador.datosPelicula();

		if (peliculas.size() != 33) {
			fail("Se esperaban 33 peliculas");
		}
	}

	@Test
	public void testDatosPeliculaCorrectos() {
		controlador = new ControladorBD("cine_reto");
		controlador.iniciarConexion();
		ArrayList<Pelicula> peliculas = controlador.datosPelicula();
		if (peliculas.getFirst().getId_Pelicula() < 0 || peliculas.getFirst().getDuracion() < 0
				|| peliculas.getFirst().getPrecio_Base() < 0 || peliculas.getFirst().getNombre().isBlank()) {
			fail("Algun dato no es correcto");
		}
	}

	/**
	 * Se comprueba que se obtengan las sesiones correctas para las peliculas
	 */
	@Test
	public void testDatosSesionFecha() {
		controlador = new ControladorBD("cine_reto");
		controlador.iniciarConexion();
		ArrayList<Sesion> sesiones1 = controlador.datosSesion_Fecha(1);
		ArrayList<Sesion> sesiones2 = controlador.datosSesion_Fecha(7);

		if (sesiones1.size() != 1 || sesiones2.size() != 4) {
			fail("Se esperaban 1 sesion");
		}
	}

	@Test
	public void testDatosSesionFechaCorrectos() {
		controlador = new ControladorBD("cine_reto");
		controlador.iniciarConexion();
		ArrayList<Sesion> sesiones = controlador.datosSesion_Fecha(1);
		//|| sesiones.getFirst().getHora_Fin() != null
//		|| sesiones.getFirst().getHora_Inicio() != null
		if (sesiones.getFirst().getFecha().isBlank() || sesiones.getFirst().getId_Pelicula() < 0
				|| sesiones.getFirst().getId_Sala() < 0 || sesiones.getFirst().getId_Sesion() < 0
				|| sesiones.getFirst().getNumero_Espectadores() <= 0) {
			fail("Algun dato no es correcto");
		}
	}

//	datossala

}
