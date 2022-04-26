package Negocio.FacturaMuseo;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.TypedQuery;
import Negocio.Empleado.Empleado;
import Negocio.EntityManagerFactory.EntityManagerFact;
import Negocio.Entrada.Entrada;
import Negocio.TurnoEmpleado.TurnoEmpleado;

public class SAFacturaMuseoImp implements SAFacturaMuseo {

	@Override
	public TCarritoMuseo abrirFactura() {
		return new TCarritoMuseo();
	}

	@Override
	public TCarritoMuseo añadirEntrada(TCarritoMuseo carrito) {
		int idEntrada =  carrito.getIdEntrada();
		int cantidad = carrito.getCantidad();
		LinkedList<TLineaFacturaMuseo> lineasFactura = carrito.getLineasFacturaMuseo();
		TLineaFacturaMuseo lineaFactura = getLinea(lineasFactura, idEntrada);
		
		if (lineaFactura != null)
			lineaFactura.setCantidad(lineaFactura.getCantidad() + cantidad);
		else
			lineasFactura.add(new TLineaFacturaMuseo(idEntrada, cantidad));
		
		
		return carrito;
	}

	private TLineaFacturaMuseo getLinea(LinkedList<TLineaFacturaMuseo> lineasFactura, int idEntrada) {
		TLineaFacturaMuseo lineaFactura = null;
		ListIterator<TLineaFacturaMuseo> iterator = lineasFactura.listIterator();
		
		while (lineaFactura == null && iterator.hasNext()) {
			TLineaFacturaMuseo linea = iterator.next();
			
			if (linea.getIdEntrada() == idEntrada)
				lineaFactura = linea;
		}
		
		return lineaFactura;
	}

	@Override
	public TCarritoMuseo buscarFacturaPorID(Integer id) {
		TCarritoMuseo carrito = null;
		TFacturaMuseo factura = new TFacturaMuseo();
		LinkedList<TLineaFacturaMuseo> lineasFactura = new LinkedList<TLineaFacturaMuseo>();
		double precioTotal = 0;
		
		if (id < 1)
			throw new IllegalArgumentException("ID incorrecto.");
		
		EntityManager em = EntityManagerFact.getInstancia().createEntityManager();
		em.getTransaction().begin();
		
		FacturaMuseo facturaLeida = em.find(FacturaMuseo.class, id);
		if(facturaLeida != null){
			factura.setFecha(facturaLeida.getFecha());
			factura.setId(facturaLeida.getId());
			factura.setPrecioTotal(facturaLeida.getPrecioTotal());
			
			List<LineaFactura> lista = facturaLeida.getLineasFactura();
			for(LineaFactura l : lista){
				TLineaFacturaMuseo lineaTienda = new TLineaFacturaMuseo();
				
				lineaTienda.setCantidad(l.getCantidad());
				lineaTienda.setPrecio(l.getPrecio());
				lineaTienda.setIdEntrada(l.getEntrada().getId());
				lineaTienda.setIdFacturaMuseo(l.getFactura().getId());
				lineasFactura.add(lineaTienda);
				precioTotal += l.getPrecio();
			}
			carrito = new TCarritoMuseo(factura, lineasFactura, precioTotal);
			
			em.getTransaction().commit();
		}
		else
			em.getTransaction().rollback();
		
		em.close();
		return carrito;
	}

