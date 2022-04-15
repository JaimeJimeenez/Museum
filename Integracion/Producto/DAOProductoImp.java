package Integracion.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Integracion.Transaction.TransactionManager;
import Negocio.Producto.TProducto;
import Negocio.Producto.TLibro;
import Negocio.Producto.TFotoObra;

public class DAOProductoImp implements DAOProducto {
	
	Connection conexion;
	PreparedStatement pStatement;
	ResultSet rs;
	
	public int registrarProducto(TProducto tproducto) {
		Integer idProducto = 0;
		
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement =  conexion.prepareStatement("INSERT INTO producto(id_fabricante, stock, precio, nombre, activo) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			pStatement.setInt(1, tproducto.getIdFabricante());
			pStatement.setInt(2, tproducto.getStock());
			pStatement.setDouble(3, tproducto.getPrecio());
			pStatement.setString(4, tproducto.getNombre());
			pStatement.setBoolean(5, true);
			pStatement.executeUpdate();
			rs = pStatement.getGeneratedKeys();
			if(rs.next()){
				idProducto = rs.getInt(1);
			}
			if(tproducto.getCategoria() != null){
				pStatement =  conexion.prepareStatement("INSERT INTO producto_libro(id, categoria) VALUES (?, ?)");
				pStatement.setInt(1, idProducto);
				pStatement.setString(2, tproducto.getCategoria());
			}
			else{
				pStatement =  conexion.prepareStatement("INSERT INTO producto_fotoobra(id, estilo, tamanio) VALUES ( ?, ?, ?)");
				pStatement.setInt(1, idProducto);
				pStatement.setString(2, tproducto.getEstilo());
				pStatement.setString(3, tproducto.getTamanio());
			}
			pStatement.executeUpdate();
			pStatement.close();
			rs.close();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return idProducto;
		
	}
	
	public int modificarProducto(TProducto tproducto) {
		Integer idProducto = 0;
		try {
			conexion= (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement= conexion.prepareStatement("UPDATE producto SET id_fabricante = ?, stock= ?, precio= ?, nombre =?, activo = ? WHERE id= ?");
			pStatement.setInt(1, tproducto.getIdFabricante());
			pStatement.setInt(2, tproducto.getStock());
			pStatement.setDouble(3, tproducto.getPrecio());
			pStatement.setString(4, tproducto.getNombre());
			pStatement.setBoolean(5, tproducto.isActivo());
			pStatement.setInt(6,tproducto.getId());
			pStatement.executeUpdate();
			if(tproducto.getCategoria()!= null){
				pStatement= conexion.prepareStatement("UPDATE producto_libro SET categoria = ? WHERE id = ?");
				pStatement.setString(1, tproducto.getCategoria());
				pStatement.setInt(2, tproducto.getId());
			}
			else{
				pStatement= conexion.prepareStatement("UPDATE producto_fotoobra SET estilo = ?,  tamanio = ? WHERE id= ?");
				pStatement.setString(1, tproducto.getEstilo());
				pStatement.setString(2, tproducto.getTamanio());
				pStatement.setInt(3, tproducto.getId());
				
			}
			pStatement.executeUpdate();
			idProducto = tproducto.getId();
			pStatement.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return idProducto;
	}
	
	public int borrarProducto(int idProducto) {
		
		try {
			conexion= (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement= conexion.prepareStatement("UPDATE producto SET activo = ? WHERE id = ?");
			pStatement.setBoolean(1, false);
			pStatement.setInt(2, idProducto);
			pStatement.executeUpdate();
			pStatement.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return idProducto;
	}
	
	public List<TProducto> mostrarListaProducto(){
		List<TProducto> listaProductos = new ArrayList<TProducto>();
		
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement = conexion.prepareStatement("SELECT * FROM producto p, producto_libro pl WHERE p.id = pl.id and activo = ?");
			pStatement.setBoolean(1, true);
			rs = pStatement.executeQuery();
			while (rs.next()) { 
				listaProductos.add(new TLibro(rs.getInt("id"), rs.getInt("id_fabricante"), rs.getInt("stock"),rs.getDouble("precio"), rs.getString("nombre"), rs.getString("categoria")));
			}
			pStatement = conexion.prepareStatement("SELECT * FROM producto p, producto_fotoobra pf WHERE p.id = pf.id and activo = ?");
			pStatement.setBoolean(1, true);
			rs = pStatement.executeQuery();
			while (rs.next()) { 
				listaProductos.add(new TFotoObra(rs.getInt("id"), rs.getInt("id_fabricante"), rs.getInt("stock"), rs.getDouble("precio"), rs.getString("nombre"), rs.getString("estilo"),rs.getString("tamanio")));
			}
			pStatement.close();
			rs.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaProductos;
	}
	

	@Override
	public TProducto buscarProductoPorId(int idProducto) {
		TProducto tproducto = null;
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement = conexion.prepareStatement("SELECT * FROM producto p join producto_libro pl on p.id = pl.id WHERE p.id = ? AND  activo = ? FOR UPDATE");
			pStatement.setInt(1, idProducto);
			pStatement.setBoolean(2, true);
			rs = pStatement.executeQuery();
			if (rs.next()) {
				tproducto =(new TLibro(rs.getInt("id"), rs.getInt("id_fabricante"), rs.getInt("stock"),rs.getDouble("precio"), rs.getString("nombre"), rs.getString("categoria")));
			}
			else{
				pStatement = conexion.prepareStatement("SELECT * FROM producto p join producto_fotoobra pf on p.id = pf.id WHERE p.id = ? AND  activo = ? FOR UPDATE");
				pStatement.setInt(1, idProducto);
				pStatement.setBoolean(2, true);
				rs = pStatement.executeQuery();
				if (rs.next()) {
					tproducto =(new TFotoObra(rs.getInt("id"), rs.getInt("id_fabricante"), rs.getInt("stock"), rs.getDouble("precio"), rs.getString("nombre"), rs.getString("estilo"),rs.getString("tamanio")));
				}
			}
			pStatement.close();
			rs.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tproducto;
	}

	@Override
	public List<TProducto> leerPorIdFabricante(int idFabricante) {
		List<TProducto> listaProductos = new ArrayList<TProducto>();
		
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement = conexion.prepareStatement("SELECT * FROM producto p, producto_libro pl WHERE p.id = pl.id AND id_fabricante = ? AND activo = ?");
			pStatement.setInt(1, idFabricante);
			pStatement.setBoolean(2, true);
			rs = pStatement.executeQuery();
			while (rs.next()) {
					listaProductos.add(new TLibro(rs.getInt("id"), rs.getInt("id_fabricante"), rs.getInt("stock"),rs.getDouble("precio"), rs.getString("nombre"), rs.getString("categoria")));
			}
			pStatement = conexion.prepareStatement("SELECT * FROM producto p, producto_fotoobra pf WHERE p.id = pf.id AND id_fabricante = ? AND activo = ?");
			pStatement.setInt(1, idFabricante);
			pStatement.setBoolean(2, true);
			rs = pStatement.executeQuery();
			while (rs.next()) {
				listaProductos.add(new TFotoObra(rs.getInt("id"), rs.getInt("id_fabricante"), rs.getInt("stock"), rs.getDouble("precio"), rs.getString("nombre"), rs.getString("estilo"),rs.getString("tamanio")));
			}
			pStatement.close();
			rs.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaProductos;
	}

	@Override
	public TProducto buscarProductoPorNombre(String nombreProducto) {
		TProducto producto = null;
		try{
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement = conexion.prepareStatement("SELECT * FROM producto p join producto_libro pl on p.id = pl.id WHERE nombre = ? FOR UPDATE");
			pStatement.setString(1, nombreProducto);
			rs = pStatement.executeQuery();
			if(rs.next()){
				producto =(new TLibro(rs.getInt("id"), rs.getInt("id_fabricante"), rs.getInt("stock"),rs.getDouble("precio"), rs.getString("nombre"), rs.getString("categoria")));
				producto.setActivo(rs.getBoolean("activo"));
			}
			else{
				pStatement = conexion.prepareStatement("SELECT * FROM producto p join producto_fotoobra pf on p.id = pf.id WHERE nombre = ? FOR UPDATE");
				pStatement.setString(1, nombreProducto);
				rs = pStatement.executeQuery();
				if(rs.next()){
					producto =(new TFotoObra(rs.getInt("id"), rs.getInt("id_fabricante"), rs.getInt("stock"), rs.getDouble("precio"), rs.getString("nombre"), rs.getString("estilo"),rs.getString("tamanio")));
				}
			}
			pStatement.close();
			rs.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return producto;
	}

}