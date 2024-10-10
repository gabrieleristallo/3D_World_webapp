package codice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatoData {
	
	public static String getDataAttuale() {
		LocalDate currentDate = LocalDate.now();

        String pattern = "yyyy-MM-dd";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        String formattedDate = currentDate.format(formatter);

        return formattedDate;
	}
	
	public static String formattaData(String data) {
		String[] datas = data.split("-");
		String anno = datas[0];
		String mese = datas[1];
		String giorno = datas[2];
		
		return giorno + "/" + mese + "/" + anno;
	}
	
	public static String formattaDataString(String data) {
		String[] datas = data.split("/");
		String giorno = datas[0];
		String mese = datas[1];
		String anno = datas[2];
		
		return giorno + " " + getMese(mese) + " " + anno;
	}
	
	public static boolean is1_8_11(String data) {
		String[] datas;
		String giorno;
		if(data.contains("/")) {
		    datas = data.split("/");
		    giorno = datas[0];
		} else if(data.contains("-")) {
		    datas = data.split("-");
		    giorno = datas[2];
		} else {
			giorno = "";
		}
		if(giorno.equals("1") || giorno.equals("8") || giorno.equals("11")) {
			return true;
		}
		return false;
	}
	
	private static String getMese(String numero) {
	    switch(numero) {
	        case "01":
	            return "Gennaio";
	        case "02":
	            return "Febbraio";
	        case "03":
	            return "Marzo";
	        case "04":
	            return "Aprile";
	        case "05":
	            return "Maggio";
	        case "06":
	            return "Giugno";
	        case "07":
	            return "Luglio";
	        case "08":
	            return "Agosto";
	        case "09":
	            return "Settembre";
	        case "10":
	            return "Ottobre";
	        case "11":
	            return "Novembre";
	        case "12":
	            return "Dicembre";
	        default:
	            return "Mese non valido";
	    }
	}

}