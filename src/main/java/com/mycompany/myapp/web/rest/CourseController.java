package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Courses;
import com.mycompany.myapp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api2")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping("/addCourse")
    public ResponseEntity<Object> addCourse(@RequestBody Courses courses) {
        return new ResponseEntity<>(courseService.addCourse(courses), HttpStatus.OK);
    }

    @GetMapping("/getAllCourses/{id}")
    public ResponseEntity<Object> getAllCourses(@PathVariable("id") Integer semester) {
        return new ResponseEntity<>(courseService.getCourses(semester), HttpStatus.OK);
    }
}
