package Presentacion.tiendaVista;

import javax.swing.JPanel;

import Presentacion.Controller.Context;
import Presentacion.vistaPrincipal.GUI;

public class TiendaGuiImp extends TiendaGui {

	private GUI tiendaPanel;
	
	public TiendaGuiImp() {
		tiendaPanel = new TiendaPanel();
	}
	
	@Override
	public JPanel getPanel() {
		return tiendaPanel.getPanel();
	}

	@Override
	public void actualizar(Context responseContext) {
		tiendaPanel.actualizar(responseContext);
	}

	@Override
	public String getNombre() {
		return tiendaPanel.getNombre();
	}

}
