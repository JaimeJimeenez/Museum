package Presentacion.Controller.Comandos.Tienda.Factura;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.Factura.TCarrito;

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
