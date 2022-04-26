package Presentacion.Controller.Comandos.Museo.FacturaMuseo;

import java.util.LinkedList;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.FacturaMuseo.TFacturaCompleta;

public class MostrarFacturaCompletaCommand implements Command {

	@Override
	public Context execute(Object data) {
		Context context;
		
		try{
			LinkedList<TFacturaCompleta> listaFacturas = SAFactoria.getInstancia().generarSAFacturaMuseo().mostrarFacturaCompleta();
			if(!listaFacturas.isEmpty()){
				context = new Context(Eventos.MOSTRAR_FACTURA_COMPLETA_OK, listaFacturas);
			}
			else{
				context = new Context(Eventos.MOSTRAR_FACTURA_COMPLETA_KO, "No existe ninguna factura");
			}
		}
		catch(Exception e){
			System.out.println(e);
			context = new Context(Eventos.MOSTRAR_FACTURA_COMPLETA_KO, e.getMessage());
		}
		return context;
	}

}
