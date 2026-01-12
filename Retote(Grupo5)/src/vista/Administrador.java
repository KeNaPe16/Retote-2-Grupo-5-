package vista;

import controlador.Controlador;

public class Administrador {

	// Declarar arrays con String
	private static String[][] producto = Principal.conseguirProductos();
	// Declarar arrays con valor int
	private static int[][] IDproducto = Principal.conseguirID();
	// Declarar arrays con valor double
	private static double[][] precios = Principal.conseguirPrecios();

	private static double esPositivoDouble(double numero) { // bucle de confirmacion de dinero ingresado positivo
		boolean positivo = false;
		while (positivo == false) {
			if (numero < 0) {
				System.out.println("El numero debe ser positivo");
				System.out.println("Por favor, vuelve a ingresar un numero");
				numero = Controlador.pedirNumeroComa();
			} else
				positivo = true;

		}
		return numero;
	}

	private static String comprobarNombre() {

		// Pedirá un String, lo guardará y luego lo validará
		boolean valido = false;
		String nombre = "Valor Inicio";

		while (valido == false) {
			System.out.println("Introduce un nombre");
			nombre = Controlador.pedirString();
			boolean existe = false;

			for (int i = 0; i < producto.length && !existe; i++) { // comprueba si el nombre esta en uso
				for (int j = 0; j < producto[i].length && !existe; j++) {
					if (producto[i][j].equalsIgnoreCase(nombre)) {
						System.out.println("El nombre ya está en uso");
						existe = true;
					}
				}
			}

			if (!existe) {
				valido = true;
			}
		}
		return nombre;
	}

	// Añadir metodo principal
	public static void eliminarProducto() {
		// Mencionar metodos que va seguir
		leerProductos();

		System.out.println("Indtroduce el ID del producto a borrar");
		// Seleccionando producto por su ID
		int ID = controlador.Controlador.validarID();
		// Hasta que no encuentre el producto eliminar, no para el bucle
		boolean encontrado = false;

		// Metodo para borrar producto
		for (int i = 0; i < IDproducto.length; i++) { // Buscar longitud (ID producto)
			for (int j = 0; j < IDproducto[i].length; j++) {
				if (ID == IDproducto[i][j]) { // Que coincida el (ID producto)
					System.out.println("Producto encontrado"); // Muestra mensaje de producto encontrado
					producto[i][j] = ""; // Convierte en un espacio a (producto)
					IDproducto[i][j] = 0; // Convierte en cero a (ID producto)
					encontrado = true; // Encontrado (producto e ID producto eliminado)
					System.out.println("Producto Eliminado"); // Muestra mensaje de producto eliminado
					Principal.guardarIdentificadores(IDproducto);
					Principal.guardarPrecios(precios);
					Principal.guardarProductos(producto);

				}

			}

		}
		// Enseñar producto eliminado
		leerProductos();

		// Muestra producto fallido
		if (encontrado == false) {
			System.out.println("Producto no encontrado"); // Muestra mensaje de error
		}

	}

	// Anadir metodo secundario a (enseñarPoducto)
	public static void leerProductos() { // metodo para leer el array doble

		for (int i = 0; i < producto.length; i++) {
			for (int j = 0; j < producto[i].length; j++) {
				if (!producto[i][j].isEmpty()) { // solo imprime el nombre y precio en el caso de que el hueco del
													// producto no este vacio
					System.out.print("Producto del tipo " + (i + 1) + " en el hueco " + (j + 1) + " " + producto[i][j]);
					System.out.println(" " + precios[i][j] + "€" + " ID: " + IDproducto[i][j]);
				}
			}
		}
		// Separador
		System.out.println("----");
	}

	// Anadir metodo secundario a (borrarPoducto)

	public static void pedirTipo() {

		// Pedirá el tipo de producto, lo guardará y luego lo validará

		System.out.println("Por favor, introduzca el NÚMERO del tipo de producto:");
		System.out.println();
		System.out.println("1.- Bebidas");
		System.out.println("2.- Salados");
		System.out.println("3.- Dulces");
		System.out.println("4.- Otros");

		int tipo = Controlador.pedirNumeroEnteroRango(1, 4) - 1; // menos 1 pues la maquina interpreta desde el 0 al 3
		boolean comprobado = comprobarEspacio(tipo);

		if (comprobado == false) {
			System.out.println("No hay hueco en la maquina");
		} else { // si hay hueco, guardar
			guardarNuevoProducto(pedirID(), comprobarNombre(), esPositivoDouble(Controlador.pedirNumeroComa()), tipo);
		}
	}

	public static int pedirID() {
		// Pedirá un ID, lo guardará y luego lo validará1
		boolean valido = false;
		int ID = -999999999;

		while (valido == false) {
			ID = Controlador.validarID();
			boolean existe = false;

			for (int i = 0; i < IDproducto.length && !existe; i++) { // comprueba que no se repita
				for (int j = 0; j < IDproducto[i].length && !existe; j++) {
					if (IDproducto[i][j] == ID) {
						System.out.println("El ID ya está en uso");
						existe = true;
					}
				}
			}

			if (!existe) {
				valido = true;
			}
		}

		return ID;
	}

