package Negocio.FacturaMuseo;

import java.util.LinkedList;
import java.util.List;

public interface SAFacturaMuseo {

	public TCarritoMuseo abrirFactura();

	public TCarritoMuseo añadirEntrada(TCarritoMuseo carrito);

	public TCarritoMuseo buscarFacturaPorID(Integer ID);

	public boolean devolverEntrada(TLineaFacturaMuseo linea);

	public TCarritoMuseo quitarEntrada(TCarritoMuseo carrito);

	public LinkedList<TCarritoMuseo> listarFactura();

	public boolean cerrarFactura(TCarritoMuseo carrito);

	public LinkedList<TFacturaMuseo> leerPorIdEmpleado(Integer idEmpleado);

	public LinkedList<TFacturaCompleta> mostrarFacturaCompleta();
}