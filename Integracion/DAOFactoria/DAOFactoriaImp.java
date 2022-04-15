package Integracion.DAOFactoria;

import Integracion.Cliente.DAOCliente;
import Integracion.Cliente.DAOClienteImp;
import Integracion.Descuento.DAODescuento;
import Integracion.Descuento.DAODescuentoImp;
import Integracion.Fabricante.DAOFabricante;
import Integracion.Fabricante.DAOFabricanteImp;
import Integracion.Factura.DAOFactura;
import Integracion.Factura.DAOFacturaImp;
import Integracion.Factura.DAOLineaFactura;
import Integracion.Factura.DAOLineaFacturaImp;
import Integracion.Producto.DAOProducto;
import Integracion.Producto.DAOProductoImp;
import Integracion.Proveedor.DAOProveedor;
import Integracion.Proveedor.DAOProveedorImp;

public class DAOFactoriaImp extends DAOFactoria {

	public DAOFabricante generarDAOFabricante() { return new DAOFabricanteImp(); }

	public DAOCliente generarDAOCliente() { return new DAOClienteImp(); }

	public DAOProducto generarDAOProducto() { return new DAOProductoImp(); }

	public DAODescuento generarDAODescuento() { return new DAODescuentoImp(); }

	public DAOFactura generarDAOFactura() { return new DAOFacturaImp(); }

	public DAOProveedor generarDAOProveedor() { return new DAOProveedorImp(); }

	public DAOLineaFactura generarDAOLineaFactura() { return new DAOLineaFacturaImp(); }
	
}
