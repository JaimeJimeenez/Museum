package Negocio.Fabricante;

import Negocio.Producto.TProducto;

import java.util.List;

import Integracion.DAOFactoria.DAOFactoria;
import Integracion.Fabricante.DAOFabricante;
import Integracion.Producto.DAOProducto;
import Integracion.Transaction.Transaction;
import Integracion.Transaction.TransactionManager;

public class SAFabricanteImp implements SAFabricante {

	public int registrarFabricante(TFabricante tFabricante) throws Exception {
		int fabricante = 0;

		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();

		DAOFabricante daoFabricante = DAOFactoria.getInstancia().generarDAOFabricante();
		TFabricante fabricanteLeido = daoFabricante.buscarFabricantePorNombre(tFabricante.getNombre());

		if (fabricanteLeido == null) {
			fabricante = daoFabricante.registrarFabricante(tFabricante);
			transaction.commit();
		} else if (fabricanteLeido != null && !fabricanteLeido.isActivo()) {
			tFabricante.setActivo(true);
			tFabricante.setId(fabricanteLeido.getId());
			daoFabricante.modificarFabricante(tFabricante);
			fabricante = fabricanteLeido.getId();
			transaction.commit();
		} else {
			transaction.rollback();
		}

		return fabricante;
	}

	public int modificarFabricante(TFabricante tFabricante) throws Exception {
		int fabricante = 0;
		
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();

		DAOFabricante daoFabricante = DAOFactoria.getInstancia().generarDAOFabricante();
		TFabricante fabricanteLeido = daoFabricante.buscarFabricantePorID(tFabricante.getId());

		if (fabricanteLeido != null && fabricanteLeido.isActivo()) {
			fabricanteLeido.setActivo(true);
			fabricante = daoFabricante.modificarFabricante(tFabricante);
			transaction.commit();
		} else {
			transaction.rollback();
		}

		return fabricante;
	}

	public int borrarFabricante(Integer IDFabricante) throws Exception {
		int fabricante = 0;
		
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();

		DAOFabricante daoFabricante = DAOFactoria.getInstancia().generarDAOFabricante();
		DAOProducto daoProducto = DAOFactoria.getInstancia().generarDAOProducto();
		TFabricante fabricanteLeido = daoFabricante.buscarFabricantePorID(IDFabricante);

		if (fabricanteLeido != null && fabricanteLeido.isActivo()) {
			List<TProducto> listaProductos = daoProducto.leerPorIdFabricante(IDFabricante);
			if(listaProductos.isEmpty()){
				fabricante = daoFabricante.borrarFabricante(IDFabricante);
				transaction.commit();
			}
			else{
				transaction.rollback();
				throw new IllegalArgumentException("No se puede borrar el fabricante porque tiene poductos asociados");
			}
		} else {
			transaction.rollback();
		}

		return fabricante;
	}

	public List<TFabricante> mostrarListaFabricantes() throws Exception{
		List<TFabricante> fabricante = null;
		
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();

		DAOFabricante daoFabricante = DAOFactoria.getInstancia().generarDAOFabricante();
		fabricante = daoFabricante.mostrarListaFabricantes();

		if (fabricante != null) {
			transaction.commit();
		} else {
			transaction.rollback();
		}

		return fabricante;
	}

	public TFabricante buscarFabricantePorID(Integer IDFabricante) throws Exception{
		TFabricante fabricante = null;
		
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();

		DAOFabricante daoFabricante = DAOFactoria.getInstancia().generarDAOFabricante();
		fabricante = daoFabricante.buscarFabricantePorID(IDFabricante);

		if (fabricante != null && fabricante.isActivo()) {
			transaction.commit();
		} else {
			fabricante = null;
			transaction.rollback();
		}

		return fabricante;
	}
}