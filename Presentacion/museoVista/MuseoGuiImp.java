package Presentacion.museoVista;

import javax.swing.JPanel;

import Presentacion.Controller.Context;
import Presentacion.vistaPrincipal.GUI;

public class MuseoGuiImp extends MuseoGui {
	
	private GUI museoPanel;
	
	public MuseoGuiImp() {
		museoPanel = new MuseoPanel();
	}

	@Override
	public JPanel getPanel() {
		return museoPanel.getPanel();
	}

	@Override
	public void actualizar(Context responseContext) {
		museoPanel.actualizar(responseContext);
	}

	@Override
	public String getNombre() {
		return museoPanel.getNombre();
	}
	
}
