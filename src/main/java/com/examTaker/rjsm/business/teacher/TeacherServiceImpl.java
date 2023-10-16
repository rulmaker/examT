package com.examTaker.rjsm.business.teacher;

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

    //get by id

    //create

    //delete
}
