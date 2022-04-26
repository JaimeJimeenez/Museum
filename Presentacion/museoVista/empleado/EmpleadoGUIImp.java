package Presentacion.museoVista.empleado;

import javax.swing.JPanel;

import Presentacion.Controller.Context;
import Presentacion.vistaPrincipal.GUI;

public class EmpleadoGUIImp extends EmpleadoGUI{

	private GUI panel;
	
	public EmpleadoGUIImp(){
		panel = new EmpleadoPanel();
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
