package Presentacion.tiendaVista.factura;

import javax.swing.JPanel;

import Presentacion.Controller.Context;
import Presentacion.vistaPrincipal.GUI;

public class FacturaGUIImp extends FacturaGUI{

	private GUI panel;
	
	public FacturaGUIImp(){
		panel = new FacturaPanel();
	}
	
	public JPanel getPanel() {
		return panel.getPanel();
	}

	public void actualizar(Context responseContext) {
		panel.actualizar(responseContext);
	}

	public String getNombre() {
		return panel.getNombre();
	}

}
