package com.codecool.restauratio.repository;

import com.codecool.restauratio.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
