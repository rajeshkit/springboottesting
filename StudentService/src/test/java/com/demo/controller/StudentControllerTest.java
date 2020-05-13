package com.demo.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.model.Student;
import com.demo.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StudentService studentService;
	
	@InjectMocks
	private StudentController studentController;
	@Test
	public void testSayHello() throws Exception {
			Mockito.when(studentService.sayHello()).thenReturn("Hello World");
			mockMvc.perform(get("/api/say")).andExpect(content().string(containsString("Hello World")));
	}
	
	@Test
	public void testGetStudentById() throws Exception {
		Mockito.when(studentService.getStudentById(Mockito.anyInt()))
		.thenReturn(Optional.of(new Student(100,"Rajesh",2,"III",200,"Civil")));
		mockMvc.perform(get("/api/student/100").accept(MediaType.APPLICATION_JSON))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.studentId", is(100)));
	}
	@Test
	public void testGetAllStudents() throws Exception {
		Mockito.when(studentService.getAllStudents())
		.thenReturn(Arrays.asList(new Student(100,"Rajesh",2,"III",200,"Civil"),
				new Student(101,"Dhoni",3,"VI",200,"CSE")));
		mockMvc.perform(get("/api/students").accept(MediaType.APPLICATION_JSON))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
//			//.andDo(print())
			.andExpect(jsonPath("$[0].studentId", is(100)))
			.andExpect(jsonPath("$[0].studentName", is("Rajesh")))
			.andExpect(jsonPath("$[1].studentId", is(101)))
			.andExpect(jsonPath("$[1].studentName", is("Dhoni")));
	}
	@Test
	public void testAdmission() throws Exception {
		Student student = new Student(100,"Rajesh",2,"III",200,"Civil");
		Mockito.when(studentService.newAddmission(student)).thenReturn(student);
		
	   ObjectMapper mapper = new ObjectMapper();
	   mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	   ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	   String requestJson=ow.writeValueAsString(student );

		System.out.println(requestJson);
		
		
		  mockMvc.perform(post("/api/add/student")
		  .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
		  .content(requestJson))
		  .andDo(print());
		 
	 
		
	}
}
