package Negocio.Descuento;

import java.util.ArrayList;
import java.util.List;

import Integracion.DAOFactoria.DAOFactoria;
import Integracion.Descuento.DAODescuento;
import Integracion.Transaction.Transaction;
import Integracion.Transaction.TransactionManager;

public class SADescuentoImp implements SADescuento {

	public int añadirDescuento(TDescuento tDescuento) throws Exception {
		int idDescuento = 0;
		
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();
		
		DAODescuento daoDescuento = DAOFactoria.getInstancia().generarDAODescuento();
		TDescuento leido = daoDescuento.buscarDescuentoPorPorcentaje(tDescuento.getPorcentaje());
		
		if (leido == null) {
			idDescuento = daoDescuento.añadirDescuento(tDescuento);
			transaction.commit();
		} else if (leido != null && !leido.isActivo()) {
			tDescuento.setActivo(true);
			tDescuento.setId(leido.getId());
			daoDescuento.modificarDescuento(tDescuento);
			idDescuento = leido.getId();
			transaction.commit();
		}
		else
			transaction.rollback();
		
		return idDescuento;
	}

	public int modificarDescuento(TDescuento tDescuento) throws Exception {
		int idDescuento = 0;
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();
		
		DAODescuento daoDescuento = DAOFactoria.getInstancia().generarDAODescuento();
		TDescuento leido = daoDescuento.buscarDescuentoporId(tDescuento.getId());
		
		if (leido != null && leido.isActivo()) {
			tDescuento.setActivo(true);
			idDescuento = daoDescuento.modificarDescuento(tDescuento);
			transaction.commit();
		}
		else
			transaction.rollback();
		
		return idDescuento;
	}

	public int borrarDescuento(int idDescuento) throws Exception {
		int res = 0;
		
		if (idDescuento < 1) throw new IllegalArgumentException("ID incorrecto");
		
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();
		
		DAODescuento daoDescuento = DAOFactoria.getInstancia().generarDAODescuento();
		TDescuento leido = daoDescuento.buscarDescuentoporId(idDescuento);
		
		if (leido != null && leido.isActivo()) {
			res = daoDescuento.borrarDescuento(idDescuento);
			transaction.commit();
		}
		else 
			transaction.rollback();
		
		return res;
	}

	public List<TDescuento> mostrarListaDescuentos() throws Exception {
		List<TDescuento> listaDescuento = new ArrayList<TDescuento>();
		
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();
		
		DAODescuento daoDescuento = DAOFactoria.getInstancia().generarDAODescuento();
		listaDescuento = daoDescuento.mostrarListaDescuentos();
		
		if (listaDescuento != null) 
			transaction.commit();
		else
			transaction.rollback();
		
		return listaDescuento;
	}

	public TDescuento buscarDescuentoporId(int idDescuento) throws Exception {
		if (idDescuento < 1) throw new IllegalArgumentException("ID incorrecto");
		
		TDescuento tDescuento = null;
		
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();
		
		DAODescuento daoDescuento = DAOFactoria.getInstancia().generarDAODescuento();
		tDescuento = daoDescuento.buscarDescuentoporId(idDescuento);
		
		if (tDescuento != null && tDescuento.isActivo())
			transaction.commit();
		else {
			tDescuento = null;
			transaction.rollback();
		}
		
		return tDescuento;
	}
}