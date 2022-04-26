package Presentacion.museoVista.entrada;

import javax.swing.JPanel;

import Presentacion.Controller.Context;
import Presentacion.vistaPrincipal.GUI;

public class EntradaGuiImp extends EntradaGui {

	GUI panel;
	
	public EntradaGuiImp() {
		panel = new EntradaPanel();
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