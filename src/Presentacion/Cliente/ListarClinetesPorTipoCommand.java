package src.Presentacion.Cliente;

import java.util.List;

import src.Negocio.Cliente.TCliente;
import src.Negocio.SAFactoria.SAFactoria;
import src.Presentacion.CommandFactory.Command;
import src.Presentacion.Controller.Context;
import src.Presentacion.Controller.Eventos;

public class ListarClientesPorTipoCommand implements Command {
	
	public Context execute(Object data) {
		Context context;
		List<TCliente> res;
		
		try {
			res = SAFactoria.getInstancia().generarSACliente().listarClientesPorTipo((boolean) data);
			
			if (res == null)
				context = new Context(Eventos.LISTAR_CLIENTES_POR_TIPO_OK, res);
			else 
				context = new Context(Eventos.LISTAR_CLIENTES_POR_TIPO_KO, "No existe ningun cliente con ese tipo.");
			
		} catch(Exception e) {
			context = new Context(Eventos.LISTAR_CLIENTES_POR_TIPO_KO, "Error al intentar listar los clientes de este tipo");
		}
		
		return context;
	}
}