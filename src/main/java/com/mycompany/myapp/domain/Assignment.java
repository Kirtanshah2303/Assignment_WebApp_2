package com.mycompany.myapp.domain;

import java.util.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "assignment")
public class Assignment {

    @Id
    private String id;

    @CreatedDate
    private Date assignmentAddedOn;

    @DBRef
    private Courses course;

    private String assignmentAim;

    private Date dueDate;
    private Float marks;

    Map<User, Boolean> isCompleted = new HashMap<>();

    public Assignment() {}

    public Assignment(String id, Date assignmentAddedOn, Courses course, String assignmentAim, Date dueDate, Float marks) {
        this.id = id;
        this.assignmentAddedOn = assignmentAddedOn;
        this.course = course;
        this.assignmentAim = assignmentAim;
        this.dueDate = dueDate;
        this.marks = marks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getAssignmentAddedOn() {
        return assignmentAddedOn;
    }

    public void setAssignmentAddedOn(Date assignmentAddedOn) {
        this.assignmentAddedOn = assignmentAddedOn;
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }

    public String getAssignmentAim() {
        return assignmentAim;
    }

    public void setAssignmentAim(String assignmentAim) {
        this.assignmentAim = assignmentAim;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Float getMarks() {
        return marks;
    }

    public void setMarks(Float marks) {
        this.marks = marks;
    }

    public Map<User, Boolean> getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Map<User, Boolean> isCompleted) {
        this.isCompleted = isCompleted;
    }
}
