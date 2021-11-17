package Negocio.Factura;

import java.util.ArrayList;
import java.util.List;

public class TCarrito {
    
    private TFacturaTienda factura;
	private List<TLineaFactura> lineaFactura;
	private int idProducto;
	private int cantidad;
	private double precioTotal;
	
	public TCarrito(){
		this.factura = new TFacturaTienda();
		this.lineaFactura = new ArrayList<TLineaFactura>();
		this.precioTotal = 0;
	}
	
	public TCarrito(TFacturaTienda factura, List<TLineaFactura> lineaFactura, double precioTotal){
		this.factura = factura;
		this.lineaFactura = lineaFactura;
		this.precioTotal = precioTotal;
	}
	
	public TFacturaTienda getFactura(){
		return this.factura;
	}
	
	public void setFactura(TFacturaTienda factura){
		this.factura = factura;
	}
	
	public List<TLineaFactura> getLineaFactura(){
		return this.lineaFactura;
	}
	
	public void setLineaFactura(List<TLineaFactura> lineaFactura){
		this.lineaFactura = lineaFactura;
	}
	
	public int getIdProducto(){
		return this.idProducto;
	}
	
	public void setIdProducto(int idProducto){
		this.idProducto = idProducto;
	}
	
	public int getCantidad(){
		return this.cantidad;
	}
	
	public void setCantidad(int cantidad){
		this.cantidad = cantidad;
	}
	
	public double getPrecioTotal(){
		return this.precioTotal;
	}
	
	public void setPrecioTotal(double precioTotal){
		this.precioTotal = precioTotal;
	}
}
