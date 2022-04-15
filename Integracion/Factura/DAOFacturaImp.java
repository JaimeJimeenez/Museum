package Integracion.Factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import Integracion.Transaction.TransactionManager;
import Negocio.Factura.TFacturaTienda;

public class DAOFacturaImp implements DAOFactura{
	
	Connection conexion;
	PreparedStatement pStatement;
	ResultSet rs;


	@Override
	public List<TFacturaTienda> mostrarListaFacturas() {
		List<TFacturaTienda> listaFacturas = new ArrayList<TFacturaTienda>();
		try{
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement = conexion.prepareStatement("SELECT DISTINCT id, id_cliente, id_descuento, preciototal, fecha FROM factura_tienda");
			rs = pStatement.executeQuery();
			while(rs.next()){
				listaFacturas.add(new TFacturaTienda(rs.getInt(1), rs.getInt(2), rs.getInt(3),rs.getDouble(4),rs.getDate(5)));
			}
			pStatement.close();
			rs.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return listaFacturas;
	}

	@Override
	public int insertarFactura(TFacturaTienda tFactura) {
		int idFactura = -1;
		
		try{
			conexion = (Connection)TransactionManager.getInstancia().getTransaccion().getResource();
			
			pStatement = conexion.prepareStatement("INSERT INTO factura_tienda (id,id_cliente,id_descuento, preciototal, fecha,activo) VALUES (null,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			pStatement.setInt(1, tFactura.getIdCliente());
			if (tFactura.getIdDescuento() == 0) {
				pStatement.setNull(2, Types.INTEGER);
			} else {
				pStatement.setInt(2, tFactura.getIdDescuento());
			}
			pStatement.setDouble(3, tFactura.getPrecioTotal());
			pStatement.setDate(4, tFactura.getFecha());
			pStatement.setBoolean(5,true /*tFactura.isActivo()*/);
			pStatement.executeUpdate();
			rs = pStatement.getGeneratedKeys();
			
			if (rs.next()){
				idFactura = rs.getInt(1);
				
			}
			
			pStatement.close();
			rs.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return idFactura;
	}

	@Override
	public TFacturaTienda buscarFacturaPorId(int id) {
		TFacturaTienda tFactura = null;
		
		try{
			conexion = (Connection)TransactionManager.getInstancia().getTransaccion().getResource();
			
			pStatement = conexion.prepareStatement("SELECT DISTINCT id, id_cliente, id_descuento, preciototal, "
					+ "fecha FROM factura_tienda WHERE id = "+id+" AND activo = true FOR UPDATE");
			rs = pStatement.executeQuery();
			
			if(rs.next()){
				tFactura = new TFacturaTienda(rs.getInt(1), rs.getInt(2), rs.getInt(3),rs.getDouble(4),rs.getDate(5));
			}
			pStatement.close();
			rs.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return tFactura;
	}
	
	
}