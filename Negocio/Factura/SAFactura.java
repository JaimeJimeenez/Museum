package Negocio.Factura;

import java.util.List;

import Negocio.Producto.TProducto;

public interface SAFactura {

    public TCarrito abrirFactura();

	public TCarrito añadirProducto(TCarrito carrito) throws Exception;

	public TCarrito bucarFacturaID(int id) throws Exception;

	public boolean cerrarFactura(TCarrito carrito) throws Exception;

	public boolean devolverProducto(TLineaFactura lineaFactura) throws Exception;

	public List<TCarrito> mostrarListaFacturas() throws Exception;

	public TCarrito quitarProducto(TCarrito carrito);

	public List<TFacturaTienda> leerIDdeCliente(int idCliente) throws Exception;

	public List<TFacturaTienda> leerIDdeDescuento(int idDescuento) throws Exception;

	public List<TProducto> mostrarProductoMasComprado() throws Exception;
	
	public List<TFacturaTienda> mostrarFacturaConMayorPorcentajeDescuento() throws Exception;
}
