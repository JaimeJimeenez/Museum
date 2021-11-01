package Negocio.Cliente;

import java.util.List;

import Integracion.Transacciones.Transaction;
import Integracion.Transacciones.TransactionManager;
import Integracion.DAOFactoria.DAOFactoria;
import Integracion.Cliente.DAOCliente;

public class SAClienteImp implements SACliente {

	public int registrarCliente(TCliente tCliente) {
		int idCliente = 0;
		
		Transaction transaction = TransactionManager.getInstancia().getTransaccion();
		transaction.start();  
		
		DAOCliente daoCliente =  DAOFactoria.getInstancia().generarDAOCliente();
		TCliente clienteLeido = daoCliente.buscarClientePorDNI(tCliente.getDni()); //mirar si el nombre del metodo esta OK
		
		if(clienteLeido == null){
			idCliente = daoCliente.registrarCliente(tCliente);
			transaction.commit();
		}
		else if(clienteLeido != null && !clienteLeido.isActivo()){
			clienteLeido.setActivo(true);
			daoCliente.modificarCliente(clienteLeido);
			idCliente = clienteLeido.getId();
			transaction.commit();
		}
		else{
			transaction.rollback();
		}
		
		return idCliente;
	}


	public void modificarCliente(TCliente tCliente) throws Exception{
		
		Transaction transaction = TransactionManager.getInstancia().getTransaccion();
		transaction.start(); 
		
		DAOCliente daoCliente =  DAOFactoria.getInstancia().generarDAOCliente();
		TCliente clienteLeido = daoCliente.buscarClientePorDNI(tCliente.getDni()); //mirar si el nombre del metodo esta OK
		
		if(clienteLeido != null && clienteLeido.isActivo()){
			tCliente.setActivo(true);
			daoCliente.modificarCliente(tCliente);
			transaction.commit();
		}else{
			transaction.rollback();
		}
		
	}

	public int borrarCliente(int idCliente) throws Exception{
		int resp = 0;
		
		if (idCliente < 1) throw new IllegalArgumentException("ID incorrecto.");
		
		Transaction transaction = TransactionManager.getInstancia().getTransaccion();
		transaction.start(); 
		
		DAOCliente daoCliente =  DAOFactoria.getInstancia().generarDAOCliente();
		TCliente clienteLeido = daoCliente.buscarClientePorId(idCliente); 
		
		if(clienteLeido != null && clienteLeido.isActivo()){
			resp = daoCliente.borrarCliente(idCliente);
			transaction.commit();
		} else {
			transaction.rollback();
		}
		
		return resp;
	}

	public List<TCliente> mostrarListaClientes() throws Exception{
		List<TCliente> listaClientes = null;
		
		Transaction transaction = TransactionManager.getInstancia().getTransaccion();
		transaction.start(); 
		
		DAOCliente daoCliente =  DAOFactoria.getInstancia().generarDAOCliente();
		listaClientes = daoCliente.mostrarListaClientes(); 
		
		if(listaClientes != null){
			transaction.commit();
		}
		else{
			transaction.rollback();
		}
		
		
		return listaClientes;
	}

	public TCliente buscarClientePorId(int idCliente) throws Exception{
		if (idCliente < 1) throw new IllegalArgumentException("ID incorrecto.");		

		TCliente respuesta = null;
		
		Transaction transaction = TransactionManager.getInstancia().getTransaccion();
		transaction.start(); 
		
		DAOCliente daoCliente =  DAOFactoria.getInstancia().generarDAOCliente();
		respuesta = daoCliente.buscarClientePorId(idCliente); 
		
		if (respuesta != null && respuesta.isActivo()) {
			transaction.commit();
		} else {
			respuesta = null;
			transaction.rollback();
		}
		
		return respuesta;
		
	}
	
	public List<TCliente> listarClientesPorTipo(boolean tipo) throws Exception{
		List<TCliente> listaClientes = null;
		
		Transaction transaction = TransactionManager.getInstancia().getTransaccion();
		transaction.start(); 
		
		DAOCliente daoCliente =  DAOFactoria.getInstancia().generarDAOCliente();
		listaClientes = daoCliente.listarClientesPorTipo(tipo);
		
		if(listaClientes != null){
			transaction.commit();
		}
		 else{
			transaction.rollback();
		}
		
		
		return listaClientes;
	}
}