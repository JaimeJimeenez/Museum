package Presentacion.museoVista.turnoEmpleado;

import javax.swing.JPanel;

import Presentacion.Controller.Context;
import Presentacion.vistaPrincipal.GUI;

public class TurnoEmpleadoGuiImp extends TurnoEmpleadoGui {

	GUI panel;
	
	public TurnoEmpleadoGuiImp() {
		panel = new TurnoEmpleadoPanel();
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
