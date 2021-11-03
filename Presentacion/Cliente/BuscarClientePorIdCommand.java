package Presentacion.Cliente;

import Negocio.SAFactoria.SAFactoria;
import Negocio.Cliente.TCliente;
import Presentacion.CommandFactory.Command;
import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;


public class BuscarClientePorIdCommand implements Command {

	public Context execute(Object data) {
		Context context;
		TCliente res = null;
		
		try {
			res = SAFactoria.getInstancia().generarSACliente().buscarClientePorId((int) data);
			
			if (res != null)
				context = new Context(Eventos.BUSCAR_CLIENTE_POR_ID_OK, "Cliente encontrado");
			else 
				context = new Context(Eventos.BUSCAR_CLIENTE_POR_ID_KO, "No ha sido posible encontrar el cliente");
		} catch (Exception e) {
			context = new Context(Eventos.BUSCAR_CLIENTE_POR_ID_KO, "No ha sido posible encontrar el cliente");
		}
		return context;
	}
}