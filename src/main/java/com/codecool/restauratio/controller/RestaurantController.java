package com.codecool.restauratio.controller;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
//        catch (ConnectToDBFailed e) {
//            HttpStatus.);
//            return "<html><body><h1>" + res.raw().getStatus() + "</h1><p>SERVICE UNAVAILABLE</p></body></html>";
//        }
        return "restaurants";
    }

}

//    public static ModelAndView renderRestaurant(Request req, Response res, String restaurantId) throws ConnectToDBFailed {
//        Map<String, Object> params = new HashMap<>();
//        int formattedRestaurantId = Integer.parseInt(restaurantId);
//        params.put("restaurant", restService.getRestaurantId(formattedRestaurantId));
//        return new ModelAndView(params, "restaurant");
//    }
//
//    public static void makeReservationAtRestaurant(Request req, Response res) throws ConnectToDBFailed {
//
//    }
//
//    public static String restaurantBrowseByLocation(Request request, Response response) {
//        Map<String, String> data = JsonHandler.parseJson(request);
//        String targetLocation = data.get("location");
//        return restService.restaurantLocationBrowser(targetLocation);
//    }
