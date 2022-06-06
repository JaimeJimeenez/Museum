package Presentacion.Controller.Comandos.Tienda.Producto;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.comandos.commandFactory.Command;
import negocio.SAFactoria.SAFactoria;
import negocio.producto.TProducto;

public class RegistrarProductoCommand implements Command {
	
	public Context execute(Object data) {
		Context context = null;
		int res;
		
		try{
			res = SAFactoria.getInstancia().generarSAProducto().registrarProducto((TProducto) data);
			
			if(res > 0)
				context = new Context(Eventos.REGISTRAR_PRODUCTO_OK, "Producto registrado con ID:" + res + ".");
			else
				context = new Context(Eventos.REGISTRAR_PRODUCTO_KO, "Error al registrar producto.");
		} catch (Exception e){
			context = new Context(Eventos.REGISTRAR_PRODUCTO_KO, e.getMessage());
		}
		
		return context;
	}
}