package Negocio.Empleado;

public abstract class TEmpleado {
	
	private final String PATRON_DNI = "[0-9]{8}\\w";

	private int id;
	private String dni;
	private String nombre;
	private double nomina;
	private int idTurnoEmpleado;
	private boolean isParcial;
	private boolean activo;
	
	public TEmpleado(){
	}
	
	public TEmpleado(int id, int idTurnoEmpleado, String dni, String nombre, boolean isParcial, double nomina, boolean activo){
		if (id < 1) throw new IllegalArgumentException("ID de Producto incorrecto.");
		if (!dni.trim().matches(PATRON_DNI)) throw new IllegalArgumentException("DNI incorrecto. Formato: 99999999X");
		if (idTurnoEmpleado < 1) throw new IllegalArgumentException("ID de turno empleado incorrecto.");
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.idTurnoEmpleado = idTurnoEmpleado;
		this.isParcial = isParcial;
		this.nomina = nomina;
		this.activo = activo;
		
	}
	
	public TEmpleado(int idTurnoEmpleado, String dni, String nombre, boolean isParcial, double nomina, boolean activo){
		if (!dni.trim().matches(PATRON_DNI)) throw new IllegalArgumentException("DNI incorrecto. Formato: 99999999X");
		if (idTurnoEmpleado < 1) throw new IllegalArgumentException("ID de turno empleado incorrecto.");
		this.dni = dni;
		this.nombre = nombre;
		this.idTurnoEmpleado = idTurnoEmpleado;
		this.isParcial = isParcial;
		this.nomina = nomina;
		this.activo = activo;
	}
	
	public TEmpleado(int id, String dni, String nombre, int idTurnoEmpleado){
		if (!dni.trim().matches(PATRON_DNI)) throw new IllegalArgumentException("DNI incorrecto. Formato: 99999999X");
		
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.idTurnoEmpleado = idTurnoEmpleado;
	}
	
	public TEmpleado(String dni, String nombre, int idTurnoEmpleado){
		if (!dni.trim().matches(PATRON_DNI)) throw new IllegalArgumentException("DNI incorrecto. Formato: 99999999X");
		
		this.dni = dni;
		this.nombre = nombre;
		this.idTurnoEmpleado = idTurnoEmpleado;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getDni(){
		return dni;
	}
	
	public void setDni(String dni){
		this.dni = dni;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public double getNomina(){
		return nomina;
	}
	
	public void setNomina(double nomina){
		this.nomina = nomina;
	}
	
	public int getIdTurnoEmpleado() {
		return idTurnoEmpleado;
	}

	public void setIdTurnoEmpleado(int idTurnoEmpleado) {
		this.idTurnoEmpleado = idTurnoEmpleado;
	}
	
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public boolean isParcial() {
		return isParcial;
	}

	public void setParcial(boolean isParcial) {
		this.isParcial = isParcial;
	}
	
	public abstract double getSueldo();
	public abstract void setSueldo(double sueldo);
	
	public abstract String getComplementos();
	public abstract void setComplementos(String complementos);
	
	public abstract double getSueldoHoras();
	public abstract void setSueldoHoras(double sueldoHoras);
	
	public abstract int getHoras();
	public abstract void setHoras(int horas);
	
}
