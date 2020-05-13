package com.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Student {
	@Id
	@GeneratedValue
	private Integer studentId;
	private String studentName;
	private Integer yearOfStudy;
	private String semester;
	private Integer departmentId;
	private String departmentName;
	public Student() {
		super();
	}
	
	public Student(Integer studentId,String studentName, Integer yearOfStudy, String semester, Integer departmentId,
			String departmentName) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.yearOfStudy = yearOfStudy;
		this.semester = semester;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}

	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Integer getYearOfStudy() {
		return yearOfStudy;
	}
	public void setYearOfStudy(Integer yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return studentId+" "+studentName+" "+semester+" "+yearOfStudy+" "+departmentName+" "+departmentId;
	}
}
