
import cuentapalabras.ContadorPalabras;

public class PruebaContadorPalabras {
	public static void main(String[] args) {
		String[] datos = {
			"Esta es la primera frase del ejemplo",
			"y esta es la segunda frase"
		};
		ContadorPalabras cp = new ContadorPalabras();
		cp.incluyeTodas(datos, "[ ]");
		System.out.println(cp);
	}
}
