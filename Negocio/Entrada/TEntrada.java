package Negocio.Entrada;

import java.util.Date;

public class TEntrada {

	private Integer id;
	private Date fecha;
	private double precio;
	private boolean activo;
	private Integer numeroEntradas;
	private String obra;
	private Integer sala;
	
	public TEntrada() { }

	public TEntrada(Integer id, Date fecha, double precio, boolean activo, Integer numeroEntradas, String obra,
			Integer sala) {
		this.id = id;
		this.fecha = fecha;
		this.precio = precio;
		this.activo = activo;
		this.numeroEntradas = numeroEntradas;
		this.obra = obra;
		this.sala = sala;
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
	
}
