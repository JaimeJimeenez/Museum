package Integracion.Cliente;

import java.util.List;

import Negocio.Cliente.TCliente;

public interface DAOCliente {

	public int registrarCliente(TCliente tCliente);

	public int modificarCliente(TCliente tCliente);

	public int borrarCliente(int idCliente);

	public List<TCliente> mostrarListaClientes();

	public TCliente buscarClientePorId(int idCliente);

	public List<TCliente> listarClientesPorTipo(boolean tipo);
	
	public TCliente buscarClientePorDNI(String dni);
}
