package ru.nc.ibublig.catalogsb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nc.ibublig.catalogsb.dao.AppRoleDAO;
import ru.nc.ibublig.catalogsb.dao.AppUserDAO;
import ru.nc.ibublig.catalogsb.model.AppUser;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private AppUserDAO userDAO;
    @Autowired
    private AppRoleDAO roleDAO;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationUser(
            @RequestParam String username,
            @RequestParam String password,
            Map<String, Object> model){
        AppUser userFromDB = userDAO.findUserAccount(username);
        if (userFromDB != null){
            model.put("message", "Попробуйте другой логин");
            return "registration";
        }

        userDAO.saveUser(new AppUser(username,password,true));
        AppUser appUser = userDAO.findUserAccount(username);

        roleDAO.setRole(appUser.getUserId(), appUser.ROLE_USER);

        return "redirect:/login";
    }
}
