package vista;

import controlador.Controlador;

public class Cliente {

	private static String[] listaCompra = new String[25];

	public static void llenarCompras() { // rellenar todos los espacios de nada, para evitar valores null, tambien sirve
											// para reiniciar las compras

		for (int i = 0; i < listaCompra.length; i++) { // rellenar todos los espacios de nada, para evitar valores null
			listaCompra[i] = "";
		}
	}

	public static void menuCliente() { // menu de espera del ala del cliente
		llenarCompras();
		System.out.println("Bienvenido, dale al enter para continuar");
		Controlador.pedirString();
		System.out.println("");
		realizarCompras();
	}

	public static int leerProductosTipo(int linea) { // sirve tanto para leer como para saber la cantidad de productos
														// de un tipo en especifico
		String[][] productos = Principal.conseguirProductos(); // debido a que productos esta en principal y es privado
																// es necesario este paso en caso de querer trabajar con
																// el en otra clase
		double[][] precios = Principal.conseguirPrecios();// debido a que precios esta en principal y es privado
															// es necesario este paso en caso de querer trabajar con
															// el en otra clase
		int contador = 0;
		System.out.println("");
		for (int i = 0; i < productos.length; i++) {
			if (!productos[linea][i].isEmpty()) { // solo imprime el nombre y precio en el caso de que el hueco del
													// producto no este vacio
				System.out.print("Producto numero " + (i + 1) + " " + productos[linea][i] + " ");
				System.out.printf("%.2f€%n", precios[linea][i]); // .2f Formato numérico decimal con 2 cifras
																	// después del punto. f significa floating
																	// point (número real). € Es simplemente el
																	// símbolo del euro que se mostrará justo
																	// después del número. %n Salto de línea

				contador++;
			}
		}
		return contador; // a contador devuelve dinero
	}

	public static void anadirCompra(int tipoDeseado, int cantidadProductos) { // anade la compra deseada a la lista de
																				// compras
		boolean anadido = false;
		String[][] productos = Principal.conseguirProductos();
		System.out.println("Que producto desea?");
		int numeroElegido = controlador.Controlador.pedirNumeroEnteroRango(1, cantidadProductos);
		numeroElegido--; // debido a que se pide como minimo un 1 pero la lista empieza por 0
		for (int i = 0; i < listaCompra.length; i++) {
			if (listaCompra[i].isEmpty() && anadido == false) {
				listaCompra[i] = productos[tipoDeseado][numeroElegido];
				anadido = true; // elige producto
			}
		}
		if (anadido == false) { // rechaza producto
			System.out.println("La lista de compras esta llena, termine su compra o cancelela");
		}
	}

	public static double leerListaCompra() { // leer los precios de los productos y su precio

		String[][] productos = Principal.conseguirProductos(); // debido a que productos esta en principal y es privado
																// es necesario este paso en caso de querer trabajar con
																// el en otra clase
		double[][] precios = Principal.conseguirPrecios();// debido a que precios esta en principal y es privado
															// es necesario este paso en caso de querer trabajar con
															// el en otra clase
		double precioTotal = 0;

		System.out.println("");
		for (int i = 0; i < listaCompra.length; i++) { // busca dentro de la lista
			if (!listaCompra[i].isBlank()) { // solo busca los huecos llenos
				for (int j = 0; j < productos.length; j++) { // buscar producto comprado
					for (int k = 0; k < productos[j].length; k++) { // en la j --> filas y k --> columnas
						if (productos[j][k] == listaCompra[i]) { // busca en ambas filas/columnas en la lista de la
																	// compra
							System.out.print("Producto numero " + (i + 1) + " " + listaCompra[i] + " "); // producto
																											// numero 1
																											// de la
																											// lista de
																											// la
																											// compra
							System.out.printf("%.2f€%n", precios[j][k]); // .2f Formato numérico decimal con 2 cifras
																			// después del punto. f significa floating
																			// point (número real). € Es simplemente el
																			// símbolo del euro que se mostrará justo
																			// después del número. %n Salto de línea

							precioTotal = precioTotal + precios[j][k]; // guardan precioTotal del producto elegido de
																		// antes + precios actuales

						}
					}
				}
			}
		}
		precioTotal = Controlador.redondear(precioTotal, 2); // obliga a precioTotal a tener dos decimales
		System.out.print("En total: ");
		System.out.printf("%.2f€%n", precioTotal);
		return precioTotal;
	}

