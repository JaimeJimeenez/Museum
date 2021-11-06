package src.Presentacion.Cliente;

import java.util.List;

import negocio.SAFactoria.SAFactoria;
import negocio.cliente.TCliente;
import presentacion.commandFactory.Command;
import presentacion.controller.Context;
import presentacion.controller.Eventos;

public class MostrarListaClientesCommand implements Command {
	
	public Context execute(Object data ) {
		Context context;
		List<TCliente> res;
		
		try {
			
			res = SAFactoria.getInstancia().generarSACliente().mostrarListaClientes();
			
			if (res != null) 
				context = new Context(Eventos.MOSTRAR_LISTA_CLIENTES_OK, "Lista clientes");
			else
				context = new Context(Eventos.MOSTRAR_LISTA_CLIENTES_KO, "No existe ning�n cliente");
			
		} catch (Exception e) {
			context = new Context(Eventos.MOSTRAR_LISTA_CLIENTES_KO, e.getMessage());
		}
		
		return context;
	}
}