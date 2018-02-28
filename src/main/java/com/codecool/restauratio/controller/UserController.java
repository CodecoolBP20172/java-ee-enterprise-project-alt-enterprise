package com.codecool.restauratio.controller;

import com.codecool.restauratio.customException.AccessForbiddenException;
import com.codecool.restauratio.models.users.User;
import com.codecool.restauratio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String renderUserPage(@PathVariable(value = "id") int id,
                                 Model model,
                                 HttpSession session) throws AccessForbiddenException {
        if (!session.getAttribute("id").equals(id)) {
            throw new AccessForbiddenException();
        }
        User user = userRepository.findOne(id);
        model.addAttribute("user", user);
        model.addAttribute("loggedin", true);
        return "userpage";
    }
}
