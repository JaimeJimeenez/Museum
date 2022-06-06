package Presentacion.Controller.Comandos.Tienda.Cliente;

import java.util.List;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.*;
import Negocio.SAFactoria.SAFactoria;
import Negocio.Cliente.TCliente;

public class MostrarListaClientesCommand implements Command {
	
	public Context execute(Object data ) {
		Context context;
		List<TCliente> res;
		
		try {
			
			res = SAFactoria.getInstancia().generarSACliente().mostrarListaClientes();
			
			if (res != null) 
				context = new Context(Eventos.MOSTRAR_LISTA_CLIENTES_OK, res);
			else
				context = new Context(Eventos.MOSTRAR_LISTA_CLIENTES_KO, "No existe ningun cliente");
			
		} catch (Exception e) {
			context = new Context(Eventos.MOSTRAR_LISTA_CLIENTES_KO, e.getMessage());
		}
		
		return context;
	}
}