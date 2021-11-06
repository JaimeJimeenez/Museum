package src.Integracion.DAOFactoria;

import integracion.fabricante.DAOFabricante;
import integracion.factura.DAOFactura;
import integracion.producto.DAOProducto;
import integracion.proveedor.DAOProveedor;
import src.Integracion.Cliente.DAOCliente;
import integracion.descuento.DAODescuento;

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
}
