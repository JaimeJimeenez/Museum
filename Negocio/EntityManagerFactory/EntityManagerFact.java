package Negocio.EntityManagerFactory;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFact {
	
	private static EntityManagerFactory instancia;
	
	public synchronized static EntityManagerFactory getInstancia(){
		if(instancia == null){
			instancia = Persistence.createEntityManagerFactory("museo");
		}
		return instancia;
	}

}

