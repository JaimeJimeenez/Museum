package Presentacion.museoVista.empleado;

import Presentacion.vistaPrincipal.GUI;

public abstract class EmpleadoGUI implements GUI{
	
	private static EmpleadoGUI instancia;
	
	public static synchronized EmpleadoGUI getInstancia(){
		if(instancia == null){
			instancia = new EmpleadoGUIImp();
		}
		return instancia;
	}

}
