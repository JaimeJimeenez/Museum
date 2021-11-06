package src.Integracion.DAOFactoria;

import src.Integracion.Cliente.DAOCliente;
import src.Integracion.Cliente.DAOClienteImp;

public class DAOFactoriaImp extends DAOFactoria {

	public DAOFabricante generarDAOFabricante() {
		return new DAOFabricanteImp();
	}

	public DAOCliente generarDAOCliente() {
		return new DAOClienteImp();
	}

	public DAOProducto generarDAOProducto() {
		return new DAOProductoImp();
	}

	public DAODescuento generarDAODescuento() {
		return new DAODescuentoImp();
	}

	public DAOFactura generarDAOFactura() {
		return new DAOFacturaImp();
	}

	public DAOProveedor generarDAOProveedor() {
		return new DAOProveedorImp();
	}
	
}
