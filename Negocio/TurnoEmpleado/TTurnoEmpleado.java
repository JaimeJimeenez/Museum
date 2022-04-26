package Negocio.TurnoEmpleado;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TTurnoEmpleado {
	
	private int id;
	private String horaEntrada;
	private String horaSalida;
	private boolean activo;

	
	public TTurnoEmpleado(){}
	
	
	public TTurnoEmpleado(String horaEntrada, String horaSalida) {
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
	}
	
	public TTurnoEmpleado(int id, String horaEntrada, String horaSalida) {
		if(comprarHoras(horaEntrada)) throw new IllegalArgumentException("Hora entrada en formato incorrecto");
		if(comprarHoras(horaSalida)) throw new IllegalArgumentException("Hora salida en formato incorrecto");
		
		this.id = id;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
	}
	
	public int getId() { return id; }
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getHoraEntrada() { return horaEntrada; }
	
	public void setHoraEntrada(String horaEntrada) {
		if(comprarHoras(horaEntrada)) throw new IllegalArgumentException("Hora entrada en formato incorrecto");
		
		this.horaEntrada = horaEntrada; 
	}
	
	public String getHoraSalida() { return horaSalida; }
	
	public void setHoraSalida(String horaSalida) {
		if(comprarHoras(horaSalida)) throw new IllegalArgumentException("Hora salida en formato incorrecto");
		
		this.horaSalida = horaSalida; 
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public boolean isActivo() {
		return activo;
	}
	
	private static boolean comprarHoras(String hora){
		DateFormat dateF = new SimpleDateFormat("HH:mm");
		try{
			dateF.parse(hora);
			return false; //false para que no lance excepcion al ser llamado
		}catch(ParseException e){
			return true;
		}
		
	}
}
