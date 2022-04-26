package Negocio.Empleado;

import jakarta.persistence.*;
import negocio.turnoEmpleado.TurnoEmpleado;

@Entity
public class JornadaParcial extends Empleado {
	
	@Column(name = "sueldoHoras")
	private double sueldoHoras;
	
	@Column(name ="horas")
	private int horas;
	
	public JornadaParcial(int id, String dni, String nombre, TurnoEmpleado turnoEmpleado, boolean activo, double nomina,boolean isParcial, double sueldoHoras, int horas){
		super(id, dni, nombre, turnoEmpleado, activo, nomina, isParcial);
		this.horas = horas;
		this.sueldoHoras = sueldoHoras;
	}
	
	public JornadaParcial(){
	}
	
	public double getSueldoHoras(){
		return this.sueldoHoras;
	}
	
	public void setSueldoHoras(double sueldoHoras) {
		this.sueldoHoras = sueldoHoras;
	}
	
	public int getHoras(){
		return this.horas;
	}
	
	public void setHoras(int horas){
		this.horas = horas;
	}

	@Override
	public double calcularNomina() {
		return sueldoHoras * horas * 30;
	}
}