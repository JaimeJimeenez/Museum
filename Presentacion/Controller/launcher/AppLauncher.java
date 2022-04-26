package Presentacion.Controller.launcher;

import javax.swing.SwingUtilities;

import Presentacion.vistaPrincipal.PrincipalGUI;

public class AppLauncher {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				try {
					PrincipalGUI.getInstancia();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
