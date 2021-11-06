package src.Integracion.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.Integracion.Transaccion.TransactionManager;
import src.Negocio.Cliente.TCliente;
import src.Negocio.Cliente.TCorriente;
import src.Negocio.Cliente.TSocio;

public class DAOClienteImp implements DAOCliente {

	Connection conexion;
	PreparedStatement pStatement;
	ResultSet rs;
	
	public int registrarCliente(TCliente tCliente) {
		Integer idCliente = 0;
		
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			if (tCliente.isCorriente()) {
				pStatement = conexion.prepareStatement("INSERT INTO CLIENTE (id, dni, direccion, gastosEnvio) VALUES (null, ?, ?, ?");
				pStatement.setString(1, tCliente.getDni());
				pStatement.setString(2, tCliente.getDireccion());
				pStatement.setFloat(3, ((TCorriente)tCliente).getGastosEnvio());
			}
			else {
				pStatement = conexion.prepareStatement("INSERT INTO CLIENTE (id, dni, direccion, numero, cuota) VALUES (null, ?, ?, ?, ?)");
				pStatement.setString(1, tCliente.getDni());
				pStatement.setString(2, tCliente.getDireccion());
				pStatement.setInt(4, ((TSocio)tCliente).getNumero());
				pStatement.setFloat(5, ((TSocio)tCliente).getCuota());
			}
			rs = pStatement.getGeneratedKeys();
			if (rs.next())
				idCliente= rs.getInt(1);
			pStatement.close();
			rs.close();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return idCliente;
	}

	public void modificarCliente(TCliente tCliente) {
		try {
			conexion= (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			if (tCliente.isCorriente()) {
				pStatement= conexion.prepareStatement("UPDATE cliente SET direccion= ?, gastosEnvio= ? WHERE id= ?");
				pStatement.setString(1, tCliente.getDireccion());
				pStatement.setFloat(2, ((TCorriente) tCliente).getGastosEnvio());
				pStatement.setInt(3, tCliente.getId());
			}
			else {
				pStatement= conexion.prepareStatement("UPDATE cliente SET direccion= ?, numero= ?, cuota= ? WHERE id= ?");
				pStatement.setString(1, tCliente.getDireccion());
				pStatement.setInt(2, ((TSocio) tCliente).getNumero());
				pStatement.setFloat(3, ((TSocio) tCliente).getCuota());
				pStatement.setInt(4, tCliente.getId());
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int borrarCliente(int idCliente) {
		
		try {
			conexion= (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			pStatement= conexion.prepareStatement("UPDATE cliente SET activo = false WHERE id_cliente = ?");
			pStatement.setInt(1, idCliente);
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
			pStatement = conexion.prepareStatement("SELECT * FROM CLIENTE WHERE activo = true");
			rs = pStatement.executeQuery();
			while (rs.next()) {
				if (rs.getBoolean(5)) 
					listaClientes.add(new TCorriente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4)));
				else {
					listaClientes.add(new TSocio(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5)));
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
			pStatement = conexion.prepareStatement("SELECT * FROM CLIENTE WHERE id = ? AND activo = true");
			pStatement.setInt(1, idCliente);
			rs = pStatement.executeQuery();
			if (rs.next()) {
				if (rs.getBoolean(5)) 
					tCliente = new TCorriente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4));
				else 
					tCliente = new TSocio(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5));
			}
			pStatement.close();
			rs.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return tCliente;
	}

	public List<TCliente> listarClientesPorTipo(boolean tipo) {
		List<TCliente> listaClientes = new ArrayList<TCliente>();
		
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			
			if (tipo) {
				pStatement = conexion.prepareStatement("SELECT * FROM CLIENTE WHERE isCorriente = true AND activo = true");
				rs = pStatement.executeQuery();
				while (rs.next()) 
					listaClientes.add(new TCorriente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4)));
			}
			else {
				pStatement = conexion.prepareStatement("SELECT * FROM CLIENTE WHERE tipo = socio AND activo = true");
				rs = pStatement.executeQuery();
				while (rs.next()) 
					listaClientes.add(new TSocio(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5)));
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
			
			pStatement = conexion.prepareStatement("SELECT * FROM CLIENTE WHERE DNI = ?");
			pStatement.setString(1, dni);
			rs = pStatement.executeQuery();
			if (rs.next()) {
				if (rs.getBoolean(5)) 
					tCliente = new TCorriente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4));
				else 
					tCliente = new TSocio(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5));
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