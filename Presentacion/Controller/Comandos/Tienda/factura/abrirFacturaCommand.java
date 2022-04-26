package Presentacion.Controller.Comandos.Tienda.factura;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.comandos.commandFactory.Command;
import negocio.SAFactoria.SAFactoria;

public class abrirFacturaCommand implements Command{

	public Context execute(Object data) {
		return new Context(Eventos.ABRIR_FACTURA, SAFactoria.getInstancia().generarSAFactura().abrirFactura());
	}

}
