
package com.tow.db.JPA;

import java.io.FileReader;
import java.io.IOException;
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

		String fileName = "H:\\WorkSpace_Eclipse3\\Java-JPA-Hibernate\\src\\main\\java\\com\\tow\\db\\data\\customerDBSample.csv";
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Customer> beans = new CsvToBeanBuilder(new FileReader(fileName)).withType(Customer.class).build().parse();

//		beans.forEach(System.out::println);

		return beans;
	}

	public List<CustomerTemp> CSVtoCustomerObject() throws IOException {

		String fileName = "H:\\WorkSpace_Eclipse3\\Java-JPA-Hibernate\\src\\main\\java\\com\\tow\\db\\data\\customerDBSample.csv";
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<CustomerTemp> beans = new CsvToBeanBuilder(new FileReader(fileName)).withType(CustomerTemp.class).build()
				.parse();

//		beans.forEach(System.out::println);

		return beans;
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