package Presentacion.Controller.Comandos.Tienda.cliente;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.comandos.commandFactory.Command;
import negocio.SAFactoria.SAFactoria;
import negocio.cliente.TCliente;

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