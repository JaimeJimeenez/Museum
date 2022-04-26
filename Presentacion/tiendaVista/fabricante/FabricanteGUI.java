package Presentacion.tiendaVista.fabricante;

import Presentacion.vistaPrincipal.GUI;

public abstract class FabricanteGUI implements GUI {
	
	private static FabricanteGUI instancia;

	public static FabricanteGUI getInstancia() {
		if(instancia == null){
			instancia = new FabricanteGUIImp();
		}
		return instancia;
	}

	public static void setInstancia(FabricanteGUI instancia) {
		FabricanteGUI.instancia = instancia;
	}
}