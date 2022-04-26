package Presentacion.Controller.Comandos.Museo.Empleado;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.Empleado.TEmpleado;

public class BuscarEmpleadoPorIdCommand implements Command{

	@Override
	public Context execute(Object data) {
		Context context;
		try{
			TEmpleado empleado = SAFactoria.getInstancia().generarSAEmpleado().buscarEmpleadoPorId((int) data);
			if(empleado != null)
				context = new Context(Eventos.BUSCAR_EMPLEADO_POR_ID_OK, empleado);
			else
				context = new Context(Eventos.BUSCAR_EMPLEADO_POR_ID_KO, "Error al buscar el empleado por id");
		}
		catch(Exception e){
			context = new Context(Eventos.BUSCAR_EMPLEADO_POR_ID_KO, e.getMessage());
		}
		return context;
	}

}
