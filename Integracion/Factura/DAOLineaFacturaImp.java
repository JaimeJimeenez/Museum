package Integracion.Factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Integracion.Transaction.TransactionManager;
import Negocio.Factura.TLineaFactura;
import Negocio.Producto.*;

public class DAOLineaFacturaImp implements DAOLineaFactura{

	Connection conexion;
	PreparedStatement pStatement;
	ResultSet rs;
	
	public void insertarLineaFactura(TLineaFactura lineaFactura) throws SQLException  {
		conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
		pStatement = conexion.prepareStatement("INSERT INTO factura_producto (id_factura, id_producto, cantidad, precio) VALUES (?,?,?,?)");
		pStatement.setInt(1, lineaFactura.getIdFactura());
		pStatement.setInt(2, lineaFactura.getProducto().getId());
		pStatement.setInt(3, lineaFactura.getCantidad());
		pStatement.setDouble(4, lineaFactura.getPrecio());
		pStatement.executeUpdate();
		pStatement.close();
	}

	
	public TLineaFactura buscarLineaFactura(int idFactura, int idProducto) throws Exception {
		TLineaFactura lineaFactura = null;
		conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
		pStatement = conexion.prepareStatement("SELECT * FROM producto p LEFT JOIN producto_fotoobra pf ON p.id = pf.id LEFT JOIN producto_libro pl ON p.id = pl.id WHERE p.id = "+idProducto+" FOR UPDATE");
		rs = pStatement.executeQuery();
		if (rs.next()) {
			TProducto producto = null;
			if (rs.getString("categoria")!=null) {
				producto = new TLibro(rs.getInt("id"), rs.getInt("id_fabricante"), rs.getInt("stock"),rs.getDouble("precio"), rs.getString("nombre"),  rs.getString("categoria"));
			} else {
				producto = new TFotoObra(rs.getInt("id"), rs.getInt("id_fabricante"), rs.getInt("stock"),rs.getDouble("precio"), rs.getString("nombre"),rs.getString("estilo"), rs.getString("tamano"));
				
			}
			pStatement = conexion.prepareStatement("SELECT * FROM factura_producto WHERE id_factura = "+idFactura+" AND id_producto = "+idProducto+" FOR UPDATE");
			rs=pStatement.executeQuery();
			while (rs.next()) {
				lineaFactura = new TLineaFactura(idFactura, producto, rs.getInt("cantidad"), rs.getDouble("precio"));
			}
		}
		pStatement.close();
		rs.close();
		return lineaFactura;
	}

	public List<TLineaFactura> mostrarLineasFacturas(int idFactura) throws Exception {
		List<TLineaFactura> listaFacturas = new ArrayList<TLineaFactura>();
		TProducto producto = null;
		conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
		pStatement = conexion.prepareStatement("SELECT * FROM factura_producto WHERE id_factura = "+idFactura+" FOR UPDATE");
		rs = pStatement.executeQuery();
		while (rs.next()) {
			String sql = "SELECT * FROM producto p LEFT JOIN producto_fotoobra pf ON p.id = pf.id LEFT JOIN producto_libro pl ON p.id = pl.id WHERE p.id = "+rs.getInt("id_producto")+" FOR UPDATE";
			pStatement = conexion.prepareStatement(sql);
			ResultSet resultSet = pStatement.executeQuery();
			if (resultSet.next()) {
				if (resultSet.getString("categoria") != null) {
					producto = new TLibro(resultSet.getInt("id"), resultSet.getInt("id_fabricante"), resultSet.getInt("stock"), resultSet.getDouble("precio"), resultSet.getString("nombre"), resultSet.getString("categoria"));
				
				} else {
					producto = new TFotoObra(resultSet.getInt("id"), resultSet.getInt("id_fabricante"), resultSet.getInt("stock"), resultSet.getDouble("precio"), resultSet.getString("nombre"), resultSet.getString("estilo"), resultSet.getString("tamanio"));
					
				}
				listaFacturas.add(new TLineaFactura(rs.getInt("id_factura"), producto, rs.getInt("cantidad"), rs.getDouble("precio")));
				resultSet.close();
			}
			resultSet.close();
		}
		pStatement.close();
		rs.close();
		return listaFacturas;
	}

	public void modificarLineaFactura(TLineaFactura lineaFactura) throws Exception {
		conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
		pStatement = conexion.prepareStatement("UPDATE factura_producto SET cantidad = ?, precio = ? WHERE id_factura = "+lineaFactura.getIdFactura()+" AND id_producto = "+lineaFactura.getProducto().getId());
		pStatement.setInt(1, lineaFactura.getCantidad());
		pStatement.setDouble(2, lineaFactura.getPrecio());
		pStatement.executeUpdate();
		pStatement.close();
	}

	public void eliminarLineaFactura(TLineaFactura lineaFactura) throws Exception {
		conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
		pStatement = conexion.prepareStatement("DELETE FROM factura_producto WHERE id_factura = "+lineaFactura.getIdFactura()+" AND id_producto = "+lineaFactura.getProducto().getId());
		pStatement.executeUpdate();
		pStatement.close();
	}

}
