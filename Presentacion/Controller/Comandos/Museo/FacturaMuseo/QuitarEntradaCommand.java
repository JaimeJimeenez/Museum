package Presentacion.Controller.Comandos.Museo.FacturaMuseo;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.FacturaMuseo.TCarritoMuseo;

public class QuitarEntradaCommand implements Command {

	@Override
	public Context execute(Object data) {
		Context context;
		
		try{
			TCarritoMuseo carrito = SAFactoria.getInstancia().generarSAFacturaMuseo().quitarEntrada((TCarritoMuseo) data);
			if(carrito != null){
				context = new Context(Eventos.QUITAR_ENTRADA_OK, carrito);
			}
			else{
				context = new Context(Eventos.QUITAR_ENTRADA_KO, "Error al quitar entrada");
			}
		}
		catch(Exception e){
			context = new Context(Eventos.QUITAR_ENTRADA_KO, e.getMessage());
		}
		return context;
	}

}
