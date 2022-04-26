package Presentacion.tiendaVista.producto;

import javax.swing.JPanel;

import Presentacion.Controller.Context;
import Presentacion.vistaPrincipal.GUI;

public class ProductoGuiImp extends ProductoGui {

	GUI panel;
	
	public ProductoGuiImp() {
		panel = new ProductoPanel();
	}
	
	public String getNombre() {
		return panel.getNombre();
	}
	
	public JPanel getPanel() {
		return panel.getPanel();
	}
	
	public void actualizar(Context responseContext) {
		panel.actualizar(responseContext);
	}
}
