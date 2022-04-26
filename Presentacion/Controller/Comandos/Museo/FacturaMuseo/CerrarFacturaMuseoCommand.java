package Presentacion.Controller.Comandos.Museo.FacturaMuseo;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.FacturaMuseo.TCarritoMuseo;

public class CerrarFacturaMuseoCommand implements Command {

	@Override
	public Context execute(Object data) {
		Context context;
		
		try{
			boolean cerrada = SAFactoria.getInstancia().generarSAFacturaMuseo().cerrarFactura((TCarritoMuseo) data);
			if(cerrada){
				
				context = new Context (Eventos.CERRAR_FACTURA_MUSEO_OK, "Factura cerrada con exito");
			}
			else{
				context = new Context (Eventos.CERRAR_FACTURA_MUSEO_KO, "Error al cerrar factura");
			}
		}
		catch (Exception e){
			System.out.println(e);
			context = new Context(Eventos.CERRAR_FACTURA_MUSEO_KO, e.getMessage());
		}
		return context;
	}

}
