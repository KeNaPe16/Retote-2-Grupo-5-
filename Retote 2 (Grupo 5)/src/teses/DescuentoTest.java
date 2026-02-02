package teses;

import static org.junit.Assert.*;

import java.sql.Time;
import java.util.ArrayList;

import org.junit.Test;

import controlador.Controlador;
import modelo.Sesion;
import vista.Comprador;
import vista.Principal;

public class DescuentoTest {

// (precioSesion*numeroespectadores)-(precioSesion*numeroespectadores*0.2)  precio total - descuento
//20-(20*0.2) = 16
//20-(20*0.3) = 14

	/**
	 * Testea los descuentos del 20% correctamente aplicados
	 */
	@Test
	public void testDescuentoVeinteBien() {
		Comprador principal = new Comprador();
		Controlador controladorES = new Controlador();
		ArrayList<Sesion> sesionesElegidas = new ArrayList<Sesion>();
		ArrayList<Integer> numEspectadores = new ArrayList<Integer>();
		Sesion sesion1 = new Sesion(1, Time.valueOf("18:30:00"), Time.valueOf("20:15:00"), "2026-01-28", 3, 10, 2, 5);
		sesionesElegidas.add(sesion1);
		numEspectadores.add(2);

		double resultado = principal.DescuentoVeinte(controladorES, sesionesElegidas, numEspectadores, 0, 0);
		assertEquals(16.0, resultado, 0.01);

	}

	/**
	 * Testea los descuentos del 30% correctamente aplicados
	 */
	@Test
	public void testDescuentoTreintaBien() {
		Comprador principal = new Comprador();
		Controlador controladorES = new Controlador();
		ArrayList<Sesion> sesionesElegidas = new ArrayList<Sesion>();
		ArrayList<Integer> numEspectadores = new ArrayList<Integer>();
		Sesion sesion1 = new Sesion(1, Time.valueOf("18:30:00"), Time.valueOf("20:15:00"), "2026-01-28", 3, 10, 2, 5);
		sesionesElegidas.add(sesion1);
		numEspectadores.add(2);

		double resultado = principal.DescuentoTreinta(controladorES, sesionesElegidas, numEspectadores, 0, 0);
		assertEquals(14.0, resultado, 0.01);

	}

	/**
	 * Testea que pasaría si intentas pedir un descuento del 20% con cero
	 * espectadores
	 */
	@Test
	public void testDescuentoVeinteCeroEspectadores() {
		Comprador principal = new Comprador();
		Controlador controladorES = new Controlador();
		ArrayList<Sesion> sesionesElegidas = new ArrayList<Sesion>();
		ArrayList<Integer> numEspectadores = new ArrayList<Integer>();

		Sesion sesion1 = new Sesion(3, Time.valueOf("17:00:00"), Time.valueOf("19:00:00"), "2026-01-28", 3, 10, 2, 5);
		sesionesElegidas.add(sesion1);
		numEspectadores.add(0);

		double resultado = principal.DescuentoVeinte(controladorES, sesionesElegidas, numEspectadores, 0, 0);

		assertEquals(0.0, resultado, 0.01);
	}

	/**
	 * Testea que pasaría si intentas pedir un descuento del 30% con cero
	 * espectadores
	 */
	@Test
	public void testDescuentoTreintaCeroEspectadores() {
		Comprador principal = new Comprador();
		Controlador controladorES = new Controlador();
		ArrayList<Sesion> sesionesElegidas = new ArrayList<Sesion>();
		ArrayList<Integer> numEspectadores = new ArrayList<Integer>();

		Sesion sesion1 = new Sesion(3, Time.valueOf("17:00:00"), Time.valueOf("19:00:00"), "2026-01-28", 3, 10, 2, 5);
		sesionesElegidas.add(sesion1);
		numEspectadores.add(0);

		double resultado = principal.DescuentoTreinta(controladorES, sesionesElegidas, numEspectadores, 0, 0);

		assertEquals(0.0, resultado, 0.01);
	}

