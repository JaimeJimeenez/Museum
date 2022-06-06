package Presentacion.Controller.Comandos.Museo.TurnoEmpleado;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.*;
import Negocio.SAFactoria.SAFactoria;

public class CalculoNominaCommand implements Command {

	@Override
	public Context execute(Object data) {
		Context context = null;
		try {
			double res = SAFactoria.getInstancia().generarSATurnoEmpleado().calculoNominaDeTurno((int) data);
			
			if (res >= 0)
				context = new Context(Eventos.CALCULO_NOMINA_TURNO_OK, res);
			else
				context = new Context(Eventos.CALCULO_NOMINA_TURNO_KO, "El turno introducido no existe");
		} catch(Exception e) {
			context = new Context(Eventos.CALCULO_NOMINA_TURNO_KO, e.getMessage());
		}
		
		return context;
	}

}
