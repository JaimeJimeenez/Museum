package Negocio.Empleado;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.*;
import negocio.facturaMuseo.FacturaMuseo;
import negocio.turnoEmpleado.TurnoEmpleado;

@Entity
@Table(name = "entity_empleado")
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name = "negocio.empleado.Empleado.buscarPorDni", query = "SELECT e FROM Empleado e WHERE e.dni =:dni"),
	@NamedQuery(name = "negocio.empleado.Empleado.mostrarListaEmpleados", query = "SELECT e FROM Empleado e WHERE e.activo = 1"),
	@NamedQuery(name = "negocio.empleado.Empleado.leerPorIdDeTurnoEmpleado", query = "SELECT e FROM Empleado e WHERE e.turnoEmpleado.id =:id"),
	@NamedQuery(name = "negocio.empleado.Empleado.listarPorJornada", query = "SELECT e FROM Empleado e WHERE e.activo = 1 AND e.isParcial =:jornada")
})
public abstract class Empleado {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empleado")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "idTurnoEmpleado")
	private TurnoEmpleado turnoEmpleado;
	
	@Column(name = "dni")
	private String dni;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "isParcial", columnDefinition = "boolean default true")
	private boolean isParcial;
	
	@Column(name = "activo", columnDefinition = "boolean default true")
	private boolean activo;
	
	@Column(name = "nomina")
	private double nomina;
	
	@OneToMany(mappedBy = "Empleado")
	private Collection<FacturaMuseo> facturas;
	
	@Version
	private Integer version;
	
	public Empleado() {
	}
	
	public Empleado(int id, String dni, String nombre, TurnoEmpleado turnoEmpleado, boolean activo, double nomina, boolean isParcial) {
		this.id=id;
		this.dni=dni;
		this.nombre=nombre;
		this.turnoEmpleado = turnoEmpleado;
		this.activo=activo;
		this.nomina = nomina;
		this.isParcial = isParcial;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public boolean isParcial() {
		return isParcial;
	}
	
	public void setParcial(boolean isParcial) {
		this.isParcial = isParcial;
	}
	
	public double getNomina(){
		return nomina;
	}
	
	public void setNomina(double nomina){
		this.nomina = nomina;
	}

	public TurnoEmpleado getTurnoEmpleado(){
		return turnoEmpleado;
	}
	
	public void setTurnoEmpleado(TurnoEmpleado turnoEmpleado) {
		this.turnoEmpleado = turnoEmpleado;
	}
	
	public Collection<FacturaMuseo> getFacturas() {
		return facturas;
	}

	public void setFacturas(Collection<FacturaMuseo> facturas) {
		this.facturas = facturas;
	}

	public int getVersion(){
		return version;
	}
	
	public void setVersion(int version){
		this.version = version;
	}
	
	public abstract double calcularNomina();
	
}