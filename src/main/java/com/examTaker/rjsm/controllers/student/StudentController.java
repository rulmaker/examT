package com.examTaker.rjsm.controllers.student;

import com.examTaker.rjsm.business.student.StudentServiceImpl;
import com.examTaker.rjsm.models.Student;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
@Log4j2
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
            log.warn("There are no students registered");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen estudiantes registrados");
        }else{
            log.info("Student list generated succesfully");
            return ResponseEntity.ok(students);
        }
    }


    //get one
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentbyId(@PathVariable Long id){
        Optional<Student> student = studentService.getStudentById(id);

        if(student.isPresent()){
            log.info("Returning student with id: {}", student.get().getStudent_id());
            return ResponseEntity.ok(student.get());
        }else{
            log.warn("Student with id: {}, not found.", student.get().getStudent_id());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudiante no encontrado");
        }
    }

    //create
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student studentSaved = studentService.createStudent(student);

        log.info("Student with id: {} created.", studentSaved.getStudent_id());
        return ResponseEntity.status(HttpStatus.CREATED).body(studentSaved);

    }
}
