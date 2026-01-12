package controlador;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Controlador {

	public static Scanner sc = new Scanner(System.in);

	public static int pedirNumeroEntero() {
		// Valores ha tener en cuenta:
		boolean numeroValido = false;
		int numero = 0;

		// Bucle do-while hasta poner un número entero válido
		do {
			try {
				System.out.println("Introduzca un número");
				numero = Integer.parseInt(sc.nextLine());
				numeroValido = true;
			} catch (NumberFormatException e) {
				System.out.println("Lo siento, se esperaba un número");
			}
		} while (!numeroValido);

		return numero;
	}

	public static int pedirNumeroEnteroRango(int minimo, int maximo) {
		// Valores ha tener en cuenta:
		boolean numeroValido = false;
		int numero = 0;

		// Bucle do-while hasta poner un número entero entre el rango válido
		do {
			try {
				System.out.println("Introduce un número entre: " + minimo + " y " + maximo);
				numero = Integer.parseInt(sc.nextLine());
				numeroValido = true;
			} catch (NumberFormatException e) {
				System.out.println("Lo siento, se esperaba un número entre " + minimo + " y " + maximo);
			}
		} while (!numeroValido || numero < minimo || numero > maximo);
		return numero;
	}

	public static int validarID() {
		// Valores ha tener en cuenta, empezamos por uno inválido normalmente
		int codigo = 001;

		// Bucle do-while hasta poner un ID válido
		do {
			try {
				System.out.println(
						"Introduzca el número identificador del producto, introduzca un valor numerico entre 100 y 999: ");
				codigo = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException excepcion) {
				System.out.println("Se esperaba un valor númerico entre 100 y 999.");
			}
			if (codigo < 100 || codigo > 999) {
				System.out.print("Número no válido. ");
			}
		} while (codigo < 100 || codigo > 999);

		return codigo;

//	System.out.println("codigo correcto");
		// Lo de arriba no va xd, de todas maneras, no hacía falta.
		// El admin sabrá que va porque no le dejará seguir hasta que de un ID válido
	}

	public static double pedirNumeroComa() {
		//Valores ha tener en cuenta:
		boolean numeroValido = false;
		double numero = 0.0;
		
		// Bucle do-while hasta poner un número con o sin coma válido
		// Bueno, sin coma, porque el admin deberá poner punto en vez de coma
		do {
			try {
				System.out.println("Introduzca precio, no use coma al poner el precio.");
				numero = Double.parseDouble(sc.nextLine());
				numeroValido = true;
			} catch (NumberFormatException e) {
				System.out.println("Lo siento, se esperaba un número o ha usado coma al poner el precio.");
			}
		} while (!numeroValido);

		return numero;
	}

	public static String pedirString() {
//		System.out.println("Introduzca el nombre del nuevo producto:");
		// El admin le pondrá el nombre que quiera
		String recibido = sc.nextLine();
		return recibido;

	}
	
	public static double redondear(double numero, int cantidadComa) {
	   		
	    BigDecimal bd = BigDecimal.valueOf(numero);
	    bd = bd.setScale(cantidadComa, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}