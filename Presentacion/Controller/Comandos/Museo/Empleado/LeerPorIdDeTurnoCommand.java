package Presentacion.Controller.Comandos.Museo.Empleado;

import java.util.List;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.Empleado.TEmpleado;

public class LeerPorIdDeTurnoCommand implements Command{

	@Override
	public Context execute(Object data) {
		Context context;
		try{
			List<TEmpleado> empleados = SAFactoria.getInstancia().generarSAEmpleado().leerPorIdDeTurnoEmpleado((int) data);
			if(empleados != null){
				context = new Context(Eventos.LEER_POR_ID_TURNO_OK, empleados);
			}
			else{
				context = new Context(Eventos.LEER_POR_ID_TURNO_KO, "Error al leer empleados por ID de turno");
			}
		}
		catch(Exception e){
			context = new Context(Eventos.LEER_POR_ID_TURNO_KO, e.getMessage());
		}
		return context;
	}

}
