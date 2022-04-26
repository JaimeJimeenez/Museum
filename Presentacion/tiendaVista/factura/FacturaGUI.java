package Presentacion.tiendaVista.factura;

import Presentacion.vistaPrincipal.GUI;

public abstract class FacturaGUI implements GUI{

	private static FacturaGUI instancia;

	public static FacturaGUI getInstancia() {
		if(instancia == null){
			instancia = new FacturaGUIImp();
		}
		return instancia;
	}

	public static void setInstancia(FacturaGUI instancia) {
		FacturaGUI.instancia = instancia;
	}

}
