package Negocio.Cliente;

public abstract class TCliente {
	
	private final String PATRON_DNI = "[0-9]{8}\\w";
	
	private int id;
	private String direccion;
	private String dni;
	private boolean isCorriente;
	private boolean activo;
	
	public TCliente(int id, String dni, String direccion, boolean isCorriente) {
		if (!dni.trim().matches(PATRON_DNI)) throw new IllegalArgumentException("DNI incorrecto. Formato: 99999999X");
		
		this.id = id;
		this.dni = dni;
		this.direccion = direccion;
		this.isCorriente = isCorriente;
	}

	public int getId() { return id; }
	
	public void setId(int id) { this.id = id; }
	
	public String getDireccion() { return direccion; }

	public void setDireccion(String direccion) { this.direccion = direccion; }

	public String getDni() { return dni; }

	public void setDni(String dni) { this.dni = dni; }

	public boolean isActivo() { return activo; }
	
	public void setActivo(boolean activo) { this.activo = activo; }
	
	public boolean isCorriente() { return isCorriente; }
	
}