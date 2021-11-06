package src.Integracion.Transaccion;

public abstract class TransactionFactory {

	private static TransactionFactory instancia = null;

	public static TransactionFactory getInstancia() {
		if(instancia==null){
			instancia = new TransactionFactoryImp();
		}
		return instancia;
	}

	public abstract Transaction getTransaction();
}