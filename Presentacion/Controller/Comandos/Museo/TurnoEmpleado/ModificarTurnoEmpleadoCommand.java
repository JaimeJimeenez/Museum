package Presentacion.Controller.Comandos.Museo.TurnoEmpleado;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.comandos.commandFactory.Command;
import negocio.SAFactoria.SAFactoria;
import negocio.turnoEmpleado.*;

public class ModificarTurnoEmpleadoCommand implements Command {

	@Override
	public Context execute(Object data) {
		Context context = null;
		
		try {
			int res = SAFactoria.getInstancia().generarSATurnoEmpleado().modificarTurno((TTurnoEmpleado) data);
			
			if (res > 0)
				context = new Context(Eventos.MODIFICAR_TURNO_OK, "Turno modificado con id: " +res);
			else
				context = new Context(Eventos.MODIFICAR_TURNO_KO, "No existe ese turno");
			
		} catch (Exception e) {
			context = new Context(Eventos.MODIFICAR_TURNO_KO, e.getMessage());
		}
		
		return context;
	}

}
