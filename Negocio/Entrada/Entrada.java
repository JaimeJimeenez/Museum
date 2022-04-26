package Negocio.Entrada;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import jakarta.persistence.*;
import negocio.facturaMuseo.LineaFactura;

@Entity
@Table(name = "entity_entrada")
@Inheritance(strategy = InheritanceType.JOINED)

 @NamedQueries({
	 @NamedQuery(name = "negocio.entrada.Entrada.buscarPorId", query = "SELECT e FROM Entrada e WHERE e.id = :id"), // No lo veo necesario porque existe em.find
	 @NamedQuery(name = "negocio.entrada.Entrada.listarEntradas", query = "SELECT e FROM Entrada e")
 })
 
public class Entrada {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_entrada")
	private Integer id;
	
	@Column(name = "obra")
	private String obra;
	
	@Column(name = "sala")
	private Integer sala;
	
	@Column(name = "numeroEntradas")
	private Integer numeroEntradas;
	
	@Column(name = "precio")
	private double precio;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@OneToMany(mappedBy = "entrada")
	private Collection<LineaFactura> lineas;

	@Column(name = "activo", columnDefinition = "boolean default true")
	private boolean activo;
	
	@Version
	private Integer version;

	public Entrada() { }
	
	public Entrada(Integer id, Date fecha, double precio, boolean activo, Integer numeroEntradas, String obra,
			Integer sala) {
		this.id = id;
		this.fecha = fecha;
		this.precio = precio;
		this.activo = activo;
		this.numeroEntradas = numeroEntradas;
		this.obra = obra;
		this.sala = sala;
		this.lineas = new ArrayList<>();
	}

	public Integer getId() { return id; }

	public void setId(Integer id) { this.id = id; }

	public Date getFecha() { return fecha; }

	public void setFecha(Date fecha) { this.fecha = fecha; }

	public double getPrecio() { return precio; }

	public void setPrecio(double precio) { this.precio = precio; }

	public boolean isActivo() { return activo; }

	public void setActivo(boolean activo) { this.activo = activo; }

	public Integer getNumeroEntradas() { return numeroEntradas; }

	public void setNumeroEntradas(Integer numeroEntradas) { this.numeroEntradas = numeroEntradas; }

	public String getObra() { return obra; }

	public void setObra(String obra) { this.obra = obra; }

	public Integer getSala() { return sala; }

	public void setSala(Integer sala) { this.sala = sala; }
	
	public Collection<LineaFactura> getLineas() { return lineas; }
	
	public void addLinea(LineaFactura linea) { lineas.add(linea); }
	
}
