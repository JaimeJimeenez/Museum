package Presentacion.Controller.Comandos.Tienda.Descuento;

import java.util.ArrayList;
import java.util.List;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.*;
import Negocio.SAFactoria.SAFactoria;
import Negocio.Descuento.TDescuento;

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