	/**
	 * Testea que pasaría so pones en el índice un número fuera de rango
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testIndiceFueraDeRango() {
		Comprador principal = new Comprador();
		Controlador controladorES = new Controlador();
		ArrayList<Sesion> sesiones = new ArrayList<>();
		ArrayList<Integer> espectadores = new ArrayList<>();

		sesiones.add(new Sesion(1, Time.valueOf("18:30:00"), Time.valueOf("20:15:00"), "2026-01-28", 3, 10, 2, 5));
		espectadores.add(2);

		principal.DescuentoVeinte(controladorES, sesiones, espectadores, 5, 0);
	}

	/**
	 * Testea que pasaría si el redondeo no estuviese aplicado
	 */
	@Test
	public void testRedondeoNoAplicado() {
		Comprador principal = new Comprador();
		Controlador controladorES = new Controlador();
		ArrayList<Sesion> sesiones = new ArrayList<>();
		ArrayList<Integer> espectadores = new ArrayList<>();

		sesiones.add(new Sesion(1, Time.valueOf("18:30:00"), Time.valueOf("20:15:00"), "2026-01-28", 3, 10.333, 2, 5));
		espectadores.add(1);

		double resultado = principal.DescuentoVeinte(controladorES, sesiones, espectadores, 0, 0);

		assertEquals(8.27, resultado, 0.01);
	}

	/**
	 * Test que prueba si el índice comienza por otro número que no empiece por 0
	 */
	@Test
	public void testDescuentoIndiceUno() {
		Comprador principal = new Comprador();
		Controlador controladorES = new Controlador();
		ArrayList<Sesion> sesiones = new ArrayList<>();
		ArrayList<Integer> espectadores = new ArrayList<>();

		sesiones.add(new Sesion(1, Time.valueOf("16:00:00"), Time.valueOf("18:00:00"), "2026-01-28", 3, 8, 2, 5));
		sesiones.add(new Sesion(2, Time.valueOf("19:00:00"), Time.valueOf("21:00:00"), "2026-01-28", 3, 12, 2, 5));

		espectadores.add(1);
		espectadores.add(3);

		double resultado = principal.DescuentoVeinte(controladorES, sesiones, espectadores, 1, 0);

		assertEquals(28.8, resultado, 0.01);
	}

	/**
	 * Testea los descuentos del 30% correctamente aplicados pero con un precio
	 * decimal
	 */
	@Test
	public void testDescuentoTreintaPrecioDecimal() {
		Comprador principal = new Comprador();
		Controlador controladorES = new Controlador();
		ArrayList<Sesion> sesiones = new ArrayList<>();
		ArrayList<Integer> espectadores = new ArrayList<>();

		sesiones.add(new Sesion(1, Time.valueOf("18:30:00"), Time.valueOf("20:15:00"), "2026-01-28", 3, 7.5, 2, 5));
		espectadores.add(3);

		double resultado = principal.DescuentoTreinta(controladorES, sesiones, espectadores, 0, 0);

		// 7.5 * 3 = 22.5 → -30% = 15.75
		assertEquals(15.75, resultado, 0.01);
	}

	/**
	 * Testea los descuentos del 20% correctamente aplicados pero con un precio
	 * decimal
	 */
	@Test
	public void testDescuentoVeintePrecioDecimal() {
		Comprador principal = new Comprador();
		Controlador controladorES = new Controlador();
		ArrayList<Sesion> sesiones = new ArrayList<>();
		ArrayList<Integer> espectadores = new ArrayList<>();

		sesiones.add(new Sesion(1, Time.valueOf("18:30:00"), Time.valueOf("20:15:00"), "2026-01-28", 3, 7.5, 2, 5));
		espectadores.add(2);

		double resultado = principal.DescuentoVeinte(controladorES, sesiones, espectadores, 0, 0);

		// 7.5 * 2 = 15 → -20% = 12
		assertEquals(12.0, resultado, 0.01);
	}

}
