package Presentacion.Controller.Comandos.Tienda.Producto;

import java.util.List;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.Producto.TProducto;

public class MostrarListaProductoCommand implements Command {
	
	public Context execute(Object data) {
		Context context;
		List<TProducto> res = null;
		
		try{
			res = SAFactoria.getInstancia().generarSAProducto().mostrarListaProducto();
			
			if(res != null)
				context = new Context(Eventos.MOSTRAR_LISTA_PRODUCTOS_OK, res);
			else
				context = new Context(Eventos.MOSTRAR_LISTA_PRODUCTOS_KO, "No ha sido posible encontrar productos.");
		} catch (Exception e){
			context = new Context(Eventos.MOSTRAR_LISTA_PRODUCTOS_KO, e.getMessage());
		}
		
		return context;
	}
}