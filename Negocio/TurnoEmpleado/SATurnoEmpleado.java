package Negocio.TurnoEmpleado;

import java.util.List;

public interface SATurnoEmpleado {
	
	public int registrarTurno(TTurnoEmpleado tTurnoEmpleado);
	
	public int modificarTurno(TTurnoEmpleado tTurnoEmpleado);
	
	public int borrarTurno(int id);
	
	public TTurnoEmpleado buscarTurnoPorID(int id);
	
	public List<TTurnoEmpleado> listarTurnos();
	
	public double calculoNominaDeTurno(int id);
	
}
