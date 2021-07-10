package com.tow.db.JPA;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.opencsv.bean.CsvToBeanBuilder;
import com.tow.db.JPA.model.QuestionTemp;

public class QuesCsv2db_Insert {

	public List<QuestionTemp> DatacsvToclass1(String filepath) throws IllegalStateException, FileNotFoundException {

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<QuestionTemp> Queslist = new CsvToBeanBuilder(new FileReader(filepath)).withType(QuestionTemp.class)
				.build().parse();

		Queslist.forEach(System.out::println);

		return Queslist;
	}

	@Test
	public void justRun() throws IOException {
		List<QuestionTemp> cList = DatacsvToclass1(
				"H:\\WorkSpace_Eclipse3\\Java-JPA-Hibernate\\src\\main\\resources\\data\\questionsSet.csv");
	}

//	@Test
	public void importTodb() throws IllegalStateException, FileNotFoundException {

		// use persistence.xml configuration

//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_Online_Exam");
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();

//		List<Question> queslist1 = this.DataDetails();
//		queslist1.forEach(x -> em.merge(x));
//		em.getTransaction().commit();
//		em.close();
//		emf.close();

	}
}
