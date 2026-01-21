package controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import modelo.Compra;
import modelo.Entrada;

public class ControladorFicheros {

	private String ruta;

	public ControladorFicheros(String ruta) {
		this.ruta = ruta;
	}

	/**
	 * Metodo encargado de la escritura de ficheros atendiendo al formato
	 * especificado por el fichero de ejemplo, donde, en este caso, se separa la
	 * información por 5 simbolos consecutivos de =
	 * 
	 * @param nombreFichero -> Nombre con la extensión del fichero que queremos
	 *                      generar
	 * @param personas      -> Lista con los datos que queremos guardar en el
	 *                      fichero
	 */
	public void escribirGrabarCompra(String nombreFichero, Compra compra, ArrayList<Entrada> entradasAGrabar) {

		try {
			BufferedWriter escribirFichero = new BufferedWriter(new FileWriter(ruta + nombreFichero));
			escribirFichero.write(compra.toString());
			escribirFichero.newLine();
			escribirFichero.newLine();
			escribirFichero.write("Que incluye: ");
			escribirFichero.newLine();
			escribirFichero.write("-------------------");
			escribirFichero.newLine();
			for (int i = 0; i < entradasAGrabar.size(); i++) {
				escribirFichero.write(entradasAGrabar.get(i).toString());
				escribirFichero.newLine();
				escribirFichero.write("-------------------");
				escribirFichero.newLine();
			}
			escribirFichero.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
