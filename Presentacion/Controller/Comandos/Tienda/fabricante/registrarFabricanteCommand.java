package Presentacion.Controller.Comandos.Tienda.fabricante;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.comandos.commandFactory.Command;
import negocio.SAFactoria.SAFactoria;
import negocio.fabricante.TFabricante;

public class registrarFabricanteCommand implements Command {

	public Context execute(Object data) {
		Context context;
		try{
			int fabricante = SAFactoria.getInstancia().generarSAFabricante().registrarFabricante((TFabricante) data);
			if (fabricante > 0){
				context = new Context(Eventos.REGISTRAR_FABRICANTE_OK, "Fabricante registrado con ID: " + fabricante);
			}
			else{
				context = new Context(Eventos.REGISTRAR_FABRICANTE_KO, "Fabricante no registrado");
			}
		}
		catch(Exception e){
			context = new Context(Eventos.REGISTRAR_FABRICANTE_KO, e.getMessage());
		}
		return context;
	}
}