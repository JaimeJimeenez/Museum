package Presentacion.Controller.Comandos.Museo.Empleado;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.Empleado.TEmpleado;

public class ModificarEmpleadoCommand implements Command{

	@Override
	public Context execute(Object data) {
		Context context;
		try{
			int empleado = SAFactoria.getInstancia().generarSAEmpleado().modificarEmpleado((TEmpleado) data);
			if(empleado > 0){
				context = new Context(Eventos.MODIFICAR_EMPLEADO_OK, "Empleado modificado con éxito");
			}
			else{
				context = new Context(Eventos.MODIFICAR_EMPLEADO_KO, "Error al modificar el empleado");
			}
		}
		catch(Exception e){
			context = new Context(Eventos.MODIFICAR_EMPLEADO_KO, e.getMessage());
		}
		return context;
	}

}
