package vista;

import java.util.ArrayList;

import controlador.Controlador;
import controlador.ControladorBD;
import modelo.Cliente;

public class Pruebas {

	public static void main(String[] args) {

		conexionBD();
	}

	public static void conexionBD() {
		Controlador controladorES = new Controlador();
		ControladorBD controladorBD = new ControladorBD("cine_reto");
		boolean conexionConExito = controladorBD.iniciarConexion();
		if (conexionConExito) {
			System.out.println("Se realizó la conexion con exito");
		} else {
			System.out.println("No se realizó la conexion con exito");
		}
		menuEspera(controladorBD, controladorES);
		controladorBD.cerrarConexion();

	}

	public static void menuEspera(ControladorBD controladorBD, Controlador controladorES) {

		boolean usuarioEncontrado = false;

		while (usuarioEncontrado == false) {
			System.out.println("");
			System.out.println("Bienvenido, por favor pulse enter para continuar.");
			controladorES.pedirString();
			Cliente usuario = login(controladorBD, controladorES);
			if (usuario != null) {
				usuarioEncontrado = true;
				menuPeliculas(controladorBD, controladorES, usuario);
			}
		}
	}

	public static Cliente login(ControladorBD controladorBD, Controlador controladorES) {
		Cliente clienteCorrecto = null;
		ArrayList<Cliente> clientes = controladorBD.datosCliente();
		boolean reintentar = true;

		while (reintentar == true) {

			System.out.println("");
			System.out.println("Por favor, escriba su email.");
			String intentoEmail = controladorES.pedirString();
			System.out.println("Por favor, escriba su contraseña.");
			String intentoContraseña = controladorES.pedirString();
			boolean loginCorrecto = false;

			for (int i = 0; i < clientes.size(); i++) {
				if (clientes.get(i).getEmail().equals(intentoEmail)
						&& clientes.get(i).getContraseña().equals(intentoContraseña)) {
					loginCorrecto = true;
					reintentar = false;
					clienteCorrecto = clientes.get(i);
				}
			}

			if (loginCorrecto == false) {
				System.out.println("");
				System.out.println("Login incorrecto, intentarlo de nuevo?");
				System.out.println("1-Si");
				System.out.println("2-No");
				int opcion = controladorES.pedirNumeroEnteroRango(1, 2);
				if (opcion == 2) {
					reintentar = false;
				}
			}

		}
		return clienteCorrecto;
	}

	public static void menuPeliculas(ControladorBD controladorBD, Controlador controladorES, Cliente usuario) {

	}

}