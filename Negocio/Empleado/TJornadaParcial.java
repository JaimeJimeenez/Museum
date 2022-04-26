package Negocio.Empleado;

public class TJornadaParcial extends TEmpleado{
	
	private double sueldoHoras;
	private int horas;
	
	public TJornadaParcial(int id, int idTurnoEmpleado, String dni, String nombre,  boolean isParcial, double nomina, boolean activo,int sueldoHoras, int horas ){
		super(id,idTurnoEmpleado, dni, nombre,  isParcial, nomina, activo);
		this.sueldoHoras = sueldoHoras;
		this.horas = horas;
	}
	
	public TJornadaParcial(String dni, String nombre, int idTurnoEmpleado, double sueldoHoras, int horas){
		super(dni, nombre, idTurnoEmpleado);
		this.sueldoHoras = sueldoHoras;
		this.horas = horas;
	}
	
	public TJornadaParcial(int id, String dni, String nombre, int idTurnoEmpleado, double sueldoHoras, int horas){
		super(id, dni, nombre, idTurnoEmpleado);
		this.sueldoHoras = sueldoHoras;
		this.horas = horas;
	}
	
	public TJornadaParcial() {
	}

	@Override
	public double getSueldo() {
		return 0;
	}
	
	@Override
	public void setSueldo(double sueldo) {
		
	}

	@Override
	public String getComplementos() {
		return null;
	}
	
	@Override
	public void setComplementos(String complementos) {
		
	}

	@Override
	public double getSueldoHoras() {
		return sueldoHoras;
	}
	
	@Override
	public void setSueldoHoras (double sueldoHoras) {
		this.sueldoHoras = sueldoHoras;
	}

	@Override
	public int getHoras() {
		return horas;
	}

	@Override
	public void setHoras(int horas) {
		this.horas = horas;
	}

}
