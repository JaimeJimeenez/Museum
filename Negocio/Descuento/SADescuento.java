package Negocio.Descuento;

import java.util.List;

public interface SADescuento {
    
    public int añadirDescuento(TDescuento tDescuento) throws Exception;

	public int modificarDescuento(TDescuento tDescuento) throws Exception;

	public int borrarDescuento(int idDescuento) throws Exception;

	public List<TDescuento> mostrarListaDescuentos() throws Exception;

	public TDescuento buscarDescuentoporId(int idDescuento) throws Exception;
}
