package Integracion.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Integracion.Transaction.TransactionManager;
import Negocio.Cliente.TCliente;
import Negocio.Cliente.TCorriente;
import Negocio.Cliente.TSocio;

public class DAOClienteImp implements DAOCliente {

	Connection conexion;
	PreparedStatement pStatement;
	ResultSet rs;
	
	public int registrarCliente(TCliente tCliente) {
		Integer idCliente = 0;
		
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement = conexion.prepareStatement("INSERT INTO cliente (dni, direccion, activo) VALUES (?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			pStatement.setString(1, tCliente.getDni());
			pStatement.setString(2, tCliente.getDireccion());
			pStatement.setBoolean(3, true);
			pStatement.executeUpdate();
			rs = pStatement.getGeneratedKeys();
			if (rs.next())
				idCliente= rs.getInt(1);
			
			if (tCliente.getCuota() == 0) {
				pStatement = conexion.prepareStatement("INSERT INTO cliente_corriente(id, gastos_envio) VALUES (?,?)");
				pStatement.setInt(1, idCliente);
				pStatement.setDouble(2, ((TCorriente)tCliente).getGastosEnvio());
			}
			else { 
				pStatement = conexion.prepareStatement("INSERT INTO cliente_socio (id, numero, cuota) VALUES (?, ?, ?)");
				pStatement.setInt(1, idCliente);
				pStatement.setInt(2, tCliente.getNumero());
				pStatement.setDouble(3, tCliente.getCuota());
		
				
			}
			pStatement.executeUpdate();
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
			pStatement= conexion.prepareStatement("UPDATE cliente SET direccion= ?, activo = ? WHERE id= ?");
			pStatement.setString(1, tCliente.getDireccion());
			pStatement.setBoolean(2, tCliente.isActivo());
			pStatement.setInt(3, tCliente.getId());
			pStatement.executeUpdate();
			if (tCliente.getGastosEnvio() != 0) {
				pStatement= conexion.prepareStatement("UPDATE cliente_corriente SET gastos_envio= ? WHERE id= ?");
				pStatement.setDouble(1, tCliente.getGastosEnvio());
				pStatement.setInt(2, tCliente.getId());
			}
			else {
				pStatement= conexion.prepareStatement("UPDATE cliente_socio SET numero = ?, cuota = ? WHERE id = ?");
				pStatement.setInt(1, ((TSocio) tCliente).getNumero());
				pStatement.setDouble(2, ((TSocio) tCliente).getCuota());
				pStatement.setInt(3, tCliente.getId());
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
			pStatement = conexion.prepareStatement("SELECT * FROM cliente c JOIN cliente_corriente co ON co.id = c.id WHERE activo = true");
			rs = pStatement.executeQuery();
			while (rs.next()) {
				listaClientes.add(new TCorriente(rs.getInt("id"), rs.getString("dni"), rs.getString("direccion"), rs.getDouble("gastos_envio")));
			}
			pStatement = conexion.prepareStatement("SELECT * FROM cliente c JOIN cliente_socio so ON so.id = c.id WHERE activo = true");
			rs = pStatement.executeQuery();
			while (rs.next()) {
				listaClientes.add(new TSocio(rs.getInt("id"), rs.getString("dni"), rs.getString("direccion"), rs.getInt("numero"), rs.getDouble("cuota")));
				
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
			pStatement = conexion.prepareStatement("SELECT * FROM cliente c inner join cliente_corriente co on c.id = co.id WHERE c.id = ? FOR UPDATE");
			pStatement.setInt(1, idCliente);
			rs = pStatement.executeQuery();
			if (rs.next()) {
				tCliente = new TCorriente(rs.getInt("id"), rs.getString("dni"), rs.getString("direccion"), rs.getDouble("gastos_envio"));
				tCliente.setActivo(rs.getBoolean("activo"));
			}
			else{
				pStatement = conexion.prepareStatement("SELECT * FROM cliente c inner join cliente_socio so on c.id = so.id WHERE c.id = ? FOR UPDATE");
				pStatement.setInt(1, idCliente);
				rs = pStatement.executeQuery();
				if (rs.next()) {
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
				pStatement = conexion.prepareStatement("SELECT * FROM cliente c inner join cliente_corriente co on c.id = co.id  where activo = true");
				rs = pStatement.executeQuery();
				while (rs.next()) 
					listaClientes.add(new TCorriente(rs.getInt("id"), rs.getString("dni"), rs.getString("direccion"), rs.getDouble("gastos_envio")));
			}
			else {
				pStatement = conexion.prepareStatement("SELECT * FROM cliente c inner join cliente_socio so on c.id = so.id WHERE activo = true");
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
			
			pStatement = conexion.prepareStatement("SELECT * FROM cliente c inner join cliente_corriente co on c.id = co.id WHERE DNI = ? FOR UPDATE");
			pStatement.setString(1, dni);
			rs = pStatement.executeQuery();
			if (rs.next()) {
				tCliente = new TCorriente(rs.getInt("id"), rs.getString("dni"), rs.getString("direccion"), rs.getDouble("gastos_envio"));
				tCliente.setActivo(rs.getBoolean("activo"));
			}
			else{
				pStatement = conexion.prepareStatement("SELECT * FROM cliente c inner join cliente_socio so on c.id = so.id WHERE DNI = ? FOR UPDATE");
				pStatement.setString(1, dni);
				rs = pStatement.executeQuery();
				if (rs.next()) {
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