
package cuentapalabras;

import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * La clase ContadorPalabras  almacena en una lista de PalabraEnTexto 
 * las palabras que aparecen en un texto.
 */
public class ContadorPalabras {
	// Variable privada para almacenar las palabras de un texto.
	private List<PalabraEnTexto> palabras;
	
	/**
	 * Constructor que inicializa la lista de palabras a un ArrayList vacío.
	 */
	public ContadorPalabras() {
		palabras = new ArrayList<>();
	}
	
	/**
	 * Método que devuelve la posición que ocupa una palabra determinada
	 * en la lista de palabras en texto. Si no se localiza devuelve -1.
	 * 
	 * @param pal	String con la palabra
	 * @return	Posición de la palabra en la lista o -1 si no está
	 */
	private int esta(String pal) {
		// El parámetro es de tipo String, pero los elementos almacenados en la lista son de
		// tipo PalabraEnTexto. Por ello, se crea un objeto PalabraEnTexto con ese parámetro.
		// Esto nos permtirá compararlo con equals con cada elemento de la lista.
		PalabraEnTexto p = new PalabraEnTexto(pal);
		int i = 0;
		while ((i < palabras.size()) && ! p.equals(palabras.get(i))) {
			++i;
		}
		return (i < palabras.size()) ? i : -1;
	}
	
	/**
	 * Método que devuelve la instancia de PalabraEnTexto en la lista 
	 * que coincide con ella y la devuelve. Si la palabra no se encuentra 
	 * en el texto deberá lanzar la excepción NoSuchElementException.
	 * 
	 * @param pal	String con la palabra a encontrar
	 * @return	Objeto PalabraEnTexto que contiene la palabra
	 */
	public PalabraEnTexto encuentra(String pal) {
		int p = esta(pal);
		if (p < 0) {
			throw new NoSuchElementException("No existe la palabra " + pal);
		}
		return palabras.get(p);
	}
	
	/**
	 * Método protegido que incrementa el número de apariciones 
	 * de la palabra (en la lista) que corresponda a la cadena pal 
	 * en el contador de palabras si es que ya existía, o añade 
	 * una palabra nueva en caso contrario. Nótese que si la palabra 
	 * recibida como parámetro está vacía, entonces no se hará nada.
	 * 
	 * @param pal String con la palabra a incluir o incrementar
	 */
	protected void incluye(String pal) {
		if (pal.length() > 0) { // ignora si palabra vacia
			int p = esta(pal);
			if (p < 0) {
				palabras.add(new PalabraEnTexto(pal));
			} else {
				palabras.get(p).incrementa();
			}
		}
	}
	
	/**
	 * Método privado que permite extraer del primer argumento (linea) las palabras 
	 * usando los delimitadores incluidos en del. Cada una de las palabras obtenidas 
	 * se irán acumulando en el contador, invocando al método incluye.
	 * Versión con split
	 * 
	 * @param linea Cadena de caracteres con palabras 
	 * @param del	Delimitador que separa las palabras
	 */
	private void incluyeTodas(String linea, String del) {
		String[] pal = linea.split(del);
		for (String p : pal) {
			this.incluye(p);
		}
	}
	
	/**
	 * Método con implementación alternativa (con Scanner) para incluir todas las 
	 * palabras dadas en una cadena de caracteres.
	 * 
	 * @param linea	Strng con palabras separadas por delimitadores
	 * @param del	Delimitadores que separan las palabras
	 */
	private void incluyeTodas_AlternativoConScanner(String linea, String del) {
		try (Scanner sc = new Scanner(linea)) {
			sc.useDelimiter(del);
			while (sc.hasNext()) {
				this.incluye(sc.next());
			}
		}
	}
	
	/**
	 * Método que incluye todas las palabras que se encuentran en el array texto, proporcionado
	 * como primer argumento. Cada elemento del array será una línea de texto y, en cada línea, 
	 * las palabras se deben separar usando los delimitadores incluidos en del (segundo argumento).
	 *
	 * @param texto	Array con cadenas que incluyen varias palabras
	 * @param del	Delimitadores usados para separar las palabras
	 */
	public void incluyeTodas(String[] texto, String del) {
		for (String linea : texto) {
			this.incluyeTodas(linea, del);
		}
	}
	
	/**
	 * incluye todas las palabras que se encuentran en el fichero (primer argumento). 
	 * Cada elemento del fichero será una línea de texto y en cada línea, las palabras 
	 * se deben separar usando los delimitadores incluidos en del (segundo argumento).
	 * 
	 * @param nomFich	Nombre del fichero con la información
	 * @param del		Delimitadores que separan las palabras
	 * @throws IOException	En caso de que se produzca un error de I/O
	 */
	public void incluyeTodasFichero(String nomFich, String del) throws IOException {
		try (BufferedReader buffReader = Files.newBufferedReader(Path.of(nomFich))) {
			String linea = buffReader.readLine();
			while (linea != null) {
				this.incluyeTodas(linea, del);
				linea = buffReader.readLine();
			}
		}
	}
	
	/**
	 * Versión alternativa de lectura de fichero con Scanner.
	 * 
	 * @param nomFich	Nombre del fichero con la información
	 * @param del		Delimitadores que separan las palabras
	 * @throws IOException	En caso de que se produzca un error de I/O
	 */
	public void incluyeTodasFichero_AlternativoConScanner(String nomFich, String del) throws IOException {
		try (Scanner sc = new Scanner(Path.of(nomFich))) {
			while (sc.hasNextLine()) {
				this.incluyeTodas(sc.nextLine(), del);
			}
		}
	}
	
	/**
	 * Presenta la información registrada en la lista de palabras en texto sobre el fichero 
	 * indicado en el argumento. La información se almacenará por líneas, donde cada línea
	 * incliurá la palabra en texto. 
	 * 
	 * @param nomFich	Nombre del fichero
	 * @throws FileNotFoundException	En caso de que no se encuentre el fichero
	 */
	public void presentaPalabras(String nomFich) throws FileNotFoundException {
		try (PrintWriter pw = new PrintWriter(nomFich)) {
			presentaPalabras(pw);
		}
	}
	
	/**
	 * Presenta la información registrada en la lista de palabras en texto sobre el PrintWriter 
	 * indicado en el argumento. La información se almacenará por líneas, donde cada línea
	 * incliurá la palabra en texto. 
	 * 
	 * @param pw	PrintWriter sobre el que se pasa la información
	 */
	public void presentaPalabras(PrintWriter pw) {
		for (PalabraEnTexto pet : palabras) {
			pw.println(pet);
		}
	}
	
	/**
	 * Representación textual de los objetos ContadorPalabras.
	 */
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(" - ", "[", "]");
		for (PalabraEnTexto pal : palabras) {
			sj.add(pal.toString());
		}
		return sj.toString();
	}
}
