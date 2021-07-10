package com.tow.db.JPA.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class MCQ {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int ID;

	@Column(length = 3000)
	String Question;

	@Column(length = 1000)
	String Answer1, Answer2, Answer3, Answer4;

	String Correct;

	@Override
	public String toString() {
		return "MCQ [ID=" + ID + ", Question=" + Question + ", Answer1=" + Answer1 + ", Answer2=" + Answer2
				+ ", Answer3=" + Answer3 + ", Answer4=" + Answer4 + ", Correct=" + Correct + "]";
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getQuestion() {
		return Question;
	}

	public void setQuestion(String question) {
		Question = question;
	}

	public String getAnswer1() {
		return Answer1;
	}

	public void setAnswer1(String answer1) {
		Answer1 = answer1;
	}

	public String getAnswer2() {
		return Answer2;
	}

	public void setAnswer2(String answer2) {
		Answer2 = answer2;
	}

	public String getAnswer3() {
		return Answer3;
	}

	public void setAnswer3(String answer3) {
		Answer3 = answer3;
	}

	public String getAnswer4() {
		return Answer4;
	}

	public void setAnswer4(String answer4) {
		Answer4 = answer4;
	}

	public String getCorrect() {
		return Correct;
	}

	public void setCorrect(String correct) {
		Correct = correct;
	}

}
