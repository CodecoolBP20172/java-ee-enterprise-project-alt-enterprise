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
    private int id;
    @Column(unique = true, nullable = false)
    private String userName;
    @Column(nullable = false)
    private String FirstName;
    @Column(nullable = false)
    private String LastName;
    @Column(unique = true, nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String address;
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

    public User(String userName, String FirstName, String LastName,
                String password, String email, String address,
                boolean isAdmin, boolean isOwner) {
        this.userName = userName;
        this.FirstName = FirstName;
        this.LastName = LastName;
        setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        this.email= email;
        this.address = address;
        this.isAdmin = isAdmin;
        this.isOwner = isOwner;
        reservations = new ArrayList<>();
        restaurants = new ArrayList<>();
        orders = new ArrayList<>();
    }

    public int getUserId(){
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

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
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

    public void addOrder(Order order) {
        this.orders.add(order);
    }


    public void addRestaurant(Restaurant rest) {
            this.restaurants.add(rest);
    }

    @Override
    public String toString() {
        return "User = " +
                "userName: " + userName +
                ", restaurants: " + restaurants;
    }
}
