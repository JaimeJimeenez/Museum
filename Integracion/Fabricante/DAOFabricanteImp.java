package Integracion.Fabricante;

import Negocio.Fabricante.TFabricante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Integracion.Transaction.TransactionManager;

public class DAOFabricanteImp implements DAOFabricante {
	
	Connection conexion;
	PreparedStatement pStatement;
	ResultSet rs;

	public int registrarFabricante(TFabricante tFabricante) {
		Integer IDFabricante = 0;
		
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement = conexion.prepareStatement("INSERT INTO fabricante (nombre, direccion, activo) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			pStatement.setString(1, tFabricante.getNombre());
			pStatement.setString(2, tFabricante.getDireccion());
			pStatement.setBoolean(3, true);
			pStatement.executeUpdate();
			rs = pStatement.getGeneratedKeys();
			if (rs.next()){
				IDFabricante=rs.getInt(1);
			}
			pStatement.close();
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return IDFabricante;
	}

	public int modificarFabricante(TFabricante tFabricante) {
		Integer IDFabricante = 0;
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement = conexion.prepareStatement("UPDATE fabricante SET nombre = ?, direccion = ?, activo = ? WHERE id = ?");
			pStatement.setString(1, tFabricante.getNombre());
			pStatement.setString(2, tFabricante.getDireccion());
			pStatement.setBoolean(3, tFabricante.isActivo());
			pStatement.setInt(4, tFabricante.getId());
			pStatement.executeUpdate();
			IDFabricante = tFabricante.getId();
			pStatement.close();
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return IDFabricante;
	}

	public int borrarFabricante(Integer IDFabricante) {
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement = conexion.prepareStatement("UPDATE fabricante SET activo = ? WHERE id = ?");
			pStatement.setBoolean(1, false);
			pStatement.setInt(2, IDFabricante);
			pStatement.executeUpdate();
			pStatement.close();
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return IDFabricante;
	}

	public List<TFabricante> mostrarListaFabricantes() {
		List<TFabricante> listaFabricantes = new ArrayList<TFabricante>();
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement = conexion.prepareStatement("SELECT * FROM fabricante WHERE activo = true");
			rs = pStatement.executeQuery();
			while(rs.next()){
				listaFabricantes.add(new TFabricante(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4)));
			}
			pStatement.close();
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return listaFabricantes;
		
	}

	public TFabricante buscarFabricantePorID(Integer IDFabricante) {
		TFabricante fabricante = null;
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement = conexion.prepareStatement("SELECT * FROM fabricante WHERE id = ? FOR UPDATE");
			pStatement.setInt(1, IDFabricante);
			rs = pStatement.executeQuery();
			if(rs.next()){
				fabricante = new TFabricante(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
			}
			pStatement.close();
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return fabricante;
	}
	
	public TFabricante buscarFabricantePorNombre(String nombreFabricante) {
		TFabricante fabricante = null;
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement = conexion.prepareStatement("SELECT * FROM fabricante WHERE nombre = ? FOR UPDATE");
			pStatement.setString(1, nombreFabricante);
			rs = pStatement.executeQuery();
			if(rs.next()){
				fabricante = new TFabricante(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
			}
			pStatement.close();
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return fabricante;
	}
}