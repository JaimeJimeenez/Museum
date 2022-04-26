/**
 * 
 */
package Negocio.FacturaMuseo;

public class TFacturaCompleta {

	private Integer idFactura;
	private Integer idEntrada;
	private Integer idEmpleado;

	public TFacturaCompleta(Integer id, Integer idEntrada2, Integer idEmpleado2) {
		// TODO Auto-generated constructor stub
		this.idFactura = id;
		this.idEntrada =idEntrada2;
		this.idEmpleado = idEmpleado2;
	}

	public Integer getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}

	public Integer getIdEntrada() {
		return idEntrada;
	}


	public void setIdEntrada(Integer idEntrada) {
		this.idEntrada = idEntrada;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}
	
	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}



}