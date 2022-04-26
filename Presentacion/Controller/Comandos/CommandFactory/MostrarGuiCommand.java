package Presentacion.Controller.Comandos.CommandFactory;


import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;

//Command generico para mostrar cualquier GUI
public class MostrarGuiCommand implements Command {

	private Eventos event;
	
	public MostrarGuiCommand(Eventos event) {
		this.event = event;
	}
	
	public Context execute(Object data) {
		return new Context(event, null);
	}
}