package Presentacion.museoVista.facturaMuseo;

import javax.swing.JPanel;

import Presentacion.Controller.Context;
import Presentacion.vistaPrincipal.GUI;

public class FacturaMuseoGUIImp extends FacturaMuseoGUI {
	
	GUI panel;
	
	public FacturaMuseoGUIImp(){
		panel = new FacturaMuseoPanel();
	}

	@Override
	public JPanel getPanel() {
		return panel.getPanel();
	}

	@Override
	public void actualizar(Context responseContext) {
		panel.actualizar(responseContext);
		
	}

	@Override
	public String getNombre() {
		return panel.getNombre();
	}

}
