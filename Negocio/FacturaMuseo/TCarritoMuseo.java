/**
 * 
 */
package Negocio.FacturaMuseo;

import java.awt.List;
import java.util.LinkedList;

public class TCarritoMuseo {
	
	private TFacturaMuseo facturaMuseo;
	private LinkedList<TLineaFacturaMuseo> lineasFacturaMuseo;
	private int idEntrada;
	private int cantidad;
	private double precio;
	
	
	public TCarritoMuseo() {
		this.facturaMuseo = new TFacturaMuseo();
		this.lineasFacturaMuseo = new LinkedList<TLineaFacturaMuseo>();
		this.precio = 0;
	}
	
	public TCarritoMuseo(TFacturaMuseo factura) {
		this.facturaMuseo = factura;
		this.lineasFacturaMuseo = new LinkedList<TLineaFacturaMuseo>();
		
		this.precio = 0;
	}
	
	public TCarritoMuseo(TFacturaMuseo factura, LinkedList<TLineaFacturaMuseo> lineasFactura, double precioTotal) {
		this.facturaMuseo = factura;
		this.lineasFacturaMuseo = lineasFactura;
		this.precio = precioTotal;
	}
	
	public TFacturaMuseo getFacturaMuseo() {
		return facturaMuseo;
	}

	public void setFacturaMuseo(TFacturaMuseo facturaMuseo) {
		this.facturaMuseo = facturaMuseo;
	}

	public LinkedList<TLineaFacturaMuseo> getLineasFacturaMuseo() {
		return lineasFacturaMuseo;
	}

	public void setLineasFacturaMuseo(LinkedList<TLineaFacturaMuseo> lineasFacturaMuseo) {
		this.lineasFacturaMuseo = lineasFacturaMuseo;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
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