package Presentacion.Controller.Dispatcher;

import Presentacion.Controller.Context;
import Presentacion.museoVista.MuseoGui;
import Presentacion.museoVista.empleado.EmpleadoGUI;
import Presentacion.museoVista.entrada.EntradaGui;
import Presentacion.museoVista.facturaMuseo.FacturaMuseoGUI;
import Presentacion.museoVista.turnoEmpleado.TurnoEmpleadoGui;
import Presentacion.tiendaVista.TiendaGui;
import Presentacion.tiendaVista.cliente.ClienteGui;
import Presentacion.tiendaVista.descuento.DescuentoGui;
import Presentacion.tiendaVista.fabricante.FabricanteGUI;
import Presentacion.tiendaVista.factura.FacturaGUI;
import Presentacion.tiendaVista.producto.ProductoGui;
import Presentacion.vistaPrincipal.PrincipalGUI;

public class DispatcherImp extends Dispatcher {

	public void dispatch(Context response) {
		
		switch(response.getEvent()){ 
		
			//Vista Principal
			case MOSTRAR_INICIO:
				PrincipalGUI.getInstancia().actualizar(response);
				break;
				
			//Vista Tienda
			case MOSTRAR_TIENDA:
				TiendaGui.getInstancia().actualizar(response);
				break;
		
			//Fabricante
			case REGISTRAR_FABRICANTE_OK: 
			case REGISTRAR_FABRICANTE_KO:
			case MODIFICAR_FABRICANTE_OK: 
			case MODIFICAR_FABRICANTE_KO:
			case BORRAR_FABRICANTE_OK: 
			case BORRAR_FABRICANTE_KO:
			case MOSTRAR_LISTA_FABRICANTES_OK: 
			case MOSTRAR_LISTA_FABRICANTES_KO:
			case BUSCAR_FABRICANTE_POR_ID_OK:
			case BUSCAR_FABRICANTE_POR_ID_KO:
				FabricanteGUI.getInstancia().actualizar(response);
				break;
				
			//Cliente
			case REGISTRAR_CLIENTE_OK:
			case REGISTRAR_CLIENTE_KO:
			case MODIFICAR_CLIENTE_OK: 
			case MODIFICAR_CLIENTE_KO:
			case BORRAR_CLIENTE_OK:
			case BORRAR_CLIENTE_KO:
			case MOSTRAR_LISTA_CLIENTES_OK:
			case MOSTRAR_LISTA_CLIENTES_KO:
			case BUSCAR_CLIENTE_POR_ID_OK: 
			case BUSCAR_CLIENTE_POR_ID_KO:
			case LISTAR_CLIENTES_POR_TIPO_OK: 
			case LISTAR_CLIENTES_POR_TIPO_KO:
				ClienteGui.getInstance().actualizar(response);
				break;
				
			//Descuento
			case REGISTRAR_DESCUENTO_OK: 
			case REGISTRAR_DESCUENTO_KO:
			case MODIFICAR_DESCUENTO_OK:
			case MODIFICAR_DESCUENTO_KO:
			case BORRAR_DESCUENTO_OK:
			case BORRAR_DESCUENTO_KO:
			case MOSTRAR_LISTA_DESCUENTO_OK: 
			case MOSTRAR_LISTA_DESCUENTO_KO:
			case BUSCAR_DESCUENTO_POR_ID_OK: 
			case BUSCAR_DESCUENTO_POR_ID_KO:
				DescuentoGui.getInstancia().actualizar(response);
				break;
			
			//Producto
			case REGISTRAR_PRODUCTO_OK: 
			case REGISTRAR_PRODUCTO_KO:
			case MODIFICAR_PRODUCTO_OK: 
			case MODIFICAR_PRODUCTO_KO:
			case BORRAR_PRODUCTO_OK:
			case BORRAR_PRODUCTO_KO:
			case MOSTRAR_LISTA_PRODUCTOS_OK:
			case MOSTRAR_LISTA_PRODUCTOS_KO:
			case BUSCAR_PRODUCTO_POR_ID_OK:
			case BUSCAR_PRODUCTO_POR_ID_KO:
			case LEER_POR_ID_FABRICANTE_OK: 
			case LEER_POR_ID_FABRICANTE_KO:
			case MOSTRAR_PRODUCTO_POR_NOMBRE_FABRICANTE_OK:
			case MOSTRAR_PRODUCTO_POR_NOMBRE_FABRICANTE_KO:
				ProductoGui.getInstance().actualizar(response);
				break;
			
			//Factura
			case ABRIR_FACTURA:
			case AÑADIR_PRODUCTO_OK:
			case AÑADIR_PRODUCTO_KO:
			case BUSCAR_FACTURA_POR_ID_OK:
			case BUSCAR_FACTURA_POR_ID_KO:
			case CERRAR_FACTURA_OK:
			case CERRAR_FACTURA_KO:
			case DEVOLVER_PRODUCTO_OK:
			case DEVOLVER_PRODUCTO_KO:
			case MOSTRAR_LISTA_FACTURAS_OK:
			case MOSTRAR_LISTA_FACTURAS_KO:
			case QUITAR_PRODUCTO_OK:
			case QUITAR_PRODUCTO_KO:
			case MOSTRAR_PRODUCTO_MAS_COMPRADO_OK:
			case MOSTRAR_PRODUCTO_MAS_COMPRADO_KO:
			case MOSTRAR_FACTURA_CON_MAYOR_PORCENTAJE_DESCUENTO_OK:
			case MOSTRAR_FACTURA_CON_MAYOR_PORCENTAJE_DESCUENTO_KO:
			case LEER_POR_ID_DESCUENTO_OK:
			case LEER_POR_ID_DESCUENTO_KO:
			case LEER_POR_ID_CLIENTE_OK:
			case LEER_POR_ID_CLIENTE_KO:
			case MOSTRAR_FACTURA_TIENDA_COMPLETA_OK:
			case MOSTRAR_FACTURA_TIENDA_COMPLETA_KO:
				FacturaGUI.getInstancia().actualizar(response);
				break;
			
			//Vista Museo
			case MOSTRAR_MUSEO:
				MuseoGui.getInstancia().actualizar(response);
				break;
			
			//Empleado	
			case REGISTRAR_EMPLEADO_OK:
			case REGISTRAR_EMPLEADO_KO:
			case MODIFICAR_EMPLEADO_OK:
			case MODIFICAR_EMPLEADO_KO:
			case BORRAR_EMPLEADO_OK:
			case BORRAR_EMPLEADO_KO:
			case MOSTRAR_LISTA_EMPLEADOS_OK:
			case MOSTRAR_LISTA_EMPLEADOS_KO:
			case BUSCAR_EMPLEADO_POR_ID_OK:
			case BUSCAR_EMPLEADO_POR_ID_KO:
			case LISTAR_EMPLEADOS_POR_JORNADA_OK:
			case LISTAR_EMPLEADOS_POR_JORNADA_KO:
			case LEER_POR_ID_TURNO_OK:
			case LEER_POR_ID_TURNO_KO:
			case MOSTRAR_NOMINA_EMPLEADO_OK:
			case MOSTRAR_NOMINA_EMPLEADO_KO:
				EmpleadoGUI.getInstancia().actualizar(response);
				break;
				
			//Turno Empleado
			case REGISTRAR_TURNO_OK:
			case REGISTRAR_TURNO_KO:
			case MODIFICAR_TURNO_OK:
			case MODIFICAR_TURNO_KO:
			case BORRAR_TURNO_OK:
			case BORRAR_TURNO_KO:
			case LISTAR_TURNOS_OK:
			case LISTAR_TURNOS_KO:
			case BUSCAR_TURNO_POR_ID_OK:
			case BUSCAR_TURNO_POR_ID_KO:
			case CALCULO_NOMINA_TURNO_OK:
			case CALCULO_NOMINA_TURNO_KO:
				TurnoEmpleadoGui.getInstance().actualizar(response);
				break;
			
			//Entrada
			case REGISTRAR_ENTRADA_OK:
			case REGISTRAR_ENTRADA_KO:
			case MODIFICAR_ENTRADA_OK:
			case MODIFICAR_ENTRADA_KO:
			case BORRAR_ENTRADA_OK:
			case BORRAR_ENTRADA_KO:
			case MOSTRAR_LISTA_ENTRADAS_OK:
			case MOSTRAR_LISTA_ENTRADAS_KO:
			case BUSCAR_ENTRADA_POR_ID_OK:
			case BUSCAR_ENTRADA_POR_ID_KO:
				EntradaGui.getInstance().actualizar(response);
				break;
			
			//Factura Museo
			case ABRIR_FACTURA_MUSEO:
			case AÑADIR_ENTRADA:
			case AÑADIR_ENTRADA_OK:
			case AÑADIR_ENTRADA_KO:
			case BUSCAR_FACTURA_MUSEO_POR_ID:
			case BUSCAR_FACTURA_MUSEO_POR_ID_OK:
			case BUSCAR_FACTURA_MUSEO_POR_ID_KO:
			case DEVOLVER_ENTRADA:
			case DEVOLVER_ENTRADA_OK:
			case DEVOLVER_ENTRADA_KO:
			case QUITAR_ENTRADA:
			case QUITAR_ENTRADA_OK:
			case QUITAR_ENTRADA_KO:
			case MOSTRAR_LISTA_FACTURAS_MUSEO:
			case MOSTRAR_LISTA_FACTURAS_MUSEO_OK:
			case MOSTRAR_LISTA_FACTURAS_MUSEO_KO:
			case CERRAR_FACTURA_MUSEO:
			case CERRAR_FACTURA_MUSEO_OK:
			case CERRAR_FACTURA_MUSEO_KO:
			case LEER_POR_ID_EMPLEADO:
			case LEER_POR_ID_EMPLEADO_OK:
			case LEER_POR_ID_EMPLEADO_KO:
			case MOSTRAR_FACTURA_COMPLETA:
			case MOSTRAR_FACTURA_COMPLETA_OK:
			case MOSTRAR_FACTURA_COMPLETA_KO:
				FacturaMuseoGUI.getInstance().actualizar(response);
				break;
				
			default:
				System.out.println("Evento en Dispatcher desconocido: " + response.getEvent().toString());
				break;
				
		}
		
		
	}
}