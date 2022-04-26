package Negocio.Empleado;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.TypedQuery;

import negocio.entityManagerFactory.EntityManagerFact;
import negocio.entrada.Entrada;
import negocio.turnoEmpleado.TurnoEmpleado;

public class SAEmpleadoImp implements SAEmpleado{

	@Override
	public int registrarEmpleado(TEmpleado tEmpleado) {
		
		int idEmpleado = -1;
		
		EntityManager em = EntityManagerFact.getInstancia().createEntityManager();
		em.getTransaction().begin();
		
		TurnoEmpleado turnoEmpleado = em.find(TurnoEmpleado.class, tEmpleado.getIdTurnoEmpleado(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		TypedQuery<Empleado> empleadoLectura = em.createNamedQuery("negocio.empleado.Empleado.buscarPorDni", Empleado.class);
		empleadoLectura.setParameter("dni", tEmpleado.getDni());
		if(turnoEmpleado != null && turnoEmpleado.isActivo()){
			Empleado empleado;
			if(empleadoLectura.getResultList().size() == 0){
				if(tEmpleado instanceof TJornadaParcial){
					empleado = new JornadaParcial();
					((JornadaParcial) empleado).setSueldoHoras(((TJornadaParcial)tEmpleado).getSueldoHoras());
					((JornadaParcial) empleado).setHoras(((TJornadaParcial)tEmpleado).getHoras());
					empleado.setParcial(true);
				} else {
					empleado = new JornadaCompleta();
					((JornadaCompleta) empleado).setComplementos(((TJornadaCompleta)tEmpleado).getComplementos());
					((JornadaCompleta) empleado).setSueldo(((TJornadaCompleta)tEmpleado).getSueldo());
					empleado.setParcial(false);
				}
				empleado.setNomina(empleado.calcularNomina());
				empleado.setDni(tEmpleado.getDni());
				empleado.setId(tEmpleado.getId());
				empleado.setNombre(tEmpleado.getNombre());
				empleado.setTurnoEmpleado(turnoEmpleado);
				empleado.setActivo(true);
				em.persist(empleado);
				em.refresh(turnoEmpleado);
				em.getTransaction().commit();
				idEmpleado = empleado.getId();
				
			}
			else{
				empleado =  empleadoLectura.getSingleResult();
				if (!empleado.isActivo()) {
					empleado.setActivo(true);
					em.refresh(turnoEmpleado);
					em.getTransaction().commit();
				} else
					em.getTransaction().rollback();
				idEmpleado = empleado.getId();
			}

		
		}
		else{
			em.getTransaction().rollback();
			throw new IllegalArgumentException("No existe el turno del empleado");
		}		
				
		em.close();
		return idEmpleado;
				
	}

	@Override
	public int modificarEmpleado(TEmpleado empleado) {
		
		int idEmpleado = -1;

		// Iniciamos la transaccion
		EntityManager em = EntityManagerFact.getInstancia().createEntityManager();
		em.getTransaction().begin();

		// Buscamos el empleado
		Empleado empleadoLectura = em.find(Empleado.class, empleado.getId());
		TurnoEmpleado turnoEmpleadoLectura = em.find(TurnoEmpleado.class, empleado.getIdTurnoEmpleado());

		// si existe en la base de datos y esta activo
		if (empleadoLectura != null && empleadoLectura.isActivo() && turnoEmpleadoLectura != null && turnoEmpleadoLectura.isActivo()) {
			
			if (empleado instanceof TJornadaParcial) {
				((JornadaParcial) empleadoLectura).setHoras(empleado.getHoras());
				((JornadaParcial) empleadoLectura).setSueldoHoras(empleado.getSueldoHoras());
			}

			else {

				((JornadaCompleta) empleadoLectura).setComplementos(empleado.getComplementos());
				((JornadaCompleta)empleadoLectura).setSueldo(empleado.getSueldo());
			}

			empleadoLectura.setNomina(empleadoLectura.calcularNomina());
			empleadoLectura.setActivo(true);
			//empleadoLectura.setDni(empleado.getDni());
			empleadoLectura.setNombre(empleado.getNombre());
			em.getTransaction().commit();
			idEmpleado = empleadoLectura.getId();
	
		}
		else{
			em.getTransaction().rollback();
			throw new IllegalArgumentException("No existen empleados");
		}

		em.close();
		return idEmpleado;
	}
	

	@Override
	public int borrarEmpleado(int id) {
		
		int idEmpleado = -1;
		// Iniciamos la transaccion
		EntityManager em = EntityManagerFact.getInstancia().createEntityManager();
		em.getTransaction().begin();

		// Buscamos el empleado por id
		Empleado empleadoLectura= em.find(Empleado.class, id);

		// Si existe y esta activo
		if (empleadoLectura != null && empleadoLectura.isActivo()) {
			empleadoLectura.setActivo(false);
			em.getTransaction().commit();
			idEmpleado = empleadoLectura.getId();
		} else
			em.getTransaction().rollback();

		em.close();
		return idEmpleado;
	}

	@Override
	public List<TEmpleado> mostrarListaEmpleados() {
		
		List<TEmpleado> res = new ArrayList<TEmpleado>();
		
		EntityManager em = EntityManagerFact.getInstancia().createEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<Empleado> query = em.createNamedQuery("negocio.empleado.Empleado.mostrarListaEmpleados", Empleado.class);
		List<Empleado> listaLeida = query.getResultList();
		
		if(listaLeida.size() > 0){
			TEmpleado empleado;
			for (Empleado e : listaLeida){
				if (e instanceof JornadaParcial) {
					empleado = new TJornadaParcial();
					empleado.setHoras(((JornadaParcial) e).getHoras());
					empleado.setSueldoHoras(((JornadaParcial) e).getSueldoHoras());
					empleado.setParcial(true);
				}
				else {
					empleado = new TJornadaCompleta();
					empleado.setComplementos(((JornadaCompleta) e).getComplementos());
					empleado.setSueldo(((JornadaCompleta) e).getSueldo());
					empleado.setParcial(false);
				}
				empleado.setId(e.getId());
				empleado.setDni(e.getDni());
				empleado.setNombre(e.getNombre());
				empleado.setIdTurnoEmpleado(e.getTurnoEmpleado().getId());
				empleado.setActivo(true);
				res.add(empleado);
			}
			em.getTransaction().commit();
		}
		else {
			em.getTransaction().rollback();
		}
		em.close();
		return res;
	}

	@Override
	public TEmpleado buscarEmpleadoPorId(int id) {
		
		TEmpleado res = null;

		// Iniciamos la transaccion
		EntityManager em = EntityManagerFact.getInstancia().createEntityManager();
		em.getTransaction().begin();

		// Creamos y ejecutamos la query
		Empleado e = em.find(Empleado.class, id);

		if (e != null && e.isActivo()) {
			if(e instanceof JornadaParcial){
				res = new TJornadaParcial();
				res.setHoras(((JornadaParcial) e).getHoras());
				res.setSueldoHoras(((JornadaParcial) e).getSueldoHoras());
				res.setParcial(true);
			}
			else{
				res = new TJornadaCompleta();
				res.setSueldo(((JornadaCompleta) e).getSueldo());
				res.setComplementos(((JornadaCompleta) e).getComplementos());
				res.setParcial(false);
			}
			res.setId(e.getId());
			res.setDni(e.getDni());
			res.setNombre(e.getNombre());
			res.setIdTurnoEmpleado(e.getTurnoEmpleado().getId());
			res.setActivo(true);
			em.getTransaction().commit();
		} else
			em.getTransaction().rollback();

		em.close();
		return res;
	}

	@Override
	public List<TEmpleado> listarPorJornada(boolean isParcial) {
		List<TEmpleado> res = new ArrayList<TEmpleado>();
		
		EntityManager em = EntityManagerFact.getInstancia().createEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<Empleado> query = em.createNamedQuery("negocio.empleado.Empleado.listarPorJornada", Empleado.class);
		query.setParameter("jornada", isParcial);
		List<Empleado> listaLeida = query.getResultList();
		
		if(listaLeida.size() > 0){
			TEmpleado empleado;
			for (Empleado e : listaLeida){
				if (e instanceof JornadaParcial) {
					empleado = new TJornadaParcial();
					empleado.setHoras(((JornadaParcial) e).getHoras());
					empleado.setSueldoHoras(((JornadaParcial) e).getSueldoHoras());
					empleado.setParcial(true);
				}
	
				else {
					empleado = new TJornadaCompleta();
					empleado.setComplementos(((JornadaCompleta) e).getComplementos());
					empleado.setSueldo(((JornadaCompleta) e).getSueldo());
					empleado.setParcial(false);
				}
				empleado.setId(e.getId());
				empleado.setDni(e.getDni());
				empleado.setNombre(e.getNombre());
				empleado.setIdTurnoEmpleado(e.getTurnoEmpleado().getId());
				empleado.setActivo(true);
				res.add(empleado);
			}
			em.getTransaction().commit();
		}
		else
			em.getTransaction().rollback();
			em.close();
		
		return res;

	}

	@Override
	public List<TEmpleado> leerPorIdDeTurnoEmpleado(int idTurnoEmpleado) {
		
		List<TEmpleado> res = new ArrayList<TEmpleado>();
		
		EntityManager em = EntityManagerFact.getInstancia().createEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<Empleado> query = em.createNamedQuery("negocio.empleado.Empleado.leerPorIdDeTurnoEmpleado", Empleado.class);
		query.setParameter("id", idTurnoEmpleado);
		List<Empleado> listaLeida = query.getResultList();
		
		if(listaLeida.size() > 0){
			TEmpleado empleado;
			for (Empleado e : listaLeida){
				if(e.isActivo()){
					if (e instanceof JornadaParcial) {
						empleado = new TJornadaParcial();
						empleado.setHoras(((JornadaParcial) e).getHoras());
						empleado.setSueldoHoras(((JornadaParcial) e).getSueldoHoras());
						empleado.setParcial(true);
					}
		
					else {
						empleado = new TJornadaCompleta();
						empleado.setComplementos(((JornadaCompleta) e).getComplementos());
						empleado.setSueldo(((JornadaCompleta) e).getSueldo());
						empleado.setParcial(false);
					}
					empleado.setId(e.getId());
					empleado.setDni(e.getDni());
					empleado.setNombre(e.getNombre());
					empleado.setIdTurnoEmpleado(e.getTurnoEmpleado().getId());
					empleado.setActivo(true);
					res.add(empleado);
				}
			}
			em.getTransaction().commit();
		}
		else{
			em.getTransaction().rollback();
		}
		em.close();
		return res;

	}

	@Override
	public double mostrarNominaEmpleado(int idEmpleado) {
		double nomina = 0;
		
		EntityManager em = EntityManagerFact.getInstancia().createEntityManager();
		em.getTransaction().begin();
		
		Empleado empleado = em.find(Empleado.class, idEmpleado);
		
		if(empleado != null && empleado.isActivo()){
			nomina = empleado.getNomina();
			em.getTransaction().commit();
		}
		else{
			em.getTransaction().rollback();
		}
		em.close();
		
		return nomina;
	}

}
