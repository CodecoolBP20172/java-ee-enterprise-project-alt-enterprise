package com.codecool.restauratio.controller;

import com.codecool.restauratio.customException.AccessForbiddenException;
import com.codecool.restauratio.models.Order;
import com.codecool.restauratio.models.Reservation;
import com.codecool.restauratio.models.users.User;
import com.codecool.restauratio.repository.OrderRepository;
import com.codecool.restauratio.repository.ReservationRepository;
import com.codecool.restauratio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String renderUserPage(@PathVariable(value = "id") int id,
                                 Model model,
                                 HttpSession session, String view) throws AccessForbiddenException {
        if (!session.getAttribute("id").equals(id)) {
            throw new AccessForbiddenException();
        }
        User user = userRepository.findOne(id);
        List<Reservation> reservations = reservationRepository.findAllByUser(user.getUserId());
        List<Order> orders = orderRepository.findAllByUser(user.getUserId());
        model.addAttribute("user", user);
        model.addAttribute("reservations", reservations);
        model.addAttribute("orders", orders);
        model.addAttribute("loggedIn", true);
        if (view == null) {
            model.addAttribute("view", "profile");
        } else {
            model.addAttribute("view", view);
        }
        return "userpage";
    }

    @RequestMapping(value="/user/{id}/reservations", method = RequestMethod.GET)
    public String renderReservations(@PathVariable(value = "id") int id, Model model, HttpSession session) throws AccessForbiddenException {
        return renderUserPage(id, model, session, "reservations");
    }

    @RequestMapping(value="/user/{id}/orders", method = RequestMethod.GET)
    public String renderOrders(@PathVariable(value = "id") int id, Model model, HttpSession session) throws AccessForbiddenException {
        return renderUserPage(id, model, session, "orders");
    }
}
