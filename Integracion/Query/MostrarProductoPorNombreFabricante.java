package Integracion.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Integracion.Transaction.TransactionManager;
import Negocio.Producto.TFotoObra;
import Negocio.Producto.TLibro;
import Negocio.Producto.TProducto;

public class MostrarProductoPorNombreFabricante implements Query{

	public Object execute(Object parametro) throws Exception {
		List<TProducto> listaProductos = new ArrayList<TProducto>();
		String NombreFabricante =(String) parametro;
		Connection conexion;
		PreparedStatement pStatement;
		ResultSet rs;
		
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement = conexion.prepareStatement("SELECT * FROM producto p, fabricante f, producto_libro pl WHERE f.id = p.id_fabricante and p.id = pl.id and f.nombre = ? and p.activo = true");
			pStatement.setString(1, NombreFabricante);
			rs = pStatement.executeQuery();
			while (rs.next()) {
				listaProductos.add(new TLibro(rs.getInt("id"), rs.getInt("id_fabricante"), rs.getInt("stock"),rs.getDouble("precio"), rs.getString("nombre"), rs.getString("categoria")));
			}
			pStatement = conexion.prepareStatement("SELECT * FROM producto p, fabricante f, producto_fotoobra pf WHERE f.id = p.id_fabricante and p.id = pf.id and f.nombre = ? and p.activo = true");
			pStatement.setString(1, NombreFabricante);
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

}
