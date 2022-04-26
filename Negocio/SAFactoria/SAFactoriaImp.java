package Negocio.SAFactoria;

import Negocio.Cliente.SACliente;
import Negocio.Cliente.SAClienteImp;
import Negocio.Descuento.SADescuento;
import Negocio.Descuento.SADescuentoImp;
import Negocio.Empleado.SAEmpleado;
import Negocio.Empleado.SAEmpleadoImp;
import Negocio.Entrada.SAEntrada;
import Negocio.Entrada.SAEntradaImp;
import Negocio.Fabricante.SAFabricante;
import Negocio.Fabricante.SAFabricanteImp;
import Negocio.Factura.SAFactura;
import Negocio.Factura.SAFacturaImp;
import Negocio.FacturaMuseo.SAFacturaMuseo;
import Negocio.FacturaMuseo.SAFacturaMuseoImp;
import Negocio.Producto.SAProducto;
import Negocio.Producto.SAProductoImp;
import Negocio.TurnoEmpleado.SATurnoEmpleado;
import Negocio.TurnoEmpleado.SATurnoEmpleadoImp;

public class SAFactoriaImp extends SAFactoria {

	@Override
	public SACliente generarSACliente() { return new SAClienteImp(); }

	@Override
	public SAFabricante generarSAFabricante() { return new SAFabricanteImp(); }

	@Override
	public SAProducto generarSAProducto() { return new SAProductoImp(); }

	@Override
	public SADescuento generarSADescuento() { return new SADescuentoImp(); }

	@Override
	public SAFactura generarSAFactura() { return new SAFacturaImp(); }

	@Override
	public SAEmpleado generarSAEmpleado() { return new SAEmpleadoImp(); }
	
	@Override
	public SAFacturaMuseo generarSAFacturaMuseo(){ return new SAFacturaMuseoImp(); }
	
	@Override
	public SAEntrada generarSAEntrada() { return new SAEntradaImp(); }
	
	@Override
	public SATurnoEmpleado generarSATurnoEmpleado() { return new SATurnoEmpleadoImp(); }
}