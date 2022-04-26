package Presentacion.tiendaVista.cliente;

import javax.swing.JPanel;

import Presentacion.Controller.Context;
import Presentacion.vistaPrincipal.GUI;

public class ClienteGuiImp extends ClienteGui {
	
	private GUI panel;
	
	public ClienteGuiImp() {
		panel = new ClientePanel();
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