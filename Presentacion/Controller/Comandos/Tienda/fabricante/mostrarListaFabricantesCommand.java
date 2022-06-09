package Presentacion.Controller.Comandos.Tienda.Fabricante;

import java.util.List;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.Fabricante.TFabricante;

public class MostrarListaFabricantesCommand implements Command {

	public Context execute(Object data) {
		Context context;
		try{
			List<TFabricante> fabricante = SAFactoria.getInstancia().generarSAFabricante().mostrarListaFabricantes();
			if (fabricante != null){
				context = new Context(Eventos.MOSTRAR_LISTA_FABRICANTES_OK, fabricante);
			}
			else{
				context = new Context(Eventos.MOSTRAR_LISTA_FABRICANTES_KO, "No hay fabricantes");
			}
		}
		catch(Exception e){
			context = new Context(Eventos.MOSTRAR_LISTA_FABRICANTES_KO, e.getMessage());
		}
		return context;
	}
}