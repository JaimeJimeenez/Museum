package Presentacion.Controller.Comandos.Tienda.Descuento;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.*;
import Negocio.SAFactoria.SAFactoria;
import Negocio.Descuento.TDescuento;

public class BuscarDescuentoPorIdCommand implements Command {

	@Override
	public Context execute(Object data) {
		Context context = null;
		TDescuento res;
		
		try {
			res = SAFactoria.getInstancia().generarSADescuento().buscarDescuentoporId((int) data);
			
			if (res != null) 
				context = new Context(Eventos.BUSCAR_DESCUENTO_POR_ID_OK, res);
			else
				context = new Context(Eventos.BUSCAR_DESCUENTO_POR_ID_KO, "Descuento no encontrado");
			
		} catch (Exception e) {
			context = new Context(Eventos.BUSCAR_DESCUENTO_POR_ID_KO, e.getMessage());
		}
		
		return context;
	}
	
}
