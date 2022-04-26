package Negocio.Empleado;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import negocio.turnoEmpleado.TurnoEmpleado;

public class TJornadaCompleta extends TEmpleado{
	
	private double sueldo;
	private String complementos;
	
	public TJornadaCompleta(){
		
	}
	
	public TJornadaCompleta(int id, int idTurnoEmpleado,String dni, String nombre, boolean isParcial,double nomina,boolean activo, String complementos, double sueldo){
		super(id,idTurnoEmpleado, dni, nombre, activo, nomina, isParcial);
		if(comprobarHora(complementos)) throw new IllegalArgumentException("Complementos tiene que ser en formato HH:MM");
		
		this.sueldo = sueldo;
		this.complementos = complementos;
	}
	
	public TJornadaCompleta(String dni, String nombre, int idTurnoEmpleado, double sueldo, String complementos){
		super(dni, nombre, idTurnoEmpleado);
		if(comprobarHora(complementos)) throw new IllegalArgumentException("Complementos tiene que ser en formato HH:MM");
		
		this.sueldo = sueldo;
		this.complementos = complementos;
	}

	public TJornadaCompleta(int id, String dni, String nombre, int turnoEmpleado, double sueldo, String complementos){
		super(id, dni, nombre, turnoEmpleado);
		if(comprobarHora(complementos)) throw new IllegalArgumentException("Complementos tiene que ser en formato HH:MM");
		
		this.sueldo = sueldo;
		this.complementos = complementos;
	}

	
	@Override
	public double getSueldo() {
		return sueldo;
	}
	
	@Override
	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	@Override
	public String getComplementos() {
		return complementos;
	}
	
	@Override
	public void setComplementos(String complementos) {
		if(comprobarHora(complementos)) throw new IllegalArgumentException("Complementos tiene que ser en formato HH:MM");
		
		this.complementos = complementos;
	}

	@Override
	public double getSueldoHoras() {
		return 0;
	}
	
	@Override
	public void setSueldoHoras(double sueldoHoras) {
		
	}

	@Override
	public int getHoras() {
		return 0;
	}

	@Override
	public void setHoras(int horas) {
		
	}
	
	private static boolean comprobarHora(String hora){
		DateFormat dateF = new SimpleDateFormat("HH:mm");
		try{
			dateF.parse(hora);
			return false; //false para que no lance excepcion al ser llamado
		}catch(ParseException e){
			return true;
		}
		
	}
	
	

}
