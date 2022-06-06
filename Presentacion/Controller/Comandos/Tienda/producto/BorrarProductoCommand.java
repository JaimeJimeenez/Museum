package Presentacion.Controller.Comandos.Tienda.Producto;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.comandos.commandFactory.Command;
import negocio.SAFactoria.SAFactoria;

public class BorrarProductoCommand implements Command {
	
	@Override
	public Context execute(Object data) {
		Context context = null;
		int res;
		
		try{
			res = SAFactoria.getInstancia().generarSAProducto().borrarProducto((int) data);
			
			if(res > 0)
				context = new Context(Eventos.BORRAR_PRODUCTO_OK, "Producto borrado.");
			else
				context = new Context(Eventos.BORRAR_PRODUCTO_KO, "Error al borrar producto");
		} catch (Exception e){
			context = new Context(Eventos.BORRAR_PRODUCTO_KO, e.getMessage());
		}
		
		return context;
	}
}