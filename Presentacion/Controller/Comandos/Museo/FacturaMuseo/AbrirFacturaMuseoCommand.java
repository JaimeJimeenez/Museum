package Presentacion.Controller.Comandos.Museo.FacturaMuseo;

import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.CommandFactory.Command;
import Negocio.SAFactoria.SAFactoria;

public class AbrirFacturaMuseoCommand implements Command {

	@Override
	public Context execute(Object data) {
		return new Context(Eventos.ABRIR_FACTURA_MUSEO, SAFactoria.getInstancia().generarSAFacturaMuseo().abrirFactura());
	}

}
