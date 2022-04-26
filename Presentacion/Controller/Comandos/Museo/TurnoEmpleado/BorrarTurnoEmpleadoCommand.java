package Presentacion.Controller.Comandos.Museo.TurnoEmpleado;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.comandos.commandFactory.Command;
import negocio.SAFactoria.SAFactoria;

public class BorrarTurnoEmpleadoCommand implements Command {

	@Override
	public Context execute(Object data) {
		Context context = null;
		try {
			int res = SAFactoria.getInstancia().generarSATurnoEmpleado().borrarTurno((int) data);
			
			if (res > 0)
				context = new Context(Eventos.BORRAR_TURNO_OK, "Turno con id: " + res +  " borrado");
			else 
				context = new Context(Eventos.BORRAR_TURNO_KO, "No se ha podido borrar el turno");
			
		} catch (Exception e) {
			context = new Context(Eventos.BORRAR_TURNO_KO, e.getMessage());
		}
		
		return context;
	}

}
