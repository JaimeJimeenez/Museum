package Negocio.Factura;

import java.sql.Date;

public class TFacturaTienda {
	
	private int id;
	private int idCliente;
	private int idDescuento;
	private Date fecha;
	private double precioTotal;
	private boolean activo;
	
	public TFacturaTienda(){
		
	}
	
	public TFacturaTienda(Date date){
		this.fecha=date;
	}
	
	
	public TFacturaTienda(int id, Date fecha, float precioTotal, boolean activo){
		if (id < 1) throw new IllegalArgumentException("ID incorrecto.");
		if (precioTotal < 0) throw new IllegalArgumentException("Precio total negativo.");
		this.id = id;
		this.fecha = fecha;
		this.precioTotal = precioTotal;
		this.activo = activo;
	}
	
	public TFacturaTienda(int id,int idCliente, int idDescuento, double precioTotal,Date fecha){
		if (id < 1) throw new IllegalArgumentException("ID incorrecto.");
		if (precioTotal < 0) throw new IllegalArgumentException("Precio total negativo.");
		this.id = id;
		this.idCliente = idCliente;
		this.idDescuento = idDescuento;
		this.fecha = fecha;
		this.precioTotal = precioTotal;
		this.activo = true;
	}
	
	
	public TFacturaTienda(int id, Date fecha){
		if (id < 1) throw new IllegalArgumentException("ID incorrecto.");

		this.id = id;
		this.fecha = fecha;
	}
	
	public TFacturaTienda(int id, Date fecha, double precioTotal){
		if (id < 1) throw new IllegalArgumentException("ID incorrecto.");
		this.id = id;
		this.fecha = fecha;
		this.precioTotal = precioTotal;
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	public int getIdDescuento() {
		return idDescuento;
	}

	public void setIdDescuento(int idDescuento) {
		this.idDescuento = idDescuento;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Boolean isActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}