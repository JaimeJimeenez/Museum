package Presentacion.Controller.Comandos.Tienda.Cliente;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.*;
import Negocio.SAFactoria.SAFactoria;


public class BorrarClienteCommand implements Command {

	@Override
	public Context execute(Object data) {
		Context context = null;
		int res;
		
		try {
			res = SAFactoria.getInstancia().generarSACliente().borrarCliente((int) data);
			
			if (res > 0)
				context = new Context(Eventos.BORRAR_CLIENTE_OK, "Cliente borrado.");
			else
				context = new Context(Eventos.BORRAR_CLIENTE_KO, "Error al borrrar cliente");
			
		} catch (Exception e) {
			context = new Context(Eventos.BORRAR_CLIENTE_KO, e.getMessage());
		}
		
		
		return context;
	}
}