package Presentacion.Controller.Comandos.CommandFactory;

import Presentacion.Controller.Eventos;
import Presentacion.Controller.Comandos.Museo.*;
import Presentacion.Controller.Comandos.Museo.Empleado.*;
import Presentacion.Controller.Comandos.Museo.Entrada.*;
import Presentacion.Controller.Comandos.Museo.FacturaMuseo.*;
import Presentacion.Controller.Comandos.Museo.TurnoEmpleado.*;
import Presentacion.Controller.Comandos.Tienda.*;
import Presentacion.Controller.Comandos.Tienda.Cliente.*;
import Presentacion.Controller.Comandos.Tienda.Descuento.*;
import Presentacion.Controller.Comandos.Tienda.Fabricante.*;
import Presentacion.Controller.Comandos.Tienda.Factura.*;
import Presentacion.Controller.Comandos.Tienda.Producto.*;


public class CommandFactoryImp extends CommandFactory {

	public Command getCommand(Eventos evento) {
		Command command = null;
		
		switch(evento){
		
		//INICIO
		case MOSTRAR_INICIO: command = new MostrarGuiCommand(evento); 
		break;
		
		//TIENDA
		case MOSTRAR_TIENDA: command = new MostrarGuiCommand(evento);
		break;
		
		// FABRICANTE
		case REGISTRAR_FABRICANTE: command = new RegistrarFabricanteCommand();
		break;
		case MODIFICAR_FABRICANTE: command = new ModificarFabricanteCommand();
		break;
		case BORRAR_FABRICANTE: command = new BorrarFabricanteCommand();
		break;
		case MOSTRAR_LISTA_FABRICANTES: command = new MostrarListaFabricantesCommand();
		break;
		case BUSCAR_FABRICANTE_POR_ID: command = new BuscarFabricantesPorIdCommand();
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
		
		// PRODUCTO
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
		
		// FACTURA
		case ABRIR_FACTURA: command = new AbrirFacturaCommand();
		break;
		case AÑADIR_PRODUCTO: command = new añadirProductoCommand();
		break;
		case BUSCAR_FACTURA_POR_ID: command = new buscarIDFacturaCommand();
		break;
		case CERRAR_FACTURA: command = new cerrarFacturaCommand();
		break;
		case DEVOLVER_PRODUCTO: command = new devolverProductoCommand();
		break;
		case LEER_POR_ID_CLIENTE: command = new leerIDdeClienteCommand();
		break;
		case LEER_POR_ID_DESCUENTO: command = new leerIDdeDescuentoCommand();
		break;
		case MOSTRAR_FACTURA_CON_MAYOR_PORCENTAJE_DESCUENTO: command = new mostrarFacturaConMayorPorcentajeDescuentoCommand();
		break;
		case MOSTRAR_LISTA_FACTURAS: command = new mostrarListaFacturasCommand();
		break;
		case MOSTRAR_PRODUCTO_MAS_COMPRADO: command = new mostrarProductoMasCompradoCommand();
		break;
		case QUITAR_PRODUCTO: command = new quitarProductoCommand();
		break;
		case MOSTRAR_FACTURA_TIENDA_COMPLETA: command = new mostrarFacturaTiendaCompletaCommand();
		break;
		
		//MUSEO
		case MOSTRAR_MUSEO: command = new MostrarGuiCommand(evento);
		break;
		
		//EMPLEADO
		case REGISTRAR_EMPLEADO: command = new RegistrarEmpleadoCommand();
		break;
		case MODIFICAR_EMPLEADO: command = new ModificarEmpleadoCommand();
		break;
		case BORRAR_EMPLEADO: command = new BorrarEmpleadoCommand();
		break;
		case MOSTRAR_LISTA_EMPLEADOS: command = new MostrarListaEmpleadosCommand();
		break;
		case BUSCAR_EMPLEADO_POR_ID: command = new BuscarEmpleadoPorIdCommand();
		break;
		case LISTAR_EMPLEADOS_POR_JORNADA: command = new ListarEmpleadosPorJornadaCommand();
		break;
		case LEER_POR_ID_TURNO: command = new LeerPorIdDeTurnoCommand();
		break;
		case MOSTRAR_NOMINA_EMPLEADO: command = new MostrarNominaEmpleadoCommand();
		break;
		
		//TURNO EMPLEADO
		case REGISTRAR_TURNO: command = new RegistrarTurnoEmpleadoCommand();
		break;
		case MODIFICAR_TURNO: command = new ModificarTurnoEmpleadoCommand();
		break;
		case BORRAR_TURNO: command = new BorrarTurnoEmpleadoCommand();
		break;
		case LISTAR_TURNOS: command = new ListarTurnosCommand();
		break;
		case BUSCAR_TURNO_POR_ID: command = new BuscarPorIdTurnoCommand();
		break;
		case CALCULO_NOMINA_TURNO: command = new CalculoNominaCommand();
		break;
		
		//ENTRADA
		case REGISTRAR_ENTRADA: command = new RegistrarEntradaCommand();
		break;
		case MODIFICAR_ENTRADA: command = new ModificarEntradaCommand();
		break;
		case BORRAR_ENTRADA: command = new BorrarEntradaCommand();
		break;
		case MOSTRAR_LISTA_ENTRADAS: command = new MostrarListaEntradasCommand();
		break;
		case BUSCAR_ENTRADA_POR_ID: command = new BuscarEntradaPorIDCommand();
		break;
		
		
		//FACTURA(MUSEO)
		case ABRIR_FACTURA_MUSEO: command = new AbrirFacturaMuseoCommand();
		break;
		case AÑADIR_ENTRADA: command = new AñadirEntradaCommand();
		break;
		case BUSCAR_FACTURA_MUSEO_POR_ID: command = new BuscarFacturaMuseoPorIDCommand();
		break;
		case DEVOLVER_ENTRADA: command = new DevolverEntradaCommand();
		break;
		case QUITAR_ENTRADA: command = new QuitarEntradaCommand();
		break;
		case MOSTRAR_LISTA_FACTURAS_MUSEO: command = new ListarFacturasMuseoCommand();
		break;
		case CERRAR_FACTURA_MUSEO: command = new CerrarFacturaMuseoCommand();
		break;
		case LEER_POR_ID_EMPLEADO: command = new LeerFacturaMuseoPorIDEmpleadoCommand();
		break;
		case MOSTRAR_FACTURA_COMPLETA: command = new MostrarFacturaCompletaCommand();
		break;
		}
		
		return command;
	}
}