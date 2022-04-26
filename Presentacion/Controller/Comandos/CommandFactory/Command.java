package Presentacion.Controller.Comandos.CommandFactory;

import Presentacion.Controller.Context;

public interface Command {
	
	public Context execute(Object data);
	
}