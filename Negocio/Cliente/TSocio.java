package Negocio.Cliente;

public class TSocio extends TCliente {
	
	private int numero;
	private double cuota;
	
	public TSocio(String dni, String direccion, int numero, double cuota) {
		super(dni, direccion);
		this.numero = numero;
		this.cuota = cuota;
	}
	
	public TSocio(int id, String dni, String direccion, int numero, double cuota) {
		super(id, dni, direccion);
		this.numero= numero;
		this.cuota= cuota;
	}

	public TSocio(int id, String direccion, int numero, double cuota) {
		super(id, direccion);
		this.numero = numero;
		this.cuota = cuota;
	}

	public int getNumero() { return numero; }

	public void setNumero(int numero) { this.numero = numero; }
	
	public double getCuota() { return cuota; }

	public void setCuota(double cuota) { this.cuota = cuota; }

	@Override
	public double getGastosEnvio() { return 0; }

	@Override
	public void setGastosEnvio(double gastosEnvio) {	}
	
}