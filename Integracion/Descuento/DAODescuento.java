package Integracion.Descuento;

import java.util.List;

import Negocio.Descuento.TDescuento;

public interface DAODescuento {
    
    public int añadirDescuento(TDescuento tDescuento);

	public int modificarDescuento(TDescuento tDescuento);

	public int borrarDescuento(int idDescuento);

	public List<TDescuento> mostrarListaDescuentos();

	public TDescuento buscarDescuentoporId(int idDescuento);

	public TDescuento buscarDescuentoPorPorcentaje(int porcentaje);
}
