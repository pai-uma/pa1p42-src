
package cuentapalabras;

import java.util.Objects;

/**
 * La clase PalabraEnTexto mantiene información de una palabra (String), así como del 
 * número de veces que aparece esa palabra en un determinado texto (int).
 */
public class PalabraEnTexto {
	// Variable privada para almacenar la palabra.
	private String palabra;
	
	// Varriable privada que contabiliza el número de veces que aparece.
	private int veces;
	
	/**
	 * Constructor en el que se proporciona la palabra. 
	 * Al construir el objeto, el número de veces que aparece la 
	 * palabra se considera 1. 
	 * Además, la palabra se almacenará en mayúsculas.
	 * 
	 * @param p	String con la palabra
	 */
	public PalabraEnTexto(String p) {
		palabra = p.toUpperCase();
		veces = 1;
	}
	
	/**
	 * Método para incrementar en uno el número de veces que aparece la palabra.
	 */
	public void incrementa() {
		++veces;
	}
	
	/**
	 * Dos palabras en texto son iguales si coinciden las palabras que contienen.
	 */
	@Override
	public boolean equals(Object o) {
		boolean ok = false;
		if (o instanceof PalabraEnTexto) {
			PalabraEnTexto x = (PalabraEnTexto)o;
			// Se utiliza equals porque las palabras están almacenadas en mayúsculas.
			ok = this.palabra.equals(x.palabra);
		}
		return ok;
	}
	
	/**
	 * Redefinición de hashCode acorde a la de equals.
	 */
	@Override
	public int hashCode() {
		//return palabra.hashCode();
		return Objects.hash(palabra);
	}
	
	/**
	 * Representación textual de una palabra en texto, dada por la palabra
	 * (que estará en mayúsculas) seguido de dos puntos, un espacio y el 
	 * número de veces que aparece.
	 */
	@Override
	public String toString() {
		return palabra + ": " + veces;
	}
}
