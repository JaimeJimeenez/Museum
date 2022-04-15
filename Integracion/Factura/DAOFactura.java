package Integracion.Factura;

import java.util.List;

import Negocio.Factura.TFacturaTienda;

public interface DAOFactura {
	
	public int insertarFactura(TFacturaTienda tFactura);
	public TFacturaTienda buscarFacturaPorId(int id);
	public List<TFacturaTienda> mostrarListaFacturas();
}