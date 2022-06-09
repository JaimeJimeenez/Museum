package Presentacion.Controller.Comandos.Tienda.Fabricante;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.Fabricante.TFabricante;

public class BuscarFabricantesPorIdCommand implements Command {

	public Context execute(Object data) {
		Context context;
		try{
			TFabricante fabricante = SAFactoria.getInstancia().generarSAFabricante().buscarFabricantePorID((Integer) data);
			if (fabricante != null){
				context = new Context(Eventos.BUSCAR_FABRICANTE_POR_ID_OK, fabricante);
			}
			else{
				context = new Context(Eventos.BUSCAR_FABRICANTE_POR_ID_KO, "Fabricante no encontrado");
			}
		}
		catch(Exception e){
			context = new Context(Eventos.BUSCAR_FABRICANTE_POR_ID_KO, e.getMessage());
		}
		return context;
	}
}