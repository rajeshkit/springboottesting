package com.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.model.Department;
import com.demo.model.Student;
import com.demo.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	private RestTemplate restTemplate=new RestTemplate();
	
	public String sayHello() {
		return "say hello student controller";		
	}

	
	
	public Student newAddmission(Student newStudent) {
		// department details
		
		/*
		 * Department dept =
		 * restTemplate.getForObject("http://localhost:8085/department/" +
		 * newStudent.getDepartmentId(), Department.class);
		 * System.out.println(newStudent);
		 * System.out.println("Url "+"http://localhost:8085/department/" +
		 * newStudent.getDepartmentId());
		 * System.out.println("Dept ID"+dept.getDeptId());
		 * System.out.println("DeptName"+dept.getDeptName());
		 * newStudent.setDepartmentId(dept.getDeptId());
		 * newStudent.setDepartmentName(dept.getDeptName());
		 */ return studentRepository.save(newStudent);

	}

	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	public Optional<Student> getStudentById(Integer studentId) {
		// TODO Auto-generated method stub
		return studentRepository.findById(studentId);
	}

	public boolean isStudentExists(Student newStudent) {
		// TODO Auto-generated method stub
		return studentRepository.existsById(newStudent.getStudentId());
	}

	public void  deleteStudentById(Integer studentId) {
		// TODO Auto-generated method stub
		studentRepository.deleteById(studentId);
	}
	
}
