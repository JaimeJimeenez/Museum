package Presentacion.Controller.Comandos.Tienda.Factura;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;
import Negocio.Factura.TCarrito;

public class buscarIDFacturaCommand implements Command {

	public Context execute(Object data) {
		Context context;
		TCarrito tCarrito = null;

		try {
			tCarrito = SAFactoria.getInstancia().generarSAFactura().bucarFacturaID((int) data);

			if (tCarrito != null)
				context = new Context(Eventos.BUSCAR_FACTURA_POR_ID_OK, tCarrito);
			else
				context = new Context(Eventos.BUSCAR_FACTURA_POR_ID_KO, "Factura no encontrada");

		} catch (Exception e) {
			context = new Context(Eventos.BUSCAR_FACTURA_POR_ID_KO, e.getMessage());
		}
		return context;
	}
}
