package com.tow.db.JPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.speedment.jpastreamer.application.JPAStreamer;
import com.tow.db.JPA.model.Student;

public class StudentJPAStreamer {

//	@Test
//	public void JPSStreamEx() {

	public static void main(String[] args) {
//		JPAStreamer jpaStreamer = JPAStreamer.createJPAStreamerBuilder("studentData").build();

		JPAStreamer jpaStreamer = JPAStreamer.of("studentData");

//		long count = jpaStreamer.stream(Student.class).filter(Student$.Name.startsWith("a")).count();

//		jpaStreamer.stream(Student.class).forEach(System.out::println);

//		final Stream<Student> jpst = jpaStreamer.stream(Student.class);
//
//		jpst.forEach(x -> System.out.println(x.getName()));

		jpaStreamer.close();

	}

//	@Test
	public void JPAtestEntity() {

		// Ref:
		// https://www.journaldev.com/17379/jpa-entitymanager-hibernate#jpa-entitymanager

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("studentData");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		// Pure JPA + Hibernate
		System.out.println("Starting Transaction");
		entityManager.getTransaction().begin();

		Student aStudent = new Student();
		aStudent.setId(3);
		Student emp = entityManager.find(Student.class, aStudent.getId());
		System.out.println(emp);

		// close the entity manager
		entityManager.close();
		entityManagerFactory.close();
	}
}