	public static void menuTipos() {// menu de los tipos de productos de la maquina, hecho para aclarar el menu de
									// compras
		System.out.println("");
		System.out.println("Que tipo de producto desea?");
		System.out.println("1- Bebidas");
		System.out.println("2- Salados");
		System.out.println("3- Dulces");
		System.out.println("4- Otros");
	}

	public static void realizarCompras() { // menu principal del ala del cliente
		int tipoDeseado = -99;
		int cantidadProductos = -99;
		double precioIVA = -99.999;
		boolean salir = false; // declara cuando es falso o verdadero

		menuTipos();
		tipoDeseado = controlador.Controlador.pedirNumeroEnteroRango(1, 4) - 1;
		cantidadProductos = leerProductosTipo(tipoDeseado);
		anadirCompra(tipoDeseado, cantidadProductos);

		while (salir == false) { // declaramos que el valor empieza en falso hasta que no detecte la orden

			System.out.println("");
			System.out.println("Que desea hacer a continuacion?"); // menu de opciones de compra
			System.out.println("1- Ver el pedido");
			System.out.print("2- Comprar productos del tipo seleccionado:");
			if (tipoDeseado == 0) { // comprueba el tipo seleccionado actualmente para mostrarlo (ayuda al usuario)
				System.out.println(" Bebidas");
			} else if (tipoDeseado == 1) {
				System.out.println(" Salados");
			} else if (tipoDeseado == 2) {
				System.out.println(" Dulces");
			} else {
				System.out.println(" Otros");
			}
			System.out.println("3- Elegir otro tipo de producto");
			System.out.println("4- Terminar pedido");
			int opcion = controlador.Controlador.pedirNumeroEnteroRango(1, 4);
			if (opcion == 1) { // ejecucion de las opciones disponibles
				System.out.println("Mostrando pedido: ");
				leerListaCompra();
			} else if (opcion == 2) {
				System.out.println("Mostrando productos");
				cantidadProductos = leerProductosTipo(tipoDeseado);
				anadirCompra(tipoDeseado, cantidadProductos);
			} else if (opcion == 3) {
				System.out.println("Mostrando tipos");
				menuTipos();
				tipoDeseado = controlador.Controlador.pedirNumeroEnteroRango(1, 4) - 1;
			} else if (opcion == 4) {
				System.out.println("Terminando compra");
				salir = true;
				precioIVA = leerListaCompra();
				precioIVA = precioIVA + (precioIVA * 0.21);
				precioIVA = Controlador.redondear(precioIVA, 2);
				System.out.println("El precio con IVA es un total de: " + precioIVA + "€");
				IngresarDinero();
			}
		}

	}

