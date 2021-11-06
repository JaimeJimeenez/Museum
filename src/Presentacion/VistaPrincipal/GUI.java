package src.Presentacion.VistaPrincipal;

import javax.swing.JPanel;

import presentacion.controller.Context;

public interface GUI {
	
	public JPanel getPanel();

	public void actualizar(Context responseContext);
	
	public String getNombre();
}