package Integracion.Fabricante;

import java.util.List;

import Negocio.Fabricante.TFabricante;

public interface DAOFabricante {

    public int registrarFabricante(TFabricante tFabricante);

	public int modificarFabricante(TFabricante tFabricante);

	public int borrarFabricante(Integer IDFabricante);

	public List<TFabricante> mostrarListaFabricantes();

	public TFabricante buscarFabricantePorID(Integer IDFabricante);
	
	public TFabricante buscarFabricantePorNombre(String nombreFabricante);
    
}
