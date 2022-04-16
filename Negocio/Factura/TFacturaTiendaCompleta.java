package Negocio.Factura;

public class TFacturaTiendaCompleta {

	private Integer idFactura;
	private Integer idProducto;
	private Integer idCliente;

	public TFacturaTiendaCompleta(Integer id, Integer idProducto, Integer idCliente) {
		this.idFactura = id;
		this.idProducto = idProducto;
		this.idCliente = idCliente;
	}

	public Integer getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Integer getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	
}
