package Presentacion.Controller.Comandos.Tienda.producto;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.comandos.commandFactory.Command;
import negocio.SAFactoria.SAFactoria;
import negocio.producto.TProducto;

public class BuscarProductoPorIDCommand implements Command {

	public Context execute(Object data) {
		Context context;
		TProducto res = null;
		
		try{
			res = SAFactoria.getInstancia().generarSAProducto().buscarProductoPorId((int) data);
			
			if(res != null)
				context = new Context(Eventos.BUSCAR_PRODUCTO_POR_ID_OK, res);
			else
				context = new Context(Eventos.BUSCAR_PRODUCTO_POR_ID_KO, "No ha sido posible encontrar el producto.");
		} catch (Exception e){
			context = new Context(Eventos.BUSCAR_PRODUCTO_POR_ID_KO, "No ha sido posible encontrar el producto.");
		}
		
		return context;
	}
}