package Presentacion.Controller.Dispatcher;

import Presentacion.Controller.Context;

public abstract class Dispatcher {
	
	private static Dispatcher instance;

	public static Dispatcher getInstance() {
		if(instance == null){
			instance = new DispatcherImp();
		}
		return instance;
	}

	public static void setInstance(Dispatcher instance) {
		Dispatcher.instance = instance;
	}
	
	public abstract void dispatch(Context response);
}