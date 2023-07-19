package com.example.teachers.repository;

import com.example.teachers.model.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {   //TeacherRepository extends CrudRepository and expects specification for which class this is about and the datatype of the primary key


    Iterable<Teacher> findByDobBefore(LocalDate date);

}
