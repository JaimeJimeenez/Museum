package Presentacion.Controller.Comandos.Tienda.Fabricante;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.Fabricante.TFabricante;

public class RegistrarFabricanteCommand implements Command {

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