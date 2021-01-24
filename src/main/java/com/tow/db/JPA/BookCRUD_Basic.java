package com.tow.db.JPA;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;

import com.tow.db.JPA.model.Book;

// In Hibernate, you perform database operations via a Session which can be obtained from a SessionFactory. 
// The SessionFactory loads Hibernate configuration file, analyzes the mapping and creates connection to the database. 
// Write the following code in the setup() method to load Hibernate SessionFactory with settings loaded from hibernate.cfg.xml file

// Sample Databases: https://dev.mysql.com/doc/index-other.html

public class BookCRUD_Basic {

	protected SessionFactory sessionFactory;

	protected void setup() {
		// configures settings from hibernate.cfg.xml
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception ex) {
			System.out.println(ex.toString()); // If error display in console
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	protected void exit() {
		sessionFactory.close();
	}

	protected void create() {

		Book book = new Book();
		book.setTitle("New Java3");
		book.setAuthor("Armis H");
		book.setPrice(12.99f);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(book);

		session.getTransaction().commit();
		session.close();
	}

	protected void read() {
		Session session = sessionFactory.openSession();

		long bookId = 3;
		Book book = session.get(Book.class, bookId);

		System.out.println(book);

		session.close();
	}

	protected void update() {
		Book book = new Book();
		book.setId(6);
		book.setTitle("Ultimate Java Programming");
		book.setAuthor("Nam Ha Minh");
		book.setPrice(19.99f);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.update(book);

		session.getTransaction().commit();
		session.close();
	}

	protected void delete() {
		Book book = new Book();
		book.setId(1);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.delete(book);

		session.getTransaction().commit();
		session.close();
	}

	@Test
	public void runTest() {
//	public static void main(String[] args) {
		BookCRUD_Basic manager = new BookCRUD_Basic();
		manager.setup();

//		manager.create();

		manager.read();
		manager.update();
//		manager.delete();

		manager.exit();
	}
}