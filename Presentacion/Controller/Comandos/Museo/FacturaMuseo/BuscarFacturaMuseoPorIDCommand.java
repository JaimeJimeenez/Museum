package Presentacion.Controller.Comandos.Museo.FacturaMuseo;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.FacturaMuseo.TCarritoMuseo;

public class BuscarFacturaMuseoPorIDCommand implements Command {

	@Override
	public Context execute(Object data) {
		Context context;
		try{
			TCarritoMuseo factura = SAFactoria.getInstancia().generarSAFacturaMuseo().buscarFacturaPorID((int) data);
			
			if(factura != null){
				context = new Context(Eventos.BUSCAR_FACTURA_MUSEO_POR_ID_OK, factura);
			}
			else{
				context = new Context(Eventos.BUSCAR_FACTURA_MUSEO_POR_ID_KO, "No existe ninguna factura con ese id");

			}
		}
		catch (Exception e){
			context = new Context(Eventos.BUSCAR_FACTURA_MUSEO_POR_ID_KO, e.getMessage());
		}
		return context;
	}

}
