package presentacion.Cliente;

import negocio.SAFactoria.SAFactoria;
import presentacion.commandFactory.Command;
import presentacion.controller.Context;
import presentacion.controller.Eventos;

public class ModificarClienteCommand implements Command {
	
	public Context execute(Object data) {
		Context context;
		int res;
		
		try {
			res = SAFactoria.getInstancia().generarSACliente().modificarCliente((TCliente) data);
			
			if (res > 0) 
				context = new Context(Eventos.MODIFICAR_CLIENTE_OK, "Cliente modificado");
			else 
				context = new Context(Eventos.MODIFICAR_CLIENTE_KO, "No existe ningun cliente con esas caracter�sticas");
			
		} catch(Exception e) {
			context = new Context(Eventos.MODIFICAR_CLIENTE_KO, "Error al modificar cliente");
		}
		
		return context;
	}
}