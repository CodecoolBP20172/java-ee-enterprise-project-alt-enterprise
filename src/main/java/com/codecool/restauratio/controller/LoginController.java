package com.codecool.restauratio.controller;

import com.codecool.restauratio.customException.FailedDataVertification;
import com.codecool.restauratio.services.UserService;
import com.sun.net.httpserver.HttpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String renderLogin(@RequestParam(value = "incorrect", required = false) Boolean incorrect, Model model) {
        model.addAttribute("login", true);
        if (incorrect != null) {
            model.addAttribute("incorrect", incorrect);
        } else {
            model.addAttribute("incorrect", "false");
        }
        return "login";
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String renderRegister(@RequestParam(value = "inuse", required = false) Boolean inuse, Model model, Boolean isRegister) {
        model.addAttribute("register", true);
        if (inuse != null) {
            model.addAttribute("inuse", inuse);
        } else {
            model.addAttribute("inuse", "false");
        }
        return "login";
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public String registerUser(@RequestParam(value = "username") String username,
                                      @RequestParam(value = "password") String password, HttpSession session) {
        if (!userService.doesUserExist(username)) {
            int userId = userService.registerUser(username, password, false, false);
            session.setAttribute("id", userId);
            session.setAttribute("username", username);
            return "redirect:/";
        } else {
            return "redirect:/register?inuse=true";
        }
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String loginUser(@RequestParam(value = "username") String username,
                                   @RequestParam(value = "password") String password, HttpSession session, Model model) throws FailedDataVertification {
        if (userService.login(username, password)) {
            session.setAttribute("id", userService.getUserId(username));
            session.setAttribute("username", username);
            model.addAttribute("loggedin", true);
            return "redirect:/";
        } else {
            return "redirect:/login?incorrect=true";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
        public String logoutUser(HttpSession session, Model model) {
        session.removeAttribute("username");
        session.removeAttribute("id");
        model.addAttribute("loggedin", false);
        return "redirect:/";
    }
}
