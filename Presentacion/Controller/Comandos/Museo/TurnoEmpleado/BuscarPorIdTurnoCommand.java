package Presentacion.Controller.Comandos.Museo.TurnoEmpleado;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.comandos.commandFactory.Command;
import negocio.SAFactoria.SAFactoria;
import negocio.turnoEmpleado.TTurnoEmpleado;

public class BuscarPorIdTurnoCommand implements Command {

	@Override
	public Context execute(Object data) {
		Context context = null;
		
		try {
			TTurnoEmpleado tTurnoEmpleado = SAFactoria.getInstancia().generarSATurnoEmpleado().buscarTurnoPorID((int) data);
			
			if (tTurnoEmpleado != null) 
				context = new Context(Eventos.BUSCAR_TURNO_POR_ID_OK, tTurnoEmpleado);
			else
				context = new Context(Eventos.BUSCAR_TURNO_POR_ID_KO, "No existe ningun turno con ese id.");
			
		} catch (Exception e) {
			context = new Context(Eventos.BUSCAR_TURNO_POR_ID_KO, e.getMessage());
		}
		
		return context;
	}

}
