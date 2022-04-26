package Negocio.FacturaMuseo;


public class TLineaFacturaMuseo {
	
	private int idFacturaMuseo;
	private int idEntrada;
	private int cantidad;
	private double precio;

	public TLineaFacturaMuseo(int idFactura, int idEntrada, int cantidad) {
		this.idFacturaMuseo = idFactura;
		this.idEntrada = idEntrada;
		this.cantidad = cantidad;
	}
	public TLineaFacturaMuseo(int idEntrada, int cantidad){
		this.idEntrada = idEntrada;
		this.cantidad = cantidad;
	}

	public TLineaFacturaMuseo(int idFactura, int idEntrada, int cantidad, int precio) {
		this.idFacturaMuseo = idFactura;
		this.idEntrada = idEntrada;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	
	public TLineaFacturaMuseo() {
	}
	public int getIdFacturaMuseo() {
		return idFacturaMuseo;
	}

	public void setIdFacturaMuseo(int idFacturaMuseo) {
		this.idFacturaMuseo = idFacturaMuseo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Integer getIdEntrada() {
		return idEntrada;
	}

	public void setIdEntrada(Integer idEntrada) {
		this.idEntrada = idEntrada;
	}

}