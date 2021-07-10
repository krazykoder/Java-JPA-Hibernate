
package com.tow.db.JPA;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.opencsv.bean.CsvToBeanBuilder;
import com.tow.db.JPA.model.MCQ;

public class MCQFromCSV2DB {

	// select ID, Question, Answer1, Answer2, Answer3, Answer4, Correct from
	// questiondb where questiondb.Answer1 >0

	public List<MCQ> CSVtoMCQObject() throws IOException {

		String fileName = "data/MCQDBSample.csv";

		URL url = getClass().getClassLoader().getResource(fileName);
		File file = new File(url.getPath());

		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<MCQ> beans = new CsvToBeanBuilder(new FileReader(file)).withType(MCQ.class).build().parse();

		beans.forEach(System.out::println);

		return beans;
	}

//	@Test
	public void justRun() throws IOException {
		List<MCQ> custlist1 = CSVtoMCQObject();
	}

	@Test
	public void CreateUpdateData() throws IOException {
		// use persistence.xml configuration

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentData");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<MCQ> itemlist = CSVtoMCQObject();

//		custlist1.forEach(x -> em.persist(x));
		itemlist.forEach(x -> em.merge(x));
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