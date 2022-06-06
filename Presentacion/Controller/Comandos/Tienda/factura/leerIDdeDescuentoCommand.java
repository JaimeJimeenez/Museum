package Presentacion.Controller.Comandos.Tienda.Factura;

import java.util.List;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.comandos.commandFactory.Command;
import negocio.SAFactoria.SAFactoria;
import negocio.factura.TFacturaTienda;

public class leerIDdeDescuentoCommand implements Command{

	public Context execute(Object data) {
		Context context;
		try{
			List<TFacturaTienda> descuento = SAFactoria.getInstancia().generarSAFactura().leerIDdeDescuento((int) data);
			
			if(descuento != null){
				context = new Context(Eventos.LEER_POR_ID_DESCUENTO_OK, descuento);
			}
			else{
				context = new Context(Eventos.LEER_POR_ID_DESCUENTO_KO, "El cliente no se pudo leer");
			}
		}
		catch(Exception e){
			context = new Context(Eventos.LEER_POR_ID_DESCUENTO_KO, e.getMessage());
		}
		return context;
	}

}
