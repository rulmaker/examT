package com.examTaker.rjsm.controllers.student;

import com.examTaker.rjsm.business.student.StudentServiceImpl;
import com.examTaker.rjsm.models.Student;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/student")
@Log4j2
public class StudentController {
    private final StudentServiceImpl studentService;

    @Autowired
    public StudentController(StudentServiceImpl studentService){
        this.studentService = studentService;
    }

    //get all
    @GetMapping  //http://localhost:9090/api/v1/student
    public ResponseEntity<?> getAll(){
        List<Student> students = studentService.getAllStudents();

        if(students.isEmpty()){
            log.warn("There are no students registered. From {}.",  this.getClass().getSimpleName());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen estudiantes registrados");
        }else{
            log.info("Student list generated succesfully from {}.", this.getClass().getSimpleName());
            return ResponseEntity.ok(students);
        }
    }


    //get one
    @GetMapping("/{id}")  //http://localhost:9090/api/v1/student/{}
    public ResponseEntity<?> getStudentById(@PathVariable Long id){
        Optional<Student> student = studentService.getStudentById(id);

        if(student.isPresent()){
            log.info("Returning student from {} with id: {}", this.getClass().getSimpleName(), student.get().getStudent_id());
            return ResponseEntity.ok(student.get());
        }else{
            log.warn("Student from {} with id: {}, not found.", this.getClass().getSimpleName(), student.get().getStudent_id());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudiante no encontrado");
        }
    }

    //create
    @PostMapping  //http://localhost:9090/api/v1/student
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student studentSaved = studentService.createStudent(student);

        log.info("Student from {} with id: {} created.", this.getClass().getSimpleName(), studentSaved.getStudent_id());
        return ResponseEntity.status(HttpStatus.CREATED).body(studentSaved);

    }
}
