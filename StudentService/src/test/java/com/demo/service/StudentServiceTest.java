package com.demo.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.support.Repositories;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.model.Student;
import com.demo.repository.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {
	@Mock
	private StudentRepository studentRepository;
	
	@InjectMocks
	private StudentService studentService;
	
	@Test
	public void testSayHello() {
		assertEquals("say hello student controller", studentService.sayHello());
	}
	@Test
	public void testGetAllStudents() {
		List<Student> listOfStudent=Arrays.asList(new Student(100, "Rajesh", 4, "VIII", 200, "CSE"),
		new Student(101, "Dhoni", 2, "IV", 300, "IT"));
		
		Mockito.when(studentRepository.findAll()).thenReturn(listOfStudent);
		List<Student> list =studentService.getAllStudents();
		assertEquals(2, list.size());
	}
	@Test
	public void testGetStudentById() {
		Student student = new Student(101, "Dhoni", 2, "IV", 300, "IT");
		Mockito.when(studentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(student));
		Optional<Student> stu = studentService.getStudentById(67);
		assertTrue(stu.isPresent());
	}
	@Test
	public void testIsStudentExists() {
		Student student = new Student(101, "Dhoni", 2, "IV", 300, "IT");
		Mockito.when(studentRepository.existsById(Mockito.anyInt())).thenReturn(true);
		assertTrue(studentService.isStudentExists(student));
		
	}
	@Test 
	public void testDeleteStudentById() {
		
	}
}






