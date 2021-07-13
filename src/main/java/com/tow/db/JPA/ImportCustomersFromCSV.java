package com.tow.db.JPA;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;

import com.opencsv.bean.CsvToBeanBuilder;
import com.tow.db.JPA.model.Customer;
import com.tow.db.JPA.model.CustomerTemp;

public class ImportCustomersFromCSV {

	public List<Customer> CSVtoCustomerObject2() throws IOException {

//		String fileName = "H:\\WorkSpace_Eclipse3\\Java-JPA-Hibernate\\src\\main\\java\\com\\tow\\db\\data\\customerDBSample.csv";
		String fileName = "data/customerDBSample.csv";
		URL url = getClass().getClassLoader().getResource(fileName);
		File file = new File(url.getPath());

		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Customer> beans = new CsvToBeanBuilder(new FileReader(file)).withType(Customer.class).build().parse();

//		beans.forEach(System.out::println);

		return beans;
	}

	public List<CustomerTemp> CSVtoCustomerObject() throws IOException {

//		String fileName = "H:\\WorkSpace_Eclipse3\\Java-JPA-Hibernate\\src\\main\\java\\com\\tow\\db\\data\\customerDBSample.csv";
		String fileName = "data/customerDBSample.csv";
		URL url = getClass().getClassLoader().getResource(fileName);
		File file = new File(url.getPath());
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<CustomerTemp> beans = new CsvToBeanBuilder(new FileReader(file)).withType(CustomerTemp.class).build()
				.parse();

//		beans.forEach(System.out::println);

		return beans;
	}

//	@Test // CSV reader to a list of class Students
	public void runCSVReadTest() throws IOException {

		CSVtoCustomerObject().forEach(System.out::println);
	}

	@Test
	public void updateData() throws IOException {
		// use persistence.xml configuration

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentData");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<CustomerTemp> custlist1 = CSVtoCustomerObject();
//		custlist1.forEach(x -> em.persist(x));
		custlist1.forEach(x -> em.merge(x));
		em.getTransaction().commit();
		em.close();
		emf.close();

	}

	public void runCSV2DB() throws IOException {

		setupDB();
		System.out.println("Starting Import");
		importToDB(CSVtoCustomerObject2());
		System.out.println("Completed Import");
	}

	protected SessionFactory sessionFactory;

	protected void setupDB() {
		// configures settings from hibernate.cfg.xml
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception ex) {
			System.out.println(ex.toString()); // If error display in console
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	protected void importToDB(List<Customer> customerList) {

		System.out.println(customerList);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		customerList.forEach(x -> session.save(x));

		session.getTransaction().commit();
		session.close();
	}
}