package Integracion.Descuento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Integracion.Transaction.TransactionManager;
import Negocio.Descuento.TDescuento;

public class DAODescuentoImp implements DAODescuento {
    
    Connection conexion;
	PreparedStatement pStatement;
	ResultSet rs;
	
	@Override
	public int añadirDescuento(TDescuento tDescuento) {
		Integer idDescuento = 0;
		
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement = conexion.prepareStatement("INSERT INTO descuento (porcentaje) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
			pStatement.setInt(1, tDescuento.getPorcentaje());
			
			pStatement.executeUpdate();
			rs = pStatement.getGeneratedKeys();
			if (rs.next())
				idDescuento = rs.getInt(1);
			rs.close();
			pStatement.close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return idDescuento;
	}

	@Override
	public int modificarDescuento(TDescuento tDescuento) {
		int idDescuento = -1;
		
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement = conexion.prepareStatement("UPDATE descuento SET porcentaje = ?, activo = ? WHERE id = ?");
			pStatement.setInt(1, tDescuento.getPorcentaje());
			pStatement.setBoolean(2, true);
			pStatement.setInt(3, tDescuento.getId());
			pStatement.executeUpdate();
			pStatement.close();
			idDescuento = tDescuento.getId();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return idDescuento;
	}

	@Override
	public int borrarDescuento(int idDescuento) {
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement = conexion.prepareStatement("UPDATE descuento SET activo = ? WHERE id = ?"); 
			pStatement.setBoolean(1, false);
			pStatement.setInt(2, idDescuento);
			pStatement.executeUpdate();
			pStatement.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return idDescuento;
	}

	@Override
	public List<TDescuento> mostrarListaDescuentos() {
		List<TDescuento> listaDescuento = new ArrayList<TDescuento>();
		
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement = conexion.prepareStatement("SELECT * FROM descuento WHERE activo = true");
			rs = pStatement.executeQuery();
			while (rs.next()) 
				listaDescuento.add(new TDescuento(rs.getInt("id"), rs.getInt("porcentaje")));
			pStatement.close();
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return listaDescuento;
	}

	@Override
	public TDescuento buscarDescuentoporId(int idDescuento) {
		TDescuento tDescuento = null;
		
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement = conexion.prepareStatement("SELECT * FROM descuento WHERE activo = ? AND id = ?");
			pStatement.setBoolean(1, true);
			pStatement.setInt(2, idDescuento);
			rs = pStatement.executeQuery();
			if (rs.next())
				tDescuento = new TDescuento(rs.getInt(1), rs.getInt(2));
			pStatement.close();
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return tDescuento;
	}

	@Override
	public TDescuento buscarDescuentoPorPorcentaje(int porcentaje) {
		TDescuento tDescuento = null;
		
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement = conexion.prepareStatement("SELECT * FROM descuento WHERE porcentaje = ? AND activo = ?");
			pStatement.setInt(1, porcentaje);
			pStatement.setBoolean(2, true);
			rs = pStatement.executeQuery();
			if (rs.next())
				tDescuento = new TDescuento(rs.getInt(1), rs.getInt(2));
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tDescuento;
	}
}