	public static void IngresarDinero() { // metodo del ingresado de dinero, incluido el reiniciado en caso negativo
		double precioRestante = 0.0; // lo que falta
		double precioPagado = -99999; // no se anade cero porque sino se declara null, debido a que al bucle se inicia
										// con totalPagado < precioExacto precioPagado debe ser menor, al poner un
										// numero tan bajo se sabe si algo ha salido mal
		double precioExacto = 1000000000.0; // declaramos variable a devolver, debido a que al bucle se inicia con
											// totalPagado < precioExacto precioExacto debe ser mayor, al poner un
											// numero tan alto se sabe si algo ha salido mal
		double totalPagado = 0.0; // total a cero

		System.out.println("");
		System.out.println("Quieres confirmar la compra?");
		System.out.println("1- No (Salir)");
		System.out.println("2- Si (Continuar)");
		int opcion = controlador.Controlador.pedirNumeroEnteroRango(1, 2); // opciones minimas y maximas
		if (opcion == 1) { // Reinicio
			System.out.println("Reiniciando");
			llenarCompras();
			terminarCompra();
		} else if (opcion == 2) {
			while (totalPagado < precioExacto) { // mientras el totalPagado que sea menor precioExacto, se ejecutara el
													// siguiente codigo:
				System.out.println("Mostrando productos comprados: ");
				precioRestante = leerListaCompra();
				precioRestante = precioRestante + (precioRestante * 0.21);
				precioRestante = Controlador.redondear(precioRestante, 2); // dos decimales
				precioExacto = precioRestante;
				System.out.println("El precio con IVA es un total de: " + precioExacto + "€");
				System.out.println("Por favor, indique la cantidad de dinero que quieres ingresar");
				precioPagado = Controlador.pedirNumeroComa();
				boolean valido = false; // bucle de confirmacion de dinero ingresado positivo, al solo ser usado una //
										// vez, no se separa en metodo
				while (valido == false) { // confirmacion de numero valido, podria estar en el controlador pero se ha
											// dejado aqui para simplificar
					if (precioPagado <= 0 && precioPagado >= 125) {
						System.out.println("El numero debe ser mayor que 0 y menor que 125");
						System.out.println("Por favor, indique la cantidad de dinero que quieres ingresar");
						precioPagado = Controlador.pedirNumeroComa();
					} else
						valido = true;

				}
				System.out.println("Por favor, presione enter despues de depositar el dinero");
				Controlador.pedirString();
				precioRestante = precioRestante - precioPagado;
				totalPagado = totalPagado + precioPagado;
				System.out.println("Has pagado: " + totalPagado);

			}
			if (totalPagado == precioExacto) { // termina la compra
				terminarCompra();
			} else {
				totalPagado = totalPagado - precioExacto; // le resta
				devolverDinero(Controlador.redondear(totalPagado, 2));
				terminarCompra();
			}
		}
	}

	public static void terminarCompra() { // metodo que espera 10 segundos y envia al principio
		System.out.println("Gracias por su compra");
		System.out.println("");
		try {
			Thread.sleep(10000); // 10 segundos * 1000 milisegundos/segundo
		} catch (InterruptedException e) {
			// Manejar la excepción, por ejemplo, mostrando un mensaje
			System.err.println("La espera fue interrumpida.");
			e.printStackTrace();
		}
		Principal.menu();
	}

