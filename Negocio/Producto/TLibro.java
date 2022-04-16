package Negocio.Producto;

public class TLibro extends TProducto {

	private String categoria;
	
	public TLibro(int id,int idFabricante, int stock, double precio, String nombre, String categoria, boolean activo) {
		super(id, idFabricante, stock, precio, nombre, activo);
		this.categoria = categoria;
	}
	
	public TLibro(int id,int idFabricante, int stock, double precio, String nombre, String categoria) {
		super(id, idFabricante, stock, precio, nombre);
		this.categoria = categoria;
	}
	
	public TLibro(int idFabricante, int stock, double precio, String nombre, String categoria) {
		super(idFabricante, stock, precio, nombre);
		this.categoria = categoria;
	}
	
	public TLibro(int id) {
		super(id);
	}

	public String getCategoria() { return categoria; }

	public void setCategoria(String categoria) { this.categoria = categoria; }

	@Override
	public String getEstilo() {	return null; }

	@Override
	public String getTamanio() { return null; }
}