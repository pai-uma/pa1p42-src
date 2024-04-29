
package cuentapalabras;

import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * La clase ContadorPalabrasSig representa objetos contadores de palabras que, 
 * en los procedimientos de inclusión, no incluyen las palabras consideradas 
 * “no significativas”. Para ello, la clase deberá contener una lista de String 
 * (noSignificativas) que almacene (en mayúsculas) estas palabras no significativas.
 */
public class ContadorPalabrasSig extends ContadorPalabras {
	// Lista de palabras consideradas no significativas
	private List<String> noSignificativas;
	
	/**
	 * Constructor que invoca al de la clase ascendente e inicializa la lista
	 * de palabras no significativas a un ArrayList vacío.
	 */
	public ContadorPalabrasSig() {
		super();
		noSignificativas = new ArrayList<>();
	}
	
	/** 
	 * Las palabras que hubiese en la lista noSignificativas serán eliminadas. 
	 * A continuación, las palabras no significativas recibidas en el parámetro (palsNS) 
	 * deberán almacenarse en mayúsculas en la lista noSignificativas. Si alguna palabra 
	 * está vacía, entonces será ignorada.
	 * 
	 * @param palsNS	Array con palabras no significativas
	 */
	public void leeArrayNoSig(String[] palsNS) {
		noSignificativas.clear();
		for (String p : palsNS) {
			if (p.length() > 0) { // ignora si vacio
				noSignificativas.add(p.toUpperCase());
			}
		}
	}
	
	/**
	 * Método que recibe un parámetro de tipo String con el nombre del fichero de entrada 
	 * que contendrá la relación de palabras no significativas, y otro parámetro también 
	 * de tipo String que contendrá la cadena con los caracteres delimitadores de dichas 
	 * palabras en el fichero.
	 * 
	 * @param filNoSig	Nombre del fichero con la información sobre palabras no significativas
	 * @param sep		Delimitadores de las palabras en el fichero
	 * @throws IOException	En caso de que se produzca un error de I/O
	 */
	public void leeFicheroNoSig(String filNoSig, String sep) throws IOException {
		noSignificativas.clear();
		try (BufferedReader buffReader = Files.newBufferedReader(Path.of(filNoSig))) {
			String linea = buffReader.readLine();
			while (linea != null) {
				this.anyadePalabrasNoSignificativas(linea, sep);
				linea = buffReader.readLine();
			}
		}
	}
	
	/**
	 * Lectura de fichero de las palabras no significativas alternativa. 
	 * Implementado con Scanner.
	 * 
	 * @param filNoSig	Nombre del fichero con la información sobre palabras no significativas
	 * @param sep		Delimitadores de las palabras en el fichero
	 * @throws IOException	En caso de que se produzca un error de I/O
	 */
	public void leeFicheroNoSig_AlternativoConScanner(String filNoSig, String sep)
		throws IOException {
		noSignificativas.clear();
		try (Scanner sc = new Scanner(Path.of(filNoSig))) {
			while (sc.hasNextLine()) {
				this.anyadePalabrasNoSignificativas(sc.nextLine(), sep);
			}
		}
	}
	
	/**
	 * Método privado que extrae de linea (primer argumento) las palabras usando los 
	 * delimitadores incluidos en del (segundo argumento). Cada una de las palabras obtenidas 
	 * se añade en mayúsculas a la lista de palabras no significativas. 
	 * Si alguna palabra está vacía, entonces será ignorada.
	 * 
	 * @param linea	Cadena de caracteres con palabras
	 * @param sep	Delimitadores de las palabras
	 */
	private void anyadePalabrasNoSignificativas(String linea, String sep) {
		String[] pal = linea.split(sep);
		for (String p : pal) {
			if (p.length() > 0) { // ignora si vacio
				noSignificativas.add(p.toUpperCase());
			}
		}
	}
	
	/**
	 * Implementación alternativa (utilizando Scanner) para añadir palabras no significativas
	 * a partir de una cadena de caracteres que incluye las palabras.
	 * 
	 * @param linea	Cadena de caracteres con palabras
	 * @param sep	Delimitadores de las palabras
	 */
	private void anyadePalabrasNoSignificativas_AlternativoConScanner(String linea, String sep) {
		try (Scanner sc = new Scanner(linea)) {
			sc.useDelimiter(sep);
			while (sc.hasNext()) {
				noSignificativas.add(sc.next().toUpperCase());
			}
		}
	}
	
	/**
	 * Redefinición del método protegido incluye, que solo incluirá la palabra cuando sea significativa.
	 */
	@Override
	protected void incluye(String pal) {
		// int p = this.estaNoSig(pal);
		// if (p < 0) {
		if(!noSignificativas.contains(pal.toUpperCase()))
			super.incluye(pal);
	}
	
	
}
