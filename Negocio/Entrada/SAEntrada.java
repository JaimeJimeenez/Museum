package Negocio.Entrada;

import java.util.List;

public interface SAEntrada {
	
	public int registrarEntrada(TEntrada tEntrada);
	
	public int borrarEntrada(int idEntrada);

	public int modificarEntrada(TEntrada tEntrada);
	
	public List<TEntrada> listarEntradas();
	
	public TEntrada buscarEntrada(int idEntrada);
}
