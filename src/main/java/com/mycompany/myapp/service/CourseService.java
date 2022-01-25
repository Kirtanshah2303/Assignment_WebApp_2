package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Courses;
import com.mycompany.myapp.repository.CourseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public String addCourse(Courses courses) {
        courseRepository.save(courses);
        return "Course has been added";
    }

    public List<Courses> getCourses(Integer semester) {
        return courseRepository.findByCourseSemester(semester);
    }
}
