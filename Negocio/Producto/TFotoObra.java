package Negocio.Producto;

public class TFotoObra extends TProducto {
	
	private String estilo;
	private String tamanio;
	
	public TFotoObra(int id, int idFabricante, int stock, double precio, String nombre, String estilo, String tamanio, boolean activo) {
		super(id, idFabricante, stock, precio, nombre, activo);
		this.estilo = estilo;
		this.tamanio = tamanio;
	}
	
	public TFotoObra(int id, int idFabricante, int stock, double precio, String nombre, String estilo, String tamanio) {
		super(id, idFabricante, stock, precio, nombre);
		this.estilo = estilo;
		this.tamanio = tamanio;
	}
	public TFotoObra( int idFabricante, int stock, double precio, String nombre, String estilo, String tamanio) {
		super(idFabricante, stock, precio, nombre);
		this.estilo = estilo;
		this.tamanio = tamanio;
	}
	
	public TFotoObra(int id) {
		super(id);
	}

	public String getEstilo() {	return estilo; }
	
	public void setEstilo(String estilo) { this.estilo = estilo; }

	public String getTamanio() {	return tamanio; }

	public void setTamanio(String tamanio) { this.tamanio = tamanio; }
	
	public String getCategoria() { return null; }
}