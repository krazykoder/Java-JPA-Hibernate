
package com.tow.db.JPA;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.opencsv.bean.CsvToBeanBuilder;
import com.tow.db.JPA.model.Customer;
import com.tow.db.JPA.model.CustomerTemp;

public class CustomersFromCSV2DB {

	public List<Customer> CSVtoCustomerObject2() throws IOException {

//		String fileName = "H:\\WorkSpace_Eclipse3\\Java-JPA-Hibernate\\src\\main\\java\\com\\tow\\db\\data\\customerDBSample.csv";
//		 Put files in Resource folder under data subdir 
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
//		 Put files in Resource folder under data subdir 
		String fileName = "data/customerDBSample.csv";
		URL url = getClass().getClassLoader().getResource(fileName);
		File file = new File(url.getPath());

		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<CustomerTemp> beans = new CsvToBeanBuilder(new FileReader(file)).withType(CustomerTemp.class).build()
				.parse();

//		beans.forEach(System.out::println);

		return beans;
	}
	
	
//	@Test
	// load a file from resource directory using classLoader 
	public void fileloader() {
		String fileName = "data/customerDBSample.csv";
		URL url = getClass().getClassLoader().getResource(fileName);
		System.out.println(url);
		File file = new File(url.getPath());
		System.out.println(file.getAbsolutePath() + " " + file.getName());
	}
	
	@Test
	public void CreateUpdateData() throws IOException {
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

	public void readDb() {

	}

	public void deleteDB() {

	}

	public void updateDB() {

	}

}