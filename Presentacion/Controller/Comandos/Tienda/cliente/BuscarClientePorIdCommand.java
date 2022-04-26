package Presentacion.Controller.Comandos.Tienda.cliente;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.comandos.commandFactory.Command;
import negocio.SAFactoria.SAFactoria;
import negocio.cliente.TCliente;


public class BuscarClientePorIdCommand implements Command {

	public Context execute(Object data) {
		Context context;
		TCliente res = null;
		
		try {
			res = SAFactoria.getInstancia().generarSACliente().buscarClientePorId((int) data);
			
			if (res != null)
				context = new Context(Eventos.BUSCAR_CLIENTE_POR_ID_OK, res);
			else 
				context = new Context(Eventos.BUSCAR_CLIENTE_POR_ID_KO, "No ha sido posible encontrar el cliente");
		} catch (Exception e) {
			context = new Context(Eventos.BUSCAR_CLIENTE_POR_ID_KO, e.getMessage());
		}
		return context;
	}
}