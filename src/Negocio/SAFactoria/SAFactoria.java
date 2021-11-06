package src.Negocio.SAFactoria;

import src.Negocio.Cliente.SACliente;

public abstract class SAFactoria {
	
	private static SAFactoria instancia;
	private SAFactoria sAFactoria;
	private SAFactoria sAFactoria2;

	public static SAFactoria getInstancia() {
		if(instancia == null){
			instancia = new SAFactoriaImp();
		}
		return instancia;
	}

	public static void setIntancia(SAFactoria intancia) {
		SAFactoria.instancia = intancia;
	}

	public abstract SACliente generarSACliente();

	public abstract SAFabricante generarSAFabricante();

	public abstract SAProducto generarSAProducto();
	
	public abstract SADescuento generarSADescuento();
	
	public abstract SAFactura generarSAFactura();
	
	public abstract SAProveedor generarSAProveedor();
}