package Negocio.Factura;

import Negocio.Producto.TProducto;

public class TLineaFactura {
    private int idFactura;
	private TProducto producto;
	private int cantidad;
	private double precio;
	
	public TLineaFactura(int idFactura, TProducto producto, int cantidad, double precio){
		this.idFactura = idFactura;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	
	public TLineaFactura(TProducto producto, int cantidad){
		this.producto = producto;
		this.cantidad = cantidad;
	}
	
	public TLineaFactura(int idFactura, TProducto tProducto,  int cantidad) {
		this.idFactura = idFactura;
		this.producto = tProducto;
		this.cantidad = cantidad;
	}
	
	public int getIdFactura() {
		return idFactura;
	}
	
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	
	public TProducto getProducto() {
		return producto;
	}
	
	public void setProducto(TProducto producto) {
		this.producto = producto;
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
	
}
