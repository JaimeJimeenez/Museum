package Integracion.Factura;

import java.sql.SQLException;
import java.util.List;

import Negocio.Factura.TLineaFactura;

public interface DAOLineaFactura {

	public void insertarLineaFactura(TLineaFactura lineaFactura) throws Exception;
	
	public TLineaFactura buscarLineaFactura(int idFactura, int idProducto) throws Exception;
	
	public List<TLineaFactura> mostrarLineasFacturas(int idFactura) throws Exception;
	
	public void modificarLineaFactura(TLineaFactura lineaFactura) throws Exception;
	
	public void eliminarLineaFactura(TLineaFactura lineaFactura) throws Exception;
	
}
