package codice;

public class Colore {
	public static String hexToRGB(String hex) {
	    // Rimuovi il carattere "#" se presente
	    if (hex.startsWith("#")) {
	        hex = hex.substring(1);
	    }

	    // Estrai i componenti R, G e B dalla stringa esadecimale
	    int r = Integer.parseInt(hex.substring(0, 2), 16);
	    int g = Integer.parseInt(hex.substring(2, 4), 16);
	    int b = Integer.parseInt(hex.substring(4, 6), 16);

	    // Costruisci la stringa RGB
	    return "rgb(" + r + ", " + g + ", " + b + ")";
	}
}