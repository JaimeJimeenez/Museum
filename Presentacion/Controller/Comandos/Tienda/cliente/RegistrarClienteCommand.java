package Presentacion.Controller.Comandos.Tienda.Cliente;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.*;
import Negocio.SAFactoria.SAFactoria;
import Negocio.Cliente.TCliente;

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