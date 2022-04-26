package Presentacion.Controller.Comandos.Museo.Entrada;

import java.util.List;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.Entrada.TEntrada;

public class MostrarListaEntradasCommand implements Command {

	@Override
	public Context execute(Object data) {
		Context context = null;
		try {
			List<TEntrada> entradas = SAFactoria.getInstancia().generarSAEntrada().listarEntradas();

			if (entradas != null) 
				context = new Context(Eventos.MOSTRAR_LISTA_ENTRADAS_OK, entradas);
			else 
				context = new Context(Eventos.MOSTRAR_LISTA_ENTRADAS_KO, "No existe ninguna entrada");
		} catch (Exception e) {
			context = new Context(Eventos.MOSTRAR_LISTA_ENTRADAS_KO, e.getMessage());
		}
		return context;
	}

}
