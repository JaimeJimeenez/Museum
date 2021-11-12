package Presentacion.VistaPrincipal;

import javax.swing.JPanel;

import Presentacion.Controller.Context;

public class PrincipalGUIImp extends PrincipalGUI {

	private GUI frame;

	public PrincipalGUIImp() {
		frame = new PrincipalFrame();
	}

	public JPanel getPanel() {
		return frame.getPanel();
	}

	public void actualizar(Context responseContext) {
		frame.actualizar(responseContext);
	}

	public String getNombre() {
		return frame.getNombre();
	}
}
