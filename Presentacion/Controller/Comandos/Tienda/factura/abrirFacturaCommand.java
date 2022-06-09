package Presentacion.Controller.Comandos.Tienda.Factura;

import Negocio.SAFactoria.SAFactoria;
import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;

public class AbrirFacturaCommand implements Command{

	public Context execute(Object data) {
		return new Context(Eventos.ABRIR_FACTURA, SAFactoria.getInstancia().generarSAFactura().abrirFactura());
	}

}
