package Negocio.Cliente;

public class TCorriente extends TCliente {
	
	private double gastosEnvio;
	
	public TCorriente(String dni, String direccion, double gastosEnvio) {
		super(dni, direccion);
		this.gastosEnvio = gastosEnvio;
	}
	
	public TCorriente(int id, String dni, String direccion, double gastosEnvio) {
		super(id, dni, direccion);
		this.gastosEnvio = gastosEnvio;
	}
	
	public TCorriente(int id, String direccion, double gastosEnvio) {
		super(id, direccion);
		this.gastosEnvio = gastosEnvio;
	}

	public double getGastosEnvio() { return gastosEnvio; }

	public void setGastosEnvio(double gastosEnvio) { this.gastosEnvio = gastosEnvio; }

	@Override
	public int getNumero() { return 0; }

	@Override
	public void setNumero(int numero) { }

	@Override
	public double getCuota() { return 0; }

	@Override
	public void setCuota(double cuota) {	}
	
}