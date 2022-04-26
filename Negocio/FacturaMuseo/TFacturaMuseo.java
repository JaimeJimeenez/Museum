package Negocio.FacturaMuseo;

import java.util.Date;


public class TFacturaMuseo {

	private Integer id;
	private Integer idEmpleado;
	private double precioTotal;
	private Date fecha;
	private Boolean activo;
	
	public TFacturaMuseo(){
	}

	public TFacturaMuseo(int id,int idEmpleado, Date fecha, double precio) {
		this.id = id;
		this.fecha = fecha;
		this.precioTotal = precio;
		this.idEmpleado = idEmpleado;
		this.activo = true;
	}
	public TFacturaMuseo(int id,int idEmpleado,double precio) {
		this.id = id;
		this.precioTotal = precio;
		this.idEmpleado = idEmpleado;
		this.activo = true;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado){
		this.idEmpleado = idEmpleado;
	}
	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Boolean isActivo() {
		return activo;
	}
	
	public void setActivo (boolean activo) {
		this.activo = activo;
	}

}