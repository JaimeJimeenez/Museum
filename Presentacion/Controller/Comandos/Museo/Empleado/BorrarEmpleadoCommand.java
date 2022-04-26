package Presentacion.Controller.Comandos.Museo.Empleado;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;

public class BorrarEmpleadoCommand implements Command{

	@Override
	public Context execute(Object data) {
		Context context;
		try{
			int empleado = SAFactoria.getInstancia().generarSAEmpleado().borrarEmpleado((int) data);
			if(empleado > 0){
				context = new Context(Eventos.BORRAR_EMPLEADO_OK, "Empleado borrado con éxito");
			}
			else{
				context = new Context(Eventos.BORRAR_EMPLEADO_KO, "Error al borrar el empleado");
			}
		}
		catch(Exception e){
			context = new Context(Eventos.BORRAR_EMPLEADO_KO, e.getMessage());
		}
		return context;
	}

}
