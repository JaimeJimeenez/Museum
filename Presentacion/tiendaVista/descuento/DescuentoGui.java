package Presentacion.tiendaVista.descuento;


import Presentacion.vistaPrincipal.GUI;

public abstract class DescuentoGui implements GUI {
	private static DescuentoGui instancia;

	public static DescuentoGui getInstancia() {
		if(instancia == null){
			instancia = new DescuentoGuiImp();
		}
		return instancia;
	}

	public static void setInstancia(DescuentoGui instancia) {
		DescuentoGui.instancia = instancia;
	}
}
