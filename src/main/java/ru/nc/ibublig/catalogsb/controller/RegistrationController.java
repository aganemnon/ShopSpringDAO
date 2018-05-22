package ru.nc.ibublig.catalogsb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.nc.ibublig.catalogsb.dao.UserDAOImpl;
import ru.nc.ibublig.catalogsb.model.Role;
import ru.nc.ibublig.catalogsb.model.User;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserDAOImpl userDAO;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationUser(User user, Map<String, Object> model){
        User userFromDB = userDAO.findByUsername(user.getUsername());
        if (userFromDB != null){
            model.put("message", "Попробуйте другой логин");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        userDAO.saveOrUpdate(user);

        return "redirect:/login";
    }
}
