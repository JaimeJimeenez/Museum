package Integracion.Query;

public abstract class QueryFactoria {

    private static QueryFactoria instancia;
	
	public static QueryFactoria getInstancia(){
		if(instancia == null){
			instancia = new QueryFactoriaImp();
		}
		return instancia;
	}
	
	public abstract Query nuevaQuery(String nombre);
    
}
