package Presentacion.Controller.Comandos.Tienda.Factura;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.Factura.TCarrito;

public class añadirProductoCommand implements Command{

	public Context execute(Object data) {
		Context context;
		try{
			TCarrito carrito = SAFactoria.getInstancia().generarSAFactura().añadirProducto((TCarrito) data);
			if(carrito != null){
				context = new Context(Eventos.AÑADIR_PRODUCTO_OK, carrito);
			}
			else{
				context = new Context(Eventos.AÑADIR_PRODUCTO_KO, "El producto no se ha podido añadir");
			}
		}
		catch(Exception e){
			context = new Context(Eventos.AÑADIR_PRODUCTO_KO, e.getMessage());
		}
		return context;
	}
}
