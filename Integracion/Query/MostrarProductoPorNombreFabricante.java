package Integracion.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Integracion.Transaction.TransactionManager;
import Negocio.Producto.*;


public class MostrarProductoPorNombreFabricante implements Query {

    @Override
    public Object execute(Object parameter) throws Exception {
        List<TProducto> listaProductos = new ArrayList<TProducto>();
		String NombreFabricante =(String) parameter;
		Connection conexion;
		PreparedStatement pStatement;
		ResultSet rs;
		
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement = conexion.prepareStatement("SELECT * FROM producto p, fabricante f WHERE f.id = p.id_fabricante and f.nombre = ? and p.activo = true");
			pStatement.setString(1, NombreFabricante);
			rs = pStatement.executeQuery();
			while (rs.next()) {
				if (rs.getString("categoria") != null) 
					listaProductos.add(new TLibro(rs.getInt(1), rs.getInt(2), rs.getInt(4),rs.getDouble(5), rs.getString(3), rs.getString(6)));
				else {
					listaProductos.add(new TFotoObra(rs.getInt(1), rs.getInt(2), rs.getInt(4), rs.getDouble(5), rs.getString(3), rs.getString(7),rs.getString(8)));
				}
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
