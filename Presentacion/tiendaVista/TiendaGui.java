package Presentacion.tiendaVista;

import Presentacion.vistaPrincipal.GUI;

public abstract class TiendaGui implements GUI {
	
	private static TiendaGui instancia;
	
	public static synchronized TiendaGui getInstancia() {
		if(instancia == null) {
				instancia = new TiendaGuiImp();
		}
		return instancia;
	}
	
	public void setInstancia(TiendaGui instancia){
		TiendaGui.instancia = instancia;
	}
}
