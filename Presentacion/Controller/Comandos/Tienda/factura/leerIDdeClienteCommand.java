package Presentacion.Controller.Comandos.Tienda.factura;

import java.util.List;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.comandos.commandFactory.Command;
import negocio.SAFactoria.SAFactoria;
import negocio.cliente.TCliente;
import negocio.factura.TFacturaTienda;

public class leerIDdeClienteCommand implements Command{

	public Context execute(Object data) {
		Context context;
		try{
			List<TFacturaTienda> listaClientes = SAFactoria.getInstancia().generarSAFactura().leerIDdeCliente((int) data);
			
			if(listaClientes != null){
				context = new Context(Eventos.LEER_POR_ID_CLIENTE_OK, listaClientes);
			}
			else{
				context = new Context(Eventos.LEER_POR_ID_CLIENTE_KO, "No se ha encontrado ningun cliente con ese id.");
			}
		}
		catch(Exception e){
			context = new Context(Eventos.LEER_POR_ID_CLIENTE_KO, e.getMessage());
		}
		return context;
	}

}
