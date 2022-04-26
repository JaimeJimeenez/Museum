package Presentacion.museoVista.turnoEmpleado;

import Presentacion.vistaPrincipal.GUI;

public abstract class TurnoEmpleadoGui implements GUI {
	
	private static TurnoEmpleadoGui instance;
	
	public static synchronized TurnoEmpleadoGui getInstance() {
		if (instance == null) {
			instance = new TurnoEmpleadoGuiImp();
		}
		return instance;
	}

}
