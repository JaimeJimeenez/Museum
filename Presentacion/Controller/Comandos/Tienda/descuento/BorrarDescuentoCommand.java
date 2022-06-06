package Presentacion.Controller.Comandos.Tienda.Descuento;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.*;
import Negocio.SAFactoria.SAFactoria;

public class BorrarDescuentoCommand implements Command {

	@Override
	public Context execute(Object data) {
		Context context = null;
		int res;
		
		try {
			res = SAFactoria.getInstancia().generarSADescuento().borrarDescuento((int) data);
			
			if (res > 0)
				context = new Context(Eventos.BORRAR_DESCUENTO_OK, "Descuento borrado");
			else
				context = new Context(Eventos.BORRAR_DESCUENTO_KO, "Error al borrar el descuento");
		}
		catch (Exception e) {
			context = new Context(Eventos.BORRAR_DESCUENTO_KO, "a");
		}
		
		return context;
	}
	
	
}
