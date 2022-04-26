package Presentacion.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	
	private Utils() {}
	private static final SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
	public static Date parseFecha(String fecha) {
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }
        return fechaDate;
    }
	
	public static String formatFecha(Date fecha) {
		return formato.format(fecha);
	}
}
