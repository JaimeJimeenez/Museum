package src.Presentacion.CommandFactory;

import presentacion.controller.Eventos;
import presentacion.descuento.*;
import presentacion.fabricante.*;
import presentacion.producto.*;
import presentacion.proveedor.*;
import src.Presentacion.Cliente.*;

public class CommandFactoryImp extends CommandFactory {

	public Command getCommand(Eventos evento) {
		Command command = null;
		
		switch(evento){
		
		// FABRICANTE
		case REGISTRAR_FABRICANTE: command = new registrarFabricanteCommand();
		break;
		case MODIFICAR_FABRICANTE: command = new modificarFabricanteCommand();
		break;
		case BORRAR_FABRICANTE: command = new borrarFabricanteCommand();
		break;
		case MOSTRAR_LISTA_FABRICANTES: command = new mostrarListaFabricantesCommand();
		break;
		case BUSCAR_FABRICANTE_POR_ID: command = new buscarFabricantesPorIdCommand();
		break;
		
		// CLIENTE
		case REGISTRAR_CLIENTE: command = new RegistrarClienteCommand();
		break;
		case MODIFICAR_CLIENTE: command = new ModificarClienteCommand();
		break;
		case BORRAR_CLIENTE: command = new BorrarClienteCommand();
		break;
		case MOSTRAR_LISTA_CLIENTES: command = new MostrarListaClientesCommand();
		break;
		case LISTAR_CLIENTES_POR_TIPO: command = new ListarClientesPorTipoCommand();
		break;
		case BUSCAR_CLIENTE_POR_ID: command = new BuscarClientePorIdCommand();
		break;
		
		// DESCUENTO
		case REGISTRAR_DESCUENTO: command = new RegistrarDescuentoCommand();
		break;
		case MODIFICAR_DESCUENTO: command = new ModificarDescuentoCommand();
		break;
		case BORRAR_DESCUENTO: command = new BorrarDescuentoCommand();
		break;
		case MOSTRAR_LISTA_DESCUENTO: command = new MostrarListaDescuentoCommand();
		break;
		case BUSCAR_DESCUENTO_POR_ID: command = new BuscarDescuentoPorIdCommand();
		break;
		
		//PRODUCTO
		case REGISTRAR_PRODUCTO: command = new RegistrarProductoCommand();
		break;
		case MODIFICAR_PRODUCTO: command = new ModificarProductoCommand();
		break;
		case BORRAR_PRODUCTO: command = new BorrarProductoCommand();
		break;
		case MOSTRAR_LISTA_PRODUCTOS: command = new MostrarListaProductoCommand();
		break;
		case BUSCAR_PRODUCTO_POR_ID: command = new BuscarProductoPorIDCommand();
		break;
		case MOSTRAR_PRODUCTO_POR_NOMBRE_FABRICANTE: command = new MostrarProductoPorNombreFabricanteCommand();
		break;
		case LEER_POR_ID_FABRICANTE: command = new leerPorIDFabricanteCommand();
		break;
		
		// PROVEEDOR
		case REGISTRAR_PROVEEDOR: command = new RegistrarProveedorCommand();
		break;
		case MODIFICAR_PROVEEDOR: command = new ModificarProveedorCommand();
		break;
		case BORRAR_PROVEEDOR: command = new BorrarProveedorCommand();
		break;
		case LISTAR_PROVEEDORES: command = new ListarProveedoresCommand();
		break;
		case BUSCAR_PROVEEDOR: command = new BuscarProveedorCommand();
		break;
		
		}
		
		return command;
	}
}