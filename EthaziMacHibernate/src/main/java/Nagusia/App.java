package Nagusia;

import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class App {

	
	protected SessionFactory sessionFactory;

	public static void main(String[] args) {

		
		leerDatuak ld = new leerDatuak();
		System.out.println(0);
		ld.leerCampings();
		System.out.println(1);
		ld.leerAlojamientoRurales();
		System.out.println(2);
		ld.leerAlbergues();
		System.out.println(3);
	}

	protected void create(Ostatu ostatu) {
		
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	 
	    session.save(ostatu);
	 
	    session.getTransaction().commit();
	    session.close();
    }
	protected void exit() {
		sessionFactory.close();
    }
	protected void setup() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
		        .configure() // configures settings from hibernate.cfg.xml
		        .build();
		try {
		    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		    StandardServiceRegistryBuilder.destroy(registry);
		}
    }

}
