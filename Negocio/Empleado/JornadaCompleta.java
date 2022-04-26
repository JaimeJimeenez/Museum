package Negocio.Empleado;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jakarta.persistence.*;
import negocio.turnoEmpleado.TurnoEmpleado;

@Entity
public class JornadaCompleta extends Empleado {
	
	@Column(name = "sueldo")
	private double sueldo;
	
	@Column(name ="complementos")
	private String complementos;
	
	public JornadaCompleta(int id, String dni, TurnoEmpleado turnoEmpleado, String nombre, boolean activo, double nomina, boolean isParcial, double sueldo, String complementos){
		super(id, dni, nombre, turnoEmpleado, activo, nomina, isParcial);
		if(comprobarHoras(complementos)) throw new IllegalArgumentException("Complementos tiene que ser en formato HH:MM");
		this.complementos = complementos;
		this.sueldo = sueldo;
	}
	
	public JornadaCompleta(){
	}
	
	public double getSueldo(){
		return this.sueldo;
	}
	
	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}
	
	public String getComplementos(){
		return this.complementos;
	}
	
	public void setComplementos(String complementos){
		if(comprobarHoras(complementos)) throw new IllegalArgumentException("Complementos tiene que ser en formato HH:MM");
		this.complementos = complementos;
	}

	@Override
	public double calcularNomina() {		
		String[] complementosAux = complementos.split(":");
		double hours = Integer.parseInt(complementosAux[0]);
		double minutes = Integer.parseInt(complementosAux[1]);
		double duration = hours + minutes/60;

		return sueldo + ((sueldo/30)/8)*duration;
	}
	
	private static boolean comprobarHoras(String hora){
		DateFormat dateF = new SimpleDateFormat("HH:mm");
		try{
			dateF.parse(hora);
			return false; //false para que no lance excepcion al ser llamado
		}catch(ParseException e){
			return true;
		}
		
	}
}