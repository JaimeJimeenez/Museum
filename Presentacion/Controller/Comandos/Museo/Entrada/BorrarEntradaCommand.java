package Presentacion.Controller.Comandos.Museo.Entrada;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;

public class BorrarEntradaCommand implements Command {

	@Override
	public Context execute(Object data) {
		Context context = null;
		
		try {
			int res = SAFactoria.getInstancia().generarSAEntrada().borrarEntrada((int) data);
			
			if (res > 0)
				context = new Context(Eventos.BORRAR_ENTRADA_KO, "Entrada con id: " + res + " borrado");
			
		} catch (Exception e) {
			context = new Context(Eventos.BORRAR_ENTRADA_KO, e.getMessage());
		}
		return context;
	}

}
