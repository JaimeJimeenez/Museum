package Integracion.Transaction;

public abstract class TransactionManager {

	private static TransactionManager instancia;

	public static TransactionManager getInstancia() {
		if(instancia == null){
			instancia = new TransactionManagerImp();
		}
		return instancia;
	}

	public abstract Transaction nuevaTransaccion() throws Exception;

	public abstract Transaction getTransaccion();

	public abstract void eliminaTransaccion();
}