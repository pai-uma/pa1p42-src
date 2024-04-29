
import cuentapalabras.PalabraEnTexto;

public class PruebaPalabraEnTexto {
	public static void main(String[] args) {
		PalabraEnTexto p1 = new PalabraEnTexto("gorra");
		PalabraEnTexto p2 = new PalabraEnTexto("GORRA");
		p1.incrementa();
		System.out.println("Palabra 1 = "+p1);
		System.out.println("Palabra 2 = "+p2);
		if (p1.equals(p2)) {
			System.out.println("Las palabras son iguales");
		} else {
			System.out.println("Las palabras son distintas");
		}
	}
}
