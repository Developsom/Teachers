package com.example.teachers.controller;


import com.example.teachers.dto.TeacherDto;
import com.example.teachers.model.Teacher;
import com.example.teachers.repository.TeacherRepository;
import com.example.teachers.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping("teachers") //Routepath for all the endpoints
public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service) { ///Teacher toegevoegd en afhankelijk gemaakt van de service
        this.service = service;  /////Service object komt binnen en wordt bewaard in het veld 'service'
    }

/*
    @GetMapping
    public ResponseEntity<Iterable<Teacher>> getTeachers() {
        return ResponseEntity.ok(repos.findAll());
    }
*/

    @PostMapping ////////ENDPOINT             ////Hieronder komt de dto binnen        /BindingResult geeft ons het object waarin we kunnen zien waar het niet goed is gegaan
    public ResponseEntity<Object> createTeacher(@Valid @RequestBody TeacherDto tdto, BindingResult br){ //'@Valid annotatie forceert de controller om de validatie in de dto uit te voeren

        if (br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage() + "\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        Long id = service.createTeacher(tdto); ////Hier wordt de service gebruikt op basis van de informatie die binnenkomt uit het dto object
        tdto.id = id;

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + id).toUriString());

        return ResponseEntity.created(uri).body(tdto);
    }


/*    @GetMapping("/before")
    public ResponseEntity<Iterable<Teacher>> getTeachersBefore(@RequestParam LocalDate date) {
        return ResponseEntity.ok(repos.findByDobBefore(date));


    }*/
}
//Voila