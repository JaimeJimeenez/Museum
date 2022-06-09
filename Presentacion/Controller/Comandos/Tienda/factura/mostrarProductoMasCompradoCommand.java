package Presentacion.Controller.Comandos.Tienda.Factura;

import java.util.List;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.Producto.TProducto;

public class mostrarProductoMasCompradoCommand implements Command{

	public Context execute(Object data) {
		Context context;
		try{
			List<TProducto> productos = SAFactoria.getInstancia().generarSAFactura().mostrarProductoMasComprado();
			
			if (productos != null){
				context = new Context(Eventos.MOSTRAR_PRODUCTO_MAS_COMPRADO_OK, productos);
			}
			else{
				context = new Context(Eventos.MOSTRAR_PRODUCTO_MAS_COMPRADO_KO, "No hay productos que mostrar");
			}
		}
		catch(Exception e){
			context = new Context(Eventos.MOSTRAR_PRODUCTO_MAS_COMPRADO_KO, e.getMessage());
		}
		return context;
	}

}
