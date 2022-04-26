package Presentacion.Controller.Comandos.Tienda.fabricante;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.comandos.commandFactory.Command;
import negocio.SAFactoria.SAFactoria;
import negocio.fabricante.TFabricante;

public class modificarFabricanteCommand implements Command {

	public Context execute(Object data) {
		Context context;
		try{
			int fabricante = SAFactoria.getInstancia().generarSAFabricante().modificarFabricante((TFabricante) data);
			if (fabricante > 0){
				context = new Context(Eventos.MODIFICAR_FABRICANTE_OK, "Fabricante modificado con exito");
			}
			else{
				context = new Context(Eventos.MODIFICAR_FABRICANTE_KO, "Fabricante no encontrado");
			}
		}
		catch(Exception e){
			context = new Context(Eventos.MODIFICAR_FABRICANTE_KO, e.getMessage());
		}
		return context;
	}
}