package Negocio.Cliente;

public class TSocio extends TCliente {
	
	private int numero;
	private float cuota;
	
	public TSocio(String dni, String direccion, int numero, float cuota) {
		super(dni, direccion, false);
		this.numero = numero;
		this.cuota = cuota;
	}
	
	public TSocio(int id, String dni, String direccion, int numero, float cuota) {
		super(id, dni, direccion, false);
		this.numero= numero;
		this.cuota= cuota;
	}

	public int getNumero() { return numero; }

	public void setNumero(int numero) { this.numero = numero; }
	
	public float getCuota() { return cuota; }

	public void setCuota(float cuota) { this.cuota = cuota; }
	
}