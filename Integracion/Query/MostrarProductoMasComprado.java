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

public class MostrarProductoMasComprado implements Query{

	@Override
	public Object execute(Object parametro) {
		List<TProducto> lista = new ArrayList<TProducto>();
		Connection conexion;
		PreparedStatement pStatement;
		ResultSet rs;
		
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			// MIRAR LA CONSULTA
			pStatement = conexion.prepareStatement("SELECT p.*, SUM(fp.cantidad) AS cantidad "
					+ "FROM producto p LEFT JOIN factura_producto fp ON (p.id = fp.id_producto) "
					+ "WHERE p.activo = true GROUP BY p.id ORDER BY cantidad DESC");
			rs = pStatement.executeQuery();
			int cantidad = 0;
			boolean stop = false;
			boolean b = false;
			while (rs.next() && !stop) {
				if(!b){
					b = true;
					cantidad = rs.getInt("cantidad");
				}
				
				if(cantidad != rs.getInt("cantidad")) {
					stop = true;
				}else if (rs.getString("categoria") != null) 
					lista.add(new TLibro(rs.getInt("id"),rs.getInt("id_fabricante"),rs.getInt("stock"),rs.getDouble("precio"), rs.getString("nombre"), rs.getString("categoria")));
				else {
					lista.add(new TFotoObra(rs.getInt("id"),rs.getInt("id_fabricante"),rs.getInt("stock"),rs.getDouble("precio"), rs.getString("nombre"), rs.getString("estilo"), rs.getString("tamano")));
				}
				
				
			}
			pStatement.close();
			rs.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}

}
