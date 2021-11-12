package Integracion.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Integracion.Transaction.TransactionManager;
import Negocio.Cliente.*;

public class DAOClienteImp implements DAOCliente {

	Connection conexion;
	PreparedStatement pStatement;
	ResultSet rs;
	
	public int registrarCliente(TCliente tCliente) {
		Integer idCliente = 0;
		
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
		
			if (tCliente.getCuota() == 0) {
				pStatement = conexion.prepareStatement("INSERT INTO cliente (dni, direccion, gastos_envio, activo) VALUES (?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
				pStatement.setString(1, tCliente.getDni());
				pStatement.setString(2, tCliente.getDireccion());
				pStatement.setDouble(3, ((TCorriente)tCliente).getGastosEnvio());
				pStatement.setBoolean(4, true);
			}
			else {
				pStatement = conexion.prepareStatement("INSERT INTO cliente (dni, direccion, numero, cuota, activo) VALUES (?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
				pStatement.setString(1, tCliente.getDni());
				pStatement.setString(2, tCliente.getDireccion());
				pStatement.setInt(3, tCliente.getNumero());
				pStatement.setDouble(4, tCliente.getCuota());
				pStatement.setBoolean(5, true);
		
				
			}
			pStatement.executeUpdate();
			rs = pStatement.getGeneratedKeys();
			
			if (rs.next())
				idCliente= rs.getInt(1);
			
			rs.close();
			pStatement.close();
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return idCliente;
	}

	public int modificarCliente(TCliente tCliente) {
		int cliente = -1;
		try {
			conexion= (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			
			if (tCliente.getGastosEnvio() != 0) {
				pStatement= conexion.prepareStatement("UPDATE cliente SET direccion= ?, gastos_envio= ?, activo = ? WHERE id= ?");
				pStatement.setString(1, tCliente.getDireccion());
				pStatement.setDouble(2, tCliente.getGastosEnvio());
				pStatement.setBoolean(3, tCliente.isActivo());
				pStatement.setInt(4, tCliente.getId());
			}
			else {
				pStatement= conexion.prepareStatement("UPDATE cliente SET direccion = ?, numero = ?, cuota = ?, activo = ? WHERE id = ?");
				pStatement.setString(1, tCliente.getDireccion());
				pStatement.setInt(2, ((TSocio) tCliente).getNumero());
				pStatement.setDouble(3, ((TSocio) tCliente).getCuota());
				pStatement.setBoolean(4, tCliente.isActivo());
				pStatement.setInt(5, tCliente.getId());
			}
			pStatement.executeUpdate();
			cliente = tCliente.getId();
			
			pStatement.close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;
	}

	public int borrarCliente(int idCliente) {
		
		try {
			conexion= (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement= conexion.prepareStatement("UPDATE cliente SET activo = ? WHERE id = ?");
			pStatement.setBoolean(1, false);
			pStatement.setInt(2, idCliente);
			pStatement.executeUpdate();
			pStatement.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return idCliente;
	}

	public List<TCliente> mostrarListaClientes() {
		List<TCliente> listaClientes = new ArrayList<TCliente>();
		
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement = conexion.prepareStatement("SELECT * FROM cliente WHERE activo = true");
			rs = pStatement.executeQuery();
			while (rs.next()) {
				if (rs.getString("gastos_envio") != null) 
					listaClientes.add(new TCorriente(rs.getInt("id"), rs.getString("dni"), rs.getString("direccion"), rs.getDouble("gastos_envio")));
				else {
					listaClientes.add(new TSocio(rs.getInt("id"), rs.getString("dni"), rs.getString("direccion"), rs.getInt("numero"), rs.getDouble("cuota")));
				}
			}
			pStatement.close();
			rs.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaClientes;
	}


	public TCliente buscarClientePorId(int idCliente) {
		TCliente tCliente = null;
		
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement = conexion.prepareStatement("SELECT * FROM cliente WHERE id = ?");
			pStatement.setInt(1, idCliente);
			rs = pStatement.executeQuery();
			if (rs.next()) {
				if (rs.getString("gastos_envio") != null){ 
					tCliente = new TCorriente(rs.getInt("id"), rs.getString("dni"), rs.getString("direccion"), rs.getDouble("gastos_envio"));
					tCliente.setActivo(rs.getBoolean("activo"));
				}else {
					tCliente = new TSocio(rs.getInt("id"), rs.getString("dni"), rs.getString("direccion"), rs.getInt("numero"), rs.getDouble("cuota"));
					tCliente.setActivo(rs.getBoolean("activo"));
				}
			}
			pStatement.close();
			rs.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return tCliente;
	}

	
	public List<TCliente> listarClientesPorTipo(boolean isCorriente) {
		List<TCliente> listaClientes = new ArrayList<TCliente>();
		
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			if (isCorriente) {
				pStatement = conexion.prepareStatement("SELECT * FROM cliente WHERE gastos_envio > 0 AND activo = true");
				rs = pStatement.executeQuery();
				while (rs.next()) 
					listaClientes.add(new TCorriente(rs.getInt("id"), rs.getString("dni"), rs.getString("direccion"), rs.getDouble("gastos_envio")));
			}
			else {
				pStatement = conexion.prepareStatement("SELECT * FROM cliente WHERE gastos_envio IS NULL AND activo = true");
				rs = pStatement.executeQuery();
				while (rs.next()) 
					listaClientes.add(new TSocio(rs.getInt("id"), rs.getString("dni"), rs.getString("direccion"), rs.getInt("numero"), rs.getDouble("cuota")));
			}
			pStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaClientes;
	}

	@Override
	public TCliente buscarClientePorDNI(String dni) {
		TCliente tCliente = null;
		
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			
			pStatement = conexion.prepareStatement("SELECT * FROM cliente WHERE DNI = ?");
			pStatement.setString(1, dni);
			rs = pStatement.executeQuery();
			if (rs.next()) {
				if (rs.getString("gastos_envio") != null){ 
					tCliente = new TCorriente(rs.getInt("id"), rs.getString("dni"), rs.getString("direccion"), rs.getDouble("gastos_envio"));
					tCliente.setActivo(rs.getBoolean("activo"));
				}else {
					tCliente = new TSocio(rs.getInt("id"), rs.getString("dni"), rs.getString("direccion"), rs.getInt("numero"), rs.getDouble("cuota"));
					tCliente.setActivo(rs.getBoolean("activo"));
				}
			}
			pStatement.close();
			rs.close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tCliente;
	}

}