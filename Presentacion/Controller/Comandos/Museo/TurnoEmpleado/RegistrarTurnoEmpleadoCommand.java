package Presentacion.Controller.Comandos.Museo.TurnoEmpleado;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.TurnoEmpleado.*;

public class RegistrarTurnoEmpleadoCommand implements Command {

	@Override
	public Context execute(Object data) {
		Context context = null;
		try {
			int res = SAFactoria.getInstancia().generarSATurnoEmpleado().registrarTurno((TTurnoEmpleado) data);
			
			if (res > 0) 
				context = new Context(Eventos.REGISTRAR_TURNO_OK, "Turno registrado con id: " + res);
			else
				context = new Context(Eventos.REGISTRAR_TURNO_KO, "No se ha podido registrar el turno");
			
		} catch (Exception e) {
			context = new Context(Eventos.REGISTRAR_TURNO_KO, e.getMessage());
		}
		return context;
	}

}
