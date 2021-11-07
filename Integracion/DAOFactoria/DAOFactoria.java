package Integracion.DAOFactoria;

import Integracion.Fabricante.DAOFabricante;
import Integracion.Factura.DAOFactura;
import Integracion.Factura.DAOLineaFactura;
import Integracion.Producto.DAOProducto;
import Integracion.Proveedor.DAOProveedor;
import Integracion.Cliente.DAOCliente;
import Integracion.Descuento.DAODescuento;

public abstract class DAOFactoria {
	private static DAOFactoria instancia;

	public static DAOFactoria getInstancia() {
		if(instancia == null){
			instancia = new DAOFactoriaImp();
		}
		return instancia;
	}

	public static void setInstancia(DAOFactoria instancia) {
		DAOFactoria.instancia = instancia;
	}

	public abstract DAOFabricante generarDAOFabricante();
	
	public abstract DAOCliente generarDAOCliente();
	
	public abstract DAOProducto generarDAOProducto();
	
	public abstract DAODescuento generarDAODescuento();
	
	public abstract DAOFactura generarDAOFactura();
	
	public abstract DAOProveedor generarDAOProveedor();
	
	public abstract DAOLineaFactura generarDAOLineaFactura();
}