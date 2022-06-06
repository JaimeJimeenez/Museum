package Presentacion.Controller;

import Presentacion.Controller.Comandos.CommandFactory.Command;
import Presentacion.Controller.Dispatcher.Dispatcher;
import Presentacion.Controller.Comandos.CommandFactory.*;

public class ApplicationControllerImp extends ApplicationController {

	public void handleRequest(Context resquest) {
		Command command = ((CommandFactory) CommandFactory.getInstancia()).getCommand(resquest.getEvent());
		Context response = command.execute(resquest.getDataObject());
		Dispatcher.getInstance().dispatch(response);
	}

}