package Presentacion.Controller.Comandos.Museo.Entrada;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.Entrada.TEntrada;

public class ModificarEntradaCommand implements Command {

	@Override
	public Context execute(Object data) {
		Context context = null;
		try {
			int res = SAFactoria.getInstancia().generarSAEntrada().modificarEntrada((TEntrada) data);

			if (res > 0) {
				context = new Context(Eventos.MODIFICAR_ENTRADA_OK, "Entrada modificada");
			} else {
				context = new Context(Eventos.MODIFICAR_ENTRADA_KO, "Error al modificar entrada");
			}

		} catch (Exception e) {
			context = new Context(Eventos.MODIFICAR_ENTRADA_KO,e.getMessage());
		}
		return context;
	}

}
