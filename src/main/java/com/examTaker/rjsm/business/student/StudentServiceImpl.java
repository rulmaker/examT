package com.examTaker.rjsm.business.student;

import java.util.List;
import java.util.Optional;

import com.examTaker.rjsm.models.Student;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;



@Service
@Log4j2
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents(){
        log.info("Returning all students from {}.", this.getClass().getSimpleName());
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id){
        log.info("Returning student with id: {} from {}.", id, this.getClass().getSimpleName());
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student){
        Student studentsaved = studentRepository.save(student);
        log.info("Student saved with Id: {} from {}.", studentsaved.getStudent_id(), this.getClass().getSimpleName());
        return studentsaved;
    }

    public boolean deleteStudent(Long id){
        try{
            studentRepository.deleteById(id);
            log.info("Student with Id: {} deleted. From {}.", id, this.getClass().getSimpleName());
            return true;
        }catch(Exception e){
            log.warn("Student with id: {} couldn't be deleted. Form {}.", id, this.getClass().getSimpleName());
            return false;
        }
    }
}
