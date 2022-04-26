package Presentacion.tiendaVista.producto;

import Presentacion.vistaPrincipal.GUI;

public abstract class ProductoGui implements GUI {

	private static ProductoGui instance = null;
		
	public static synchronized ProductoGui getInstance() {
		if (instance == null) {
			instance = new ProductoGuiImp();
		}
		return instance;
	}
}
