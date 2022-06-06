package Presentacion.Controller.Comandos.Tienda.Producto;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.comandos.commandFactory.Command;
import negocio.SAFactoria.SAFactoria;
import negocio.producto.TProducto;

public class ModificarProductoCommand implements Command {
	
	public Context execute(Object data) {
		Context context = null;
		int res;
		
		try{
			res = SAFactoria.getInstancia().generarSAProducto().modificarProducto((TProducto) data);
			
			if(res > 0)
				context = new Context(Eventos.MODIFICAR_PRODUCTO_OK, "Producto modificado.");
			else
				context = new Context(Eventos.MODIFICAR_PRODUCTO_KO, "Error al modificar producto");
		} catch (Exception e){
			context = new Context(Eventos.MODIFICAR_PRODUCTO_KO, "Error al modificar producto");
		}
		
		return context;
	}
}