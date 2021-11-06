package src.Negocio.SAFactoria;

import src.Negocio.Cliente.SACliente;
import src.Negocio.Cliente.SAClienteImp;

public class SAFactoriaImp extends SAFactoria {

	public SACliente generarSACliente() { return new SAClienteImp(); }

	public SAFabricante generarSAFabricante() { return new SAFabricanteImp(); }

	public SAProducto generarSAProducto() { return new SAProductoImp(); }

	public SADescuento generarSADescuento() { return new SADescuentoImp(); }

	public SAFactura generarSAFactura() { return new SAFacturaImp(); }
	
	public SAProveedor generarSAProveedor() { return new SAProveedorImp(); }
}