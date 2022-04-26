package Presentacion.Controller.Comandos.Museo.FacturaMuseo;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.FacturaMuseo.TLineaFacturaMuseo;

public class DevolverEntradaCommand implements Command {

	@Override
	public Context execute(Object data) {
		Context context;
		
		try{
			boolean devuelto = SAFactoria.getInstancia().generarSAFacturaMuseo().devolverEntrada((TLineaFacturaMuseo) data);
			if(devuelto){
				context = new Context(Eventos.DEVOLVER_ENTRADA_OK, "Entrada devuelta");
			}
			else{
				context = new Context(Eventos.DEVOLVER_ENTRADA_KO, "No se ha podido devolver la entrada");
			}
		}
		catch (Exception e){
			System.out.println(e);
			context = new Context(Eventos.DEVOLVER_ENTRADA_KO, e.getMessage());
		}
		return context;
	}

}
