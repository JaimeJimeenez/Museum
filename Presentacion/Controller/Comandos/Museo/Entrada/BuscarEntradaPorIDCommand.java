package Presentacion.Controller.Comandos.Museo.Entrada;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.Entrada.TEntrada;

public class BuscarEntradaPorIDCommand implements Command {

	@Override
	public Context execute(Object data) {
		Context context = null;

		try {
			TEntrada tEntrada = SAFactoria.getInstancia().generarSAEntrada().buscarEntrada((int) data);
			if (tEntrada != null)
				context = new Context(Eventos.BUSCAR_ENTRADA_POR_ID_OK, tEntrada);
			else
				context = new Context(Eventos.BUSCAR_ENTRADA_POR_ID_KO, "No existe ninguna entrada con ese id");
		} catch (Exception e) {
			context = new Context(Eventos.BUSCAR_EMPLEADO_POR_ID_KO, e.getMessage());
		}
		return context;
	}

}
