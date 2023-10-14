package com.examTaker.rjsm.controllers.student;

import com.examTaker.rjsm.business.student.StudentServiceImpl;
import com.examTaker.rjsm.models.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
public class StudentController {
    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService){
        this.studentService = studentService;
    }

    //get all
    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Student> students = studentService.getAllStudents();

        if(students.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen estudiantes registrados");
        }else{
            return ResponseEntity.ok(students);
        }
    }


    //get one
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentbyId(@PathVariable Long id){
        Optional<Student> student = studentService.getStudentById(id);

        if(student.isPresent()){
            return ResponseEntity.ok(student.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudiante no encontrado");
        }
    }

    //create
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student studentSaved = studentService.createStudent(student);

        return ResponseEntity.status(HttpStatus.CREATED).body(studentSaved);

    }
}
