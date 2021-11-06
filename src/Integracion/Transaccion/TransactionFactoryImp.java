package src.Integracion.Transaccion;

public class TransactionFactoryImp extends TransactionFactory {

	@Override
	public Transaction getTransaction() {
		return new TransactionMySQL();
	}
	
}