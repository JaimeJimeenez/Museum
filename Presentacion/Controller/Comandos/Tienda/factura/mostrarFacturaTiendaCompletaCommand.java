package Presentacion.Controller.Comandos.Tienda.Factura;

import java.util.LinkedList;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.Factura.TFacturaTiendaCompleta;

public class mostrarFacturaTiendaCompletaCommand implements Command {

	public Context execute(Object data) {
		Context context;
		
		try{
			LinkedList<TFacturaTiendaCompleta> listaFacturas = SAFactoria.getInstancia().generarSAFactura().mostrarFacturaCompleta();
			if(!listaFacturas.isEmpty()){
				context = new Context(Eventos.MOSTRAR_FACTURA_TIENDA_COMPLETA_OK, listaFacturas);
			}
			else{
				context = new Context(Eventos.MOSTRAR_FACTURA_TIENDA_COMPLETA_KO, "No existe ninguna factura");
			}
		}
		catch(Exception e){
			e.printStackTrace();
			context = new Context(Eventos.MOSTRAR_FACTURA_TIENDA_COMPLETA_KO, e.getMessage());
		}
		return context;
	}
	
}
