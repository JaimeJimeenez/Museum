package Presentacion.Controller.Comandos.Tienda.Factura;

import java.util.ArrayList;
import java.util.List;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.comandos.commandFactory.Command;
import negocio.SAFactoria.SAFactoria;
import negocio.factura.TCarrito;
import negocio.factura.TFacturaTienda;

public class mostrarListaFacturasCommand implements Command{

	@Override
	public Context execute(Object data) {
		Context context;
		List<TCarrito> listaFacturas = new ArrayList<TCarrito>();
		
		try {
			listaFacturas = SAFactoria.getInstancia().generarSAFactura().mostrarListaFacturas();
			if (listaFacturas != null) {
				List<TCarrito> list = new ArrayList<>();
				for (TCarrito ft : listaFacturas) {
					TCarrito c = new TCarrito();
					c.setFactura(ft.getFactura());
					c.setPrecioTotal(ft.getPrecioTotal());
					c.setLineaFactura(ft.getLineaFactura());
					list.add(c);
				} 
				context = new Context(Eventos.MOSTRAR_LISTA_FACTURAS_OK, list);
			} else
				context = new Context(Eventos.MOSTRAR_LISTA_FACTURAS_KO, "No hay ninguna factura");
			
		} catch(Exception e) {
			context = new Context(Eventos.MOSTRAR_LISTA_FACTURAS_KO, e.getMessage());
		}
		
		return context;
	}

}
