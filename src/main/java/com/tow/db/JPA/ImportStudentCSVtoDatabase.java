package com.tow.db.JPA;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.tow.db.JPA.model.Student;

public class ImportStudentCSVtoDatabase {

	public List<Student> CSVtoStudentObject() {

		// Hashmap to map CSV data to

		// Bean attributes.
		Map<String, String> mapping = new HashMap<String, String>();
		mapping.put("Name", "Name");
		mapping.put("RollNo", "RollNo");
		mapping.put("Department", "Department");
		mapping.put("Result", "Result");
		mapping.put("Pointer", "Pointer");

		// HeaderColumnNameTranslateMappingStrategy
		// for Student class
		HeaderColumnNameTranslateMappingStrategy<Student> strategy = new HeaderColumnNameTranslateMappingStrategy<Student>();
		strategy.setType(Student.class);
		strategy.setColumnMapping(mapping);

		// Create castobaen and csvreader object
		CSVReader csvReader = null;
		try {
			csvReader = new CSVReader(new FileReader(
					"H:\\WorkSpace_Eclipse3\\Java-JPA-Stream\\src\\main\\java\\com\\tow\\db\\JPA\\StudentData.csv"));
		} catch (FileNotFoundException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CsvToBean<Student> csvToBean = new CsvToBean<Student>();

		// call the parse method of CsvToBean
		// pass strategy, csvReader to parse method
		@SuppressWarnings("deprecation")
		List<Student> list = csvToBean.parse(strategy, csvReader);

		// print details of Bean object
//		for (Student e : list) {
//			System.out.println(e);
//		}
		return list;
	}

	@Test // CSV reader to a list of class Students
	public void runCSVReadTest() {
		System.out.println(CSVtoStudentObject());
	}

//	@Test
	public void runCSV2DB() {

		setupDB();
		System.out.println("Starting Import");
		importToDB(CSVtoStudentObject());
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

	protected void importToDB(List<Student> studentList) {

		System.out.println(studentList);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		studentList.forEach(x -> session.save(x));

		session.getTransaction().commit();
		session.close();
	}

}
