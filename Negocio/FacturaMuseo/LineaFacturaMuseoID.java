package Negocio.FacturaMuseo;

import java.io.Serializable;

import jakarta.persistence.*;

@Embeddable
public class LineaFacturaMuseoID implements Serializable {
	private int id_factura;
	private int id_entrada;
	
	public LineaFacturaMuseoID(){
		
	}
	
	public LineaFacturaMuseoID(int idFactura, int idEntrada){
		this.id_factura = idFactura;
		this.id_entrada = idEntrada;
	}

	public int getIdFactura() {
		return id_factura;
	}

	public void setIdFactura(int idFactura) {
		this.id_factura = idFactura;
	}

	public int getIdEntrada() {
		return id_entrada;
	}

	public void setIdEntrada(int idEntrada) {
		this.id_entrada = idEntrada;
	}

	
	 @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_entrada;
		result = prime * result + id_factura;
		return result;
	}

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineaFacturaMuseoID other = (LineaFacturaMuseoID) obj;
		if (id_entrada != other.id_entrada)
			return false;
		if (id_factura != other.id_factura)
			return false;
		return true;
	}
}
