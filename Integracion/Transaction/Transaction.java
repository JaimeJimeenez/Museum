package Integracion.Transaction;

import java.sql.Connection;

public interface Transaction {

	public void start();
	
	public void commit();

	public void rollback();

	Connection getResource();
}
