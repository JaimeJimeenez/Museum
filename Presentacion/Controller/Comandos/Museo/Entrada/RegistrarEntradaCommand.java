package Presentacion.Controller.Comandos.Museo.Entrada;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.Entrada.TEntrada;

public class RegistrarEntradaCommand implements Command {

	@Override
	public Context execute(Object data) {
		Context context;
		try {
			int entrada=SAFactoria.getInstancia().generarSAEntrada().registrarEntrada((TEntrada) data);
			if(entrada>0){
				context = new Context(Eventos.REGISTRAR_ENTRADA_OK, "Entrada registrada con id: "+entrada);
			}
			else{
				context = new Context(Eventos.REGISTRAR_ENTRADA_KO, "Error al registrar el entrada");
			}
		} catch (Exception e) {
			context = new Context(Eventos.REGISTRAR_ENTRADA_KO, e.getMessage());
		}
		return context;
	}

}
