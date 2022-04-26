package Negocio.Factura;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import Integracion.DAOFactoria.DAOFactoria;
import Integracion.Cliente.DAOCliente;
import Integracion.Factura.DAOFactura;
import Integracion.Factura.DAOLineaFactura;
import Integracion.Producto.DAOProducto;
import Integracion.Query.QueryFactoria;
import Integracion.Transaction.Transaction;
import Integracion.Transaction.TransactionManager;
import Negocio.Cliente.TCliente;
import Negocio.Producto.TProducto;


public class SAFacturaImp implements SAFactura {

	@Override
	public TCarrito abrirFactura() {
		TCarrito carrito = new TCarrito();
		Date date= Date.valueOf(LocalDate.now());
		carrito.setFactura(new TFacturaTienda(date));
		return carrito;
	}
	
	private TLineaFactura getLinea(List<TLineaFactura> lineasFactura, int idProducto) {
		TLineaFactura lineaFactura = null;
		ListIterator<TLineaFactura> iterator = lineasFactura.listIterator();
		while (lineaFactura == null && iterator.hasNext()) {
			TLineaFactura linea = iterator.next();
			if (linea.getProducto().getId()== idProducto)
				lineaFactura = linea;
		}
		return lineaFactura;
	}
	
	private void eliminarLineaFactura(List<TLineaFactura> lineasFactura, int idProducto) {
		ListIterator<TLineaFactura> iterator = lineasFactura.listIterator();
		while(iterator.hasNext()) {
		    if(iterator.next().getProducto().getId() == idProducto)
		    	iterator.remove();
		}
	}

	@Override
	public TCarrito añadirProducto(TCarrito carrito) throws Exception {
		int id = carrito.getIdProducto();
		int cantidad = carrito.getCantidad();
		
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();
		List<TLineaFactura> lineasFactura = carrito.getLineaFactura();		
		DAOProducto daoProducto = DAOFactoria.getInstancia().generarDAOProducto();
		TLineaFactura lineaFactura = getLinea(lineasFactura, id);
		
		TProducto tproducto= daoProducto.buscarProductoPorId(id);
		if(tproducto!=null&&tproducto.isActivo()){
			if(lineaFactura!=null){
				lineaFactura.setCantidad(lineaFactura.getCantidad() + cantidad);
			}
			else
				lineasFactura.add(new TLineaFactura(tproducto, cantidad));
			transaction.commit();
		}
		else{
			transaction.rollback();
			throw new IllegalArgumentException("El producto " + id + " no existe");
		}
		return carrito;
	}

	@Override
	public TCarrito bucarFacturaID(int id) throws Exception {
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();
		TCarrito carrito = null;
		DAOFactura daoFactura = DAOFactoria.getInstancia().generarDAOFactura();
		TFacturaTienda factura = daoFactura.buscarFacturaPorId(id);
		if (factura != null) {
			DAOLineaFactura daoLineaFactura = DAOFactoria.getInstancia().generarDAOLineaFactura();
			List<TLineaFactura> lineasFactura = daoLineaFactura.mostrarLineasFacturas(id);
			double precioTotal = 0;
			for (TLineaFactura linea : lineasFactura) {
				precioTotal += linea.getPrecio();
			}
			carrito = new TCarrito(factura, lineasFactura, precioTotal);
			transaction.commit();
		} else {
			transaction.rollback();
		}
		return carrito;
	}

