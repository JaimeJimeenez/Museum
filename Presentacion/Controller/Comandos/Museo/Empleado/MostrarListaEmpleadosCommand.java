package Presentacion.Controller.Comandos.Museo.Empleado;

import java.util.List;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.Empleado.TEmpleado;

public class MostrarListaEmpleadosCommand implements Command{

	@Override
	public Context execute(Object data) {
		Context context;
		try{
			List<TEmpleado> empleados = SAFactoria.getInstancia().generarSAEmpleado().mostrarListaEmpleados();
			if(empleados != null){
				context = new Context(Eventos.MOSTRAR_LISTA_EMPLEADOS_OK, empleados);
			}
			else{
				context = new Context(Eventos.MOSTRAR_LISTA_EMPLEADOS_KO, "Error al mostrar la lista de empleados");
			}
		}
		catch(Exception e){
			context = new Context(Eventos.MOSTRAR_LISTA_EMPLEADOS_KO, e.getMessage());
		}
		return context;
	}

}
