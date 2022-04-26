package Presentacion.Controller.Comandos.Tienda.factura;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.comandos.commandFactory.Command;
import negocio.SAFactoria.SAFactoria;
import negocio.factura.TCarrito;

public class cerrarFacturaCommand implements Command {

	public Context execute(Object data) {
		Context context;
		boolean cerrada;

		try {
			cerrada = SAFactoria.getInstancia().generarSAFactura().cerrarFactura((TCarrito) data);

			if (cerrada) {
				context = new Context(Eventos.CERRAR_FACTURA_OK, "Factura cerrado con exito");
			} else {
				context = new Context(Eventos.CERRAR_FACTURA_KO, "Error al cerrar la factura");
			}

		} catch (Exception e) {
			context = new Context(Eventos.CERRAR_FACTURA_KO, e.getMessage());
		}

		return context;
	}

}
