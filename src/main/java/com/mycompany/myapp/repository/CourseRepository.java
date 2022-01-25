package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Courses;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends MongoRepository<Courses, String> {
    List<Courses> findByCourseSemester(Integer courseSemester);
}
