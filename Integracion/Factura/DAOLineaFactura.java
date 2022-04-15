package Integracion.Factura;

import java.util.List;

import Negocio.Factura.TLineaFactura;

public interface DAOLineaFactura {

	public void insertarLineaFactura(TLineaFactura lineaFactura);
	
	public TLineaFactura buscarLineaFactura(int idFactura, int idProducto);
	
	public List<TLineaFactura> mostrarLineasFacturas(int idFactura);
	
	public void modificarLineaFactura(TLineaFactura lineaFactura);
	
	public void eliminarLineaFactura(TLineaFactura lineaFactura);
	
}
