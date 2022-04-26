package Presentacion.Controller.Comandos.Tienda.descuento;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.comandos.commandFactory.Command;
import negocio.SAFactoria.SAFactoria;
import negocio.descuento.TDescuento;

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
