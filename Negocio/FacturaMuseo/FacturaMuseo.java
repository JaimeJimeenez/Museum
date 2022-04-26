package Negocio.FacturaMuseo;

import java.util.List;
import java.util.Date;
import jakarta.persistence.*;
import Negocio.Empleado.Empleado;

@Entity
@Table(name = "entity_factura_museo")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name = "negocio.facturaMuseo.facturaMuseo.buscarPorIDFacturaMuseo", query = "SELECT f FROM FacturaMuseo f WHERE f.id =:id_factura"),
	@NamedQuery(name = "negocio.facturaMuseo.facturaMuseo.leerPorIdEmpleado", query = "SELECT f FROM FacturaMuseo f WHERE f.empleado.id =:id")
})
public class FacturaMuseo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_factura")
	private Integer id;
	
//	@Column(name = "id_empleado")
//	private Integer idEmpleado;
	
	@Column(name = "precio_total")
	private double precioTotal;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@OneToMany(mappedBy = "factura")
	private List<LineaFactura> lineasFactura;
	
	@Column(name = "activo", columnDefinition = "boolean default true")
	private Boolean activo;
	
	@ManyToOne
	@JoinColumn(name = "idEmpleado")
	private Empleado empleado;
	
	@Version
	private Integer version;
	
	public FacturaMuseo() {}
	
	public FacturaMuseo(Integer id, Empleado Empleado, double precioTotal, Date fecha, boolean activo){
		this.id =id;
		this.empleado = Empleado;
		this.precioTotal = precioTotal;
		this.fecha = fecha;
		this.activo = activo;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Integer getId() {return id;}

	public void setId(Integer id) {this.id = id;}

//	public Integer getIdEmpleado() {return idEmpleado;}
//
//	public void setIdEmpleado(Integer idEmpleado) {this.idEmpleado = idEmpleado;}

	public double getPrecioTotal() {return precioTotal;}

	public void setPrecioTotal(double precioTotal) {this.precioTotal = precioTotal;}

	public Date getFecha() {return fecha;}

	public void setFecha(Date fecha) {this.fecha = fecha;}

	public Boolean isActivo() {return activo;}

	public void setActivo(Boolean activo) {this.activo = activo;}
	
	public List<LineaFactura> getLineasFactura() {
		return lineasFactura;
	}

	public void setLineasFactura(List<LineaFactura> lineasFactura) {
		this.lineasFactura = lineasFactura;
	}


	public Integer getVersion() {return version;}

	public void setVersion(Integer version) {this.version = version;}


}