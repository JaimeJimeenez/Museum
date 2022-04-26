package Negocio.SAFactoria;

import Negocio.Cliente.SACliente;
import Negocio.Descuento.SADescuento;
import Negocio.Empleado.SAEmpleado;
import Negocio.Entrada.SAEntrada;
import Negocio.Fabricante.SAFabricante;
import Negocio.Factura.SAFactura;
import Negocio.FacturaMuseo.SAFacturaMuseo;
import Negocio.Producto.SAProducto;
import Negocio.TurnoEmpleado.SATurnoEmpleado;

public abstract class SAFactoria {
	
	private static SAFactoria instancia;

	public static SAFactoria getInstancia() {
		if(instancia == null)
			instancia = new SAFactoriaImp();
		return instancia;
	}

	public static void setIntancia(SAFactoria intancia) {
		SAFactoria.instancia = intancia;
	}

	public abstract SACliente generarSACliente();

	public abstract SAFabricante generarSAFabricante();

	public abstract SAProducto generarSAProducto();
	
	public abstract SADescuento generarSADescuento();
	
	public abstract SAFactura generarSAFactura();
	
	public abstract SAEmpleado generarSAEmpleado();
	
	public abstract SAFacturaMuseo generarSAFacturaMuseo();
	
	public abstract SAEntrada generarSAEntrada();
	
	public abstract SATurnoEmpleado generarSATurnoEmpleado();
}