package com.examTaker.rjsm.business.teacher;

import java.util.List;
import java.util.Optional;

import com.examTaker.rjsm.models.Teacher;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class TeacherServiceImpl implements TeacherService{

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }

    //get all
    public List<Teacher> getAll(){
        log.info("Returning all teachers from {}.", this.getClass().getSimpleName());
        return teacherRepository.findAll();
    }

    //get by id
    public Optional<Teacher> getTeacherById(Long id){
        log.info("Returning teacher with id: {} from {}.", id, this.getClass().getSimpleName());
        return teacherRepository.findById(id);
    }

    //create
    public Teacher saveTeacher(Teacher teacher){
        log.info("Teacher saved with Id: {} from {}.", teacher.getTeacher_id(), this.getClass().getSimpleName());
        return teacherRepository.save(teacher);
    }

    //delete
    public boolean deleteTeacher(Long id){
        try{
            teacherRepository.deleteById(id);
            log.info("Teacher with Id: {} deleted. From {}.", id, this.getClass().getSimpleName());
            return true;
        }catch(Exception e){
            log.warn("Teacher with id: {} couldn't be deleted. Form {}.", id, this.getClass().getSimpleName());
            return false;
        }
    }
}
