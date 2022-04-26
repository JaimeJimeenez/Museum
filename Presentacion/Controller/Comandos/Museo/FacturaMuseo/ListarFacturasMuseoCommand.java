package Presentacion.Controller.Comandos.Museo.FacturaMuseo;

import java.util.LinkedList;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.FacturaMuseo.TCarritoMuseo;

public class ListarFacturasMuseoCommand implements Command {

	@Override
	public Context execute(Object data) {
		Context context;
		try {
			LinkedList<TCarritoMuseo> listaFacturas = SAFactoria.getInstancia().generarSAFacturaMuseo().listarFactura();
			if (!listaFacturas.isEmpty()) {
				context = new Context(Eventos.MOSTRAR_LISTA_FACTURAS_MUSEO_OK, listaFacturas);
			} else {
				context = new Context(Eventos.MOSTRAR_LISTA_FACTURAS_MUSEO_KO, "No existe ninguna factura");
			}
		} catch (Exception e) {
			System.out.println(e);
			context = new Context(Eventos.MOSTRAR_LISTA_FACTURAS_MUSEO_KO, e.getMessage());
		}
		return context;
	}

}
