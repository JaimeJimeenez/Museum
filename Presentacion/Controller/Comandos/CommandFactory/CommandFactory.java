package Presentacion.Controller.Comandos.CommandFactory;

import Presentacion.Controller.Eventos;

public abstract class CommandFactory {

	private static Object instancia;

	public static Object getInstancia() {
		if (instancia == null)
			instancia = new CommandFactoryImp();
		return instancia;
	}

	public static void setInstancia(Object instancia) {
		CommandFactory.instancia = instancia;
	}

	public abstract Command getCommand(Eventos evento);
}