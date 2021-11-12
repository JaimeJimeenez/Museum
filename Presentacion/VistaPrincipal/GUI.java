package Presentacion.VistaPrincipal;

import javax.swing.JPanel;

import Presentacion.Controller.Context;

public interface GUI {
	
	public JPanel getPanel();

	public void actualizar(Context responseContext);
	
	public String getNombre();
}