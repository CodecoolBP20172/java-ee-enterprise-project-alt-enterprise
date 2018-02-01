package com.codecool.restauratio.models;

import com.codecool.restauratio.models.users.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public abstract class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reservation_date")
    private Date date;

    @ManyToOne
    private User user;

    @ManyToOne
    private Restaurant restaurant;


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

    public Date getCreationDate() {
        return date;
    }

    public void setCreationDate(Date creationDate) {
        this.date = creationDate;
    }
}
