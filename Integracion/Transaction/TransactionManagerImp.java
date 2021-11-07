package Integracion.Transaction;

import java.util.concurrent.ConcurrentHashMap;

public class TransactionManagerImp extends TransactionManager {

	private ConcurrentHashMap<Thread, Transaction> concurrentHashMap;
	
	public TransactionManagerImp() {
		this.concurrentHashMap = new ConcurrentHashMap<Thread, Transaction>();
	}
	
	public Transaction nuevaTransaccion() throws Exception {
		Thread currentThread = Thread.currentThread();
		Transaction currentTransaction = concurrentHashMap.get(currentThread);
		if (currentTransaction == null) {
			currentTransaction = TransactionFactory.getInstancia().getTransaction();
			concurrentHashMap.put(currentThread, currentTransaction);
		}
		else
			throw new Exception("No se ha podido iniciar la transaccion");
		return currentTransaction;
	}

	public Transaction getTransaccion() {
		return concurrentHashMap.get(Thread.currentThread());
	}

	public void eliminaTransaccion() {
		concurrentHashMap.remove(Thread.currentThread());
	}
	
}