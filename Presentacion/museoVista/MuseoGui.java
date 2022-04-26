package Presentacion.museoVista;

import Presentacion.vistaPrincipal.GUI;

public abstract class MuseoGui implements GUI {
	
	private static MuseoGui instancia;
	
	public static synchronized MuseoGui getInstancia() {
		if (instancia == null) {
			instancia = new MuseoGuiImp();
		}
		return instancia;
	}
	
	public void setInstancia(MuseoGui instancia) {
		MuseoGui.instancia = instancia;
	}
}
