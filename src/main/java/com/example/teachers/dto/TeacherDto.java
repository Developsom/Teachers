package com.example.teachers.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TeacherDto {
    public Long id;


    @NotBlank  ////Validatie door spring-boot-starter-validation package
    public String firstName;
    @Size(min = 3, max = 255)
    public String lastName;
    @Past
    public LocalDate dob;
    @Max(value= 100000)
    public int salary;

}
