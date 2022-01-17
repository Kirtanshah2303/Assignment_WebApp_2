package com.mycompany.myapp.domain;

import java.util.Date;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "assignment_1")
public class Assignment_1 {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    @Indexed(unique = true)
    private String random_string;

    private boolean status;

    @Indexed(unique = true)
    private Date localDate;

    public Assignment_1() {
        id = new ObjectId().toString();
    }

    public Assignment_1(String username, String random_string, Date localDate) {
        id = new ObjectId().toString();
        this.username = username;
        this.status = false;
        this.random_string = random_string;
        this.localDate = localDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getRandom_string() {
        return random_string;
    }

    public void setRandom_string(String random_string) {
        this.random_string = random_string;
    }

    public Date getLocalDate() {
        return localDate;
    }

    public void setLocalDate(Date localDate) {
        this.localDate = localDate;
    }
}
