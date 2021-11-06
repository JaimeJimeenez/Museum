package src.Presentacion.VistaPrincipal;

import javax.swing.JPanel;

import presentacion.controller.Context;

public abstract class PrincipalGUI implements GUI {
	
	private static PrincipalGUI instancia;

	public static PrincipalGUI getInstancia() { return instancia; }

	public void setInstancia(PrincipalGUI instancia) {
		this.instancia = instancia;
	}

}
