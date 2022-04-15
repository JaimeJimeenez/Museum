package Integracion.Producto;

import java.util.List;

import Negocio.Producto.TProducto;

public interface DAOProducto {
	
	public int registrarProducto(TProducto tProducto);
	
	public int modificarProducto(TProducto tProducto);
	
	public int borrarProducto(int idProducto);
	
	public List<TProducto> mostrarListaProducto();
	
	public TProducto buscarProductoPorId(int idProducto);
	
	public List<TProducto> leerPorIdFabricante(int idFabricante);

	public TProducto buscarProductoPorNombre(String nombreProducto);
}