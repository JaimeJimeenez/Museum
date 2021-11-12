package Negocio.Descuento;

public class TDescuento {

    private int id;
	private int porcentaje;
	private boolean activo;
	
	public TDescuento(int id, int porcentaje, boolean activo) {
		if (porcentaje <= 0 || porcentaje >= 100) throw new IllegalArgumentException("Porcentaje introducido no valido.");
		this.id = id;
		this.porcentaje = porcentaje;
		this.activo = activo;
	}
	
	public TDescuento(int id, int porcentaje) {
		this.id = id;
		this.porcentaje = porcentaje;
		this.activo = true;
	}
	
	public TDescuento(int porcentaje) {
		this.porcentaje = porcentaje;
		activo = true;
	}

	public int getId() { return id; }
	
	public void setId(int id) { this.id = id; }
	
	public int getPorcentaje() { return porcentaje; }
	
	public void setPorcentaje(int porcentaje) { this.porcentaje = porcentaje; }
	
	public boolean isActivo() { return activo; }
	
	public void setActivo(boolean activo) { this.activo = activo; }
    
}
