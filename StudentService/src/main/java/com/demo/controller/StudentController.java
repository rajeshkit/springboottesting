package com.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.exceptions.CustomTypeError;
import com.demo.model.Student;
import com.demo.repository.StudentRepository;
import com.demo.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/say")
	public String say() {
		return studentService.sayHello();
	}

	@PostMapping("/add/student")
	public ResponseEntity<?> admission(@RequestBody Student newStudent) {
		Student student = studentService.newAddmission(newStudent);
		if(studentService.isStudentExists(newStudent)) {
			return new ResponseEntity<>(new CustomTypeError("Student with "+newStudent.getStudentId()+" Already Exists") ,HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(studentService.newAddmission(newStudent),HttpStatus.CREATED);
	}
	
    @GetMapping("/student/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Integer studentId){
    	Optional<Student> student = studentService.getStudentById(studentId);
    	if(student.isPresent()==false) 	{
    		return new ResponseEntity<>(new CustomTypeError("Student with "+studentId+" Does not Existst"),HttpStatus.NOT_FOUND);
    	}
    	System.out.println("my controller: "+ student.get());
    	return ResponseEntity.ok(student.get());
    }
    	
	@GetMapping("/students")
	public ResponseEntity<?> getAllStudents(){
		List<Student> listOfStudent = studentService.getAllStudents();
		if(listOfStudent.isEmpty()) {
	 		return new ResponseEntity<>(new CustomTypeError("No Student Details Found"),HttpStatus.NOT_FOUND); 		
		}
		return new ResponseEntity<>(studentService.getAllStudents(),HttpStatus.OK);
	}
	@PutMapping("/student/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") Integer studentId, @RequestBody Student student) {
        Optional<Student> uptainedStudentInfo = studentService.getStudentById(studentId);
 
        if (uptainedStudentInfo.isPresent() == false) {
            return new ResponseEntity<>(new CustomTypeError("Unable to update "+studentId+" Does not Exists"),HttpStatus.NOT_FOUND);
        }
        Student studentDetails=uptainedStudentInfo.get();
        studentDetails.setDepartmentName(student.getStudentName());
        studentDetails.setSemester(student.getSemester());
        studentDetails.setYearOfStudy(student.getYearOfStudy());
        studentDetails.setDepartmentName(student.getDepartmentName());
        studentDetails.setDepartmentId(student.getDepartmentId());
        return new ResponseEntity<>(studentService.newAddmission(studentDetails), HttpStatus.OK);
    }
	
	@DeleteMapping("/student/{id}")
    public ResponseEntity<?> DeleteStudentById(@PathVariable("id") Integer studentId) {
        Optional<Student> uptainedStudentInfo = studentService.getStudentById(studentId);
 
        if (uptainedStudentInfo.isPresent() == false) {
            return new ResponseEntity<>(new CustomTypeError("Unable to Delete "+studentId+" Does not Exists"),HttpStatus.NOT_FOUND);
        }
        studentService.deleteStudentById(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
 
}