	@Override
	public boolean devolverEntrada(TLineaFacturaMuseo linea) {
		boolean valid = false;
		
		EntityManager em = EntityManagerFact.getInstancia().createEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<LineaFactura> query = em.createQuery("SELECT f FROM LineaFactura f WHERE f.idLinea =:id", LineaFactura.class);
		LineaFacturaMuseoID id = new LineaFacturaMuseoID(linea.getIdFacturaMuseo(), linea.getIdEntrada());
		
		query.setParameter("id", id);
		if(query.getResultList().size() > 0){
			LineaFactura lineaF = query.getSingleResult();
			
			em.lock(lineaF, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			
			int nuevaCantidad = lineaF.getCantidad() - linea.getCantidad();
			if (nuevaCantidad > 0) {
				lineaF.setCantidad(nuevaCantidad);
				
				Entrada entradaLectura = em.find(Entrada.class, lineaF.getEntrada().getId());
				lineaF.setPrecio(lineaF.getPrecio() - (linea.getCantidad() * entradaLectura.getPrecio()));
				
				em.persist(lineaF);
				entradaLectura.setNumeroEntradas(entradaLectura.getNumeroEntradas() + linea.getCantidad());
				em.persist(entradaLectura);
			}
			else {
				em.remove(lineaF);
				em.refresh(lineaF.getFactura());
				
				Entrada entrada = em.find(Entrada.class, lineaF.getEntrada().getId());
				entrada.setNumeroEntradas(entrada.getNumeroEntradas() + lineaF.getCantidad());
				em.persist(entrada);
			}
			
			em.getTransaction().commit();
			valid = true;
		}
		else
			em.getTransaction().rollback();
		
		em.close();
		return valid;
	}

	@Override
	public TCarritoMuseo quitarEntrada(TCarritoMuseo carrito) {
		int idEntrada =  carrito.getIdEntrada();
		int cantidad = carrito.getCantidad();
		LinkedList<TLineaFacturaMuseo> lineasFactura = carrito.getLineasFacturaMuseo();
		
		TLineaFacturaMuseo lineaFactura = getLinea(lineasFactura, idEntrada);
		if (lineaFactura != null) {
			int nuevaCantidad = lineaFactura.getCantidad() - cantidad;
			
			if (nuevaCantidad <= 0)
				removeLineaFactura(lineasFactura, idEntrada);
			else
				lineaFactura.setCantidad(nuevaCantidad);
		} 
		else
			throw new NullPointerException("El entrada especificado no existe en la factura.");
		
		return carrito;
	}

	private void removeLineaFactura(LinkedList<TLineaFacturaMuseo> lineasFactura, int idEntrada) {
		ListIterator<TLineaFacturaMuseo> iterator = lineasFactura.listIterator();
		
		while(iterator.hasNext()) 
		    if(iterator.next().getIdEntrada() == idEntrada)
		    	iterator.remove();
		
	}

	@Override
	public LinkedList<TCarritoMuseo> listarFactura() {
		
		LinkedList<TCarritoMuseo> listaFacturas = new LinkedList<TCarritoMuseo>();
		
		EntityManager em = EntityManagerFact.getInstancia().createEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<FacturaMuseo> query = em.createQuery("SELECT f FROM FacturaMuseo f", FacturaMuseo.class);
		List<FacturaMuseo> lista = query.getResultList();
		if (lista.size() > 0){
			for (FacturaMuseo fact: lista){
				TFacturaMuseo factura = new TFacturaMuseo();
				LinkedList<TLineaFacturaMuseo> lineasFactura = new LinkedList<TLineaFacturaMuseo>();	
				double precioTotal = 0;
				
				factura.setFecha(fact.getFecha());
				factura.setId(fact.getId());
				factura.setPrecioTotal(fact.getPrecioTotal());
				
				List<LineaFactura> list = fact.getLineasFactura();
				for(LineaFactura l : list){
					TLineaFacturaMuseo lineaTienda = new TLineaFacturaMuseo();
					
					lineaTienda.setCantidad(l.getCantidad());
					lineaTienda.setPrecio(l.getPrecio());
					lineaTienda.setIdEntrada(l.getEntrada().getId());
					lineaTienda.setIdFacturaMuseo(l.getFactura().getId());
					lineasFactura.add(lineaTienda);
					precioTotal += l.getPrecio();
				}
				
				TCarritoMuseo carrito = new TCarritoMuseo(factura, lineasFactura, precioTotal);
				listaFacturas.add(carrito);
			
			}
			em.getTransaction().commit();
		}
		else
			em.getTransaction().rollback();
		
		em.close();
		return listaFacturas;
	}
	
	private boolean validarEntrada(Entrada entrada, TLineaFacturaMuseo tLinea) {
		return entrada != null && entrada.isActivo() && entrada.getNumeroEntradas() >= tLinea.getCantidad();
	}

	@Override
	public boolean cerrarFactura(TCarritoMuseo carrito) {
		boolean ok = true;
		
		EntityManager em = EntityManagerFact.getInstancia().createEntityManager();
		em.getTransaction().begin();
		
		for (TLineaFacturaMuseo l : carrito.getLineasFacturaMuseo()) {
			//Entrada entrada = em.find(Entrada.class, l.getIdEntrada(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			Entrada entrada = em.find(Entrada.class, l.getIdEntrada());
			if (!validarEntrada(entrada, l)) ok = false;
			if (ok) l.setPrecio(l.getCantidad()*entrada.getPrecio());
		}
		//Empleado empleado = em.find(Empleado.class, carrito.getFacturaMuseo().getIdEmpleado(),LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		Empleado empleado = em.find(Empleado.class, carrito.getFacturaMuseo().getIdEmpleado());
		if(empleado == null) ok = false;
		
		if(ok){
			TFacturaMuseo tFactura = carrito.getFacturaMuseo();
			
			FacturaMuseo factura = new FacturaMuseo();
			List<LineaFactura> listaLineasPersistentes= new ArrayList<LineaFactura>();
			
			factura.setActivo(true);
			factura.setEmpleado(empleado);
			factura.setPrecioTotal(0);
			factura.setFecha(new Date());
			
			em.persist(factura);
			em.flush();
			
			double total = 0;
			for (TLineaFacturaMuseo tLinea : carrito.getLineasFacturaMuseo()){
				Entrada entrada = em.find(Entrada.class, tLinea.getIdEntrada());
				
				entrada.setNumeroEntradas(entrada.getNumeroEntradas()-tLinea.getCantidad());
				em.persist(entrada);
				
				LineaFactura linea = new LineaFactura();
				LineaFacturaMuseoID idLinea = new LineaFacturaMuseoID(factura.getId(), entrada.getId());
				
				linea.setCantidad(tLinea.getCantidad());
				linea.setFactura(factura);
				linea.setEntrada(entrada);
				linea.setPrecio(tLinea.getPrecio());
				total += tLinea.getPrecio();
				linea.setIdLinea(idLinea);
				entrada.addLinea(linea);
				em.persist(linea);
				listaLineasPersistentes.add(linea);
			}
			factura.setPrecioTotal(total);
			factura.setLineasFactura(listaLineasPersistentes);
			
			em.persist(factura);
			em.getTransaction().commit();
		}
		else {
			em.getTransaction().rollback();
			em.close();
			throw new IllegalArgumentException("No existe dicho empleado o una de las entrada no existe o no hay entradas disponibles");
		}
		em.close();
		
		return ok;
	}

	@Override
	public LinkedList<TFacturaMuseo> leerPorIdEmpleado(Integer idEmpleado) {
		LinkedList<TFacturaMuseo> listaFacturas = new LinkedList<TFacturaMuseo>();
		
		EntityManager em = EntityManagerFact.getInstancia().createEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<FacturaMuseo> query = em.createQuery("SELECT f FROM FacturaMuseo f WHERE f.empleado.id =:id", FacturaMuseo.class);
		query.setParameter("id", idEmpleado);
		
		Empleado empleado = em.find(Empleado.class, idEmpleado);
		List<FacturaMuseo> lista = query.getResultList();
		if (lista.size() > 0 && empleado.isActivo()){
			for (FacturaMuseo fact: lista){
				TFacturaMuseo factura = new TFacturaMuseo();
				
				if(fact.getEmpleado().getId() == idEmpleado){
					factura.setFecha(fact.getFecha());
					factura.setId(fact.getId());
					factura.setIdEmpleado(fact.getEmpleado().getId());
					factura.setPrecioTotal(fact.getPrecioTotal());
					listaFacturas.add(factura);
				}
			}
			
			em.getTransaction().commit();
		}
		else {
			em.getTransaction().rollback();
			throw new IllegalArgumentException("Empleado esta en baja o lista vacia");
		}
		
		em.close();
		return listaFacturas;
	}

	@Override
	public LinkedList<TFacturaCompleta> mostrarFacturaCompleta() {
		LinkedList<TFacturaCompleta> listaFacturas = new LinkedList<TFacturaCompleta>();
		
		EntityManager em = EntityManagerFact.getInstancia().createEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<FacturaMuseo> query = em.createQuery("SELECT f FROM FacturaMuseo f", FacturaMuseo.class);
		List<FacturaMuseo> lista = query.getResultList();
		
		if (lista.size() > 0){
			for (FacturaMuseo fact: lista){
				List<LineaFactura> list = fact.getLineasFactura();
				
				for(LineaFactura l : list){
					TFacturaCompleta carrito = new TFacturaCompleta(fact.getId(), l.getEntrada().getId(), fact.getEmpleado().getId());
					listaFacturas.add(carrito);
				}
			}
			em.getTransaction().commit();
		}
		else
			em.getTransaction().rollback();
		
		em.close();
		return listaFacturas;
	}
	
}