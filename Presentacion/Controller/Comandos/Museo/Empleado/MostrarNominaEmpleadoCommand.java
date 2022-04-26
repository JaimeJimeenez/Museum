package Presentacion.Controller.Comandos.Museo.Empleado;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;


public class MostrarNominaEmpleadoCommand implements Command{

	@Override
	public Context execute(Object data) {
		Context context;
		try{
			double nominaEmpleado = SAFactoria.getInstancia().generarSAEmpleado().mostrarNominaEmpleado((int) data);
			if(nominaEmpleado > 0)
				context = new Context(Eventos.MOSTRAR_NOMINA_EMPLEADO_OK, nominaEmpleado);
			else
				context = new Context(Eventos.MOSTRAR_NOMINA_EMPLEADO_KO, "Error al mostrar la nomina del empleado");
			
		}
		catch(Exception e){
			context = new Context(Eventos.MOSTRAR_NOMINA_EMPLEADO_KO, e.getMessage());
		}
		return context;
	}

}
