package Presentacion.Controller.Comandos.Tienda.Factura;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.Factura.TLineaFactura;

public class devolverProductoCommand implements Command{

	public Context execute(Object data) {
		Context context;
		try {
			boolean res = SAFactoria.getInstancia().generarSAFactura().devolverProducto((TLineaFactura) data);
			if (res)
				context = new Context(Eventos.DEVOLVER_PRODUCTO_OK, "Productos devueltos");
			else
				context = new Context(Eventos.DEVOLVER_PRODUCTO_KO, "No se ha podido devolver el producto");
		} catch(Exception e) {
			context = new Context(Eventos.DEVOLVER_PRODUCTO_KO, e.getMessage());
		}
		return context;
	}

}
