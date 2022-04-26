package Presentacion.Controller.Comandos.Tienda.descuento;

import java.util.ArrayList;
import java.util.List;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.comandos.commandFactory.Command;
import negocio.descuento.TDescuento;
import negocio.SAFactoria.SAFactoria;

public class MostrarListaDescuentoCommand implements Command {

	@Override
	public Context execute(Object data) {
		Context context = null;
		List<TDescuento> res = new ArrayList<TDescuento>();
		try {
			res = SAFactoria.getInstancia().generarSADescuento().mostrarListaDescuentos();
			
			if (res != null) 
				context = new Context(Eventos.MOSTRAR_LISTA_DESCUENTO_OK, res);
			else
				context = new Context(Eventos.MOSTRAR_LISTA_DESCUENTO_KO, "No hay ningún descuento");
			
		} catch (Exception e) {
			context = new Context(Eventos.MOSTRAR_LISTA_DESCUENTO_KO, e.getMessage());
		}
		return context;
	}
	
}
