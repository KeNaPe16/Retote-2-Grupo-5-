package vista;

import controlador.Controlador;

public class Principal {

	private static String[][] productos = new String[4][5]; // array unico (static) de los productos
	private static double[][] precios = new double[4][5]; // array unico (static) de los precios
	private static int[][] identificadores = new int[4][5]; // array unico (static) de los identificadores

//	Tipo: Bebidas ([0][x])	 00 01 02 03 04	
//	Tipo: Salados ([1][x])	 10 11 12 13 14
//	Tipo: Dulces  ([2][x])   20 21 22 23 24
//	Tipo: Otros   ([3][x])   30 31 32 33 34 
// mismas posiciones el los precios y ID

	public static void llenarProductos() {

		for (int i = 0; i < productos.length; i++) { // rellenar todos los espacios de nada, para evitar valores null
			for (int j = 0; j < productos[i].length; j++) {
				productos[i][j] = "";
			}
		} // lista de productos reales, los que no esten especificados aqui deberan de ser
			// implementados con añadir producto.

		// Tipo: Bebidas
		productos[0][0] = "Agua Mineral";
		productos[0][1] = "Coca-Cola";
		productos[0][2] = "Zumo de Naranja";
		productos[0][3] = "Té Helado";
		productos[0][4] = "Bebida Energética";

		precios[0][0] = 1.00;
		precios[0][1] = 1.50;
		precios[0][2] = 1.20;
		precios[0][3] = 1.30;
		precios[0][4] = 2.00;

		identificadores[0][0] = 111;
		identificadores[0][1] = 151;
		identificadores[0][2] = 192;
		identificadores[0][3] = 177;
		identificadores[0][4] = 140;

		// Tipo: Salados
		productos[1][0] = "Patatas Fritas";
		productos[1][1] = "Palomitas";
		productos[1][2] = "Nachos con Queso";
		productos[1][3] = "Galletas Saladas";

		precios[1][0] = 1.25;
		precios[1][1] = 1.50;
		precios[1][2] = 2.00;
		precios[1][3] = 1.10;

		identificadores[1][0] = 222;
		identificadores[1][1] = 211;
		identificadores[1][2] = 287;
		identificadores[1][3] = 299;

		// Tipo: Dulces
		productos[2][0] = "Chocolate con Leche";
		productos[2][1] = "Chicle de Fresa";
		productos[2][2] = "Barrita de Cereal";

		precios[2][0] = 1.50;
		precios[2][1] = 0.80;
		precios[2][2] = 1.20;

		identificadores[2][0] = 333;
		identificadores[2][1] = 345;
		identificadores[2][2] = 322;

		// Tipo: Otros
		productos[3][0] = "Toallita Húmeda";
		productos[3][1] = "Mascarilla Desechable";

		precios[3][0] = 0.50;
		precios[3][1] = 1.00;

		identificadores[3][0] = 444;
		identificadores[3][1] = 411;
	}

	public static void leerProductos() { // metodo para leer el array doble, no es necesario en el menu principal

		for (int i = 0; i < productos.length; i++) {
			for (int j = 0; j < productos[i].length; j++) {
				System.out.print(productos[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void menu() { // menu principal, lo que se puede ver cuando la maquina no esta en ningun ala
		System.out.println("Bienvenido, ");
		System.out.println("Pulse 1 para acceder al menu de clientes ");
		System.out.println("Pulse 2 para acceder al menu de administrador ");
		int opcion = Controlador.pedirNumeroEnteroRango(1, 2);
		if (opcion == 1) {
			System.out.println("Accediendo al menu de clientes ");
			Cliente.menuCliente();
		} else if (opcion == 2) {
			System.out.println("Accediendo al menu de administrador ");
			Administrador.menuAdministrador();
		}

	}

	public static String[][] conseguirProductos() { // getter de productos, devuelve un metodo privado (solo puede acceder Principal) a otra clase
		return productos;
	}

	public static double[][] conseguirPrecios() { // getter de precios, devuelve un metodo privado (solo puede acceder Principal) a otra clase
		return precios;
	}

	public static int[][] conseguirID() { // getter de ID, devuelve un metodo privado (solo puede acceder Principal) a otra clase
		return identificadores;
	}

	public static void guardarProductos(String[][] productos) { //setter de productos, cambia un metodo privado (solo puede acceder Principal) con lo que le de otra clase
		Principal.productos = productos;
	}

	public static void guardarPrecios(double[][] precios) { //setter de precios, cambia un metodo privado (solo puede acceder Principal) con lo que le de otra clase
		Principal.precios = precios;
	}

	public static void guardarIdentificadores(int[][] identificadores) { //setter de ID, cambia un metodo privado (solo puede acceder Principal) con lo que le de otra clase
		Principal.identificadores = identificadores;
	}

	public static void main(String[] args) { //inicio del programa
		llenarProductos();
		menu();
	}

}
