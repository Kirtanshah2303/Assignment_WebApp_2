package com.mycompany.myapp.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
public class Courses {

    @Id
    private String id;

    @DBRef
    private List<User> users = new ArrayList<>();

    private String courseName;
    private Integer courseSemester;

    @CreatedDate
    private Date courseAddedOn;

    private User courseInstructor;

    public Courses() {}

    public Courses(String id, List<User> users, String courseName, Integer courseSemester, Date courseAddedOn, User courseInstructor) {
        this.id = id;
        this.users = users;
        this.courseName = courseName;
        this.courseSemester = courseSemester;
        this.courseAddedOn = courseAddedOn;
        this.courseInstructor = courseInstructor;
    }

    @Override
    public String toString() {
        return (
            "Courses{" +
            "id='" +
            id +
            '\'' +
            ", users=" +
            users +
            ", courseName='" +
            courseName +
            '\'' +
            ", courseSemester=" +
            courseSemester +
            ", courseAddedOn=" +
            courseAddedOn +
            ", courseInstructor=" +
            courseInstructor +
            '}'
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseSemester() {
        return courseSemester;
    }

    public void setCourseSemester(Integer courseSemester) {
        this.courseSemester = courseSemester;
    }

    public Date getCourseAddedOn() {
        return courseAddedOn;
    }

    public void setCourseAddedOn(Date courseAddedOn) {
        this.courseAddedOn = courseAddedOn;
    }

    public User getCourseInstructor() {
        return courseInstructor;
    }

    public void setCourseInstructor(User courseInstructor) {
        this.courseInstructor = courseInstructor;
    }
}
