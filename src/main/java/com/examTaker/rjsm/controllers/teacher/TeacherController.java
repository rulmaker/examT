package com.examTaker.rjsm.controllers.teacher;

import com.examTaker.rjsm.business.teacher.TeacherServiceImpl;
import com.examTaker.rjsm.models.Teacher;
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
@RequestMapping("/api/v1/teacher")
@Log4j2
public class TeacherController {

    private final TeacherServiceImpl teacherService;

    @Autowired
    public TeacherController(TeacherServiceImpl teacherService) {
        this.teacherService = teacherService;
    }

    //get all
    @GetMapping  //http://localhost:9090/api/v1/teacher
    public ResponseEntity<?> getAllTeachers(){
        List<Teacher> teachers = teacherService.getAll();

        if(teachers.isEmpty()){
            log.warn("There are no teachers registered. From {}.",  this.getClass().getSimpleName());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no teachers registered");
        }else{
            log.info("Teachers list generated succesfully from {}.", this.getClass().getSimpleName());
            return ResponseEntity.ok(teachers);
        }
    }


    //get one
    @GetMapping("/{id}")  //http://localhost:9090/api/v1/teacher/{}
    public ResponseEntity<?> getTeacherbyId(@PathVariable Long id){
        Optional<Teacher> teacher = teacherService.getTeacherById(id);

        if(teacher.isPresent()){
            log.info("Returning teacher from {} with id: {}", this.getClass().getSimpleName(), teacher.get().getTeacher_id());
            return ResponseEntity.ok(teacher.get());
        }else{
            log.warn("Teacher from {} with id: {}, not found.", this.getClass().getSimpleName(), teacher.get().getTeacher_id());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher was not found");
        }
    }

    //create
    @PostMapping  //http://localhost:9090/api/v1/teacher
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher){
        Teacher teacherSaved = teacherService.saveTeacher(teacher);

        log.info("Teacher from {} with id: {} created.", this.getClass().getSimpleName(), teacherSaved.getTeacher_id());
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherSaved);

    }
}
