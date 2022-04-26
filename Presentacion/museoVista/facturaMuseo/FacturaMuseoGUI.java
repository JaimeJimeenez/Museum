package Presentacion.museoVista.facturaMuseo;

import javax.swing.JPanel;

import Presentacion.vistaPrincipal.GUI;

public abstract class FacturaMuseoGUI implements GUI {

private static FacturaMuseoGUI instance = null;
	
	public static synchronized FacturaMuseoGUI getInstance() {
		if (instance == null) {
			instance = new FacturaMuseoGUIImp();
		}
		return instance;
	}

}
