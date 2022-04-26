package Presentacion.Controller.Comandos.Museo.FacturaMuseo;

import java.util.List;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.FacturaMuseo.TFacturaMuseo;

public class LeerFacturaMuseoPorIDEmpleadoCommand implements Command {

	@Override
	public Context execute(Object data) {
		Context context;
		
		try{
			List<TFacturaMuseo> listaEmpleados = SAFactoria.getInstancia().generarSAFacturaMuseo().leerPorIdEmpleado((int) data);
			if(listaEmpleados != null){
				context = new Context(Eventos.LEER_POR_ID_EMPLEADO_OK, listaEmpleados);
			}
			else{
				context = new Context(Eventos.LEER_POR_ID_EMPLEADO_KO, "Error al mostrar facturas del empleado");
			}
		}
		catch (Exception e){
			context = new Context(Eventos.LEER_POR_ID_EMPLEADO_KO, e.getMessage());
			System.out.println(e.getMessage());
		}
		return context;
	}

}
