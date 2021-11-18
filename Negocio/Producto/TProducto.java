package Negocio.Producto;

public abstract class TProducto {

	private int id;
	private int idFabricante;
	private int stock;
	private double precio;
	private boolean activo;
	private String nombre;
	
	public TProducto(int id,int idFabricante, int stock, double precio, String nombre, boolean activo) {
		this.id = id;
		this.idFabricante = idFabricante;
		this.stock = stock;
		this.precio = precio;
		this.nombre = nombre;
		this.activo = activo;
	}
	
	public TProducto(int id,int idFabricante, int stock, double precio, String nombre) {
		this.id = id;
		this.idFabricante = idFabricante;
		this.stock = stock;
		this.precio = precio;
		this.nombre = nombre;
		this.activo = true;
	}
	public TProducto(int idFabricante, int stock, double precio, String nombre) {
		this.idFabricante = idFabricante;
		this.stock = stock;
		this.precio = precio;
		this.nombre = nombre;
		this.activo = true;
	}
	
	public TProducto(int id, float precio) {
		this.id = id;
		this.precio = precio;
		this.activo = true;
	}
	
	public TProducto(int id) {
		this.id = id;
		this.activo = true;
	}

	public int getId() { return id;	}

	public void setId(int id) {	this.id = id; }
	
	public int getIdFabricante() { return idFabricante;	}

	public void setIdFabricante(int idFabricante) {	this.idFabricante = idFabricante; }

	public int getStock() {	return stock; }

	public void setStock(int stock) { this.stock = stock; }

	public double getPrecio() { return precio; }

	public void setPrecio(double precio) { this.precio = precio; }

	public boolean isActivo() {	return activo; }

	public void setActivo(boolean activo) { this.activo = activo; }

	public String getNombre() { return nombre; }

	public void setNombre(String nombre) { this.nombre = nombre; }
	
	public abstract String getCategoria();
	
	public abstract String getEstilo();
	
	public abstract String getTamanio();

}
