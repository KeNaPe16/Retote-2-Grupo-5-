package vista;

import java.util.ArrayList;

import controlador.Controlador;
import controlador.ControladorBD;
import modelo.Cliente;

public class Login {
	/**
	 * Metodo que da inicio al login, lo llama y en caso de que encuentre un usuario
	 * lo guarda y continua. Si no encuentra un usuario tambien lo guarda pero no
	 * continua
	 * 
	 * @param controladorBD
	 * @param controladorES
	 */
	public Cliente inicioLogin(ControladorBD controladorBD, Controlador controladorES) {
		Cliente usuario = login(controladorBD, controladorES);
		return usuario;
	}

	/**
	 * Metodo de login, pide el email y la contraseña al usuario y las busca en la
	 * BD. En caso de encontrarlos devuelve el cliente al que pertenece. En caso de
	 * no encontrarlo pregunta a el usuario si quiere volver a introducir el email y
	 * la contraseña. En caso de que no coincida devuelve el cliente en forma de
	 * null
	 * 
	 * @param controladorBD
	 * @param controladorES
	 * @return El cliente encontrado o null en caso de no encontrarlo
	 */
	public Cliente login(ControladorBD controladorBD, Controlador controladorES) {
		Cliente clienteCorrecto = null;
		ArrayList<Cliente> clientes = controladorBD.datosCliente();
		boolean reintentar = true;

		while (reintentar == true) {

			System.out.println("");
			System.out.println("Por favor, escriba su email.");
			String intentoEmail = controladorES.pedirString();
			System.out.println("Por favor, escriba su contraseña.");
			String intentoContraseña = controladorES.pedirString();
			clienteCorrecto = comprobarCliente(controladorBD, clientes, intentoEmail, intentoContraseña,
					clienteCorrecto);

			if (clienteCorrecto == null) {
				System.out.println("");
				System.out.println("Login incorrecto, intentarlo de nuevo?");
				System.out.println("1-Si");
				System.out.println("0-No");
				int opcion = controladorES.pedirNumeroEnteroRango(0, 1);
				if (opcion == 0) {
					reintentar = false;
				}

			} else {
				reintentar = false;
			}
		}
		return clienteCorrecto;
	}

	/**
	 * Es el metodo que busca el email y contraseña en la BD. Lo unico que hace es
	 * guardar el cliente en caso de encontralo
	 * 
	 * @param controladorBD
	 * @param clientes
	 * @param intentoEmail
	 * @param intentoContraseña
	 * @param clienteCorrecto
	 * @return El cliente en caso de encontrarlo, null en caso contrario (porque la
	 *         variable donde se guarda la variable es null por defecto)
	 */
	public Cliente comprobarCliente(ControladorBD controladorBD, ArrayList<Cliente> clientes, String intentoEmail,
			String intentoContraseña, Cliente clienteCorrecto) {

		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getEmail().equals(intentoEmail)
					&& clientes.get(i).getContraseña().equals(intentoContraseña)) {
				clienteCorrecto = clientes.get(i);
			}
		}
		return clienteCorrecto;
	}

}
