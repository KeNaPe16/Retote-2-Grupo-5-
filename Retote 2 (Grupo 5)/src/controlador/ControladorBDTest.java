package controlador;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import modelo.Cliente;

public class ControladorBDTest {

	private static ControladorBD controlador;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testControladorBDCorrecto() {
		controlador = new ControladorBD("cine_reto");
		assertNotNull(controlador);
	}

	@Test
	public void testControladorBDIncorrecto() {
		controlador = new ControladorBD(null);
		assertNotNull(controlador);
	}

	@Test
	public void testIniciarConexion() {
		controlador = new ControladorBD("cine_reto");
		boolean estadoConexion = controlador.iniciarConexion();
		assertTrue(estadoConexion);
		controlador.cerrarConexion();
	}

	@Test
	public void testIniciarConexionNoExistente() {
		controlador = new ControladorBD("no existo cruck");
		boolean estadoConexion = controlador.iniciarConexion();
		assertFalse(estadoConexion);
		controlador.cerrarConexion();
	}

	@Test
	public void testIniciarConexionConUnaConexionExistente() {
		controlador = new ControladorBD("cine_reto");
		boolean estadoConexion1 = controlador.iniciarConexion();
		boolean estadoConexion2 = controlador.iniciarConexion();
		assertTrue(estadoConexion1 && estadoConexion2);
		controlador.cerrarConexion();
		controlador.cerrarConexion();
	}

	@Test
	public void testCerrarConexion() {
		controlador = new ControladorBD("cine_reto");
		boolean estadoConexion = controlador.iniciarConexion();
		estadoConexion = controlador.cerrarConexion();
		assertTrue(estadoConexion);
	}

	@Test
	public void testCerrarConexionSinTenerUnaAbierta() {
		controlador = new ControladorBD("cine_reto");
		boolean estadoConexion = controlador.cerrarConexion();
		assertFalse(estadoConexion);
	}

	@Test
	public void testDatosCliente() {
		controlador = new ControladorBD("cine_reto");
		controlador.iniciarConexion();
		ArrayList<Cliente> clientes = controlador.datosCliente();
		if (clientes.size() != 59) {
			fail("Se esperaban 59 clientes");
		}
	}

	@Test(expected = NullPointerException.class)
	public void testDatosClienteSinIniciarConexion() {
		controlador = new ControladorBD("cine_reto");
		ArrayList<Cliente> clientes = controlador.datosCliente();
		if (clientes.size() != 59) {
			fail("Se esperaban 59 clientes");
		}
	}

	@Test
	public void testDatosUltimoIDCompra() {
		controlador = new ControladorBD("cine_reto");
		controlador.iniciarConexion();
		int ultimaCompra = controlador.datosUltimoIDCompra();
		if (ultimaCompra != 45) {
			fail("Se esperaba que el ultimo id fuese 45");
		}
	}

	@Test(expected = NullPointerException.class)
	public void testDatosUltimoIDCompraSinConexion() {
		controlador = new ControladorBD("cine_reto");
		int ultimaCompra = controlador.datosUltimoIDCompra();
		if (ultimaCompra != 45) {
			fail("Se esperaba que el ultimo id fuese 45");
		}
	}

	@Test
	public void testDatosUltimoIDEntrada() {
		controlador = new ControladorBD("cine_reto");
		controlador.iniciarConexion();
		int ultimaEntrada = controlador.datosUltimoIDEntrada();
		if (ultimaEntrada != 84) {
			fail("Se esperaba que el ultimo id fuese 84");
		}
	}

	@Test(expected = NullPointerException.class)
	public void testDatosUltimoIDEntradaSinConexion() {
		controlador = new ControladorBD("cine_reto");
		int ultimaEntrada = controlador.datosUltimoIDEntrada();
		if (ultimaEntrada != 84) {
			fail("Se esperaba que el ultimo id fuese 84");
		}
	}

}
