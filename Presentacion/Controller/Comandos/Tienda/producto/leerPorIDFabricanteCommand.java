package Presentacion.Controller.Comandos.Tienda.producto;

import java.util.List;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.comandos.commandFactory.Command;
import negocio.SAFactoria.SAFactoria;
import negocio.producto.TProducto;

public class leerPorIDFabricanteCommand implements Command {
	
	public Context execute(Object data) {
		Context context;
		List<TProducto> res = null;
		
		try{
			res = SAFactoria.getInstancia().generarSAProducto().leerPorIdFabricante((int)data);
			
			if(res != null)
				context = new Context(Eventos.LEER_POR_ID_FABRICANTE_OK, res);
			else
				context = new Context(Eventos.LEER_POR_ID_FABRICANTE_KO, "No ha sido posible encontrar productos del fabricante.");
		} catch (Exception e){
			context = new Context(Eventos.LEER_POR_ID_FABRICANTE_KO, e.getMessage());
		}
		
		return context;
	}
}