package Negocio.Cliente;

public class TCorriente extends TCliente {
	
	private float gastosEnvio;
	
	public TCorriente(String dni, String direccion, float gastosEnvio) {
		super(dni, direccion, true);
		this.gastosEnvio = gastosEnvio;
	}
	public TCorriente(int id, String dni, String direccion, float gastosEnvio) {
		super(id, dni, direccion, true);
		this.gastosEnvio = gastosEnvio;
	}
	
	public float getGastosEnvio() { return gastosEnvio; }

	public void setGastosEnvio(float gastosEnvio) { this.gastosEnvio = gastosEnvio; }
	
}