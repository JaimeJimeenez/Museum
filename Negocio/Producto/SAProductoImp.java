package Negocio.Producto;

import java.util.List;

import Integracion.DAOFactoria.DAOFactoria;
import Integracion.Fabricante.DAOFabricante;
import Integracion.Producto.DAOProducto;
import Integracion.Query.QueryFactoria;
import Integracion.Transaction.Transaction;
import Integracion.Transaction.TransactionManager;
import Negocio.Fabricante.TFabricante;

public class SAProductoImp implements SAProducto {
	
	public int registrarProducto(TProducto tProducto) throws Exception  {
		int idProducto = 0;
		
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();  
		
		DAOProducto daoProducto =  DAOFactoria.getInstancia().generarDAOProducto();
		TProducto productoLeido = daoProducto.buscarProductoPorNombre(tProducto.getNombre());
		DAOFabricante daoFabricante = DAOFactoria.getInstancia().generarDAOFabricante();
		
		if(productoLeido == null){
			if(daoFabricante.buscarFabricantePorID(tProducto.getIdFabricante()) != null){
				idProducto = daoProducto.registrarProducto(tProducto);
				transaction.commit();
			}
			else{
				transaction.rollback();
			}

		}
		else if(productoLeido != null && !productoLeido.isActivo()){
			tProducto.setActivo(true);
			tProducto.setId(productoLeido.getId());
			daoProducto.modificarProducto(tProducto);
			idProducto = productoLeido.getId();
			transaction.commit();
		}
		else{
			transaction.rollback();
		}
		
		return idProducto;
	}

	public int modificarProducto(TProducto tProducto) throws Exception {
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();
		
		DAOProducto daoProducto = DAOFactoria.getInstancia().generarDAOProducto();
		TProducto productoLeido = daoProducto.buscarProductoPorId(tProducto.getId());
		
		if(productoLeido != null && productoLeido.isActivo()){
			tProducto.setActivo(true);
			daoProducto.modificarProducto(tProducto);
			transaction.commit();
		}
		else{
			transaction.rollback();
		}
		
		return tProducto.getId();
	}

	public int borrarProducto(int idProducto) throws Exception{
		int resp = 0;
		
		if(idProducto < 1) throw new IllegalArgumentException("ID incorrecto.");
		
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();
		
		DAOProducto daoProducto = DAOFactoria.getInstancia().generarDAOProducto();
		TProducto productoLeido = daoProducto.buscarProductoPorId(idProducto);
		
		if(productoLeido != null && productoLeido.isActivo()){
			resp = daoProducto.borrarProducto(idProducto);
			transaction.commit();
		}
		else{
			transaction.rollback();
		}
		
		return resp;
	}

	public List<TProducto> mostrarListaProducto() throws Exception {
		List<TProducto> listaProductos = null;
		
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();
		
		DAOProducto daoProducto = DAOFactoria.getInstancia().generarDAOProducto();
		listaProductos = daoProducto.mostrarListaProducto();
		
		if(listaProductos != null){
			transaction.commit();
		}
		else{
			transaction.rollback();
		}
		
		return listaProductos;
	}

	public TProducto buscarProductoPorId(int idProducto) throws Exception{
		if(idProducto < 1) throw new IllegalArgumentException("ID incorrecto.");
		TProducto respuesta = null;
		
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();
		
		DAOProducto daoProducto = DAOFactoria.getInstancia().generarDAOProducto();
		respuesta = daoProducto.buscarProductoPorId(idProducto);
		
		if(respuesta != null && respuesta.isActivo()){
			transaction.commit();
		}
		else{
			transaction.rollback();
		}
		
		return respuesta;
	}

	@SuppressWarnings("unchecked")
	public List<TProducto> mostrarProductoPorNombreFabricante(String nombreFabr) throws Exception {
		List<TProducto> listaProductos = null;

		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();

		DAOFabricante daoFabricante = DAOFactoria.getInstancia().generarDAOFabricante();
		TFabricante fabricante = daoFabricante.buscarFabricantePorNombre(nombreFabr);

		if(fabricante != null){

			listaProductos = (List<TProducto>) QueryFactoria.getInstancia().nuevaQuery("mostrarProductoPorNombreFabricante").execute(nombreFabr);

			if(listaProductos != null){
				transaction.commit();
			}
			else{
				transaction.rollback();
			}
		}
		else{
			transaction.rollback();
		}

		return listaProductos;
	}

	public List<TProducto> leerPorIdFabricante(int idFabricante) throws Exception {
		List<TProducto> listaProductos = null;
		
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();
		
		DAOFabricante daoFabricante = DAOFactoria.getInstancia().generarDAOFabricante();
		TFabricante fabricante = daoFabricante.buscarFabricantePorID(idFabricante);
		
		if(fabricante != null){
		
			DAOProducto daoProducto = DAOFactoria.getInstancia().generarDAOProducto();
			listaProductos = daoProducto.leerPorIdFabricante(idFabricante);
		
			if(listaProductos != null){
				transaction.commit();
			}
			else{
				transaction.rollback();
			}
		}
		else{
			transaction.rollback();
		}
		
		return listaProductos;
	}
}