package com.codecool.restauratio.models.users;

import com.codecool.restauratio.models.Order;
import com.codecool.restauratio.models.Reservation;
import com.codecool.restauratio.models.Restaurant;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`user`")
@NamedQueries({
        @NamedQuery(
                name = "getAllUser",
                query = "SELECT u FROM User u"),
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String userName;
    @Column(unique = true, nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean isAdmin;
    @Column(nullable = false)
    private boolean isOwner;


    @OneToMany(mappedBy = "owner")
    private List<Restaurant> restaurants;

    @OneToMany(mappedBy = "guest")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;


    protected User() {
    }

    public User(String userName, String password, boolean isAdmin, boolean isOwner) {
        setUserName(userName);
        setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        setAdmin(isAdmin);
        setOwner(isOwner);
        reservations = new ArrayList<>();
        if (isOwner) {
            restaurants = new ArrayList<>();
        }
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

    public void setPassword(String password) {
        this.password = password;
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

    public void addReservation(Reservation res) {
        this.reservations.add(res);
    }

    public void addRestaurant(Restaurant rest) {
        if (restaurants != null) {
            this.restaurants.add(rest);
        }
    }

    @Override
    public String toString() {
        return "User = " +
                "userName: " + userName +
                ", restaurants: " + restaurants;
    }
}
