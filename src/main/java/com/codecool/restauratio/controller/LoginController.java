package com.codecool.restauratio.controller;

import com.codecool.restauratio.customException.FailedDataVertification;
import com.codecool.restauratio.services.UserService;
import com.sun.net.httpserver.HttpServer;
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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public static String renderLogin(@RequestParam(value = "incorrect", required = false) Boolean incorrect, Model model, Boolean isLogin) {
        model.addAttribute("login", isLogin);
        if (incorrect != null) {
            model.addAttribute("incorrect", incorrect);
        } else {
            model.addAttribute("incorrect", "false");
        }
        return "login";
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public static String renderRegister(@RequestParam(value = "inuse", required = false) Boolean inuse, Model model, Boolean isRegister) {
        model.addAttribute("register", isRegister);
        if (inuse != null) {
            model.addAttribute("inuse", inuse);
        } else {
            model.addAttribute("inuse", "false");
        }
        return "login";
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public static String registerUser(@RequestParam(value = "username") String username,
                                      @RequestParam(value = "password") String password, HttpSession session) {
        UserService userService = new UserService();
        if (!userService.doesUserExist(username)) {
            int userId = userService.registerUser(username, password, false, false);
            session.setAttribute("id", userId);
            session.setAttribute("username", username);
            return "redirect:restaurants";
        } else {
            return "redirect:/register?inuse=true";
        }
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public static String loginUser(@RequestParam(value = "username") String username,
                                   @RequestParam(value = "password") String password, HttpSession session) throws FailedDataVertification {
        UserService userService = new UserService();
        if (userService.login(username, password)) {
            session.setAttribute("id", userService.getUserId(username));
            session.setAttribute("username", username);
            return "redirect:restaurants";
        } else {
            return "redirect:/login?incorrect=true";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
        public static String logoutUser(HttpSession session) {
        session.removeAttribute("username");
        session.removeAttribute("id");
        return "/";
    }
}
