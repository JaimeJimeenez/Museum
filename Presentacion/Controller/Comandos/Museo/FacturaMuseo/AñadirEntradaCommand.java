package Presentacion.Controller.Comandos.Museo.FacturaMuseo;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.FacturaMuseo.TCarritoMuseo;

public class AñadirEntradaCommand implements Command {

	@Override
	public Context execute(Object data) {
		Context context;
		
		try{
			TCarritoMuseo carrito = SAFactoria.getInstancia().generarSAFacturaMuseo().añadirEntrada((TCarritoMuseo) data);
			if(carrito != null){
				context = new Context(Eventos.AÑADIR_ENTRADA_OK, carrito);
			}
			else{
				context = new Context(Eventos.AÑADIR_ENTRADA_KO, "Error al añadir entrada");
			}
		}
		catch (Exception e){
			context = new Context(Eventos.AÑADIR_ENTRADA_KO, e.getMessage());
		}
		return context;
	}

}
