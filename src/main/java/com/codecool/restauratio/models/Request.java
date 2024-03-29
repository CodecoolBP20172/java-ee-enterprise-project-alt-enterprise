package com.codecool.restauratio.models;

import com.codecool.restauratio.models.users.User;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reservation_date")
    private Date date;
    

    public Request(Date date){
        this.date = date;
    }

    public Request(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
