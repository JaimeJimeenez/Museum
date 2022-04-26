package Presentacion.Controller.Comandos.Museo.TurnoEmpleado;

import java.util.ArrayList;
import java.util.List;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.comandos.commandFactory.Command;
import negocio.SAFactoria.SAFactoria;
import negocio.turnoEmpleado.TTurnoEmpleado;

public class ListarTurnosCommand implements Command {

	@Override
	public Context execute(Object data) {
		Context context = null;
		
		try {
			List<TTurnoEmpleado> turnos = SAFactoria.getInstancia().generarSATurnoEmpleado().listarTurnos();
			
			if (!turnos.isEmpty())
				context = new Context(Eventos.LISTAR_TURNOS_OK, turnos);
			else
				context = new Context(Eventos.LISTAR_TURNOS_KO, "No existe ningun turno");
			
		} catch(Exception e) {
			context = new Context(Eventos.LISTAR_TURNOS_KO, e.getMessage());
		}
		return context;
	}

}
