package src.Integracion.Cliente;

import java.util.List;

import src.Negocio.Cliente.TCliente;

public interface DAOCliente {

	public int registrarCliente(TCliente tCliente);

	public void modificarCliente(TCliente tCliente);

	public int borrarCliente(int idCliente);

	public List<TCliente> mostrarListaClientes();

	public TCliente buscarClientePorId(int idCliente);

	public List<TCliente> listarClientesPorTipo(boolean tipo);
	
	public TCliente buscarClientePorDNI(String dni);
}