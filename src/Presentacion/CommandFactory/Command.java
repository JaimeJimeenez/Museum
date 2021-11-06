package src.Presentacion.CommandFactory;

import presentacion.controller.Context;

public interface Command {
	
	public Context execute(Object data);
	
}