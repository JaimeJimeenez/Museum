package Negocio.Producto;

import java.util.List;

public interface SAProducto {
	
	public int registrarProducto(TProducto tProducto) throws Exception;
	
	public int modificarProducto(TProducto tProducto) throws Exception;
	
	public int borrarProducto(int idProducto) throws Exception;
	
	public List<TProducto> mostrarListaProducto() throws Exception;
	
	public TProducto buscarProductoPorId(int idProducto) throws Exception;
	
	public List<TProducto> mostrarProductoPorNombreFabricante(String nombreFabr) throws Exception;
	
	public List<TProducto> leerPorIdFabricante(int idFabricante) throws Exception;
}