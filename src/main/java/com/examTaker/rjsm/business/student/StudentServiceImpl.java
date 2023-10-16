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
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id){
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student){
        return studentRepository.save(student);
    }

    public boolean deleteStudent(Long id){
        try{
            studentRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
