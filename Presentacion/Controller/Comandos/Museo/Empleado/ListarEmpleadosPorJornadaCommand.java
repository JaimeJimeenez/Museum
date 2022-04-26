package Presentacion.Controller.Comandos.Museo.Empleado;

import java.util.List;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.Empleado.TEmpleado;

public class ListarEmpleadosPorJornadaCommand implements Command{

	@Override
	public Context execute(Object data) {
		Context context;
		try{
			List<TEmpleado> empleados = SAFactoria.getInstancia().generarSAEmpleado().listarPorJornada((boolean) data);
			if(empleados != null){
				context = new Context(Eventos.LISTAR_EMPLEADOS_POR_JORNADA_OK, empleados);
			}
			else{
				context = new Context(Eventos.LISTAR_EMPLEADOS_POR_JORNADA_KO, "Error al listar los empleados por jornada");
			}
		}
		catch(Exception e){
			context = new Context(Eventos.LISTAR_EMPLEADOS_POR_JORNADA_KO, e.getMessage());
		}
		return context;
	}

}
