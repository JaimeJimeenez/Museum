package Presentacion.Controller.Comandos.Tienda.cliente;

import java.util.List;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.comandos.commandFactory.Command;
import negocio.SAFactoria.SAFactoria;
import negocio.cliente.TCliente;

public class ListarClientesPorTipoCommand implements Command {
	
	public Context execute(Object data) {
		Context context;
		List<TCliente> res;
		
		try {
			res = SAFactoria.getInstancia().generarSACliente().listarClientesPorTipo((boolean) data);
			
			if (res != null)
				context = new Context(Eventos.LISTAR_CLIENTES_POR_TIPO_OK, res);
			else 
				context = new Context(Eventos.LISTAR_CLIENTES_POR_TIPO_KO, "No existe ningun cliente con ese tipo.");
			
		} catch(Exception e) {
			context = new Context(Eventos.LISTAR_CLIENTES_POR_TIPO_KO, "Error al intentar listar los clientes de este tipo");
		}
		
		return context;
	}
}