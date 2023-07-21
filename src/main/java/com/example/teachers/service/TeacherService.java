package com.example.teachers.service;

import com.example.teachers.dto.TeacherDto;
import com.example.teachers.model.Teacher;
import com.example.teachers.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    private final TeacherRepository repos;

    public TeacherService(TeacherRepository repos) {
        this.repos = repos;
    }

    public Long createTeacher(TeacherDto tdto) { //Vanuit de dto wordt er een teacher aangeleverd

        // mapping dto object naar de entiteit
        Teacher t = new Teacher();
        t.setFirstName(tdto.firstName);
        t.setLastName(tdto.lastName);
        t.setDob(tdto.dob);

        repos.save(t);

        return t.getId();
    }
}
