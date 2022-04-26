package Presentacion.tiendaVista.descuento;

import javax.swing.JPanel;

import Presentacion.Controller.Context;
import Presentacion.vistaPrincipal.GUI;

public class DescuentoGuiImp extends DescuentoGui {
	private GUI panel;
	
	public DescuentoGuiImp() {
		panel = new DescuentoPanel();
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
