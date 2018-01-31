package com.codecool.restauratio.models.users;

import com.codecool.restauratio.models.Order;
import com.codecool.restauratio.models.Reservation;
import com.codecool.restauratio.models.Restaurant;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String userName;
    @Column(unique = true, nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private boolean isAdmin;
    @Column(unique = true, nullable = false)
    private boolean isOwner;


    @OneToMany(mappedBy = "owner")
    private List<Restaurant> restaurants;

    @OneToMany(mappedBy = "guest")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;


    public User(String userName, String password, boolean isAdmin, boolean isOwner) {
        this.userName = userName;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.isAdmin = isAdmin;
        this.isOwner = isOwner;
    }

    protected User() {
    }

    public long getUserId(){
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }

    public boolean checkPassword(String candidatePassword){
        return BCrypt.checkpw(candidatePassword, password);
    }
}
