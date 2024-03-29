package com.codecool.restauratio.controller;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class RestaurantController {

    @Autowired
    private RestaurantService restService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderRestaurants(Model model, HttpSession session) throws ConnectToDBFailed {
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("loggedin", session.getAttribute("id") != null);
        model.addAttribute("restaurants", restService.getRestaurants());
        model.addAttribute("locations", restService.getLocations());
        model.addAttribute("descriptions", restService.getDescriptions());
        return "restaurants";
    }

    @RequestMapping(value = "/restaurants/{restId}", method = RequestMethod.GET)
    public String renderRestaurant(@PathVariable("restId") int restId, Model model, HttpSession session) {
        model.addAttribute("restaurant", restService.getRestaurantId(restId));
        model.addAttribute("loggedin", session.getAttribute("id") != null);
        return "restaurant";
    }

}
