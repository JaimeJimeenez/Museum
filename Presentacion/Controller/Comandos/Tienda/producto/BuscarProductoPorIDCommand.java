package Presentacion.Controller.Comandos.Tienda.Producto;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.Producto.TProducto;

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