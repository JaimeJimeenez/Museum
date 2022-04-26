package Presentacion.museoVista.entrada;

import Presentacion.vistaPrincipal.GUI;

public abstract class EntradaGui implements GUI{
	
	private static EntradaGui instance=null;
	
	public static synchronized EntradaGui getInstance() {
		if (instance == null) {
			instance = new EntradaGuiImp();
		}
		return instance;
	}
}

