package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Assignment;
import com.mycompany.myapp.domain.Courses;
import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends MongoRepository<Assignment, String> {
    List<Assignment> findAssignmentsByCourse(Courses course);
}