	@Override
	public boolean cerrarFactura(TCarrito carrito) throws Exception {
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();
		
		DAOProducto daoProducto = DAOFactoria.getInstancia().generarDAOProducto();
		for (TLineaFactura linea : carrito.getLineaFactura()) {
			TProducto producto = daoProducto.buscarProductoPorId(linea.getProducto().getId());
			if (producto == null || !producto.isActivo()){
				transaction.rollback();
				throw new IllegalArgumentException("No existe el producto con ID: " + linea.getProducto().getId());
			}
			if (linea.getCantidad() > producto.getStock()){
				transaction.rollback();
				throw new IllegalArgumentException("No hay suficiente stock del producto: " + producto.getId());
			}
			linea.setPrecio(linea.getCantidad()*producto.getPrecio());
		}
		
		DAOCliente daoCliente = DAOFactoria.getInstancia().generarDAOCliente();
		int idCliente = carrito.getFactura().getIdCliente();
		TCliente clienteCompra = daoCliente.buscarClientePorId(idCliente);
		if(clienteCompra == null || !clienteCompra.isActivo()){
			transaction.rollback();
			throw new IllegalArgumentException("No existe el cliente con ID: " + clienteCompra.getId());
		}
		
		DAOFactura daoFactura = DAOFactoria.getInstancia().generarDAOFactura(); 
		DAOLineaFactura daoLineaFactura = DAOFactoria.getInstancia().generarDAOLineaFactura();
		int idFactura = daoFactura.insertarFactura(carrito.getFactura());
		if (idFactura < 1){
			transaction.rollback();
			return false;
		}
		for (TLineaFactura linea : carrito.getLineaFactura()){
			TProducto producto = daoProducto.buscarProductoPorId(linea.getProducto().getId());
			producto.setStock(producto.getStock() - linea.getCantidad());
			daoProducto.modificarProducto(producto);
			linea.setIdFactura(idFactura);
			daoLineaFactura.insertarLineaFactura(linea);
			
		}
		transaction.commit();
		return true;
	}

	@Override
	public boolean devolverProducto(TLineaFactura lineaFactura) throws Exception {
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();
		DAOLineaFactura daoLineaFactura = DAOFactoria.getInstancia().generarDAOLineaFactura();
		TLineaFactura linea = daoLineaFactura.buscarLineaFactura(lineaFactura.getIdFactura(), lineaFactura.getProducto().getId());
		if (linea != null) {
			DAOProducto daoProducto = DAOFactoria.getInstancia().generarDAOProducto(); 
			TProducto producto = daoProducto.buscarProductoPorId(linea.getProducto().getId());
			int nuevaCantidad = linea.getCantidad() - lineaFactura.getCantidad();
			if (nuevaCantidad > 0) {
				linea.setCantidad(nuevaCantidad);
				linea.setPrecio(linea.getPrecio() - lineaFactura.getCantidad() * producto.getPrecio());
				daoLineaFactura.modificarLineaFactura(linea);
				producto.setStock(producto.getStock() + lineaFactura.getCantidad());
				daoProducto.modificarProducto(producto);
			} else {
				daoLineaFactura.eliminarLineaFactura(linea);
				producto.setStock(producto.getStock() + linea.getCantidad());
				daoProducto.modificarProducto(producto);
			}
			transaction.commit();
			return true;
		}
		else{
			transaction.rollback();
			return false;
		}
	}

	@Override
	public List<TCarrito> mostrarListaFacturas() throws Exception {
		List<TFacturaTienda> factura = null;

		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();
		
		List<TCarrito> carritos = null;

		DAOFactura daoFactura = DAOFactoria.getInstancia().generarDAOFactura();
		factura = daoFactura.mostrarListaFacturas();

		if (factura != null) {
			
			carritos = new ArrayList<TCarrito>();
			
			DAOLineaFactura daoLineaFactura = DAOFactoria.getInstancia().generarDAOLineaFactura();
			for (TFacturaTienda f : factura) {
				double precioTotal = 0.0;
				List<TLineaFactura> lineasFactura = daoLineaFactura.mostrarLineasFacturas(f.getId());
				
				for (TLineaFactura l : lineasFactura)
					precioTotal += l.getPrecio();
				
				carritos.add(new TCarrito(f, lineasFactura, precioTotal));
			}
			
			transaction.commit();
		} else {
			transaction.rollback();
		}

		return carritos;

	}

