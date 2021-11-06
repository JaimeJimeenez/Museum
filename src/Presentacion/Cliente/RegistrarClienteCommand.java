package src.Presentacion.Cliente;

import java.sql.SQLException;

import negocio.SAFactoria.SAFactoria;
import negocio.cliente.TCliente;
import presentacion.commandFactory.Command;
import presentacion.controller.Context;
import presentacion.controller.Eventos;

public class RegistrarClienteCommand implements Command {
	
	public Context execute(Object data) {		
		Context context;
		int res;
		
		try {
			
			res = SAFactoria.getInstancia().generarSACliente().registrarCliente((TCliente) data);
			
			if (res > 0)
					context = new Context(Eventos.REGISTRAR_CLIENTE_OK, "Cliente registrado con ID: " + res + ".");
			else 
				context = new Context(Eventos.REGISTRAR_CLIENTE_KO, "Ya existe un cliente con ese DNI.");
			
			
		} catch (Exception e) {
			context = new Context(Eventos.REGISTRAR_CLIENTE_KO, e.getMessage());
		}
		
		return context;
	}
}