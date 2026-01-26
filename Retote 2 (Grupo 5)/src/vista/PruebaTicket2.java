package vista;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import controlador.Controlador;
import controlador.ControladorBD;
import controlador.ControladorFicheros;
import modelo.Cliente;
import modelo.Compra;
import modelo.Entrada;

public class PruebaTicket2 {
//Compra compraAGrabar, ArrayList<Entrada> entradasAGrabar, Cliente cliente

	public static void main(String[] args) {
		Controlador controladorES = new Controlador();
		ControladorBD controladorBD = new ControladorBD("cine_reto");
		PruebaTicket2 prueba = new PruebaTicket2();
		prueba.pruebaDatos(controladorBD, controladorES);

	}

	public void pruebaDatos(ControladorBD controladorBD, Controlador controladorES) {

		Timestamp fecha_hora = new Timestamp(System.currentTimeMillis());

		Compra compra = new Compra(1, 15.50, fecha_hora, 20, "12345678A");

		ArrayList<Entrada> entradas = new ArrayList<Entrada>();
		Entrada entradaPrueba1 = new Entrada(2, 7.00, 20, 1, 2, 1);
		Entrada entradaPrueba2 = new Entrada(1, 8.50, 20, 1, 1, 1);
		entradas.add(entradaPrueba1);

		Cliente cliente = new Cliente("01234567K", "marta.alvarez@example.com", "Marta Álvarez", "martita");
		mostrarTicket(compra,entradas,cliente);

	}

	public void mostrarTicket(Compra compra, ArrayList<Entrada> entradas, Cliente cliente) {

		System.out.println("-----------------------------------------------------");
		System.out.println("Compra (numero: " + compra.getId_Compra() + ") de " + compra.getPrecio_Compra()
				+ "€ con un descuento de " + compra.getDescuento() + "%");
		System.out.println("Realizada en el " + compra.getFecha_hora());
		System.out.println("\nPor el Cliente: " + cliente.getNombre_Apellidos() 
				+ "\nCon el Email: " + cliente.getEmail()
				+ "\nY el DNI: " + cliente.getDNI());
		System.out.println("\nQue incluye: ");
		System.out.println("-----------------------------------------------------\n");
		
		
		for (Entrada entrada : entradas) {
			System.out.println("Entrada (numero: " + entrada.getId_Entrada() + ") para la sesion numero: " + entrada.getId_Sesion());
			System.out.println("Valida para " + entrada.getNumero_Personas() + " personas \nComprada por " + entrada.getPrecio_Entrada()
					+ "€ con un descuento de " + entrada.getDescuento() + "%");
			System.out.println("\n-----------------------------------------------------\n");
		}
		
		
		
		
		
		
		
		
		
		

	}
}
