package Negocio.Entrada;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;
import jakarta.persistence.TypedQuery;
import Negocio.EntityManagerFactory.EntityManagerFact;

public class SAEntradaImp implements SAEntrada {

	@Override
	public int registrarEntrada(TEntrada tEntrada) {
		int idEntrada = -1;
		EntityManager em = EntityManagerFact.getInstancia().createEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<Entrada> entradaLectura = em.createNamedQuery("negocio.entrada.Entrada.buscarPorId", Entrada.class);
		entradaLectura.setParameter("id", tEntrada.getId());
		if (entradaLectura.getResultList().size() == 0) {
			Entrada entrada = new Entrada();
			
			entrada.setActivo(true);
			entrada.setFecha(tEntrada.getFecha());
			entrada.setNumeroEntradas(tEntrada.getNumeroEntradas());
			entrada.setObra(tEntrada.getObra());
			entrada.setPrecio(tEntrada.getPrecio());
			entrada.setSala(tEntrada.getSala());
			
			em.persist(entrada);
			em.getTransaction().commit();
			idEntrada = entrada.getId();
		} else {
			Entrada entrada = entradaLectura.getSingleResult();
			
			if (!entrada.isActivo()) {
				entrada.setActivo(true);
				em.getTransaction().commit();
			}
			else em.getTransaction().rollback();
			
			idEntrada = entrada.getId();
		}
		em.close();
		
		return idEntrada;
	}

	@Override
	public int borrarEntrada(int idEntrada) {
		int resId = -1;
		
		EntityManager em = EntityManagerFact.getInstancia().createEntityManager();
		em.getTransaction().begin();
		Entrada entradaLectura = em.find(Entrada.class, idEntrada);

		if (entradaLectura != null && entradaLectura.isActivo()) {
			entradaLectura.setActivo(false);
			em.getTransaction().commit();
			resId = idEntrada;
		} 
		else em.getTransaction().rollback();
		em.close();

		return resId;
	}
	
	@Override
	public int modificarEntrada(TEntrada tEntrada) {
		int id = -1;
		EntityManager em = EntityManagerFact.getInstancia().createEntityManager();
		
		em.getTransaction().begin();
		
		Entrada entradaLectura = em.find(Entrada.class, tEntrada.getId());
		if (entradaLectura != null && entradaLectura.isActivo()) {
			entradaLectura.setFecha(tEntrada.getFecha());
			entradaLectura.setNumeroEntradas(tEntrada.getNumeroEntradas());
			entradaLectura.setObra(tEntrada.getObra());
			entradaLectura.setPrecio(tEntrada.getPrecio());
			entradaLectura.setSala(tEntrada.getSala());
			em.persist(entradaLectura);
			em.getTransaction().commit();
			id = entradaLectura.getId();
		}
		else em.getTransaction().rollback();
		em.close();
		
		return id;
	}

	@Override
	public List<TEntrada> listarEntradas() {
		List<Entrada> listaEntidad = null;
		List<TEntrada> listaTransfer = new ArrayList<TEntrada>();
		EntityManager em = EntityManagerFact.getInstancia().createEntityManager();
		
		em.getTransaction().begin();
		
		TypedQuery<Entrada> query = em.createNamedQuery("negocio.entrada.Entrada.listarEntradas", Entrada.class);
		listaEntidad = query.getResultList();

		if (listaEntidad != null) {
			for (Entrada e : listaEntidad)
				if (e.isActivo()) {
					TEntrada entrada = new TEntrada();
					entrada.setId(e.getId());
					entrada.setActivo(true);
					entrada.setFecha(e.getFecha());
					entrada.setNumeroEntradas(e.getNumeroEntradas());
					entrada.setObra(e.getObra());
					entrada.setPrecio(e.getPrecio());
					entrada.setSala(e.getSala());
					listaTransfer.add(entrada);
				}
			em.getTransaction().commit();
		}
		else em.getTransaction().rollback();

		em.close();
		return listaTransfer;
	}

	@Override
	public TEntrada buscarEntrada(int idEntrada) {
		TEntrada tEntrada = null;
		EntityManager em = EntityManagerFact.getInstancia().createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		Entrada entrada = em.find(Entrada.class, idEntrada);
		transaction.commit();
		
		if (entrada != null && entrada.isActivo()) {
			tEntrada = new TEntrada();
			tEntrada.setId(entrada.getId());
			tEntrada.setFecha(entrada.getFecha());
			tEntrada.setNumeroEntradas(entrada.getNumeroEntradas());
			tEntrada.setActivo(entrada.isActivo());
			tEntrada.setObra(entrada.getObra());
			tEntrada.setPrecio(entrada.getPrecio());
			tEntrada.setSala(entrada.getSala());
		}
		
		return tEntrada;
	}

}
