package Negocio.Cliente;

public abstract class TCliente {
	
	private final String PATRON_DNI = "[0-9]{8}\\w";
	
	private int id;
	private String direccion;
	private String dni;
	private boolean activo;
	
	public TCliente(String dni, String direccion) {
		if (!dni.trim().matches(PATRON_DNI)) throw new IllegalArgumentException("DNI incorrecto. Formato: 99999999X");
		
		this.dni = dni;
		this.direccion = direccion;
		this.activo = true;
	}
	
	public TCliente(int id, String dni, String direccion) {
		if (!dni.trim().matches(PATRON_DNI)) throw new IllegalArgumentException("DNI incorrecto. Formato: 99999999X");
		
		this.id = id;
		this.dni = dni;
		this.direccion = direccion;
		this.activo = true;
	}

	public TCliente(int id, String direccion) {
		this.id = id;
		this.direccion = direccion;
		this.activo = true;
	}

	public int getId() { return id; }
	
	public void setId(int id) { this.id = id; }
	
	public String getDni() { return dni; }

	public void setDni(String dni) { this.dni = dni; }
	
	public String getDireccion() { return direccion; }

	public void setDireccion(String direccion) { this.direccion = direccion; }

	public abstract double getGastosEnvio();
	
	public abstract void setGastosEnvio(double gastosEnvio);
	
	public abstract int getNumero();
	
	public abstract void setNumero(int numero);
	
	public abstract double getCuota();
	
	public abstract void setCuota(double cuota);

	public boolean isActivo() { return activo; }
	
	public void setActivo(boolean activo) { this.activo = activo; }
	
}