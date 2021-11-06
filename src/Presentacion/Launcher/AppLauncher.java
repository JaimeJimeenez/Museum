package src.Presentacion.Launcher;

import javax.swing.SwingUtilities;

import src.Presentacion.VistaPrincipal.PrincipalGUI;

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