	public static void devolverDinero(double vueltas) { // metodo para devolver dinero
		boolean devuelto = false; // igual a falso
		System.out.println("Las vueltas son de " + vueltas);

		while (devuelto == false) {
			if (vueltas - 100 >= 0) { // no para hasta que sea igual a cero
				if (vueltas - 100 == 0) { // da las vueltas a cero
					devuelto = true; // le devuelve
					System.out.println("Se ha devuelto un billete de 100");
				} else {
					vueltas = vueltas - 100;
					vueltas = Controlador.redondear(vueltas, 2); // asegura los dos decimales, evita los problemas de double
					System.out.println("Se ha devuelto un billete de 100");
				}
			} else if (vueltas - 50 >= 0) {
				if (vueltas - 50 == 0) {
					devuelto = true;
					System.out.println("Se ha devuelto un billete de 50");
				} else {
					vueltas = vueltas - 50;
					vueltas = Controlador.redondear(vueltas, 2); // asegura los dos decimales, evita los problemas de double
					System.out.println("Se ha devuelto un billete de 50");
				}
			} else if (vueltas - 20 >= 0) {
				if (vueltas - 20 == 0) {
					devuelto = true;
					System.out.println("Se ha devuelto un billete de 20");
				} else {
					vueltas = vueltas - 20;
					vueltas = Controlador.redondear(vueltas, 2); // asegura los dos decimales, evita los problemas de double
					System.out.println("Se ha devuelto un billete de 20");
				}
			} else if (vueltas - 10 >= 0) {
				if (vueltas - 10 == 0) {
					devuelto = true;
					System.out.println("Se ha devuelto un billete de 10");
				} else {
					vueltas = vueltas - 10;
					vueltas = Controlador.redondear(vueltas, 2); // asegura los dos decimales, evita los problemas de double
					System.out.println("Se ha devuelto un billete de 10");
				}
			} else if (vueltas - 5 >= 0) {
				if (vueltas - 5 == 0) {
					devuelto = true;
					System.out.println("Se ha devuelto un billete de 5");
				} else {
					vueltas = vueltas - 5;
					vueltas = Controlador.redondear(vueltas, 2); // asegura los dos decimales, evita los problemas de double
					System.out.println("Se ha devuelto un billete de 5");
				}
			} else if (vueltas - 2 >= 0) {
				if (vueltas - 2 == 0) {
					devuelto = true;
					System.out.println("Se ha devuelto una moneda de 2 euros");
				} else {
					vueltas = vueltas - 2;
					vueltas = Controlador.redondear(vueltas, 2); // asegura los dos decimales, evita los problemas de double
					System.out.println("Se ha devuelto una moneda de 2 euros");
				}
			} else if (vueltas - 1 >= 0) {
				if (vueltas - 1 == 0) {
					devuelto = true;
					System.out.println("Se ha devuelto una moneda de 1 euro");
				} else {
					vueltas = vueltas - 1;
					vueltas = Controlador.redondear(vueltas, 2); // asegura los dos decimales, evita los problemas de double
					System.out.println("Se ha devuelto una moneda de 1 euro");
				}
			} else if (vueltas - 0.5 >= 0) {
				if (vueltas - 0.5 == 0) {
					devuelto = true;
					System.out.println("Se ha devuelto una moneda de 50 centimos");
				} else {
					vueltas = vueltas - 0.5;
					vueltas = Controlador.redondear(vueltas, 2); // asegura los dos decimales, evita los problemas de double
					System.out.println("Se ha devuelto una moneda de 50 centimos");
				}
			} else if (vueltas - 0.2 >= 0) {
				if (vueltas - 0.2 == 0) {
					devuelto = true;
					System.out.println("Se ha devuelto una moneda de 20 centimos");
				} else {
					vueltas = vueltas - 0.2;
					vueltas = Controlador.redondear(vueltas, 2); // asegura los dos decimales, evita los problemas de double
					System.out.println("Se ha devuelto una moneda de 20 centimos");
				}
			} else if (vueltas - 0.1 >= 0) {
				if (vueltas - 0.1 == 0) {
					devuelto = true;
					System.out.println("Se ha devuelto una moneda de 10 centimos");
				} else {
					vueltas = vueltas - 0.1;
					vueltas = Controlador.redondear(vueltas, 2); // asegura los dos decimales, evita los problemas de double
					System.out.println("Se ha devuelto una moneda de 10 centimos");
				}
			} else if (vueltas - 0.05 >= 0) {
				if (vueltas - 0.05 == 0) {
					devuelto = true;
					System.out.println("Se ha devuelto una moneda de 5 centimos");
				} else {
					vueltas = vueltas - 0.05;
					vueltas = Controlador.redondear(vueltas, 2); // asegura los dos decimales, evita los problemas de double
					System.out.println("Se ha devuelto una moneda de 5 centimos");
				}
			} else if (vueltas - 0.02 >= 0) {
				if (vueltas - 0.02 == 0) {
					devuelto = true;
					System.out.println("Se ha devuelto una moneda de 2 centimos");
				} else {
					vueltas = vueltas - 0.02;
					vueltas = Controlador.redondear(vueltas, 2); // asegura los dos decimales, evita los problemas de double
					System.out.println("Se ha devuelto una moneda de 2 centimos");
				}
			} else {
				devuelto = true;
				System.out.println("Se ha devuelto una moneda de 1 centimos");

			}
		}
	}
}