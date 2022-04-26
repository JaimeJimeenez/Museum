package Negocio.TurnoEmpleado;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import jakarta.persistence.*;
import Negocio.Empleado.Empleado;
import Negocio.FacturaMuseo.FacturaMuseo;

@Entity
@Table(name = "entity_turno_empleado")
@NamedQueries({
	@NamedQuery(name = "negocio.turnoEmpleado.TurnoEmpleado.buscarPorHoras", query = "SELECT t FROM TurnoEmpleado t WHERE t.horaEntrada = :hora_entrada AND t.horaSalida = :hora_salida"),
	@NamedQuery(name = "negocio.turnoEmpleado.TurnoEmpleado.listarTurnos", query = "SELECT t FROM TurnoEmpleado t ")
})
public class TurnoEmpleado {
	
	@Version
	private int version;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_turno")
	private int id;
	
	@Column(name = "hora_entrada")
	private String horaEntrada;
	
	@Column(name = "hora_salida")
	private String horaSalida;
	
	@Column(name = "activo", columnDefinition = "boolean default true")
	private boolean activo;
	
	@OneToMany(mappedBy = "TurnoEmpleado")
	private Collection<Empleado> empleados;
	
	public TurnoEmpleado(){}
	
	public int getId() { return id; }
	
	public void setId(int id) { this.id = id; }
	
	public String getHoraEntrada() { return horaEntrada; }
	
	public void setHoraEntrada(String horaEntrada) {
		if(comprobarHoras(horaEntrada)) throw new IllegalArgumentException("Hora entrada en formato incorrecto");
		
		this.horaEntrada = horaEntrada; 
	}
	
	public String getHoraSalida() { return horaSalida; }
	
	public void setHoraSalida(String horaSalida) {
		if(comprobarHoras(horaSalida)) throw new IllegalArgumentException("Hora salida en formato incorrecto");
		
		this.horaSalida = horaSalida; 
	}
	
	public Collection<Empleado> getEmpleados() { return empleados; }

	public void setActivo(boolean activo) { this.activo = activo; }
	
	public boolean isActivo() { return activo; }
	
	public int getVersion() { return version; }

	public void setVersion(int version) { this.version = version; }
	
	private static boolean comprobarHoras(String hora){
		DateFormat dateF = new SimpleDateFormat("HH:mm");
		try{
			dateF.parse(hora);
			return false; //false para que no lance excepcion al ser llamado
		}catch(ParseException e){
			return true;
		}
		
	}
	
	
}
