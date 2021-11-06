package src.Negocio.Cliente;

import java.util.List;

public interface SACliente {

	public int registrarCliente(TCliente tCliente) throws Exception;

	public void modificarCliente(TCliente tCliente) throws Exception;

	public int borrarCliente(int idCliente) throws Exception;

	public List<TCliente> mostrarListaClientes() throws Exception;

	public TCliente buscarClientePorId(int idCliente) throws Exception;

	public List<TCliente> listarClientesPorTipo(boolean tipo) throws Exception;
}