	private static boolean comprobarEspacio(int tipo) {
		// Comprobará si hay espacio en la máquina expendedora

		boolean comprobado = false;

		for (int j = 0; j < producto[tipo].length; j++) {
			if (producto[tipo][j].isEmpty()) {
				System.out.println("Hay hueco en el espacio " + (j + 1));
				comprobado = true;
			}
		}

		if (comprobado == false) {
			System.out.println("No hay hueco.");
		}

		return comprobado;
	}

	public static void guardarNuevoProducto(int codigo, String nombre, double precio, int tipo) {
		// Guardará el nuevo producto
		boolean guardado = false;
		System.out.println("Se ha guardado el nuevo producto");

		for (int j = 0; j < producto[tipo].length; j++) {
			if (producto[tipo][j].isEmpty() && guardado == false) { // solo guarda el producto nuevo en el caso de que
																	// este vacio
				producto[tipo][j] = nombre;
				IDproducto[tipo][j] = codigo;
				precios[tipo][j] = precio;
				Principal.guardarIdentificadores(IDproducto); // lo guarda en la lista Principal
				Principal.guardarPrecios(precios); // lo guarda en la lista Principal
				Principal.guardarProductos(producto); // lo guarda en la lista Principal
				guardado = true;

			}

		}

	}

	public static void seleccionarProducto() {
		System.out.println("-----");
		leerProductos();
		System.out.println("Por favor, seleccione el producto a modificar poniendo su ID númerico: ");
		// Seleccionando producto por su ID

		int ID = controlador.Controlador.validarID();

		boolean encontrado = false;

		// Metodo para seleccionar producto
		for (int i = 0; i < IDproducto.length; i++) { // Buscar longitud (ID producto)
			for (int j = 0; j < IDproducto[i].length; j++) {
				if (ID == IDproducto[i][j]) { // Que coincida el (ID producto)
					encontrado = true; // Encontrado
					System.out.println("Producto encontrado"); // Muestra mensaje de producto encontrado
					cambiarDato(i, j);
				}
			}
		}
		if (encontrado == false) {
			System.out.println("Producto no encontrado");
			// Muestra mensaje de error
			seleccionarProducto();
		} else {

		}
	}

	public static void cambiarDato(int linea, int columna) {
		// Preguntarle al administrador que quiere cambiar en el producto
		System.out.println("Escoja dato que quiere modificar.");
		System.out.println("------");
		System.out.println("Por favor, introduzca el NÚMERO de lo que desea cambiar:");
		System.out.println();
		System.out.println("1.- Nombre");
		System.out.println("2.- Precio");

		// Recoger número pedido
		int opcion = controlador.Controlador.pedirNumeroEnteroRango(1, 2);

		if (opcion == 1) {
			// cambiará el nombre del producto
			String NuevoNombre = comprobarNombre();
			producto[linea][columna] = NuevoNombre;
		} else {
			// cambiará el precio del producto
			System.out.println("Pon el nuevo precio sin la coma.");
			double NuevoPrecio = esPositivoDouble(controlador.Controlador.pedirNumeroComa());

			precios[linea][columna] = NuevoPrecio;
		}

		leerProductos();
	}

	// Realizar menú en (producto)
	public static void menuAdministrador() {
		boolean salir = false;
		boolean correcto = false;
		String usuario = "Pakito"; // usuario
		int clave = 9875; // contrasena
		System.out.println("Por favor, introduzca el usuario");
		String usuIntroducido = Controlador.pedirString();
		if (usuIntroducido.equalsIgnoreCase(usuario)) {
			System.out.println("Usuario correcto");
			System.out.println("Por favor, introduzca la clave (solo numeros)");
			int claveIntroducida = Controlador.pedirNumeroEntero();
			if (claveIntroducida == clave) {
				correcto = true;
			}
		}

		if (correcto == true) { // solo entra en el caso de que se inicie sesion correctamente
			while (salir == false) {
				System.out.println("");
				System.out.println("--MENÚ--");
				System.out.println("1.- Nuevo producto");
				System.out.println("2.- Modificar producto");
				System.out.println("3.- Eliminar producto");
				System.out.println("4.- Salir");
				// Se pide NUMERO ENTERO en controlador para (producto)
				int caso = controlador.Controlador.pedirNumeroEntero();
				// Añadir menu de opciones de (producto) usando switch
				switch (caso) {
				case 1:
					System.out.println("Nuevo producto");
					System.out.println("");
					pedirTipo();
					break;
				case 2:
					System.out.println("Modificar producto");
					System.out.println("");
					seleccionarProducto();
					break;
				case 3:
					System.out.println("Eliminar producto");
					System.out.println("");
					eliminarProducto();
					break;
				case 4:
					System.out.println("Volviendo al menu principal");
					Principal.menu();
					break;
				}
			}
		} else { // en el caso de que el inicio de sesion sea incorrecto
			System.out.println("Inicio de sesion incorrecto, volviendo al menu principal");
			Principal.menu();
		}
	}

}