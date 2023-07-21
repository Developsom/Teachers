package com.example.teachers.service;

import com.example.teachers.dto.TeacherDto;
import com.example.teachers.exception.ResourceNotFoundException;
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

    public TeacherDto getTeacher(Long id) {
        Teacher t = repos.findById(id).orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));///Lambda constructie

        // mapping van Entity naar dto
        TeacherDto tdto = new TeacherDto();
        tdto.id = t.getId();
        tdto.firstName = t.getFirstName();
        tdto.lastName = t.getLastName();
        tdto.dob = t.getDob();

        return tdto;

    }
}