	@Override
	public TCarrito quitarProducto(TCarrito carrito) {
		int id = carrito.getIdProducto();
		int cantidad = carrito.getCantidad();
		List<TLineaFactura> lineasFactura = carrito.getLineaFactura();
		TLineaFactura lineaFactura = getLinea(lineasFactura, id);
		if (lineaFactura != null) {
			int nuevaCantidad = lineaFactura.getCantidad() - cantidad;
			if (nuevaCantidad <= 0) {
				eliminarLineaFactura(lineasFactura, id);
			} else {
				lineaFactura.setCantidad(nuevaCantidad);
			}
		} else {
			throw new NullPointerException("El producto no existe en la factura");
		}
		return carrito;
	}

	@Override
	public List<TFacturaTienda> leerIDdeCliente(int idCliente) throws Exception {
		List<TFacturaTienda> listaClientes = new ArrayList<TFacturaTienda>();
		
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();
		
		DAOFactura daoFactura = DAOFactoria.getInstancia().generarDAOFactura();
		List<TFacturaTienda> listaTienda = daoFactura.mostrarListaFacturas();
		if(listaTienda.isEmpty()){transaction.rollback();}
		
		for (TFacturaTienda elem : listaTienda) {
			if (elem.getIdCliente() == idCliente)
				listaClientes.add(elem);
		}
		transaction.commit();
		return listaClientes;

	}

	@Override
	public List<TFacturaTienda> leerIDdeDescuento(int idDescuento) throws Exception {
		List<TFacturaTienda> listaDescuento = new ArrayList<TFacturaTienda>();
		
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();
		
		DAOFactura daoFactura = DAOFactoria.getInstancia().generarDAOFactura();
		List<TFacturaTienda> listaTienda = daoFactura.mostrarListaFacturas();
		
		for (TFacturaTienda elem : listaTienda) {
			if (elem.getIdDescuento() == idDescuento) {
				listaDescuento.add(elem);
			}
		}
		
		return listaDescuento;
	}

	@Override
	public List<TProducto> mostrarProductoMasComprado() throws Exception {
		List<TProducto> productos = null;
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();
		productos = (List<TProducto>) QueryFactoria.getInstancia().nuevaQuery("mostrarProductoMasComprado").execute(null);
		if(productos != null){
			transaction.commit();
		}
		else{
			transaction.rollback();
		}
		return productos;
	}

	@Override
	public List<TFacturaTienda> mostrarFacturaConMayorPorcentajeDescuento() throws Exception{
		List<TFacturaTienda> factura = null;
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();
		try {			
			factura = (List<TFacturaTienda>) QueryFactoria.getInstancia().nuevaQuery("mostrarFacturaConMayorPorcentajeDescuento").execute(null);
			if(factura != null){
				transaction.commit();
			}
			else{
				transaction.rollback();
			}
		} catch (Exception e) {
			factura = null;
		}
		return factura;
	}

	@Override
	public LinkedList<TFacturaTiendaCompleta> mostrarFacturaCompleta() throws Exception {
		LinkedList<TFacturaTiendaCompleta> listaFacturas = new LinkedList<TFacturaTiendaCompleta>();

		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		transaction.start();
		
		DAOFactura daoFactura = DAOFactoria.getInstancia().generarDAOFactura();
		List<TFacturaTienda> factura = daoFactura.mostrarListaFacturas();
		
		if (factura != null) {
			DAOLineaFactura daoLineaFactura = DAOFactoria.getInstancia().generarDAOLineaFactura();
			for(TFacturaTienda f : factura) {
				List<TLineaFactura> lineas = daoLineaFactura.mostrarLineasFacturas(f.getId());
				for(TLineaFactura l : lineas){
					TFacturaTiendaCompleta facturaCompleta = new TFacturaTiendaCompleta(f.getId(), l.getProducto().getId(), f.getIdCliente());
					listaFacturas.add(facturaCompleta);
				}
			}
			transaction.commit();
		} 
		else {
			transaction.rollback();
		}	

		return listaFacturas;
	}
	
	

}