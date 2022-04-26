package Presentacion.tiendaVista.cliente;

import Presentacion.vistaPrincipal.GUI;

public abstract class ClienteGui implements GUI {

	private static ClienteGui instance;

	public static ClienteGui getInstance() { 
		if(instance == null){
			instance = new ClienteGuiImp();
		}
		return instance;
	}
}