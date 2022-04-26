package Negocio.FacturaMuseo;

import java.util.Collection;

import jakarta.persistence.*;
import Negocio.Entrada.Entrada;

@Entity
@Table(name = "entity_linea_factura_museo")
public class LineaFactura {
	
	@Version
	private int version;
	
	@EmbeddedId
	private LineaFacturaMuseoID idLinea;
	
	@ManyToOne
	@MapsId("id_factura")
	@JoinColumn(name="id_factura", referencedColumnName="id_factura")
	private FacturaMuseo factura;
	
	// !
	@ManyToOne
	@MapsId("id_entrada")
	@JoinColumn(name="id_entrada", referencedColumnName="id_entrada")
	private Entrada entrada;
	//private Collection<Entrada> entrada;
	
	@Column(name = "cantidad")
	private Integer cantidad;
	
	@Column (name = "precio")
	private double precio;

	public LineaFactura(){ }
	
	public LineaFactura(int idFactura, int idEntrada){ idLinea = new LineaFacturaMuseoID(idFactura, idEntrada); }

	public LineaFacturaMuseoID getIdLinea() { return idLinea; }
	
	public FacturaMuseo getFactura() { return factura; }

	public void setFactura(FacturaMuseo factura) { this.factura = factura; }

	public Integer getCantidad() { return cantidad; }

	public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

	public double getPrecio() { return precio; }

	public void setPrecio(double precio) { this.precio = precio; }

	public Entrada getEntrada() { return entrada; }
	
	public void setEntrada(Entrada entrada) { this.entrada = entrada; }
	
	public void setIdLinea(LineaFacturaMuseoID idLinea) { this.idLinea = idLinea; }

}