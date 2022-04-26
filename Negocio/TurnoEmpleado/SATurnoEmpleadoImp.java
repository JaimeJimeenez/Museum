package Negocio.TurnoEmpleado;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.LockModeType;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import Negocio.SAFactoria.SAFactoria;
import Negocio.Empleado.*;
import Negocio.EntityManagerFactory.EntityManagerFact;

public class SATurnoEmpleadoImp implements SATurnoEmpleado{

	@Override
	public int registrarTurno(TTurnoEmpleado tTurnoEmpleado) {
		int idTurno = -1;
		
		
		EntityManager em = EntityManagerFact.getInstancia().createEntityManager();
		em.getTransaction().begin();
		
		TurnoEmpleado turnoLeido = em.find(TurnoEmpleado.class, tTurnoEmpleado.getId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		
		if(turnoLeido == null){
			TurnoEmpleado turno = new TurnoEmpleado();
			
			turno.setHoraEntrada(tTurnoEmpleado.getHoraEntrada());
			turno.setHoraSalida(tTurnoEmpleado.getHoraSalida());
			turno.setActivo(true);
			
			em.persist(turno);
			em.getTransaction().commit();
			idTurno = turno.getId();
			
		} else {
			if (!turnoLeido.isActivo()) {
				turnoLeido.setActivo(true);
				em.getTransaction().commit();
				idTurno = turnoLeido.getId();
			} else
				em.getTransaction().rollback();
		}
		
		
		em.close();
		
		return idTurno;
	}

	@Override
	public int modificarTurno(TTurnoEmpleado tTurnoEmpleado) {
		int id = -1;
		
		EntityManager em = EntityManagerFact.getInstancia().createEntityManager();
		em.getTransaction().begin();
		
		TurnoEmpleado turnoLeido = em.find(TurnoEmpleado.class, tTurnoEmpleado.getId());
		
		if(turnoLeido != null && turnoLeido.isActivo()){
			
			TypedQuery<TurnoEmpleado> turnoLecturaNombre = em.createNamedQuery("negocio.turnoEmpleado.TurnoEmpleado.buscarPorHoras", TurnoEmpleado.class);
			turnoLecturaNombre.setParameter("hora_entrada", tTurnoEmpleado.getHoraEntrada());
			turnoLecturaNombre.setParameter("hora_salida", tTurnoEmpleado.getHoraSalida());
			
			if(turnoLecturaNombre.getResultList().size() == 0){
				turnoLeido.setHoraEntrada(tTurnoEmpleado.getHoraEntrada());
				turnoLeido.setHoraSalida(tTurnoEmpleado.getHoraSalida());
				id = turnoLeido.getId();
				em.getTransaction().commit();
			}else{
				em.getTransaction().rollback();
				throw new IllegalArgumentException("Ya existe un turno con la misma hora de entrada y salida");
			}
		}else
			em.getTransaction().rollback();
		
		em.close();
		
		return id;
	}

	@Override
	public int borrarTurno(int id) {
		int resId = -1;
		
		EntityManager em = EntityManagerFact.getInstancia().createEntityManager();
		em.getTransaction().begin();
		
		TurnoEmpleado turnoLeido = em.find(TurnoEmpleado.class, id);
		SAEmpleado empleado = SAFactoria.getInstancia().generarSAEmpleado();
		
		if (turnoLeido != null && turnoLeido.isActivo()) {
			List<TEmpleado> listaEmpleados = empleado.leerPorIdDeTurnoEmpleado(id);
			if(listaEmpleados.isEmpty()){
				turnoLeido.setActivo(false);
				em.getTransaction().commit();
				resId = turnoLeido.getId();
			}
			else {
				em.getTransaction().rollback();
				throw new IllegalArgumentException("El turno tiene empleados activos asociados");
			}		
		} 
		else {
			em.getTransaction().rollback();
		}
		em.close();
		
		return resId;
	}

	@Override
	public TTurnoEmpleado buscarTurnoPorID(int id) {
		
		TTurnoEmpleado res = null;
		
		EntityManager em = EntityManagerFact.getInstancia().createEntityManager();
		em.getTransaction().begin();
		
		TurnoEmpleado turnoLeido = em.find(TurnoEmpleado.class, id);
		
		if(turnoLeido != null && turnoLeido.isActivo()){
			res = new TTurnoEmpleado();
			res.setId(turnoLeido.getId());
			res.setHoraEntrada(turnoLeido.getHoraEntrada());
			res.setHoraSalida(turnoLeido.getHoraSalida());
			res.setActivo(turnoLeido.isActivo());
			em.getTransaction().commit();
			
		}else
			em.getTransaction().rollback();
		
		return res;
	}

	@Override
	public List<TTurnoEmpleado> listarTurnos() {
		 
		List<TurnoEmpleado> listaEntidad = null;
		List<TTurnoEmpleado>  listaTransfer = new ArrayList<TTurnoEmpleado>();
		
		EntityManager em = EntityManagerFact.getInstancia().createEntityManager();
		em.getTransaction().begin();
		 
		TypedQuery<TurnoEmpleado> query = em.createNamedQuery("negocio.turnoEmpleado.TurnoEmpleado.listarTurnos", TurnoEmpleado.class);
		listaEntidad = query.getResultList();
		
		if(listaEntidad != null){
			for(TurnoEmpleado t: listaEntidad){
				if(t.isActivo()){
					TTurnoEmpleado turno = new TTurnoEmpleado();
					turno.setId(t.getId());
					turno.setHoraEntrada(t.getHoraEntrada());
					turno.setHoraSalida(t.getHoraSalida());
					turno.setActivo(t.isActivo());
					listaTransfer.add(turno);
				}
			}
			em.getTransaction().commit();
		}
		else
			em.getTransaction().rollback();
		
		em.close();
		 
		return listaTransfer;
	}

	@Override
	public double calculoNominaDeTurno(int id) {
		double sumSueldo = 0;
		
		EntityManager em = EntityManagerFact.getInstancia().createEntityManager();
		em.getTransaction().begin();
		
		TurnoEmpleado turnoLeido = em.find(TurnoEmpleado.class, id);
		
		if (turnoLeido != null && turnoLeido.isActivo()) {
			for (Empleado e : turnoLeido.getEmpleados()) {
				if (e.isActivo()) {
					sumSueldo += e.getNomina();
				}
			}
			em.getTransaction().commit();
		} else {
			em.getTransaction().rollback();
			sumSueldo = -1;
		}
		em.close();
		
		return sumSueldo;
		
	}


}
