package Negocio.Fabricante;

import java.util.List;

public interface SAFabricante {
    
    public int registrarFabricante(TFabricante tFabricante) throws Exception;

	public int modificarFabricante(TFabricante tFabricante) throws Exception;

	public int borrarFabricante(Integer IDFabricante) throws Exception;

	public List<TFabricante> mostrarListaFabricantes() throws Exception;

	public TFabricante buscarFabricantePorID(Integer IDFabricante) throws Exception;
}
