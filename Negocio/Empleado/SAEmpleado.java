package Negocio.Empleado;

import java.util.List;

public interface SAEmpleado {

	public int registrarEmpleado(TEmpleado empleado);
	
	public int modificarEmpleado(TEmpleado empleado);
	
	public int borrarEmpleado(int id);
	
	public List<TEmpleado> mostrarListaEmpleados();
	
	public TEmpleado buscarEmpleadoPorId (int id);
	
	public List<TEmpleado> listarPorJornada (boolean isParcial);
	
	public List<TEmpleado> leerPorIdDeTurnoEmpleado (int idTurnoEmpleado);
	
	//public List<TFactura> leerFacturaPorIdEmpleado (int idEmpleado);
	
	public double mostrarNominaEmpleado (int idEmpleado);
	
}
