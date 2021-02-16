package com.tow.db.JPA.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student implements Serializable {
	/**
	 * serial ID added
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "Name")
	private String Name;
	private String RollNo, Department, Result;
	private float Pointer;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getRollNo() {
		return RollNo;
	}

	public void setRollNo(String rollNo) {
		RollNo = rollNo;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public String getResult() {
		return Result;
	}

	public void setResult(String result) {
		Result = result;
	}

	public float getPointer() {
		return Pointer;
	}

	public void setPointer(float pointer) {
		Pointer = pointer;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", Name=" + Name + ", RollNo=" + RollNo + ", Department=" + Department
				+ ", Result=" + Result + ", Pointer=" + Pointer + "]";
	}

}