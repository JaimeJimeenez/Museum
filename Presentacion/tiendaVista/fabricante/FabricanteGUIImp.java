package Presentacion.tiendaVista.fabricante;

import javax.swing.JPanel;

import Presentacion.Controller.Context;
import Presentacion.vistaPrincipal.GUI;

public class FabricanteGUIImp extends FabricanteGUI {

	private GUI panel;
	
	public FabricanteGUIImp() {
		panel = new FabricantePanel();
	}

	public JPanel getPanel() {
		return panel.getPanel();
	}

	public void setPanel(GUI panel) {
		this.panel = panel;
	}

	public void actualizar(Context responseContext) {
		panel.actualizar(responseContext);
	}

	public String getNombre() {
		return panel.getNombre();
	}
}